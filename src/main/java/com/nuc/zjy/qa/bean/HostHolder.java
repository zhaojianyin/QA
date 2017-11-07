package com.nuc.zjy.qa.bean;

import org.springframework.stereotype.Component;

/**
 * @项目名称：QA
 * @类名称：HostHolder @类描述：
 *
 * @author 赵建银
 * @date 2017年11月7日
 * @time 下午4:33:22
 * @version 1.0
 */
@Component
public class HostHolder {
	private static ThreadLocal<User> users = new ThreadLocal<User>();

	public User getUser() {
		return users.get();
	}

	public void clear() {
		users.remove();
	}

	public void setUser(User user) {
		users.set(user);
	}

}
