<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<title>Login Form</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/style.css" rel='stylesheet' type='text/css' />
<script type="text/javascript">
var me = "${me}";
	if (me!=""){
		alert("${me}");
	}
</script>
</head>
<body>
	<div class="main">
		<div class="login">
			<h1>学生管理系统</h1>
			<div class="inset">
				<!--start-main-->
				<form action="login" method="post">
			         <div>
			         	<h2>管理登录</h2>
						<span><label>用户名</label></span>
						<span><input type="text" name="admin.username" class="textbox" ></span>
					 </div>
					 <div>
						<span><label>密码</label></span>
					    <span><input type="password" name="admin.password"  class="password"></span>
					 </div>
					<div class="sign">
                        <input type="submit" value="登录" class="submit" />
					</div>
					</form>
				</div>
			</div>
		<!--//end-main-->
		</div>

<div class="copy-right">
	<p>&copy; 2015 Ethos Login Form. All Rights Reserved</p>

</div>

</body>
</html>