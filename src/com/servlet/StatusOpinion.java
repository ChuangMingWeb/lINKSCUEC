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
import com.service.ServerService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class StatusOpinion
 */
@WebServlet("/StatusOpinion")
public class StatusOpinion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatusOpinion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//出租及租用状态判断
		PrintWriter out=response.getWriter();
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		
		JSONArray jsonarray=new JSONArray();
		JSONObject jsonobj=new JSONObject();
		
		RentServer rr=new RentServer();
		ServerService ss=new ServerService();
		RentClient rt=new RentClient();
		ClientService cs=new ClientService();
		
		HttpSession session=request.getSession(false);
		if(session==null){
			jsonobj.put("regStatus", false);
		}else{
			String name=(String)session.getAttribute("name");
			jsonobj.put("regStatus", true);
			
			
			rr.setName(name);
			List<RentServer> list=ss.statusQuery(rr);
			if(list!=null){			
				Iterator<RentServer>it=list.iterator();
				while(it.hasNext()){
					rr=it.next();
					jsonobj.put("chuzuStatus", rr.getChuzuStatus());
					jsonobj.put("zuchuStatus", rr.getZuchuStatus());
				}
			
				rt.setName(name);
				if(cs.isRent(rt)){
					jsonobj.put("hireStatus", true);
				}else{
					jsonobj.put("hireStatus", false);
				}
			}else{
				jsonobj.put("222",false);
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
