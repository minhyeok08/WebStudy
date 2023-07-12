<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../shadow/css/shadowbox.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
  margin: 0px auto;
  width:100%;
}
</style>
<script type="text/javascript" src="../shadow/js/shadowbox.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<title></title>
<style>
    body {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    .login-container {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100%;
    }
    .login-form {
        width: 450px;
        height:450px;
        padding: 40px;
        background-color: #f4f4f4;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
    }
    .login-form h1 {
        text-align: center;
    }
    .login-form label, .login-form input[type="text"], .login-form input[type="password"], .login-form input[type="submit"] {
        display: block;
        width: 100%;
        margin-bottom: 10px;
    }
    .login-form input[type="submit"] {
        margin-top: 20px;
    }
</style>
</head>
<body>
<div class="login-container">
    <div class="login-form">
        <h1>로그인</h1>
        <form method="post" action="login_ok.jsp">
            <label for="username">사용자 이름:</label>
            <input type="text" id="username" name="username" required><br>
            <label for="password">비밀번호:</label>
            <input type="password" id="password" name="password" required><br>
            <input type="submit" value="로그인">
            <input type="submit" value="회원가입" onclick="window.location.href='../member/join.do'">
            
        </form>
    </div>
</div>
</html>