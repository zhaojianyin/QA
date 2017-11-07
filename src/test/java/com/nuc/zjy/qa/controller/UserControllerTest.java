package com.nuc.zjy.qa.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @项目名称：blog
 * @类名称：UserControllerTest @类描述：
 *
 * @author 赵建银
 * @date 2017年10月3日
 * @time 上午9:44:13
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(value = { "classpath:applicationContext.xml",
		"file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml" })
public class UserControllerTest {

	MockMvc mockMvc;

	@Autowired
	WebApplicationContext context;

	@Before
	public void testGetUserInfo() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void test() throws Exception {
		MvcResult andReturn = mockMvc.perform(MockMvcRequestBuilders.get("/userInfo")).andReturn();
		System.out.println(andReturn.getRequest().getAttribute("msgs"));
	}

}
