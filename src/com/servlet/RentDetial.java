package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.RentClient;
import com.model.RentServer;
import com.service.ClientService;
import com.service.ServerService;
import com.service.isRentService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class RentDetial
 */
@WebServlet("/RentDetial")
public class RentDetial extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RentDetial() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out=response.getWriter();
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		
		JSONArray jsonarray=new JSONArray();
		JSONObject jsonobj=new JSONObject();

		HttpSession session=request.getSession(false);
		if(session==null){
			jsonobj.put("regStatus", false);
		}else{
			jsonobj.put("regStatus", true);
			String name=(String)session.getAttribute("name");
					
			String stAll = null;
			String otAll = null;
			String[] sTime=new String[100];
			for(int i=0;i<100;i++){
				sTime[i]="null";
			}
			String[] oTime=new String[100];
			String[] ATime=new String[100];
			boolean[] zuchu=new boolean[100];
			String st = null;
			String ot = null;
			
			RentServer rr=new RentServer();
			rr.setName(name);
			
			ServerService ss=new ServerService();
			List<RentServer> list=ss.query(rr);

			boolean zuchuStatus = false;
			if(list!=null){				
				Iterator<RentServer> it=list.iterator();		
				if(it.hasNext()){
					rr=it.next();
					System.out.println("server");
					zuchuStatus=rr.getZuchuStatus();
					System.out.println(zuchuStatus);
					stAll=rr.getStartTime();
					otAll=rr.getOverTime();
				}
				
				if(zuchuStatus==false){
					System.out.println("整段没租");
					sTime[0]=stAll;
					oTime[0]=otAll;
					long t1=str2d(stAll).getTime();
					long t2=str2d(otAll).getTime();
					ATime[0]=countTime(t1,t2);
					zuchu[0]=false;
				}else{
					RentClient rt = new RentClient();
					rt.setNameRent(name);
					ClientService cs = new  ClientService();
					
					List<RentClient> rtList=cs.nameRentQuery(rt);
						if(rtList.size()!=0){
							Iterator<RentClient> rtIt=rtList.iterator();
							if(rtIt.hasNext()){
								System.out.println("client");
								rt=rtIt.next();
								st=rt.getStartTime();
								ot=rt.getOverTime();
							}
					
							long t1=str2d(stAll).getTime();
							long t2=str2d(otAll).getTime();
							long t3=str2d(st).getTime();
							long t4=str2d(ot).getTime();
							
							if(t3==t1 && t4==t2){
								System.out.println("整段租");
								sTime[0]=stAll;
								oTime[0]=otAll;
								ATime[0]=countTime(1,2);
								zuchu[0]=true;
					
							}else if(t3==t1 && t4<t2){
								System.out.println("租前半段");
								sTime[0]=st;
								oTime[0]=ot;
								ATime[0]=this.countTime(t3, t4);
								zuchu[0]=true;
					
								sTime[1]=ot;
								oTime[1]=otAll;
								ATime[1]=countTime(t4, t2);
								zuchu[1]=false;	
								
							}else if(t3>t1 && t4==t2){
								System.out.println("租后半段");
								sTime[0]=stAll;
								oTime[0]=st;
								ATime[0]=countTime(t1, t3);
								zuchu[0]=false;
					
								sTime[1]=st;
								oTime[1]=otAll;
								ATime[1]=this.countTime(t3, t2);
								zuchu[1]=true;	
								
							}else if(t3>t1 && t4<t2){
								System.out.println("租中间段");
								sTime[0]=stAll;
								oTime[0]=st;
								ATime[0]=this.countTime(t1, t3);
								zuchu[0]=false;
					
								sTime[1]=st;
								oTime[1]=ot;
								ATime[1]=this.countTime(t3, t4);
								zuchu[1]=true;		
					
								sTime[3]=ot;
								oTime[3]=otAll;
								ATime[3]=this.countTime(t4, t2);
								zuchu[3]=false;		
							}	
						}else{
								jsonobj.put("client",  "kong");
						}
					}
			}else{
				jsonobj.put("request",false);
			}
			
			for(int i=0;i<sTime.length&&!sTime[i].equals("null");i++){
				jsonobj.put("startTime", sTime[i]);
				jsonobj.put("overTime",  oTime[i]);
				jsonobj.put("allTime",  ATime[i]);
				jsonobj.put("zuchuStatus",  zuchu[i]);
	
				jsonarray.add(jsonobj);
			}
	}
		out.print(jsonarray);		
		}

	private String countTime(long t1,long t2) {
		// TODO Auto-generated method stub
		long weiDay=Math.abs((t1-t2)/(1000*60*60*24));//相差天
		long weiHour=Math.abs((t1-t2)/(1000*60*60))-weiDay*24;//相差小时
				
		String subTime=weiDay+"天"+weiHour+"时";
		return subTime;
	}
	
	private Date str2d(String t){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = null;
		try {
			d = sdf.parse(t);//格式化为可直接计算的日期对象

		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
		return d;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
