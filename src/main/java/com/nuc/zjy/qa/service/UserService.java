package com.nuc.zjy.qa.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nuc.zjy.qa.bean.LoginTicket;
import com.nuc.zjy.qa.bean.User;
import com.nuc.zjy.qa.dao.LoginTicketDAO;
import com.nuc.zjy.qa.dao.UserDAO;
import com.nuc.zjy.qa.utils.Utils;

/**
 * @项目名称：QA
 * @类名称：UserService @类描述：用户service
 *
 * @author 赵建银
 * @date 2017年11月7日
 * @time 上午10:38:15
 * @version 1.0
 */
@Service
public class UserService {

	@Autowired
	UserDAO userDAO;

	@Autowired
	LoginTicketDAO loginTicketDAO;

	public User getUser(int id) {
		return userDAO.selectByid(id);
	}

	public User getUserByName(String name) {
		return userDAO.selectByName(name);
	}

	/**
	 * 注册用户
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public Map<String, String> register(String name, String password) {
		Map<String, String> map = new HashMap<>();
		if (StringUtils.isEmpty(name)) {
			map.put("msg", "用户名不为空");
			return map;
		}
		if (StringUtils.isEmpty(password)) {
			map.put("msg", "密码不为空");
			return map;
		}
		User user = userDAO.selectByName(name);
		if (user != null) {
			map.put("msg", "用户名存在");
			return map;
		}

		user = new User();
		user.setName(name);
		user.setSalt(UUID.randomUUID().toString().substring(0, 5));
		user.setHeadUrl(String.format("http://images.newcoder.com/head/%dt.png", new Random().nextInt(1000)));
		user.setPassword(Utils.MD5(password + user.getSalt()));
		userDAO.addUser(user);
		user = userDAO.selectByName(name);
		String ticket = addTicket(user.getId());
		map.put("ticket", ticket);

		return map;
	}

	/**
	 * 用户登录
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public Map<String, String> login(String name, String password) {
		Map<String, String> map = new HashMap<>();
		if (StringUtils.isEmpty(name)) {
			map.put("msg", "用户名不为空");
			return map;
		}
		if (StringUtils.isEmpty(password)) {
			map.put("msg", "密码不为空");
			return map;
		}
		User user = userDAO.selectByName(name);
		if (user == null) {
			map.put("msg", "用户名不存在");
			return map;
		}
		if (!Utils.MD5(password + user.getSalt()).equals(user.getPassword())) {
			map.put("msg", "密码错误");
			return map;
		}

		String ticket = addTicket(user.getId());
		map.put("ticket", ticket);
		return map;
	}

	public String addTicket(int userId) {
		LoginTicket loginTicket = new LoginTicket();
		loginTicket.setUserId(userId);
		Date date = new Date();
		date.setTime(3600 * 24 * 100 + date.getTime());// 天
		loginTicket.setExpired(date);
		loginTicket.setStatus(0);
		loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
		loginTicketDAO.addTicket(loginTicket);
		return loginTicket.getTicket();
	}

	/**
	 * 
	 * 退出
	 * 
	 * @param ticket
	 */
	public void loginout(String ticket) {
		loginTicketDAO.updateStatus(ticket, 1);
	}
}
