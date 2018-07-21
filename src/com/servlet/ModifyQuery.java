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
 * Servlet implementation class ModifyQuery
 */
@WebServlet("/ModifyQuery")
public class ModifyQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyQuery() {
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
		
		RentServer rr=new RentServer();
		ServerService ss=new ServerService();
		
		HttpSession session=request.getSession(false);
		if(session==null){
			jsonobj.put("regStatus", false);
		}else{
			String name=(String)session.getAttribute("name");
			jsonobj.put("regStatus", true);
				
			rr.setName(name);
			
			List<RentServer> list=ss.infoQuery(rr);
			if(list!=null){
				Iterator<RentServer>it=list.iterator();
				while(it.hasNext()){
					rr=it.next();
					jsonobj.put("chuzuStatus", rr.getChuzuStatus());
					jsonobj.put("zuchuStatus", rr.getZuchuStatus());
					jsonobj.put("name", rr.getName());
					jsonobj.put("password", rr.getPassword());
					jsonobj.put("startTime", rr.getStartTime());
					jsonobj.put("overTime", rr.getOverTime());
					jsonobj.put("allTime", rr.getAllTime());
					jsonobj.put("price", rr.getPrice());				
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
