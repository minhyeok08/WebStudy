package com.sist.food;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.dao.*;
/*
 * 	@GetMapping => GET
 *  @PostMapping => POST
 *  @RequestMapping => GET/POST
 *  -------------------------------- 400
 */

@WebServlet("/FoodSearchServlet")
public class FoodSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// 사용자 요청값을 받는다
		String addr=request.getParameter("addr");
		if(addr==null)
			addr="마포";
		
		String strPage=request.getParameter("page");
		if(strPage==null)
			strPage="1";
		
		int curpage=Integer.parseInt(strPage);
		
		FoodDAO dao=FoodDAO.newInstance();
		List<FoodVO> list=dao.foodFindData(addr, curpage);
		int totalpage = (int)(Math.ceil(dao.foodRowCount(addr)/12.0));
		int count = dao.foodRowCount(addr);
		final int BLOCK=5;
		// curpage=1 => startPage=1
		// (2-1)/5 => 1/5*5 => 0 (5-1)/5*5 ==> 0
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		// (6-1)/5*5 => 5+1 => 6
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		// < [1][2][3][4][5] >
		//	 --- 		 ---
		//   startPage	endPage
		//	 curpage => 1~5 일때동안 => startPage=1 유지 , endPage=5 유지
		//	 curpage => 6~10	=> startPage=6 유지 , endPage=10 유지 => 1,6,11
		// < [6][7][8][9][10] >
		// 화면
		/*
		 * 	<ul class="pagination">
			  <li><a href="#">1</a></li>
			  <li><a href="#">2</a></li>
			  <li><a href="#">3</a></li>
			  <li><a href="#">4</a></li>
			  <li><a href="#">5</a></li>
			</ul>
		 */
		PrintWriter out=response.getWriter();
		// 페이징기법
	}

}
