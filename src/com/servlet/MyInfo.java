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

import com.model.Person;
import com.model.RentServer;
import com.service.PersonService;
import com.service.ServerService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class MyInfo
 */
@WebServlet("/MyInfo")
public class MyInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfo() {
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
		
		Person pr=new Person();
		PersonService pe=new PersonService();
		
		HttpSession session=request.getSession(false);
		if(session==null){
			jsonobj.put("regStatus", false);
		}else{
			String name=(String)session.getAttribute("name");
			pr.setName(name);
			jsonobj.put("regStatus", true);
				
			pr.setName(name);
			
			List<Person> list=pe.infoQuery(pr);
			if(list!=null){
				Iterator<Person>it=list.iterator();
				while(it.hasNext()){
					pr=it.next();
					jsonobj.put("name", pr.getName());
					jsonobj.put("password", pr.getPassword());
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
