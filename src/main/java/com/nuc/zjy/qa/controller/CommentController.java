package com.nuc.zjy.qa.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nuc.zjy.qa.bean.Comment;
import com.nuc.zjy.qa.bean.EntityType;
import com.nuc.zjy.qa.bean.HostHolder;
import com.nuc.zjy.qa.service.CommentService;
import com.nuc.zjy.qa.service.QuestionService;
import com.nuc.zjy.qa.utils.Utils;

/**
 * @项目名称：QA
 * @类名称：CommentController @类描述：
 *
 * @author 赵建银
 * @date 2017年11月8日
 * @time 上午11:03:48
 * @version 1.0
 */
@Controller
public class CommentController {

	@Autowired
	HostHolder hostHolder;

	@Autowired
	CommentService commentService;

	@Autowired
	QuestionService questionService;

	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	public String addComment(@RequestParam("questionId") int questionId, @RequestParam("content") String content) {
		Comment comment = new Comment();
		comment.setContent(content);
		if (hostHolder.getUser() != null) {
			comment.setUserId(hostHolder.getUser().getId());
		} else {
			comment.setUserId(Utils.USER_XX);
		}
		comment.setCreateDate(new Date());
		comment.setEntityId(questionId);
		comment.setEntityType(EntityType.ENTITY_QUESTION);
		commentService.addComment(comment);
		int count = commentService.getCommentCount(comment.getEntityId(), comment.getEntityType());
		questionService.updatequestionCommentCount(comment.getEntityId(), count);
		return "redirect:/question/" + questionId;
	}

}
