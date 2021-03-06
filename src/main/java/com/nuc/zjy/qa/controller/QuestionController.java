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
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuc.zjy.qa.bean.Comment;
import com.nuc.zjy.qa.bean.EntityType;
import com.nuc.zjy.qa.bean.HostHolder;
import com.nuc.zjy.qa.bean.Msg;
import com.nuc.zjy.qa.bean.Question;
import com.nuc.zjy.qa.bean.User;
import com.nuc.zjy.qa.service.CommentService;
import com.nuc.zjy.qa.service.FollowService;
import com.nuc.zjy.qa.service.LikeService;
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

	@Autowired
	LikeService likeService;

	@Autowired
	FollowService followService;

	@RequestMapping(value = "/question/add", method = RequestMethod.POST)
	@ResponseBody
	public Msg addQuestion(@RequestParam("title") String title, @RequestParam("content") String content) {
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
		Msg msg = new Msg();
		if (questionService.addQuestion(question) > 0) {
			msg.setCode(0);
			msg.setMsg("ok");
			return msg;
		}
		msg.setCode(1);
		return msg;
	}

	@RequestMapping(value = "/question/{qid}", method = RequestMethod.GET)
	public String questionDetail(Model model, @PathVariable("qid") int qid) {
		Question question = questionService.getById(qid);
		List<Comment> commentsList = commentService.getCommentByEntity(qid, EntityType.ENTITY_QUESTION);
		model.addAttribute("question", question);
		if (hostHolder.getUser() == null) {
			model.addAttribute("followed", false);
		} else {
			model.addAttribute("followed",
					followService.isFollower(hostHolder.getUser().getId(), qid, EntityType.ENTITY_QUESTION));
		}

		// 关注用户
		List<Msg> followers = new ArrayList<>();

		List<Integer> followers2 = followService.getFollowers(qid, EntityType.ENTITY_QUESTION, 20);
		
		for (Integer integer : followers2) {
			Msg msg = new Msg();
			User user = userService.getUser(integer);
			if (user == null) {
				continue;
			}
			msg.add("name", user.getName());
			msg.add("headUrl", user.getHeadUrl());
			msg.add("id", user.getId());
			followers.add(msg);
		}
		model.addAttribute("followers", followers);

		List<Msg> comments = new ArrayList<>();
		for (Comment comment : commentsList) {
			Msg msg = new Msg();
			if (hostHolder.getUser() == null) {
				msg.add("liked", 0);
			} else {
				msg.add("liked", likeService.getLikeStatus(hostHolder.getUser().getId(), comment.getId(),
						EntityType.ENTITY_COMMENT));
			}
			msg.add("comment", comment);
			msg.add("likeCount", likeService.getLikeCount(comment.getId(), EntityType.ENTITY_COMMENT));
			msg.add("user", userService.getUser(comment.getUserId()));
			comments.add(msg);
		}
		model.addAttribute("comments", comments);
		return "detail";

	}

}
