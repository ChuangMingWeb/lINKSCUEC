package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.conn.Conn;
import com.model.Person;

public class PersonService {	private Connection conn;
	private PreparedStatement pstmt;

	public PersonService(){
		conn=new Conn().getCon();
	}

	public List<Person> infoQuery(Person pr) {
		// TODO Auto-generated method stub
		List<Person> list=new ArrayList<>();
		try{
			pstmt=conn.prepareStatement("select * from person where name=?");
			pstmt.setString(1,pr.getName());
			
			ResultSet rs=pstmt.executeQuery();

			if(rs.next()){
				pr.setId(rs.getInt("id"));
				pr.setName(rs.getString("name"));
				pr.setPassword(rs.getString("password"));
				pr.setCreditStar(rs.getInt("creditStar"));
				pr.setCredit(rs.getString("credit"));
				
				list.add(pr);
				return list;
		  }				
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}	
		return list;
	}
}
