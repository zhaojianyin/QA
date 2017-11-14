package com.nuc.zjy.qa.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nuc.zjy.qa.service.UserService;

/**
 * @项目名称：QA
 * @类名称：LoginController @类描述：
 *
 * @author 赵建银
 * @date 2017年11月7日
 * @time 上午11:31:50
 * @version 1.0
 */
@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@RequestMapping(path = { "/reg" }, method = RequestMethod.POST)
	public String reg(Model model, @RequestParam("password") String password, @RequestParam("name") String name,
			@RequestParam(value = "rememberme", defaultValue = "false") boolean remeberme, HttpServletResponse response,
			@RequestParam(value = "next", required = false) String next) {
		Map<String, String> map = userService.register(name, password);
		if (map.containsKey("ticket")) {
			Cookie cookie = new Cookie("ticket", map.get("ticket"));
			cookie.setPath("/");
			if (remeberme) {
				cookie.setMaxAge(3600 * 24 * 5);
			}
			response.addCookie(cookie);
			if (StringUtils.isEmpty(next)) {
				return "redirect:" + next;
			}
		} else {
			model.addAttribute("msg", map.get("msg"));
			return "login";
		}
		return "redirect:/";
	}

	@RequestMapping(path = { "/login" }, method = RequestMethod.POST)
	public String login(Model model, @RequestParam("password") String password, @RequestParam("name") String name,
			@RequestParam(value = "rememberme", defaultValue = "false") boolean remeberme, HttpServletResponse response,
			@RequestParam(value = "next", required = false) String next) {
		Map<String, String> map = userService.login(name, password);
		if (map.containsKey("ticket")) {
			Cookie cookie = new Cookie("ticket", map.get("ticket"));
			cookie.setPath("/");
			if (remeberme) {
				cookie.setMaxAge(3600 * 24 * 5);
			}
			response.addCookie(cookie);
			if (StringUtils.isEmpty(next)) {
				return "redirect:" + next;
			}
		} else {
			model.addAttribute("msg", map.get("msg"));
			return "login";
		}
		return "redirect:/";
	}

	@RequestMapping(path = { "/logout" }, method = RequestMethod.GET)
	public String loginout(@CookieValue("ticket") String ticket) {
		userService.loginout(ticket);
		return "redirect:/";
	}

	@RequestMapping(path = { "/reglogin" }, method = RequestMethod.GET)
	public String reglogin(Model model, @RequestParam(value = "next", required = false) String next) {
		model.addAttribute("next", next);
		return "login";
	}
}
