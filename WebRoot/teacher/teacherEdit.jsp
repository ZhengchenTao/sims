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

<title>My JSP 'teacherAdd.jsp' starting page</title>

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
			<s:form action="teacher/doEdit" method="post">
				<input type="hidden" value="${tea.id }" name="teacher.id">
				<div class="form-group">
					<label for="name">姓名</label> <input type="text"
						class="form-control" id="name" name="teacher.name"
						value="${tea.name }">
				</div>
				<div class="form-group">
					<label for="sex">性别</label><select class="form-control"
						name="teacher.sex" id="sex">
						<s:if test="#tea.sex=='男'.toString()">
							<option selected="selected">男</option>
							<option>女</option>
						</s:if>
						<s:else>
							<option>男</option>
							<option  selected="selected">女</option>
						</s:else>
					</select>
				</div>
				<div class="form-group">
					<label for="age">年龄</label> <input type="text" class="form-control"
						id="age" name="teacher.age" value="${tea.age }">
				</div>
				<a href="teacher/index" class="btn btn-default pull-right">返回</a>
				<button type="submit" class="btn btn-primary pull-left">提交</button>
			</s:form>
		</div>
	</div>
</body>
</html>
