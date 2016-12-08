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

<title>My JSP 'login.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<style type="text/css">
.forums-table {
	margin: 20px;
}
</style>
</head>

<body>
	<div class="forums-table">
		<a href="admin/add" class="btn btn-primary btn-sm pull-left "
			id="addforum"> <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;添加用户
		</a>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>用户名</th>
					<th>密码</th>
					<th>权限</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#list" var="l">
					<tr>
						<td><s:property value="id" /></td>
						<td><s:property value="username" /></td>
						<td><s:property value="password" /></td>
						<td><s:if test="#l.role==1">
								超级管理员
							</s:if>
							<s:else>
								管理员
							</s:else></td>
						<td><a href="admin/edit?id=${l.id}">修改</a> <a
							href="admin/delete?id=${l.id} ">删除</a></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="text-center">
			<nav>
			<ul class="pagination">
				<li><a href="admin/index?page=${page-1 }">&laquo;</a></li>

				<s:bean name="org.apache.struts2.util.Counter" var="Counter">
					<s:param name="first" value="1" />
					<s:param name="last" value="#cpage" />
					<s:iterator>
						<s:if test="current-1 == page">
							<li class="active"><a href="admin/index?page=${current-1 } ">${current-1 }</a></li>
						</s:if>
						<s:else>
							<li><a href="admin/index?page=${current-1 } ">${current-1 }</a></li>
						</s:else>
					</s:iterator>
				</s:bean>

				<li><a href="admin/index?page=${page+1 }">&raquo;</a></li>
			</ul>

			</nav>
		</div>
	</div>
</body>
</html>
