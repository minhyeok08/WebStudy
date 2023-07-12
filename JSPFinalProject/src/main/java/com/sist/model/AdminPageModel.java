package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Connection.Request;

import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
public class AdminPageModel {
	@RequestMapping("adminpage/adminpage_main.do")
	public String admin_main(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("adminpage_jsp", "../adminpage/adminpage_reserve.jsp");
		request.setAttribute("main_jsp", "../adminpage/adminpage_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("adminpage/adminpage_reserve.do")
	public String admin_reserve(HttpServletRequest request, HttpServletResponse response)
	{
		ReserveDAO dao=ReserveDAO.newInstance();
		List<ReserveVO> list=dao.reserveAdminData();
		request.setAttribute("list", list);
		request.setAttribute("adminpage_jsp", "../adminpage/adminpage_reserve.jsp");
		request.setAttribute("main_jsp", "../adminpage/adminpage_main.jsp");
		return "../main/main.jsp";
	}
}