<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	request/response/session => 웹 개발의 핵심
	---------------- ------- 프로그램의 실행하는 중
	session은 서버에 저장 : 사용자의 정보를 지속적으로 관리
	------- 1) 장바구니 , 결제 , 예약 , 추천 ...
			2) session에 저장이 되면 모든 JSP에서 사용이 가능 (전역변수)
	클래스명 => HttpSession
				클라이언트마다 1개 생성 => id가 부여 (구분자)
									  -- sessionId => 채팅 , 상담..
	주요메소드
			getId() : 세션마다 저장 구분자
			setMaxinactiveInterval() => 저장 기간을 설정
			 => 기본 default => 1800 (초단위 : 30분)
			 => 경매 
			 inNew() ; ID가 할당이 된것인지 여부 확인
			 => 장바구니
			 invalidate() : session에 저장된 모든 내용을 지운다
			 				로그아웃
			 setAttribute() :session에 정보 저ㅏㅇ
			 getAttribute()	: 저장된 데이터 일부를 
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>HttpServletSession(session):177page</h1>
</body>
</html>