package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class ModifyOut
 */
@WebServlet("/ModifyOut")
public class ModifyOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//修改出租信息
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
			String password=request.getParameter("password");
			String startTime=request.getParameter("startTime");
			String overTime=request.getParameter("overTime");
			String allTime=request.getParameter("allTime");
			float price=Float.parseFloat(request.getParameter("price"));
			
			RentServer rr=new RentServer();
			rr.setName(name);
			rr.setPassword(password);
			rr.setStartTime(startTime);
			rr.setOverTime(overTime);
			rr.setAllTime(allTime);
			rr.setPrice(price);
			
			if(new ServerService().updateInfo(rr)){
				jsonobj.put("chuzuStatus",true);
			}else{
				jsonobj.put("chuzuStatus",false);
			}
		}
		jsonarray.add(jsonobj);
		out.print(jsonarray);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
