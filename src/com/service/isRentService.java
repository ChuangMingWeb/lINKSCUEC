package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.conn.Conn;
import com.model.Person;
import com.model.RentClient;
import com.model.RentServer;

public class isRentService {

	private Connection conn;
	private PreparedStatement pstmt;
	
	public isRentService(){
		conn=new Conn().getCon();
	}
	
	public List<RentClient> query(RentServer rr,RentClient rt) {
		// TODO Auto-generated method stub
		List<RentClient> list=new ArrayList<>();
		try{
			pstmt=conn.prepareStatement("select * from rent_server where id=?");
			pstmt.setInt(1,rr.getId());
			//System.out.println(rr.getId());
			
			ResultSet rsrr =pstmt.executeQuery();
			if(rsrr.next()){
				
				String nameRent=rsrr.getString("name");
				//System.out.println(nameRent);
	    		String passwordRent=rsrr.getString("password");
	    		//String startTime=rsrr.getString("startTime");
	    		//String overTime=rsrr.getString("overTime");
	    		//String allTime=rsrr.getString("allTime");
	    		float price=rsrr.getFloat("price");
	    		
				pstmt=conn.prepareStatement("insert into rent_client (name,nameRent,passwordRent,startTime,overTime,price,hireStatus) values(?,?,?,?,?,?,?)");
			    pstmt.setString(1,rt.getName());
			    pstmt.setString(2,nameRent);
			    pstmt.setString(3,passwordRent);
			    pstmt.setString(4,rt.getStartTime());
			    pstmt.setString(5,rt.getOverTime());
			    pstmt.setFloat(6,price);
			    pstmt.setBoolean(7,true);
			    
			    pstmt.executeUpdate();
				
			  //更新rent_server数据库
		    	pstmt=conn.prepareStatement("update rent_server set zuchuStatus=true where id=?");
		    	pstmt.setLong(1,rr.getId());
		    	
		    	pstmt.executeUpdate();
		    	
		    	rt.setNameRent(nameRent);
		    	rt.setPasswordRent(passwordRent);
		    	rt.setHireStatus(true);
		    	list.add(rt);
		    	//return list;
			}
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return list;	
	}
}
