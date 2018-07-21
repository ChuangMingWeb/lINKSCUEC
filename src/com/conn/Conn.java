package com.conn;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {
	public Connection getCon(){
		try{
			//1.��������
			Class.forName("com.mysql.jdbc.Driver");
			//2.���ݿ�url���������ַ���utf8
			String url="jdbc:mysql://localhost/link_list?useUnicode=true&characterEncoding=utf-8&useSSL=false";
			//3.�������ݿ��¼
			String user="root";
			String password="";
			//4.�������ݿ�
			Connection conn=DriverManager.getConnection(url,user,password);
			//System.out.println("666");
		//	System.out.println(conn.getMetaData().getURL());
			return conn;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
