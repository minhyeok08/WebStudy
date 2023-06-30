package com.sist.dao;
import java.util.*;
import java.sql.*;
public class RentDAO {
   private Connection conn;
   private PreparedStatement ps;
   private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
   private static RentDAO dao;
   public RentDAO()
   {
      try
      {
         Class.forName("oracle.jdbc.driver.OracleDriver");
      }catch(Exception ex) {}
   }
   public static RentDAO newInstance()
   {
      if(dao==null)
         dao=new RentDAO();
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
   
   public void RentInfoInsert(RentInfoVO vo)
   {
      try
      {
         getConnection();
         String sql="INSERT INTO rent_info VALUES("
               + "?,?,?,?,?,?,?,?,?)";
         ps=conn.prepareStatement(sql);
         ps.setInt(1, vo.getCid());
         ps.setString(2, vo.getPoster());
         ps.setString(3, vo.getCarName());
         ps.setString(4, vo.getCarSize());
         ps.setString(5, vo.getSeater());
         ps.setString(6, vo.getFuelType());
         ps.setString(7, vo.getGearType());
         ps.setString(8, vo.getBrand());
         ps.setInt(9, vo.getPrice());
         
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
   
}