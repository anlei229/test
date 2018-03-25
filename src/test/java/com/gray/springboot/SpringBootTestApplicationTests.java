package com.gray.springboot;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Matchers.contains;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Contains;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.gray.springboot.controller.IndexController;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootTestApplicationTests {

	private MockMvc mvc;
	
	@Before
	public void before()
	{
		this.mvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
	}
	@Test
	public void contextLoads() throws Exception {
		RequestBuilder req=get("/index/get/张三/11");

		mvc.perform(req).andExpect(status().isOk()).andExpect(jsonPath("age").value("11"));
	}

}
