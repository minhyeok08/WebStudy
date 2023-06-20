<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	// forward => 잘라내기 느낌 // 본인파일에 내용만 바뀌는
	// sendRedirect => 새로운 파일 새로운 내용 
	//response.sendRedirect("response_2.jsp");
	RequestDispatcher rd=request.getRequestDispatcher("response_2.jsp");
	rd.forward(request, response);
	System.out.println(request);
%>