package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.conn.Conn;
import com.model.Status;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StatusService {
	private Connection conn;
	private PreparedStatement pstmt;
	
	public StatusService(){
		conn=new Conn().getCon();
	}
	
	public  boolean isRent(Status ss) {
		// TODO Auto-generated method stub
		try {
			pstmt=conn.prepareStatement("select * from status where name=?");
			pstmt.setString(1,ss.getName());
			
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()){
				boolean hireStatus=rs.getBoolean("hireStatus");
				ss.setHireStatus(hireStatus);
				
				if(hireStatus){
					return true;
				}else{
					return false;
				}			
		  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
