package com.nuc.zjy.qa.bean;

import java.util.Date;

/**
 * @项目名称：QA
 * @类名称：Message @类描述：
 *
 * @author 赵建银
 * @date 2017年11月8日
 * @time 下午2:56:29
 * @version 1.0
 */
public class Message {
	private int id;
	private int fromId;
	private int toId;
	private String content;
	private Date createDate;
	private int hasRead;
	private String conversationId;

	public Message() {
		super();
	}

	public Message(int id, int fromId, int toId, String content, Date createDate, int hasRead, String conversationId) {
		super();
		this.id = id;
		this.fromId = fromId;
		this.toId = toId;
		this.content = content;
		this.createDate = createDate;
		this.hasRead = hasRead;
		this.conversationId = conversationId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFromId() {
		return fromId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
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

	public int getHasRead() {
		return hasRead;
	}

	public void setHasRead(int hasRead) {
		this.hasRead = hasRead;
	}

	public String getConversationId() {
		if (fromId < toId) {
			conversationId = fromId + "_" + toId;
		} else {
			conversationId = toId + "_" + fromId;
		}
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", fromId=" + fromId + ", toId=" + toId + ", content=" + content + ", createDate="
				+ createDate + ", hasRead=" + hasRead + ", conversationId=" + conversationId + "]";
	}

}
