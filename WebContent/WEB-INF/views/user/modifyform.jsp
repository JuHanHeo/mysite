<%@page import="com.jx372.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<% UserVo user = (UserVo)request.getAttribute("vo"); %>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="/mysite/user?a=modifyuser">
				<input id="no" name="no" type="hidden"  value="<%=user.getNo() %>"/>
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="<%=user.getName() %>">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" readonly="readonly" value="<%=user.getEmail() %>">
					
					<label class="block-label">패스워드</label>
					<input name="passwd" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" <% if(user.getGender().equals("female")){  %> checked = "checked" <%} %>>
						<label>남</label> <input type="radio" name="gender" value="male" <% if(user.getGender().equals("male")){  %> checked = "checked" <%} %>>
					</fieldset>
					
					
					<input type="submit" value="수정하기">
					
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>