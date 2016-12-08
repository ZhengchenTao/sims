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

<title>My JSP 'studentAdd.jsp' starting page</title>

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
			<s:form action="student/doAdd" method="post">
				<div class="form-group">
					<label for="name">姓名</label> <input type="text"
						class="form-control" id="name" name="student.name">
				</div>
				<div class="form-group">
					<label for="sex">性别</label> <select class="form-control"
						name="student.sex" id="sex">
						<option>男</option>
						<option>女</option>
					</select>
				</div>
				<div class="form-group">
					<label for="classes_id">班级</label> <select class="form-control"
						name="student.classes_id" id="classes_id">
						<s:iterator value="#list" var="l">
							<option value="${l.id }">${l.grade}</option>
						</s:iterator>
					</select>
				</div>
				<div class="form-group">
					<label for="age">年龄</label> <input type="text" class="form-control"
						id="age" name="student.age">
				</div>
				<a href="student/index" class="btn btn-default pull-right">返回</a>
				<button type="submit" class="btn btn-primary pull-left">提交</button>
			</s:form>
		</div>
	</div>
</body>
</html>
