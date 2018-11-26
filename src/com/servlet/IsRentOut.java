package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.RentServer;
import com.service.ServerService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class IsRentOut
 */
@WebServlet("/isRentOut")
public class IsRentOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IsRentOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//是否出租校园网
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
			//System.out.println(name);
			
			String outSs=request.getParameter("outStatus");
			//System.out.println(outSs);
			boolean outStatus=Boolean.parseBoolean(outSs);
			//System.out.println(outStatus);	
		
			RentServer rr=new RentServer();
			rr.setName(name);
			
			//先查询该账号是否被租用
			ServerService ss=new ServerService();
			List<RentServer> list=ss.statusQuery(rr);
			if(list!=null){			
				Iterator<RentServer>it=list.iterator();
				if(it.hasNext()){
					rr=it.next();
					if(rr.getZuchuStatus()){
						jsonobj.put("zuchuStatus", true);
						return;
					}else{
						if(new ServerService().updateIsRent(rr,outStatus)){
							//System.out.println("666");
							//System.out.println(outStatus);
							jsonobj.put("chuzuStatus",outStatus);
						}else{
							jsonobj.put("request",false);
						}
				
						jsonobj.put("zuchuStatus",false);
					}
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
