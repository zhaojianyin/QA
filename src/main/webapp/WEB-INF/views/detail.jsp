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
<title>问题详情</title>
<link rel="stylesheet" href="${APP_PATH }/static/styles/detail.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="zg-wrap zu-main clearfix with-indention-votebar"
		itemscope="" itemtype="http://schema.org/Question"
		id="zh-single-question-page" data-urltoken="36301524" role="main">
		<div class="zu-main-content">
			<div class="zu-main-content-inner">
				<meta itemprop="isTopQuestion" content="false">
				<meta itemprop="visitsCount" content="402">
				<div id="zh-question-title" data-editable="true"
					class="zm-editable-status-normal">
					<h2 class="zm-item-title">
						<span class="zm-editable-content">${question.title}</span>
					</h2>
				</div>
				<div id="zh-question-detail"
					class="zm-item-rich-text zm-editable-status-normal">
					<div class="zm-editable-content">${question.content}</div>
				</div>
				<div class="zm-side-section">
					<div class="zm-side-section-inner"
						id="zh-question-side-header-wrap">
						<c:if test="${followed == true}">
							<button
								class="follow-button zg-follow zg-btn-white js-follow-question"
								data-id="${question.id}" data-status="1">取消关注</button>
						</c:if>
						<c:if test="${followed == false}">
							<button
								class="follow-button zg-follow zg-btn-green js-follow-question"
								data-id="${question.id}">关注问题</button>
						</c:if>
						<div class="zh-question-followers-sidebar">
							<div class="zg-gray-normal">
								<a href="javascript:void(0);"><strong class="js-user-count">${followers.size()}</strong></a>人关注该问题
							</div>
							<div class="list zu-small-avatar-list zg-clear js-user-list">
							
								<c:forEach var="vo" items="${followers }">
									<a class="zm-item-link-avatar js-user-${vo.extend.id}"
										href="${APP_PATH }/user/${vo.extend.id}" data-original_title="${vo.extend.name}"> <img
										src="${vo.extend.headUrl}" class="zm-item-img-avatar"></a>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<div id="zh-question-answer-wrap" data-pagesize="10"
					class="zh-question-answer-wrapper navigable"
					data-widget="navigable"
					data-navigable-options="{&quot;items&quot;: &quot;&gt;.zm-item-answer&quot;}"
					data-init="{&quot;params&quot;: {&quot;url_token&quot;: 36301524, &quot;pagesize&quot;: 10, &quot;offset&quot;: 0}, &quot;nodename&quot;: &quot;QuestionAnswerListV2&quot;}">
					<c:forEach items="${comments}" var="comment">
						<div class="zm-item-answer  zm-item-expanded js-comment">
							<link itemprop="url" href="">
							<meta itemprop="answer-id" content="22162611">
							<meta itemprop="answer-url-token" content="66862039">
							<a class="zg-anchor-hidden" name="answer-22162611"></a>
							<div class="zm-votebar goog-scrollfloater js-vote"
								data-id="${comment.extend.comment.id}">
								<c:if test="${comment.extend.liked > 0}">
									<button class="up js-like pressed" title="赞同">
										<i class="icon vote-arrow"></i> <span
											class="count js-voteCount">${comment.extend.likeCount}</span>
										<span class="label sr-only">赞同</span>
									</button>
								</c:if>
								<c:if test="${comment.extend.liked <= 0}">
									<button class="up js-like" title="赞同">
										<i class="icon vote-arrow"></i> <span
											class="count js-voteCount">${comment.extend.likeCount}</span>
										<span class="label sr-only">赞同</span>
									</button>
								</c:if>

								<c:if test="${comment.extend.liked < 0}">
									<button class="down js-dislike pressed" title="反对，不会显示你的姓名">
										<i class="icon vote-arrow"></i> <span class="label sr-only">反对，不会显示你的姓名</span>
									</button>
								</c:if>
								<c:if test="${comment.extend.liked >= 0}">
									<button class="down js-dislike" title="反对，不会显示你的姓名">
										<i class="icon vote-arrow"></i> <span class="label sr-only">反对，不会显示你的姓名</span>
									</button>
								</c:if>
							</div>
							<div class="answer-head">
								<div class="zm-item-answer-author-info">
									<a class="zm-item-link-avatar avatar-link" href=""
										target="_blank" data-tip="p$t$yingxiaodao"> <img
										src="${comment.extend.user.headUrl}"
										class="zm-list-avatar avatar"></a> <a class="author-link"
										data-tip="p$t$yingxiaodao" target="_blank" href="">${comment.extend.user.name}</a>
								</div>
								<div class="zm-item-vote-info">
									<span class="voters text"> <a href="" class="more text"><span
											class="js-voteCount">${comment.extend.likeCount}</span>&nbsp;人赞同</a>
									</span>
								</div>
							</div>
							<div class="zm-item-rich-text expandable js-collapse-body"
								data-resourceid="6727688" data-action="/answer/content"
								data-author-name="营销岛"
								data-entry-url="/question/36301524/answer/66862039">
								<div class="zm-editable-content clearfix">
									${comment.extend.comment.content}</div>
							</div>
							<a class="zg-anchor-hidden ac" name="22162611-comment"></a>
							<div
								class="zm-item-meta answer-actions clearfix js-contentActions">
								<div class="zm-meta-panel">
									<a itemprop="url" class="answer-data-link meta-item"
										target="_blank" href="">发布于 <fmt:formatDate
											value="${comment.extend.comment.createDate }"
											timeStyle="yyyy-MM-dd" /></a> <a href="" name="addcomment"
										class="meta-item toggle-comment js-toggleCommentBox"> <i
										class="z-icon-comment"></i>0 条评论
									</a>

									<button class="item-collapse js-collapse"
										style="transition: none;">
										<i class="z-icon-fold"></i>收起
									</button>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<a name="draft"></a>
				<form action="${APP_PATH}/addComment" method="post">
					<input type="hidden" name="questionId" value="${question.id}" />
					<div id="zh-question-answer-form-wrap"
						class="zh-question-answer-form-wrap">
						<div class="zm-editable-editor-wrap" style="">
							<div class="zm-editable-editor-outer">
								<div class="zm-editable-editor-field-wrap">
									<textarea name="content" id="content"
										class="zm-editable-editor-field-element editable"
										style="width: 100%;"></textarea>
								</div>
							</div>

							<div class="zm-command clearfix">
								<span class=" zg-right">
									<button type="submit" class="submit-button zg-btn-blue">发布回答</button>
								</span>
							</div>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>
	<jsp:include page="js.jsp"></jsp:include>
	<script type="text/javascript"
		src="${APP_PATH}/static/scripts/main/site/detail.js"></script>
</body>
</html>