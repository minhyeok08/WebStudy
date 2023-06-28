<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@700&family=Roboto+Mono:ital,wght@0,200;0,300;0,500;1,100;1,200;1,700&display=swap" rel="stylesheet">
<style type="text/css">

.container{
	margin-top:50px;
}
.row{
	margin:0px auto;
	width:450px;
}
h1{
	text-align:center;
	font-family: 'Nanum Gothic', sans-serif;
}
</style>

</head>
<body>
	<div class="container">
		<h1>Login</h1>
		<div class="row">
			<form method=post action=login_ok.jsp id="frm">
			<table class="table">
				<tr>
					<td width=20%>ID</td>
					<td width=80%>
						<input type=text name=id size=15 class="input-sm" id=id required>
					</td>
				</tr>
				<tr>
					<td width=20%>Password</td>
					<td width=80%>
						<input type=password name=pwd size=15 class="input-sm" id=pwd required>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<span id="print" style="color:red"></span>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type=submit class="btn btn-sm btn-danger" value=로그인 id="logBtn">
						<a href="goods.jsp" class="btn btn-sm btn-success">상품목록</a>
					</td>
				</tr>		
			</table>
			</form>
		</div>
	</div>
</body>
</html>