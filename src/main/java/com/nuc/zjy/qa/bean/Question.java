package com.nuc.zjy.qa.bean;

import java.util.Date;

/**
 * @项目名称：QA
 * @类名称：Question @类描述：
 *
 * @author 赵建银
 * @date 2017年11月7日
 * @time 上午8:41:31
 * @version 1.0
 */
public class Question {
	private int id;
	private String title;
	private String content;
	private Date createDate;
	private int userId;
	private int commentCount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", title=" + title + ", content=" + content + ", createDate=" + createDate
				+ ", userId=" + userId + ", commentCount=" + commentCount + "]";
	}

}
