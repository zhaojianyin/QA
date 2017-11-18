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
<title>个人</title>
<link rel="stylesheet" href="${APP_PATH }/static/styles/index.css">
<link rel="stylesheet" href="${APP_PATH }/static/styles/detail.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="zg-wrap zu-main clearfix " role="main">
		<div class="zm-profile-section-wrap zm-profile-followee-page">
			<div class="zm-profile-section-list">
				<div id="zh-profile-follows-list">
					<div class="zh-general-list clearfix">
						<div
							class="zm-profile-card zm-profile-section-item zg-clear no-hovercard">
							<div class="zg-right">
								<c:if test="${profileUser.extend.followed == true }">
									<button
										class="zg-btn zg-btn-unfollow zm-rich-follow-btn small nth-0
                                    js-follow-user"
										data-status="1" data-id="${profileUser.extend.user.id}">取消关注</button>
								</c:if>
								<c:if test="${profileUser.extend.followed == false }">
									<button
										class="zg-btn zg-btn-follow zm-rich-follow-btn small nth-0
                                    js-follow-user"
										data-id="${profileUser.extend.user.id}">关注</button>
								</c:if>
							</div>
							<a title="Barty" class="zm-item-link-avatar"
								href="${APP_PATH }/user/${profileUser.extend.user.id}"> <img
								src="${profileUser.extend.user.headUrl}"
								class="zm-item-img-avatar">
							</a>
							<div class="zm-list-content-medium">
								<h2 class="zm-list-content-title">
									<a data-tip="p$t$buaabarty"
										href="${APP_PATH }/user/${profileUser.extend.user.id}"
										class="zg-link">${profileUser.extend.user.name}</a>
								</h2>

								<div class="details zg-gray">
									<a target="_blank"
										href="${APP_PATH }/user/${profileUser.extend.user.id}/followers"
										class="zg-link-gray-normal">${profileUser.extend.followerCount}粉丝</a>
									/ <a target="_blank"
										href="${APP_PATH }/user/${profileUser.extend.user.id}/followees"
										class="zg-link-gray-normal">${profileUser.extend.followeeCount}关注</a>
									/ <a target="_blank" href="#" class="zg-link-gray-normal">${profileUser.extend.commentCount}
										回答</a> / <a target="_blank" href="#" class="zg-link-gray-normal">548
										赞同</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="zu-main-content">
			<div class="zu-main-content-inner">
				<div class="zg-section" id="zh-home-list-title">
					<i class="zg-icon zg-icon-feedlist"></i>最新动态 <input type="hidden"
						id="is-topstory"> <span
						class="zg-right zm-noti-cleaner-setting" style="list-style: none">
						<a href="" class="zg-link-gray-normal"> <i
							class="zg-icon zg-icon-settings"></i>设置
					</a>
					</span>
				</div>
				<c:forEach items="${msgs}" var="msg">
					<div class="zu-main-feed-con navigable" data-feedtype="topstory"
						id="zh-question-list" data-widget="navigable"
						data-navigable-options="{&quot;items&quot;:&quot;&gt; .zh-general-list .feed-content&quot;,&quot;offsetTop&quot;:-82}">
						<a href="javascript:;" class="zu-main-feed-fresh-button"
							id="zh-main-feed-fresh-button" style="display: none"></a>
						<div id="js-home-feed-list"
							class="zh-general-list topstory clearfix"
							data-init="{&quot;params&quot;: {}, &quot;nodename&quot;: &quot;TopStory2FeedList&quot;}"
							data-delayed="true" data-za-module="TopStoryFeedList">

							<div class="feed-item folding feed-item-hook feed-item-2"
								feed-item-a="" data-type="a" id="feed-2"
								data-za-module="FeedItem" data-za-index="">
								<div class="feed-item-inner">
									<div class="avatar">
										<a title="${msg.extend.user.name}" data-tip="p$t$amuro1230"
											class="zm-item-link-avatar" target="_blank"
											href="${APP_PATH }/user/${msg.extend.user.id}"> <img
											src="${msg.extend.user.headUrl}" class="zm-item-img-avatar"></a>
									</div>
									<div class="feed-main">
										<div class="feed-content" data-za-module="AnswerItem">
											<meta itemprop="answer-id" content="389034">
											<meta itemprop="answer-url-token" content="13174385">
											<h2 class="feed-title">
												<a class="question_link" target="_blank"
													href="${APP_PATH}/question/${msg.extend.question.id}">${msg.extend.question.title}</a>
											</h2>

											<div class="feed-question-detail-item">
												<div class="question-description-plain zm-editable-content"></div>
											</div>
											<div class="expandable entry-body">
												<div class="zm-item-vote">
													<a class="zm-item-vote-count js-expand js-vote-count"
														href="javascript:;" data-bind-votecount="">${msg.extend.count}</a>
												</div>
												<div class="zm-item-answer-author-info">
													<fmt:formatDate value="${msg.extend.question.createDate}"
														timeStyle="yyyy-MM-dd" />
												</div>
												<div class="zm-item-rich-text expandable js-collapse-body">
													<div class="zh-summary summary clearfix">${msg.extend.question.content}</div>
												</div>
											</div>
											<div class="feed-meta">
												<div
													class="zm-item-meta answer-actions clearfix js-contentActions">
													<div class="zm-meta-panel">
														<a href="#" name="addcomment"
															class="meta-item toggle-comment js-toggleCommentBox">
															<i class="z-icon-comment"></i>${msg.extend.question.commentCount}条评论
														</a>
														<button class="meta-item item-collapse js-collapse">
															<i class="z-icon-fold"></i>收起
														</button>
													</div>
												</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
				</c:forEach>
				<a href="javascript:;" id="zh-load-more" data-method="next"
					class="zg-btn-white zg-r3px zu-button-more" style="">更多</a>
			</div>
		</div>
	</div>
	<jsp:include page="js.jsp"></jsp:include>
	<script type="text/javascript"
		src="${APP_PATH }/static/scripts/main/site/profile.js"></script>
</body>
</html>