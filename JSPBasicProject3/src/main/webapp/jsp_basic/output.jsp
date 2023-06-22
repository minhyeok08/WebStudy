<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.temp.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="bean" class="com.sist.temp.SawonBean">
	<jsp:setProperty name="bean" property="*"/>
</jsp:useBean>
<%--
	<jsp:useBean id="bean" class="com.sist.temp.SawonBean">
	SawonBean bean=new SawonBean()
	<jsp:setProperty name="bean" property="*"/>
	
	public void aaa(SawonBean bean) ==> Spring
	-------------------------------------------------------------
	218page 빈을 이용한 JSP 파일 작성
	-----------------------------
		jsp 액션 태그
		=> 화면 이동 , 화면 연결 , 값 전송 , 객체 생성 , 멤버변수에 값 주입 , 멤버변수 값을 읽기
												  <jsp.setProperty> <jsp:getProperty>
							   			<jsp.useBean>
			 |			|	   ***<jsp:param>
			 		***<jsp:include>
		  ***<jsp:forward>
	-----------------------------
	<jsp:useBean> : 객체 메모리 할당 => 객체 생성
	속성
		id : 객체명
		class : 클래스명
		scope : 사용범위
		  = page (default) => 한개 JSP에서 사용 (다른 페이지 이동 => 메모리 해제)
		    *** 생략이 가능
		  = request => 사용자 요청이 있는 경우에 사용
		  = session => 프로그램이 유지하고 있는 동안 (접속~종료)
		  = application => 프로그램 종료시까지 유지
		<jsp:useBean id="a" class="A">
						   ------------ 패키지명.클래스명 => Class.forName()
		=> 자바 변경
			A a = new A();
	-----------------------------
	<jsp:setProperty> : 변수에 값을 설정 (쓰기)
	  => setter
	  name : 객체명
	  property : 변수명
	  value : 값을 설정
	  
	  class A
	  {
	  	private int no;
	  	private String name;
	  	
	  	setNo() getNo() setName() getName()
	  }
	  // 1. 메모리 할당
	  <jsp:useBean id="a
	-----------------------------
	<jsp:setProperty> : 읽기
	  => getter
	-----------------------------
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름:<%=bean.getName() %><br>
	성별:<%=bean.getSex() %><br>
	부서:<%=bean.getDept() %><br>
	직위:<%=bean.getJob() %><br>
	연봉:<%=bean.getPay() %><br>
</body>
</html>