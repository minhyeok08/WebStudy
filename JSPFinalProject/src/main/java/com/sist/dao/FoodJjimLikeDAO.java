package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.common.*;
import com.sist.vo.*;

public class FoodJjimLikeDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDataBase db=new CreateDataBase();
	private static FoodJjimLikeDAO dao;
	
	public static FoodJjimLikeDAO newInstance()
	{
		if(dao==null)
			dao=new FoodJjimLikeDAO();
		return dao;
	}
	// Jjim 저장
	public void foodJjimInsert(FoodJJimVO vo)
	{
		try
		{
			conn=db.getConnection();
			String sql="insert into food_jjim values("
					+ "fj_no_seq.nextval,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getId());
			ps.setInt(2, vo.getFno());
			ps.executeUpdate();
					
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			db.disConnection(conn, ps);
		}
	}
	// Jjim 확인
	public int foodJjimCount(String id, int fno)
	{
		int count=0;
		try
		{
			conn=db.getConnection();
			String sql="SELECT count(*) "
					+ "FROM food_jjim "
					+ "WHERE fno=? AND id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, fno);
			ps.setString(2, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			db.disConnection(conn, ps);
		}
		return count;
	}
	// Jjim 목록 => 마이페이지 (찜관리)
	public List<FoodJJimVO> foodJjimListData(String id)
	{
		List<FoodJJimVO> list=new ArrayList<FoodJJimVO>();
		try
		{
			conn=db.getConnection();
			/*String sql="select no,fno,"
					+ "(SELECT poster FROM food_house WHERE fno=food_jjim.fno),"
					+ "(SELECT name FROM food_house WHERE fno=food_jjim.fno),"
					+ "(SELECT phone FROM food_house WHERE fno=food_jjim.fno) "
					+ "FROM food_jjim "
					+ "where id=? "
					+ "order by no desc";*/
			String sql="SELECT no,fno,foodGetPoster(fno),"
					+ "foodGetName(fno),foodGetPhone(fno) "
					+ "FROM food_jjim "
					+ "where id=? "
					+ "order by no desc";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodJJimVO vo=new FoodJJimVO();
				vo.setNo(rs.getInt(1));
				vo.setFno(rs.getInt(2));
				String poster=rs.getString(3);
				poster=poster.substring(0,poster.indexOf("^"));
				poster=poster.replace("#", "&");
				vo.setPoster(poster);
				vo.setName(rs.getString(4));
				vo.setTel(rs.getString(5));
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			db.disConnection(conn, ps);
		}
		return list;
	}
	// Jjim 취소
	public void foodJjimCancel(int no)
	{
		try
		{
			conn=db.getConnection();
			String sql="delete from food_jjim "
					+ "where no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			db.disConnection(conn, ps);
		}
	}
	// Like 저장
	public void foodLikeInsert(FoodLikeVO vo)
	{
		try
		{
			conn=db.getConnection();
			String sql="INSERT INTO food_like values("
					+ "fl_no_seq.nextval,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getId());
			ps.setInt(2, vo.getFno());
			ps.executeUpdate();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			db.disConnection(conn, ps);
		}
	}
	// Like 확인
	public int foodLikeOk(int fno,String id)
	{
		int count=0;
		try
		{
			conn=db.getConnection();
			String sql="SELECT count(*) "
					+ "FROM food_like "
					+ "WHERE fno=? AND id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, count);
			ps.setString(2, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			db.disConnection(conn, ps);
		}
		return count;
	}
	// Like 총개수 읽기
	public int foodLikeCount(int fno)
	{
		int count=0;
		try
		{
			conn=db.getConnection();
			String sql="SELECT count(*) "
					+ "FROM food_like "
					+ "where fno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, fno);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			db.disConnection(conn, ps);
		}
		return count;
	}
	
}
