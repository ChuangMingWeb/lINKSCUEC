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
import com.service.isRentService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class isRent
 */
@WebServlet("/isRent")
public class IsRent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IsRent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//确定租用
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
			String name=(String) session.getAttribute("name");
			//System.out.println(name);
			
			int id=Integer.parseUnsignedInt(request.getParameter("id"));
			String startTime=request.getParameter("startTime");
			String overTime=request.getParameter("overTime");
			String allTime=request.getParameter("allTime");
			float price=Float.parseFloat(request.getParameter("price"));
			RentClient rt=new RentClient();
			rt.setName(name);
			RentServer rr=new RentServer();
			rr.setId(id);
			/*rr.setOverTime(overTime);
			rr.setAllTime(allTime);
			rr.setPrice(price);*/
			
			List<RentClient> list=new isRentService().query(rr,rt);
			if(list!=null){
				Iterator<RentClient>it=list.iterator();
				if(it.hasNext()){
					rt=it.next();
					jsonobj.put("hireStatus",true);
					jsonobj.put("nameRent", rt.getNameRent());
					jsonobj.put("passwordRent", rt.getPasswordRent());
						
				}else{
					jsonobj.put("hireStatus",false);
				}							

			}else{
				jsonobj.put("hireStatus",false);
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
