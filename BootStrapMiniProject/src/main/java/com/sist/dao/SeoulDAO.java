/*
 * 	request : 사용자 요청값을 받는 경우 => getParameter()
 * 	response : 전송 (브라우저) => html , cookie
 * 				=> 한파일에서 1번만 수행이 가능
 * 				=> 서버에서 화면 변경 (sendRedirect)
 * 	cookie : 브라우저에 저장 => 최근 방문
 * 	session : 서버에 정보 저장 => 예약, 장바구니
 * 	----------- JSP / Spring / Spring-boot
 */
package com.sist.dao;

import java.util.*;
import java.sql.*;
import com.sist.dbconn.*;
public class SeoulDAO {
	private String[] tables= {
		"",
		"seoul_location",
		"seoul_nature",
		"seoul_shop",
	};
	private Connection conn;
	private PreparedStatement ps;
	private CreateDataBase db = new CreateDataBase();
	private static SeoulDAO dao;
	
	// 1. 기능
	
	public List <SeoulVO> seoulListData(int type)
	{
		List<SeoulVO> list = new ArrayList<SeoulVO>();
		try
		{
			conn=db.getConnection();
			String sql="SELECT no,title,poster,rownum "
					+ "FROM "+tables[type]
					+ " WHERE rownum<=20";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				SeoulVO vo = new SeoulVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex)
		{
			
		}
		finally
		{
			
		}
		return list;
	}
	public static SeoulDAO newInstance()
	{
		if(dao==null)
			dao=new SeoulDAO();
		return dao;
	}
	// 2. 총페이지 구하기
	public int seoulTotalPage(int type)
	{
		int total=0;
		try
		{
			conn=db.getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) "
					+ "FROM "+tables[type];
			// ps.setString(1,tables[type]) => 이렇게하면 밑에처럼 작은 따옴표가 붙음
			// FROM 'seoul_location'
			/*
			 * 	INSERT INTO table_name VALUES(?,?)...
			 * 	홍길동 남자
			 * 	INSERT INTO table_name VALUES('홍길동','남자');
			 * 	ps.setString(1,"홍길동")
			 */
		}catch(Exception ex)
		{
			
		}
		finally
		{
			db.disConnection(conn, ps);
		}
		return total;
	}
	// 3. 상세보기
	public SeoulVO seoulDetailData(int no, int type)
	{
		SeoulVO vo = new SeoulVO();
		try
		{
			
		}catch(Exception ex)
		{
			
		}
		finally
		{
			
		}
		return vo;
	}
}











