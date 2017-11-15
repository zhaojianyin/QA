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
<title>站内信</title>
<link rel="stylesheet" media="all"
	href="${APP_PATH}/static/styles/letter.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="main">
		<div class="zg-wrap zu-main clearfix ">
			<ul class="letter-list">
				<c:forEach items="${conversions}" var="conversation">
					<li id="conversation-item-10005_622873"><a class="letter-link"
						href="${APP_PATH}/msg/detail?conversationId=${conversation.extend.message.conversationId}"></a>
						<div class="letter-info">
							<span class="l-time"><fmt:formatDate
									value="${conversation.extend.message.createDate}"
									timeStyle="yyyy-MM-dd" /> </span>
							<div class="l-operate-bar">

								<a
									href="${APP_PATH}/msg/detail?conversationId=${conversation.extend.message.conversationId}">
									共${conversation.extend.message.id}条会话 </a>
							</div>
						</div>
						<div class="chat-headbox">
							<c:if test="${conversation.extend.unread != 0}">
								<span class="msg-num">${conversation.extend.unread}</span>
							</c:if>
							<a class="list-head"> <img alt="头像"
								src="${conversation.extend.user.headUrl}">
							</a>
						</div>
						<div class="letter-detail">
							<a title="通知" class="letter-name level-color-1">
								${conversation.extend.user.name} </a>
							<p class="letter-brief">${conversation.extend.message.content}</p>
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