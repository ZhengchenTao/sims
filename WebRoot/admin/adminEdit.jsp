<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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

<title>My JSP 'adminAdd.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<style type="text/css">
.edit {
	margin-top: 100px;
}
</style>
</head>

<body>
	<div class="row" style="width:100%">
		<div class="edit col-md-4 col-md-offset-4">
			<s:form action="admin/doEdit" method="post">
				<input type="hidden" value="${stu.id }" name="admin.id">
				<div class="form-group">
					<label for="username">用户名</label> <input type="text"
						class="form-control" id="name" name="admin.username"
						value="${stu.username }">
				</div>
				<div class="form-group">
					<label for="password">密码</label> <input type="text"
						class="form-control" id="password" name="admin.password"
						value="${stu.password }">
				</div>
				<div class="form-group">
					<label for="role">权限</label> <select class="form-control"
						name="admin.role" id="role">
						<s:if test="#stu.role=='1'.toString()">
							<option value="1" selected="selected">超级管理员</option>
							<option value="0">管理员</option>
						</s:if><s:else>
							<option value="1">超级管理员</option>
							<option value="0" selected="selected">管理员</option>
						</s:else>
					</select>
				</div>
				<a href="admin/index" class="btn btn-default pull-right">返回</a>
				<button type="submit" class="btn btn-primary pull-left">提交</button>
			</s:form>
		</div>
	</div>
</body>
</html>
