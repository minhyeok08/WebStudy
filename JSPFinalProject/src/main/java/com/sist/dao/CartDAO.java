package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.common.*;
import com.sist.vo.*;
public class CartDAO {
	private Connection conn;
	private PreparedStatement ps; 
	private CreateDataBase db=new CreateDataBase();
	private static CartDAO dao;
	private String[] tab= {"","goods_all","goods_best","goods_new","goods_special"};
	
	public static CartDAO newInstance()
	{
		if(dao==null)
			dao=new CartDAO();
			return dao;
	}
	public void cartInsert(CartVO vo)
	{
		try
		{
			conn=db.getConnection();
			String sql="select count(*) "
					+ "from project_cart "
					+ "where goods_no=? and type=? and issale<>1";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getGoods_no());
			ps.setInt(2, 1);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			rs.close();
			
			if(count!=0)
			{
				sql="update project_cart set "
						+ "amount=amount+"+vo.getAmount()
						+" where goods_no=? and type=? and id?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, vo.getGoods_no());
				ps.setInt(2, 1);
				ps.setString(3, vo.getId());
				rs=ps.executeQuery();
				ps.executeUpdate();
			}
			else
			{
				sql="insert into project_cart(cart_no,goods_no,type,amount,price,id) "
						+ "values(pct_cno_seq.nextval,?,?,?,?,?)";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, vo.getGoods_no());
				ps.setInt(2, 1);
				ps.setInt(3, vo.getAmount());
				ps.setInt(4, vo.getPrice());
				ps.setString(5, vo.getId());
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
	public List<CartVO> mypageCartListData(String id)
	{
		List<CartVO> list=new ArrayList<CartVO>();
		try
		{
			conn=db.getConnection();
			String sql="select cart_no,goods_no,"
					+ "(select goods_name from goods_all where no=pc.goods_no) as goods_name,"
					+ "(select goods_poster from goods_all where no=pc.goods_no) as goods_poster,"
					+ "(select goods_price from goods_all where no=pc.goods_no) as goods_price,"
							+ "amount,regdate,issale,ischeck,price "
							+ "from project_cart pc "
							+ "where id=? and issale<>1 "
							+ "order by cart_no desc";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				CartVO vo=new CartVO();
				vo.setCart_no(rs.getInt(1));
				vo.setGoods_no(rs.getInt(2));
				vo.setGoods_name(rs.getString(3));
				vo.setGoods_poster(rs.getString(4));
				vo.setGoods_price(rs.getString(5));
				vo.setAmount(rs.getInt(6));
				vo.setRegdate(rs.getDate(7));
				vo.setIssale(rs.getInt(8));
				vo.setIscheck(rs.getInt(9));
				vo.setPrice(rs.getInt(10));
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
	public List<CartVO> admipageCartList(int type)
	{
		List<CartVO> list=new ArrayList<CartVO>();
		try
		{
			conn=db.getConnection();
			String sql="select cart_no,goods_no,"
					+ "(select goods_name from "+tab[type]+" where no=pc.goods_no) as goods_name,"
					+ "(select goods_name from "+tab[type]+" where no=pc.goods_no) as goods_poster,"
					+ "(select goods_name from "+tab[type]+" where no=pc.goods_no) as goods_price,"
							+ "amount,regdate,issale,ischeck"
							+ "from project_cart pc "
							+ "order by cart_no desc";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				CartVO vo=new CartVO();
				vo.setCart_no(rs.getInt(1));
				vo.setGoods_no(rs.getInt(2));
				vo.setGoods_name(rs.getString(3));
				vo.setGoods_poster(rs.getString(4));
				vo.setGoods_price(rs.getString(5));
				vo.setAmount(rs.getInt(6));
				vo.setRegdate(rs.getDate(7));
				vo.setIssale(rs.getInt(8));
				vo.setIscheck(rs.getInt(9));
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
	// adminOK
	public void cartBuy(int no)
	{
		try
		{
			conn=db.getConnection();
			String sql="update project_cart set "
					+ "issale=1 "
					+ "where cart_no=?";
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
	// 구매취소
	public void cartCancel(int no)
	{
		try
		{
			conn=db.getConnection();
			String sql="delete from project_cart "
					+ "where cart_no=?";
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
	
	public List<CartVO> mypageCartBuyListData(String id)
	{
		List<CartVO> list=new ArrayList<CartVO>();
		try
		{
			conn=db.getConnection();
			String sql="select cart_no,goods_no,"
					+ "(select goods_name from goods_all where no=pc.goods_no) as goods_name,"
					+ "(select goods_poster from goods_all where no=pc.goods_no) as goods_poster,"
					+ "(select goods_price from goods_all where no=pc.goods_no) as goods_price,"
							+ "amount,regdate,issale,ischeck,price "
							+ "from project_cart pc "
							+ "where id=? and issale=1 "
							+ "order by cart_no desc";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				CartVO vo=new CartVO();
				vo.setCart_no(rs.getInt(1));
				vo.setGoods_no(rs.getInt(2));
				vo.setGoods_name(rs.getString(3));
				vo.setGoods_poster(rs.getString(4));
				vo.setGoods_price(rs.getString(5));
				vo.setAmount(rs.getInt(6));
				vo.setRegdate(rs.getDate(7));
				vo.setIssale(rs.getInt(8));
				vo.setIscheck(rs.getInt(9));
				vo.setPrice(rs.getInt(10));
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
}
