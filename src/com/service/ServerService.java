package com.service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.conn.Conn;
import com.model.RentServer;
import com.model.RentServer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class  ServerService {
//��֤�����Ƿ�Ϸ�
	private Connection conn;
	private PreparedStatement pstmt;
	
	public ServerService(){
		conn=new Conn().getCon();
	}
	
	public boolean vali(RentServer rr){
		try{
			pstmt=conn.prepareStatement("insert into rent_server (name,password,startTime,overTime,allTime,price,chuzuStatus,zuchuStatus) values(?,?,?,?,?,?,?,?)");
			pstmt.setString(1,rr.getName());
			pstmt.setString(2, rr.getPassword());
			pstmt.setString(3, rr.getStartTime());
			pstmt.setString(4, rr.getOverTime());
			pstmt.setString(5, rr.getAllTime());
			pstmt.setFloat(6, rr.getPrice());
			pstmt.setBoolean(7, true);
			pstmt.setBoolean(8, false);
			
			pstmt.executeUpdate();
			
				return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}	
	}
	
	public List<RentServer> query(RentServer rr){
		List<RentServer> list=new ArrayList<>();
		try{
			pstmt=conn.prepareStatement("select * from rent_server where name=?");
			pstmt.setString(1,rr.getName());
	
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				//RentServer rc =new RentServer();
				rr.setId(rs.getInt("id"));
				rr.setStartTime(rs.getString("startTime"));
				rr.setOverTime(rs.getString("overTime"));
				rr.setAllTime(rs.getString("allTime"));
				rr.setPrice(rs.getInt("price"));
				rr.setCreditStar(rs.getInt("creditStar"));
				rr.setZuchuStatus(rs.getBoolean("zuchuStatus"));		
				rr.setChuzuStatus(rs.getBoolean("chuzuStatus"));	
				
				list.add(rr);
			}
			return list;
			//return null;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
	}
 }

	public List<RentServer> statusQuery(RentServer rr) {
		// TODO Auto-generated method stub
		List<RentServer> list=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement("select * from rent_server where name=?");
			pstmt.setString(1,rr.getName());
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){		
				rr.setId(rs.getInt("id"));
				rr.setChuzuStatus(rs.getBoolean("chuzuStatus"));
				rr.setZuchuStatus(rs.getBoolean("zuchuStatus"));
				
				list.add(rr);
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	public List<RentServer> infoQuery(RentServer rr) {
		// TODO Auto-generated method stub
		List<RentServer> list=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement("select * from rent_server where name=?");
			pstmt.setString(1,rr.getName());
			System.out.println("1");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){		
				rr.setId(rs.getInt("id"));
				rr.setChuzuStatus(rs.getBoolean("chuzuStatus"));
				rr.setZuchuStatus(rs.getBoolean("zuchuStatus"));
				rr.setName(rs.getString("name"));
				rr.setPassword(rs.getString("password"));
				rr.setStartTime(rs.getString("StartTime"));
				rr.setOverTime(rs.getString("OverTime"));
				rr.setAllTime(rs.getString("allTime"));
				rr.setPrice(Float.parseFloat(rs.getString("price")));
				
				list.add(rr);
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public boolean updateInfo(RentServer rr) {
		// TODO Auto-generated method stub
		//String name=rr.getName();
		System.out.println("1");
		try{
			pstmt=conn.prepareStatement("update rent_server set password=?,startTime=?,overTime=?,allTime=?,price=?,chuzuStatus=?,zuchuStatus=? where name=?");
			pstmt.setString(1, rr.getPassword());
			pstmt.setString(2, rr.getStartTime());
			pstmt.setString(3, rr.getOverTime());
			pstmt.setString(4, rr.getAllTime());
			pstmt.setFloat(5, rr.getPrice());
			pstmt.setBoolean(6, true);
			pstmt.setBoolean(7, false);
			pstmt.setString(8, rr.getName());
			
			pstmt.executeUpdate();
			
				
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}	
		return true;
	}

	public boolean updateIsRent(RentServer rr, boolean outStatus) {
		// TODO Auto-generated method stub
		//System.out.println("1");
		try{
			pstmt=conn.prepareStatement("update rent_server set chuzuStatus=? where name=?");
			pstmt.setBoolean(1,outStatus);
			pstmt.setString(2, rr.getName());
			
			pstmt.executeUpdate();
			//System.out.println("2");
				return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}	
	}
}
