package com.nuc.zjy.qa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuc.zjy.qa.bean.HostHolder;
import com.nuc.zjy.qa.bean.Message;
import com.nuc.zjy.qa.bean.Msg;
import com.nuc.zjy.qa.bean.User;
import com.nuc.zjy.qa.service.MessageService;
import com.nuc.zjy.qa.service.UserService;

/**
 * @项目名称：QA
 * @类名称：MessageController @类描述：
 *
 * @author 赵建银
 * @date 2017年11月8日
 * @time 下午3:29:08
 * @version 1.0
 */
@Controller
public class MessageController {

	@Autowired
	MessageService messageService;

	@Autowired
	HostHolder hostHolder;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/msg/addMessage", method = RequestMethod.POST)
	@ResponseBody
	public Msg addMessage(@RequestParam("toName") String toName, @RequestParam("content") String content) {
		Message message = new Message();
		Msg msg = new Msg();
		if (hostHolder.getUser() == null) {
			msg.setCode(999);
			return msg.add("msg", "未登录");
		}

		User user = userService.getUserByName(toName);
		if (user == null) {
			msg.setCode(1);
			return msg.add("msg", "不存在");
		}
		message.setContent(content);
		message.setCreateDate(new Date());
		message.setFromId(hostHolder.getUser().getId());
		message.setToId(user.getId());
		message.setHasRead(0);
		msg.setCode(0);
		return msg;
	}

	@RequestMapping(value = "/msg/list")
	public String getConversionList(Model model) {
		if (hostHolder.getUser() == null) {
			return "redirect:/reglogin";
		}
		int localhost = hostHolder.getUser().getId();
		List<Message> conversionList = messageService.getConversionList(localhost);
		List<Msg> conversions = new ArrayList<>();
		for (Message message : conversionList) {
			Msg msg = new Msg();
			msg.add("message", message);
			int tagerid = message.getFromId() == localhost ? message.getToId() : message.getFromId();
			msg.add("user", userService.getUser(tagerid));
			msg.add("unread", messageService.getConversionCount(localhost, message.getConversationId()));
			conversions.add(msg);
		}
		model.addAttribute("conversions", conversions);
		return "letter";
	}

	@RequestMapping(value = "/msg/detail")
	public String getConversionDetail(Model model, @RequestParam("conversationId") String conversationId) {
		List<Message> MessageDetil = messageService.getConversionDetil(conversationId);
		List<Msg> messages = new ArrayList<>();
		for (Message message : MessageDetil) {
			Msg msg = new Msg();
			msg.add("message", message);
			msg.add("user", userService.getUser(message.getFromId()));
			messages.add(msg);
		}
		model.addAttribute("messages", messages);
		return "letterDetail";
	}
}
