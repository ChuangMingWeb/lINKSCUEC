package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Person;
import com.model.RentClient;
import com.model.Status;
import com.service.ClientService;
import com.service.LoginService;
import com.service.ServerService;
import com.service.StatusService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
			
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		
		HttpSession session=request.getSession();
		session.setAttribute("name",name);
		
		Person pr=new Person();
		pr.setName(name);
		pr.setPassword(password);
		
		RentClient rc=new RentClient();
		rc.setName(name);
		ClientService cs=new ClientService();
		
		if(new LoginService().setToSql(pr)){
			jsonobj.put("regStatus",true);
		}else{
			jsonobj.put("regStatus",false);
		}
		
		if(cs.isRent(rc)){
			jsonobj.put("hireStatus",true);
		}else{
			jsonobj.put("hireStatus",false);
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
