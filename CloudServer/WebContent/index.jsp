<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>登录</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<style>
* {
	padding: 0px;
	margin: 0px;
}

body {
	font-family: Arial, Helvetica, sans-serif;
	background: url(images/grass.jpg);
	font-size: 13px;
}

img {
	border: 0;
}

.lg {
	width: 468px;
	height: 468px;
	margin-left:300px;
	margin-top:100px;
	background: url(images/login_bg.png) no-repeat;
}

.lg_top {
	height: 200px;
	width: 468px;
}

.lg_main {
	width: 400px;
	height: 180px;
	margin: 0 25px;
}

.lg_m_1 {
	width: 290px;
	height: 100px;
	padding: 60px 55px 20px 55px;
}

.ur {
	height: 37px;
	border: 0;
	color: #666;
	width: 250px;
	margin: 8px 28px;
	background: url(images/user.png) no-repeat;
	padding-left: 10px;
	font-size: 16pt;
	font-family: Arial, Helvetica, sans-serif;
}

.pw {
	height: 37px;
	border: 0;
	color: #666;
	width: 250px;
	margin: 4px 28px;
	background: url(images/password.png) no-repeat;
	padding-left: 10px;
	font-size: 16pt;
	font-family: Arial, Helvetica, sans-serif;
}

.bn {
	width: 330px;
	height: 72px;
	background: url(images/enter.png) no-repeat;
	border: 0;
	display: block;
	font-size: 18px;
	color: #FFF;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bolder;
}

.lg_foot {
	height: 80px;
	width: 330px;
	padding: 6px 68px 0 68px;
}
</style>
<script language="javascript">
       function login1(){
            var url = "login.action";
            var data = "user.name="+$("#username").val()+"&user.password="+$("#password").val();
            $.ajax({
                 type:"post",
                 url:url,
                 data:data,
                 dataType:"text",
                 success:function(ret){
                     //alert(ret);
                     var obj = eval("("+ret+")");
                     if("-1"==obj)alert("密码错误");
                     else
                     	window.location = "listCFile.action"
                 },
                 error:function(){
                     alert("服务器错误");
                 }
            });
       };
</script>
	</head>
	<body class="b">
		<div class="lg">
			<form action="login.action" method="POST" id="l_form">
				<div class="lg_top"></div>
				<div class="lg_main">
					<div class="lg_m_1">
						<input id="username" name="user.name" value="" class="ur" />
						<input id="password" name="user.password" type="password" value="" class="pw" />
					</div>
				</div>
				<div class="lg_foot">
					<input type="button" value="Login In" class="bn" id="login" onclick="login1()"/>
				</div>
			</form>
		</div>
	</body>
    
</html>
