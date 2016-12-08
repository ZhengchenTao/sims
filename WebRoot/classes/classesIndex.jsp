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
		<a href="classes/add" class="btn btn-primary btn-sm pull-left "
			id="addforum"> <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;添加班级
		</a>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>班级</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#list" var="l">
					<tr>
						<td><s:property value="id" /></td>
						<td><s:property value="grade" /></td>
						<td><a href="classes/edit?id=${l.id}">修改</a> <a
							href="classes/delete?id=${l.id} ">删除</a></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="text-center">
			<nav>
			<ul class="pagination">
				<li><a href="classes/index?page=${page-1 }">&laquo;</a></li>

				<s:bean name="org.apache.struts2.util.Counter" var="Counter">
					<s:param name="first" value="1" />
					<s:param name="last" value="#cpage" />
					<s:iterator>
						<s:if test="current-1 == page">
							<li class="active"><a
								href="classes/index?page=${current-1 } ">${current-1 }</a></li>
						</s:if>
						<s:else>
							<li><a href="classes/index?page=${current-1 } ">${current-1 }</a></li>
						</s:else>
					</s:iterator>
				</s:bean>

				<li><a href="classes/index?page=${page+1 }">&raquo;</a></li>
			</ul>

			</nav>
		</div>
	</div>
</body>
</html>
