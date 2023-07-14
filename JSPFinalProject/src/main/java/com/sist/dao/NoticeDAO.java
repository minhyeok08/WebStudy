package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.common.*;
import com.sist.vo.*;

public class NoticeDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDataBase db=new CreateDataBase();
	private static NoticeDAO dao;
	
	public static NoticeDAO newInstance()
	{
		if(dao==null)
			dao=new NoticeDAO();
		return dao;
	}
	public List<NoticeVO> noticeListData(int page)
	{
		List<NoticeVO> list=new ArrayList<NoticeVO>();
		try
		{
			conn=db.getConnection();
			String sql="select no,subject,name,type,to_char(regdate,'YYYY-MM-DD HH24:MI:SS'),hit,num "
					+ "FROM (select no,subject,name,type,regdate,hit,rownum as num "
					+ "from (select /*+ INDEX_DESC(project_notice pn_no_pk)*/no,subject,name,type,regdate,hit "
					+ "from project_notice)) "
					+ "where num between ? AND ? ";
			ps=conn.prepareStatement(sql);
			int rowSize=10;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				NoticeVO vo=new NoticeVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setType(rs.getInt(4));
				vo.setDbday(rs.getString(5));
				vo.setHit(rs.getInt(6));
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
	public int noticeTotalPage()
	{
		int total=0;
		try
		{
			conn=db.getConnection();
			String sql="select ceil(count(*)/10.0) from project_notice";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		}catch(Exception ex)
		{
			db.disConnection(conn, ps);
		}
		return total;
	}
	// 상세보기
	public NoticeVO noticeDetailData(int no)
	{
		NoticeVO vo=new NoticeVO();
		try
		{
			conn=db.getConnection();
			String sql="update project_notice set "
					+ "hit=hit+1 "
					+ "where no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			
			sql="select no,name,subject,content,type,to_char(regdate,'YYYY-MM-DD HH24:MI:SS'),hit "
					+ "from project_notice "
					+ "where no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setType(rs.getInt(5));
			vo.setDbday(rs.getString(6));
			vo.setHit(rs.getInt(7));
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			db.disConnection(conn, ps);
		}
		return vo;
	}
	// 목록 => 제작
	// admin만
	// 추가
	public void noticeInsert(NoticeVO vo)
	{
		try
		{
			conn=db.getConnection();
			String sql="insert into project_notice values("
					+ "pn_no_seq.nextval,?,?,?,?,?,sysdate,0)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getName());
			ps.setInt(3, vo.getType());
			ps.setString(4, vo.getSubject());
			ps.setString(5, vo.getContent());
			// in,out 입출력 오류
			/*
			 * 	null : URL => server.xml
			 * 	실행 => 오류가 없고 화면에 데이터가 안들어온다
			 */
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
	// 삭제
	public void noticeDelete(int no)
	{
		try
		{
			conn=db.getConnection();
			String sql="delete from project_notice "
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
	// 수정
	public NoticeVO noticeUpdateData(int no)
	{
		NoticeVO vo=new NoticeVO();
		try
		{
			conn=db.getConnection();
			String sql="select no,type,subject,content "
					+ "from project_notice "
					+ "where no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setType(rs.getInt(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			rs.close();
					
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			db.disConnection(conn, ps);
		}
		return vo;
	}
	public void noticeUpdate(NoticeVO vo)
	{
		try
		{
			conn=db.getConnection();
			String sql="update project_notice set "
					+ "type=?,subject=?,content=? "
					+ "where no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getType());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setInt(4, vo.getNo());
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
}
