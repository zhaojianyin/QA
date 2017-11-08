package com.nuc.zjy.qa.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.nuc.zjy.qa.intercept.LoginRequredIntercept;
import com.nuc.zjy.qa.intercept.PassportIntercept;

/**
 * @项目名称：QA
 * @类名称：QAWebConf @类描述：
 *
 * @author 赵建银
 * @date 2017年11月7日
 * @time 下午4:41:02
 * @version 1.0
 */
@Component
public class QAWebConf extends WebMvcConfigurerAdapter {

	@Autowired
	PassportIntercept passportIntercept;

	@Autowired
	LoginRequredIntercept loginRequredIntercept;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(passportIntercept);
		registry.addInterceptor(loginRequredIntercept).addPathPatterns("/user/*");// 用户登录页面
		super.addInterceptors(registry);
	}
}
