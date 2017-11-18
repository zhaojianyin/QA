package com.nuc.zjy.qa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.nuc.zjy.qa.bean.Comment;
import com.nuc.zjy.qa.dao.CommentDAO;

/**
 * @项目名称：QA
 * @类名称：CommentService @类描述：评论service
 *
 * @author 赵建银
 * @date 2017年11月8日
 * @time 上午10:55:12
 * @version 1.0
 */

@Service
public class CommentService {
	@Autowired
	CommentDAO commentDAO;

	@Autowired
	Sensitive sensitive;

	/**
	 * 获取实体的所有评论
	 * 
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	public List<Comment> getCommentByEntity(int entityId, int entityType) {
		return commentDAO.selectCommentByEntity(entityId, entityType);
	}

	/**
	 * 添加评论
	 * 
	 * @param comment
	 */
	public void addComment(Comment comment) {
		comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
		comment.setContent(sensitive.filter(comment.getContent()));
		commentDAO.addComment(comment);
	}

	/**
	 * 
	 * 获取评论个数
	 * 
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	public int getCommentCount(int entityId, int entityType) {
		return commentDAO.getCommentCount(entityId, entityType);
	}

	/**
	 * 
	 * 删除评论
	 * 
	 * @param commentId
	 */
	public void deleteComment(int commentId) {
		commentDAO.updateStatus(1, commentId);
	}

	public Object getUserCommentCount(Integer uid) {
		return null;
	}
}
