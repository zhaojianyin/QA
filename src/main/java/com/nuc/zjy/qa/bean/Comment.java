package com.nuc.zjy.qa.bean;

import java.util.Date;

/**
 * @项目名称：QA
 * @类名称：Comment @类描述：评论
 *
 * @author 赵建银
 * @date 2017年11月8日
 * @time 上午10:41:53
 * @version 1.0
 */
public class Comment {
	private int id;
	private int userId;
	private int entityId;
	private int entityType;
	private String content;
	private Date createDate;
	private int status;

	public Comment() {
		super();
	}

	public Comment(int id, int userId, int entityId, int entityType, String content, Date createDate, int status) {
		super();
		this.id = id;
		this.userId = userId;
		this.entityId = entityId;
		this.entityType = entityType;
		this.content = content;
		this.createDate = createDate;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public int getEntityType() {
		return entityType;
	}

	public void setEntityType(int entityType) {
		this.entityType = entityType;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", userId=" + userId + ", entityId=" + entityId + ", entityType=" + entityType
				+ ", content=" + content + ", createDate=" + createDate + ", status=" + status + "]";
	}

}
