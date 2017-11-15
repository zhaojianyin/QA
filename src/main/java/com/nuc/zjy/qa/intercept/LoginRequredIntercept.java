package com.nuc.zjy.qa.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nuc.zjy.qa.bean.HostHolder;

/**
 * @项目名称：QA
 * @类名称：LoginRequredIntercept @类描述：登陆过滤，实现跳回原来的界面
 *
 * @author 赵建银
 * @date 2017年11月8日
 * @time 上午9:21:57
 * @version 1.0
 */
@Component
public class LoginRequredIntercept implements HandlerInterceptor {

	@Autowired
	HostHolder hostHolder;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(hostHolder.getUser());
		if (hostHolder.getUser() != null) {
			response.sendRedirect("/relogin?next" + request.getRequestURI());
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
