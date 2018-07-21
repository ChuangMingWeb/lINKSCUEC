package com.service;


import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import com.conn.Conn;
import com.model.RentClient;
import com.model.RentServer;

public class ClientService {
	private Connection conn;
	private PreparedStatement pstmt;
	
	public ClientService(){
		conn=new Conn().getCon();
	}
	
	private java.sql.Timestamp sTod(String dateString) 
			  throws java.text.ParseException { 
			   DateFormat dateFormat; 
			  dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH);//设定格式  
			  dateFormat.setLenient(false); 
			  java.util.Date timeDate = dateFormat.parse(dateString);//util类型 
			  java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());//Timestamp类型,timeDate.getTime()返回一个long型 
			  return dateTime; 
			} 
	
	public List<RentServer> searchTime(String st,String ot,String name){
		List<RentServer>list=new ArrayList<RentServer>();
		try{

			//pstmt=conn.prepareStatement("select * from rent_sever where id=?");
			pstmt=conn.prepareStatement("select * from rent_server where startTime=? and overTime=? ");
			//System.out.println(sTod(st));
				pstmt.setTimestamp(1,sTod(st));			
				pstmt.setTimestamp(2, sTod(ot));
				/*pstmt.setString(1,st);			
				pstmt.setString(2,ot);*/

			//pstmt.setLong(1,1);
			
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){ 
				do{
					String nameServer=rs.getNString(name);
					int zuchuStatus=rs.getInt("zuchuStatus");
					int chuzuStatus=rs.getInt("chuzuStatus");
				    //System.out.println("进入了这里");
					if(zuchuStatus==0 && chuzuStatus==1 && !(name.equals(name))){
						RentServer rc =new RentServer();
						rc.setId(rs.getInt("id"));
						rc.setStartTime(rs.getString("startTime"));
						rc.setOverTime(rs.getString("overTime"));
						rc.setAllTime(rs.getString("allTime"));
						rc.setPrice(rs.getFloat("price"));
						rc.setCreditStar(rs.getInt("creditStar"));
						
						list.add(rc);		
					
					}					
				}while(rs.next());
				System.out.println(list.size());
				System.out.println("进入==");
				return list;
			}else{
				pstmt=conn.prepareStatement("select * from rent_server where startTime<=? and overTime>?");
				pstmt.setTimestamp(1,sTod(st));
				pstmt.setTimestamp(2, sTod(st));
				//pstmt.setString(1,ot);
				//pstmt.setString(2, ot);
				
				ResultSet rs1=pstmt.executeQuery();
				if(rs1.next()){
					do{
						boolean zuchuStatus=rs1.getBoolean("zuchuStatus");
						boolean chuzuStatus=rs1.getBoolean("chuzuStatus");
					
						if(zuchuStatus==false && chuzuStatus==true){
							RentServer rc =new RentServer();
							rc.setId(rs1.getInt("id"));
							rc.setStartTime(rs1.getString("startTime"));
							rc.setOverTime(rs1.getString("overTime"));
							rc.setAllTime(rs1.getString("allTime"));
							rc.setPrice(rs1.getFloat("price"));
							rc.setCreditStar(rs1.getInt("creditStar"));
								
							list.add(rc);
						}
							
					}while(rs1.next());
				}
				
				pstmt=conn.prepareStatement("select * from rent_server where startTime>=? and overTime<=?");
				pstmt.setTimestamp(1,sTod(st));
				pstmt.setTimestamp(2, sTod(ot));
				//pstmt.setString(1,ot);
				//pstmt.setString(2, ot);
				
				ResultSet rs3=pstmt.executeQuery();
				if(rs3.next()){
					do{
						boolean zuchuStatus=rs3.getBoolean("zuchuStatus");
						boolean chuzuStatus=rs3.getBoolean("chuzuStatus");
					
						if(zuchuStatus==false && chuzuStatus==true){
							RentServer rc =new RentServer();
							rc.setId(rs3.getInt("id"));
							rc.setStartTime(rs3.getString("startTime"));
							rc.setOverTime(rs3.getString("overTime"));
							rc.setAllTime(rs3.getString("allTime"));
							rc.setPrice(rs3.getFloat("price"));
							rc.setCreditStar(rs3.getInt("creditStar"));
								
							list.add(rc);
						}
							
					}while(rs3.next());
				}
						
				
				pstmt=conn.prepareStatement("select * from rent_server where startTime<=? and overTime>?");
				pstmt.setTimestamp(1,sTod(ot));
				pstmt.setTimestamp(2, sTod(ot));
				
				ResultSet rs2=pstmt.executeQuery();
				if(rs2.next()){
					do{
						boolean zuchuStatus=rs2.getBoolean("zuchuStatus");
						boolean chuzuStatus=rs2.getBoolean("chuzuStatus");
					
						if(zuchuStatus==false && chuzuStatus==true){
							RentServer rc =new RentServer();
							rc.setId(rs2.getInt("id"));
							rc.setStartTime(rs2.getString("startTime"));
							rc.setOverTime(rs2.getString("overTime"));
							rc.setAllTime(rs2.getString("allTime"));
							rc.setPrice(rs2.getFloat("price"));
							rc.setCreditStar(rs2.getInt("creditStar"));
								
							list.add(rc);
						}
							
					}while(rs2.next());
				}
				
				//System.out.println(list.size());
				
				for(int i=0;i<list.size()-1;i++){
					
					for(int j=list.size()-1;j>i;j--){
						
						if(list.get(i).equals(list.get(j))){
							//System.out.println("remo");
							list.remove(j);
							
						}
					}
				}
				//System.out.println(list.size());
				//System.out.println("进入>=");
					return list;
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
 }

	public boolean isRent(RentClient rc) {
		// TODO Auto-generated method stub
		
		boolean hireStatus = false;
		try {
			pstmt=conn.prepareStatement("select * from rent_client where name=?");
			pstmt.setString(1,rc.getName());
			
			ResultSet rs=pstmt.executeQuery();

			if(rs.next()){
				hireStatus=rs.getBoolean("hireStatus");
				rc.setHireStatus(hireStatus);
				
				if(hireStatus==true){
					
					return true;
				}else{
					return false;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return hireStatus;
	}

	public List<RentClient> hireQuery(RentClient rt) {
		// TODO Auto-generated method stub
		List<RentClient>list=new LinkedList<RentClient>();
		try {
			pstmt=conn.prepareStatement("select * from rent_client where name=?");
			pstmt.setString(1,rt.getName());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				boolean hireStatus=rs.getBoolean("hireStatus");
				//System.out.println(hireStatus);
				if(hireStatus==true){
					rt.setHireStatus(hireStatus);
					rt.setStartTime(rs.getString("startTime"));
					rt.setOverTime(rs.getString("overTime"));
					rt.setNameRent(rs.getString("nameRent"));
					rt.setPasswordRent(rs.getString("passwordRent"));
				
					list.add(rt);
					return list;
				}				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<RentClient> nameRentQuery(RentClient rt) {
		// TODO Auto-generated method stub
		List<RentClient>list=new ArrayList<RentClient>();
		try {
			pstmt=conn.prepareStatement("select * from rent_client where nameRent=?");
			pstmt.setString(1,rt.getNameRent());
			ResultSet rs=pstmt.executeQuery();
			System.out.println(rt.getNameRent());
			while(rs.next()){
				System.out.println(rt.getNameRent());
				boolean hireStatus=rs.getBoolean("hireStatus");
				if(hireStatus==true){
					rt.setHireStatus(hireStatus);
					rt.setStartTime(rs.getString("startTime"));
					rt.setOverTime(rs.getString("overTime"));
					rt.setNameRent(rs.getString("nameRent"));
					rt.setPasswordRent(rs.getString("passwordRent"));
				
					list.add(rt);
					return list;
				}				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return list;		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conn == null) ? 0 : conn.hashCode());
		result = prime * result + ((pstmt == null) ? 0 : pstmt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientService other = (ClientService) obj;
		if (conn == null) {
			if (other.conn != null)
				return false;
		} else if (!conn.equals(other.conn))
			return false;
		if (pstmt == null) {
			if (other.pstmt != null)
				return false;
		} else if (!pstmt.equals(other.pstmt))
			return false;
		return true;
	}

}

