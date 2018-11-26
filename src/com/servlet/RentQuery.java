package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.service.ClientService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class RentQuery
 */
@WebServlet("/RentQuery")
public class RentQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RentQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//是否借用
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out=response.getWriter();
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		
		JSONArray jsonarray=new JSONArray();
		JSONObject jsonobj=new JSONObject();
		
		HttpSession session=request.getSession(false);//false表示得到的会话已有
		if(session==null){
			//如果没有获得已有会话，则返回前端未登录状态
			jsonobj.put("regStatus",false);
		}else{
			jsonobj.put("regStatus",true);
			
			String name=(String)session.getAttribute("name");
		
			RentClient rt=new RentClient();
			rt.setName(name);
			ClientService cs=new ClientService();
			
			List<RentClient> list=cs.hireQuery(rt);
			if(list!=null){			
				Iterator<RentClient>it=list.iterator();
				while(it.hasNext()){
					rt=it.next();
					jsonobj.put("startTime", rt.getStartTime());
					jsonobj.put("overTime", rt.getOverTime());
					System.out.println(rt.getStartTime());
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date nowTime=new Date();
					Date st = null;
					Date ot = null;
					try {
						st = sdf.parse(rt.getStartTime());
						ot = sdf.parse(rt.getOverTime());//格式化为可直接计算的日期对象
						//	nt=sdf.parse(nowTime)
					} catch (ParseException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				
					long time1=st.getTime();
					long time2=ot.getTime();
					long time3=nowTime.getTime();
				
					long weiDay=Math.abs((time2-time3)/(1000*60*60*24));//相差天
					long weiHour=Math.abs((time2-time3)/(1000*60*60))-weiDay*24;//相差小时
						
					String weiRent=weiDay+"天"+weiHour+"时";
					jsonobj.put("weiRent", weiRent);
				
					jsonobj.put("nameRent", rt.getNameRent());
					jsonobj.put("passwordRent", rt.getPasswordRent());
				
					/*jsonarray.add(jsonobj);
					out.print(jsonobj);*/
			}
			
		}else{
			jsonobj.put("request",false);
		}			
	}
		jsonarray.add(jsonobj);
		out.print(jsonobj);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
