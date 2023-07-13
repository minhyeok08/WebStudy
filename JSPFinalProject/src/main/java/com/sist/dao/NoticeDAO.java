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
}
