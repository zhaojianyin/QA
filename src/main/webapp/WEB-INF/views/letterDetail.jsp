<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="all"
	href="${APP_PATH}/static/styles/letter.css">
<title>站内信</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="main">
		<div class="zg-wrap zu-main clearfix ">
			<ul class="letter-chatlist">
				<c:forEach var="message" items="${messages}">
					<li id="msg-item-4009580"><a class="list-head"> <img
							alt="头像" src="${message.extend.user.headUrl}">
					</a>
						<div class="tooltip fade right in">
							<div class="tooltip-arrow"></div>
							<div class="tooltip-inner letter-chat clearfix">
								<div class="letter-info">
									<p class="letter-time">
										<fmt:formatDate value="${message.extend.message.createDate}"
											timeStyle="yyyy-MM-dd" />
									</p>
									<a href="javascript:void(0);" id="del-link" name="4009580">删除</a>
								</div>
								<p class="chat-content">${message.extend.message.content}</p>
							</div>
						</div></li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<jsp:include page="js.jsp"></jsp:include>
	<script type="text/javascript"
		src="${APP_PATH}/static/scripts/main/site/detail.js"></script>
</body>
</html>