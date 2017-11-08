package com.nuc.zjy.qa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nuc.zjy.qa.bean.Comment;
import com.nuc.zjy.qa.bean.EntityType;
import com.nuc.zjy.qa.bean.HostHolder;
import com.nuc.zjy.qa.bean.Msg;
import com.nuc.zjy.qa.bean.Question;
import com.nuc.zjy.qa.service.CommentService;
import com.nuc.zjy.qa.service.QuestionService;
import com.nuc.zjy.qa.service.UserService;
import com.nuc.zjy.qa.utils.Utils;

/**
 * @项目名称：QA
 * @类名称：QuestionContraller @类描述：
 *
 * @author 赵建银
 * @date 2017年11月8日
 * @time 上午10:22:13
 * @version 1.0
 */
@Controller
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@Autowired
	HostHolder hostHolder;

	@Autowired
	CommentService commentService;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/question/add", method = RequestMethod.POST)
	public void addQuestion(@RequestParam("title") String title, @RequestParam("content") String content) {
		Question question = new Question();
		question.setTitle(title);
		question.setContent(content);
		question.setCommentCount(0);
		question.setCreateDate(new Date());
		if (hostHolder.getUser() != null) {
			question.setUserId(hostHolder.getUser().getId());
		} else {
			question.setUserId(Utils.USER_XX);
		}
		questionService.addQuestion(question);
	}

	@RequestMapping(value = "/question/{qid}", method = RequestMethod.GET)
	public String questionDetail(Model model, @PathVariable("qid") int qid) {
		Question question = questionService.getById(qid);
		List<Comment> commentsList = commentService.getCommentByEntity(qid, EntityType.ENTITY_QUESTION);
		model.addAttribute("question", question);
		List<Msg> comments = new ArrayList<>();
		for (Comment comment : commentsList) {
			Msg msg = new Msg();
			msg.add("comment", comment);
			msg.add("user", userService.getUser(comment.getUserId()));
			comments.add(msg);
		}
		model.addAttribute("comments", comments);
		return "xiangqingjiemian";

	}

}
