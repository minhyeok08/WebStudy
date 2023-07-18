package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.common.*;
import com.sist.vo.*;
public class ReplyBoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDataBase db=new CreateDataBase();
	private static ReplyBoardDAO dao;
	
	public static ReplyBoardDAO newInstance()
	{
		if(dao==null)
			dao=new ReplyBoardDAO();
		return dao;
	}
	// 사용자
	// 목록
	public List<ReplyBoardVO> replyBoardListData(int page)
	{
		List<ReplyBoardVO> list=new ArrayList<ReplyBoardVO>();
		try
		{
			conn=db.getConnection();
			String sql="select no,subject,name,to_char(regdate,'YYYY-MM-DD'),hit,group_tab,num "
					+ "from (select no,subject,name,regdate,hit,group_tab,rownum as num "
					+ "from (select no,subject,name,regdate,hit,group_tab "
					+ "from project_replyBoard order by group_id desc, group_step asc)) "
					+ "where num between ? and ?";
			ps=conn.prepareStatement(sql);
			int rowSize=10;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ReplyBoardVO vo=new ReplyBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				vo.setGroup_tab(rs.getInt(6));
				list.add(vo);
			}
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
	// 총페이지
	public int replyBoardTotalPage()
	{
		int total=0;
		try
		{
			conn=db.getConnection();
			String sql="select ceil(count(*)/10.0) from project_replyBoard";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			db.disConnection(conn, ps);
		}		
		return total;
	}
	// 묻기 (새글)
	public void replyBoardInsert(ReplyBoardVO vo)
	{
		try
		{
			conn=db.getConnection();
			String sql="insert into project_replyboard(no,id,name,subject,content,group_id) "
					+ "values(pr_no_seq.nextval,?,?,?,?,"
					+ "(select nvl(max(group_id)+1,1) from project_replyboard))";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getSubject());
			ps.setString(4, vo.getContent());
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
	public void replyBoardDelete(int no)
	{
		 try
			{
				conn=db.getConnection();
				String sql="select group_id,isreply "
						+ "from project_replyboard "
						+ "where no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				ResultSet rs=ps.executeQuery();
				rs.next();
				int gi=rs.getInt(1);
				int isreply=rs.getInt(2);
				rs.close();
				
				if(isreply==1)
				{
					sql="delete from project_replyboard "
							+ "where group_id=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, gi);
					ps.executeUpdate();
				}
				else
				{
					sql="delete from project_replyboard "
							+ "where no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, no);
					ps.executeUpdate();
				}
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				db.disConnection(conn, ps);
			}
	}
	// 내용
	public ReplyBoardVO replyBoardDetailData(int no)
	{
		ReplyBoardVO vo=new ReplyBoardVO();
		try
		{
			conn=db.getConnection();
			String sql="update project_replyboard set "
					+ "hit=hit+1 "
					+ "where no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			
			sql="select no,name,id,subject,content,to_char(regdate,'YYYY-MM-DD'),hit "
					+ "from project_replyboard "
					+ "where no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setId(rs.getString(3));
			vo.setSubject(rs.getString(4));
			vo.setContent(rs.getNString(5));
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
	// 수정
	public ReplyBoardVO replyBoardUpdateData(int no)
	{
		ReplyBoardVO vo=new ReplyBoardVO();
		try
		{
			conn=db.getConnection();
			String sql="select no,name,id,subject,content,to_char(regdate,'YYYY-MM-DD'),hit "
					+ "from project_replyboard "
					+ "where no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setId(rs.getString(3));
			vo.setSubject(rs.getString(4));
			vo.setContent(rs.getNString(5));
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
	public void replyBoardUpdate(ReplyBoardVO vo)
	{
		try
		{
			conn=db.getConnection();
			String sql="update project_replyboard set "
					+ "subject=? , content=? "
					+ "where no=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getSubject());
			ps.setString(2, vo.getContent());
			ps.setInt(3, vo.getNo());
			
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
	
/*
 * try
		{
			conn=db.getConnection();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			db.disConnection(conn, ps);
		}
		
 */
	
	// 관리자
	// 목록
	public List<ReplyBoardVO> replyBoardAdminListData(int page)
	{
		List<ReplyBoardVO> list=new ArrayList<ReplyBoardVO>();
		try
		{
			conn=db.getConnection();
			String sql="select no,subject,name,to_char(regdate,'YYYY-MM-DD'),hit,isreply,group_step,num "
					+ "from (select no,subject,name,regdate,hit,isreply,group_step,rownum as num "
					+ "from (select no,subject,name,regdate,hit,isreply,group_step "
					+ "from project_replyboard order by group_id desc)) "
					+ "where num between ? and ? "
					+ "and group_step=0";
			ps=conn.prepareStatement(sql);
			int rowSize=10;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ReplyBoardVO vo=new ReplyBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				vo.setIsreply(rs.getInt(6));
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
	public int replyBoardAdminTotalPage()
	{
		int total=0;
		try
		{
			conn=db.getConnection();
			String sql="select ceil(count(*)/10.0) "
					+ "from subject_replyboard "
					+ "where group_step=0";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			db.disConnection(conn, ps);
		}
		return total;
	}
	// 답변 (새글)
	public void replyBoardAdminInsert(int pno,ReplyBoardVO vo)
	{
		try
		{
			conn=db.getConnection();
			String sql="select group_id from project_replyboard "
					+ "where no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int gi=rs.getInt(1);
			rs.close();
			
			sql="insert into project_replyboard(no,id,name,subject,content,group_id,group_step,group_tab) "
					+ "values(pr_no_seq.nextval,?,?,?,?,?,1,1)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getSubject());
			ps.setString(4, vo.getContent());
			ps.setInt(5, gi);
			ps.executeUpdate();
			
			sql="update project_replyboard set "
					+ "isreply=1 "
					+ "where no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ps.executeUpdate();
			conn.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
			try 
			{
				conn.rollback();
			}catch(Exception e) {}
			ex.printStackTrace();
		}finally
		{
			try 
			{
				conn.setAutoCommit(true);
			}catch(Exception e2) 
			{
				db.disConnection(conn, ps);
			}
		}
	}
	// 삭제
	// 수정
}
