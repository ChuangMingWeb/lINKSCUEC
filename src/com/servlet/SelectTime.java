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

import com.model.RentClient;
import com.model.RentServer;
import com.service.ClientService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SelectTime
 */
@WebServlet("/SelectTime")
public class SelectTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectTime() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//选择租用时间段
		// TODO Auto-generated method stub
		//PrintWriter out=response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out=response.getWriter();
		response.setContentType("text/json;charset=utf-8");
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
			String startTime=request.getParameter("startTime");
			String overTime=request.getParameter("overTime");
			
			List<RentServer> list1=new ClientService().searchTime(startTime,overTime,name);
			if(list1!=null){
				Iterator<RentServer>  it=list1.iterator();
				while(it.hasNext()){
					
					RentServer rr =it.next();
					jsonobj.put("id", rr.getId());
					//System.out.println(rr.getId());
					jsonobj.put("startTime", rr.getStartTime());
					jsonobj.put("overTime", rr.getOverTime());
					jsonobj.put("allTime", rr.getAllTime());
					jsonobj.put("price", rr.getPrice());
					jsonobj.put("creditStar", rr.getCreditStar());
					
					jsonarray.add(jsonobj);
				}
			}else{
				jsonobj.put("id", null);
			}
			
		}

		
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
