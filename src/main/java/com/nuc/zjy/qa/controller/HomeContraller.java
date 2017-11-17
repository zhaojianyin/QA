package com.nuc.zjy.qa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nuc.zjy.qa.bean.EntityType;
import com.nuc.zjy.qa.bean.HostHolder;
import com.nuc.zjy.qa.bean.Msg;
import com.nuc.zjy.qa.bean.Question;
import com.nuc.zjy.qa.service.FollowService;
import com.nuc.zjy.qa.service.QuestionService;
import com.nuc.zjy.qa.service.UserService;

/**
 * @项目名称：QA
 * @类名称：HomeContraller @类描述：
 *
 * @author 赵建银
 * @date 2017年11月7日
 * @time 上午10:24:32
 * @version 1.0
 */
@Controller
public class HomeContraller {

	@Autowired
	UserService userService;

	@Autowired
	QuestionService questionService;

	@Autowired
	HostHolder hostHolder;

	@Autowired
	FollowService followService;

	@RequestMapping(path = { "/", "/index" }, method = { RequestMethod.GET })
	public String index(Model model) {
		model.addAttribute("msgs", getQuestions(0));
		return "index";
	}

	@RequestMapping("/user/{userId}")
	public String UserIndex(@PathVariable("userId") int userId, Model model) {
		model.addAttribute("msgs", getQuestions(userId));
		return "index";
	}

	private List<Msg> getQuestions(int userId) {
		List<Question> questions = questionService.getLastQuestions(userId);
		List<Msg> msgs = new ArrayList<>();
		for (Question question : questions) {
			Msg msg = new Msg();
			msg.add("question", question);
			msg.add("count", followService.getFollowerCount(question.getId(), EntityType.ENTITY_QUESTION));
			msg.add("user", userService.getUser(question.getUserId()));
			msgs.add(msg);
		}
		return msgs;
	}

}