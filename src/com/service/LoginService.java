package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.conn.Conn;
import com.model.Person;

public class LoginService {
	private Connection conn;
	private PreparedStatement pstmt;
	
	public LoginService(){
		conn=new Conn().getCon();
	}
	
	public boolean setToSql(Person pr){
		try{//先判断该用户有没有注册过
			pstmt=conn.prepareStatement("select * from person where name=?");
			pstmt.setString(1,pr.getName());
			
			ResultSet rs=pstmt.executeQuery();
			
			//如果注册过，判断密码相不相等，不等则更新记录
			if(rs.next()){
				String password=pr.getPassword();
					if(!(password.equalsIgnoreCase(rs.getString("password")))){
						pstmt=conn.prepareStatement("update person set password=? where name=?");
						pstmt.setString(1,password);
						pstmt.setString(2,pr.getName());
						
						pstmt.executeUpdate();
					}
		  }else{//没有注册过则插入
			  pstmt=conn.prepareStatement("insert into person (name,password) values(?,?)");
			  pstmt.setString(1,pr.getName());
			  pstmt.setString(2, pr.getPassword());
			
		   	  pstmt.executeUpdate();
		  }				
				return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}	
	}
}
