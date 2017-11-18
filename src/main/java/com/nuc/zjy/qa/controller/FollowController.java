package com.nuc.zjy.qa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuc.zjy.qa.bean.EntityType;
import com.nuc.zjy.qa.bean.HostHolder;
import com.nuc.zjy.qa.bean.Msg;
import com.nuc.zjy.qa.bean.Question;
import com.nuc.zjy.qa.bean.User;
import com.nuc.zjy.qa.service.CommentService;
import com.nuc.zjy.qa.service.FollowService;
import com.nuc.zjy.qa.service.QuestionService;
import com.nuc.zjy.qa.service.UserService;

/**
 * @项目名称：QA
 * @类名称：FollowController @类描述：关注
 *
 * @author 赵建银
 * @date 2017年11月16日
 * @time 上午8:39:15
 * @version 1.0
 */
@Controller
public class FollowController {

	@Autowired
	FollowService followService;

	@Autowired
	QuestionService questionService;

	@Autowired
	HostHolder hostHolder;

	@Autowired
	UserService userService;

	@Autowired
	CommentService commentService;

	@RequestMapping(value = "/followUser")
	@ResponseBody
	public Msg follow(@RequestParam("userId") int userId) {
		Msg msg = new Msg();
		if (hostHolder.getUser() == null) {
			msg.setCode(999);
			return msg;
		}
		boolean ret = followService.follow(hostHolder.getUser().getId(), userId, EntityType.ENTITY_USER);
		msg.setCode(ret ? 0 : 1);
		msg.add("msg", String.valueOf(followService.getFolloweeCount(userId, EntityType.ENTITY_USER)));
		return msg;

	}

	@RequestMapping(value = "/unfollowUser")
	@ResponseBody
	public Msg unfollow(@RequestParam("userId") int userId) {
		Msg msg = new Msg();
		if (hostHolder.getUser() == null) {
			msg.setCode(999);
			return msg;
		}

		boolean ret = followService.unfollow(hostHolder.getUser().getId(), userId, EntityType.ENTITY_USER);
		msg.setCode(ret ? 0 : 1);
		msg.add("msg", String.valueOf(followService.getFolloweeCount(userId, EntityType.ENTITY_USER)));
		return msg;

	}

	@RequestMapping(value = "/followQuestion")
	@ResponseBody
	public Msg followQuestion(@RequestParam("questionId") int questionId) {
		Msg msg = new Msg();
		if (hostHolder.getUser() == null) {
			msg.setCode(999);
			return msg;
		}

		Question question = questionService.getById(questionId);
		if (question == null) {
			msg.setCode(1);
			msg.add("msg", "问题不存在");
			return msg;
		}

		boolean ret = followService.follow(hostHolder.getUser().getId(), questionId, EntityType.ENTITY_QUESTION);
		Map<String, Object> map = new HashMap<>();
		map.put("headUrl", hostHolder.getUser().getHeadUrl());
		map.put("name", hostHolder.getUser().getName());
		map.put("id", hostHolder.getUser().getId());
		map.put("count", followService.getFollowerCount(questionId, EntityType.ENTITY_QUESTION));
		msg.setCode(ret ? 0 : 1);
		msg.add("msg", map);
		return msg;

	}

	@RequestMapping(value = "/unfollowQuestion")
	@ResponseBody
	public Msg unfollowQuestion(@RequestParam("questionId") int questionId) {
		Msg msg = new Msg();
		if (hostHolder.getUser() == null) {
			msg.setCode(999);
			return msg;
		}
		Question question = questionService.getById(questionId);
		if (question == null) {
			msg.setCode(1);
			msg.add("msg", "问题不存在");
			return msg;
		}
		boolean ret = followService.unfollow(hostHolder.getUser().getId(), questionId, EntityType.ENTITY_QUESTION);
		Map<String, Object> map = new HashMap<>();
		map.put("headUrl", hostHolder.getUser().getHeadUrl());
		map.put("name", hostHolder.getUser().getName());
		map.put("id", hostHolder.getUser().getId());
		map.put("count", followService.getFollowerCount(questionId, EntityType.ENTITY_QUESTION));
		msg.setCode(ret ? 0 : 1);
		msg.add("msg", map);
		return msg;
	}

	@RequestMapping(path = { "/user/{uid}/followers" }, method = { RequestMethod.GET })
	public String followers(Model model, @PathVariable("uid") int userId) {
		List<Integer> followerIds = followService.getFollowers(userId, EntityType.ENTITY_USER, 10);
		if (hostHolder.getUser() != null) {
			model.addAttribute("followers", getUsersInfo(hostHolder.getUser().getId(), followerIds));
		} else {
			model.addAttribute("followers", getUsersInfo(0, followerIds));
		}
		model.addAttribute("followerCount", followService.getFollowerCount(userId, EntityType.ENTITY_USER));
		model.addAttribute("curUser", userService.getUser(userId));
		return "followers";
	}

	
	@RequestMapping(path = { "/user/{uid}/followees" }, method = { RequestMethod.GET })
	public String followees(Model model, @PathVariable("uid") int userId) {
		List<Integer> followerIds = followService.getFollowees(userId, EntityType.ENTITY_USER, 10);
		if (hostHolder.getUser() != null) {
			model.addAttribute("followees", getUsersInfo(hostHolder.getUser().getId(), followerIds));
		} else {
			model.addAttribute("followees", getUsersInfo(0, followerIds));
		}
		model.addAttribute("followeeCount", followService.getFolloweeCount(userId, EntityType.ENTITY_USER));
		model.addAttribute("curUser", userService.getUser(userId));
		return "followees";
	}
	
	private List<Msg> getUsersInfo(int localUserId, List<Integer> userIds) {
		List<Msg> userInfos = new ArrayList<Msg>();
		for (Integer uid : userIds) {
			User user = userService.getUser(uid);
			if (user == null) {
				continue;
			}
			Msg vo = new Msg();
			vo.add("user", user);
			vo.add("commentCount", commentService.getUserCommentCount(uid));
			vo.add("followerCount", followService.getFollowerCount(uid, EntityType.ENTITY_USER));
			vo.add("followeeCount", followService.getFolloweeCount(uid, EntityType.ENTITY_USER));
			if (localUserId != 0) {
				vo.add("followed", followService.isFollower(localUserId, uid, EntityType.ENTITY_USER));
			} else {
				vo.add("followed", false);
			}
			userInfos.add(vo);
		}
		return userInfos;
	}
}
