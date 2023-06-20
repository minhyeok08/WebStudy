package com.sist.dao;
import java.util.*;
import java.sql.*;
public class RentMainDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static RentMainDAO dao;
	public RentMainDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	public static RentMainDAO newInstance()
	{
		if(dao==null)
			dao=new RentMainDAO();
		return dao;
	}
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
	}
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	// Category 추가
	public void RentMainInsert(RentMainVO vo)
	{
		try
		{
			getConnection();
			String sql="INSERT INTO project_rent_main VALUES("
					+ "pr_carID_seq.nextval,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getCarID());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getSort());
			ps.setString(4, vo.getPoster());
			
			ps.executeUpdate();	
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
	public List<RentMainVO> project_rent_main()
	{
		List<RentMainVO> list=new ArrayList<RentMainVO>();
		try
		{
			getConnection();
			String sql="SELECT /*+INDEX_ASC(project_rent_main pr_carID_pk)*/carID,Name,sort,poster "
					+ "FROM project_rent_main";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				RentMainVO vo=new RentMainVO();
				
				String s=rs.getString(1);
				vo.setName(rs.getString(2));
				vo.setSort(rs.getString(3));
				vo.setPoster(rs.getString(4));
				list.add(vo);
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return list;
		
	}
}
