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
<title>关注对象</title>
<link rel="stylesheet" href="${APP_PATH}/static/styles/result.css">
<link rel="stylesheet" href="${APP_PATH}/static/styles/detail.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="main">
		<div class="zg-wrap zu-main clearfix ">
			<div class="zm-profile-section-wrap zm-profile-followee-page">
				<div class="zm-profile-section-head">
					<span class="zm-profile-section-name"> <a href="#">${curUser.name}</a>
						关注了 ${followeeCount} 人
					</span>
				</div>
				<div class="zm-profile-section-list">
					<div id="zh-profile-follows-list">
						<div class="zh-general-list clearfix">
							<c:forEach var="vo" items="${followees }">
								<div
									class="zm-profile-card zm-profile-section-item zg-clear no-hovercard">
									<c:if test="${followed == true }">
										<div class="zg-right">
											<button
												class="zg-btn zg-btn-unfollow zm-rich-follow-btn small nth-0
                                    js-follow-user"
												data-status="1" data-id="${vo.extend.user.id}">取消关注</button>
										</div>
									</c:if>
									<c:if test="${followed == false }">
										<div class="zg-right">
											<button
												class="zg-btn zg-btn-follow zm-rich-follow-btn small nth-0
                                    js-follow-user"
												data-id="${vo.extend.user.id}">关注</button>
										</div>
									</c:if>
									<a title="Barty" class="zm-item-link-avatar"
										href="${APP_PATH }/user/${vo.extend.user.id}"> <img
										src="${vo.extend.user.headUrl}" class="zm-item-img-avatar">
									</a>
									<div class="zm-list-content-medium">
										<h2 class="zm-list-content-title">
											<a data-tip="p$t$buaabarty"
												href="${APP_PATH }/user/${vo.extend.user.id}"
												class="zg-link" title="Barty">${vo.extend.user.name}</a>
										</h2>

										<!-- <div class="zg-big-gray">计蒜客教研首席打杂</div> -->
										<div class="details zg-gray">
											<a target="_blank"
												href="${APP_PATH }/user/${vo.extend.user.id}/followers"
												class="zg-link-gray-normal">${vo.extend.followerCount}粉丝</a>
											/ <a target="_blank"
												href="${APP_PATH }/user/${vo.extend.user.id}/followees"
												class="zg-link-gray-normal">${vo.extend.followeeCount}关注</a>
											/ <a target="_blank" href="#" class="zg-link-gray-normal">${vo.extend.commentCount}
												回答</a> / <a target="_blank" href="#" class="zg-link-gray-normal">548
												赞同</a>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
						<a aria-role="button" class="zg-btn-white zu-button-more">更多</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="js.jsp"></jsp:include>
	<script type="text/javascript"
		src="${APP_PATH}/static/scripts/main/site/follow.js"></script>
</body>
</html>