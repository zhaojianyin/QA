package com.nuc.zjy.qa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuc.zjy.qa.bean.EntityType;
import com.nuc.zjy.qa.bean.HostHolder;
import com.nuc.zjy.qa.bean.Msg;
import com.nuc.zjy.qa.service.LikeService;

/**
 * @项目名称：QA
 * @类名称：LikeController @类描述：
 *
 * @author 赵建银
 * @date 2017年11月9日
 * @time 上午10:38:32
 * @version 1.0
 */
@Controller
public class LikeController {

	@Autowired
	LikeService likeService;

	@Autowired
	HostHolder hostHolder;

	@RequestMapping(value = "/like", method = RequestMethod.POST)
	@ResponseBody
	public Msg like(@RequestParam("commentId") int commentId) {
		Msg msg = new Msg();
		if (hostHolder.getUser() == null) {
			msg.setCode(999);
			return msg;
		}
		long likeCount = likeService.like(hostHolder.getUser().getId(), commentId, EntityType.ENTITY_COMMENT);
		msg.setCode(0);
		msg.add("msg", String.valueOf(likeCount));
		return msg;
	}

	@RequestMapping(value = "/dislike", method = RequestMethod.POST)
	@ResponseBody
	public Msg dislike(@RequestParam("commentId") int commentId) {
		Msg msg = new Msg();
		if (hostHolder.getUser() == null) {
			msg.setCode(999);
			return msg;
		}

		long likeCount = likeService.dislike(hostHolder.getUser().getId(), commentId, EntityType.ENTITY_COMMENT);
		msg.setCode(0);
		return msg.add("msg", String.valueOf(likeCount));
	}

}
