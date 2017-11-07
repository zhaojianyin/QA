package com.nuc.zjy.qa.intercept;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nuc.zjy.qa.bean.HostHolder;
import com.nuc.zjy.qa.bean.LoginTicket;
import com.nuc.zjy.qa.bean.User;
import com.nuc.zjy.qa.dao.LoginTicketDAO;
import com.nuc.zjy.qa.dao.UserDAO;

/**
 * @项目名称：QA
 * @类名称：PassportIntercept @类描述：
 *
 * @author 赵建银
 * @date 2017年11月7日
 * @time 下午4:13:40
 * @version 1.0
 */
@Component
public class PassportIntercept implements HandlerInterceptor {

	@Autowired
	LoginTicketDAO loginTicketDAO;

	@Autowired
	UserDAO userDao;

	@Autowired
	HostHolder hostHolder;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String ticket = null;
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("ticket")) {
					ticket = cookie.getValue();
					break;
				}
			}
		}
		if (ticket != null) {
			LoginTicket loginTicket = loginTicketDAO.selectTicket(ticket);
			if (loginTicket == null || loginTicket.getExpired().before(new Date()) || loginTicket.getStatus() != 0) {
				return true;
			}

			User user = userDao.selectByid(loginTicket.getId());
			hostHolder.setUser(user);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			modelAndView.addObject("user", hostHolder.getUser());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		hostHolder.clear();
	}

}
