package com.gray.springboot.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gray.springboot.model.User;

@RestController
@RequestMapping(value="/index")
public class IndexController {

	@RequestMapping
	public String index()
	{
		return "success";
	}
	
	@RequestMapping(value="get/{userName}/{age}")
	public User get(@PathVariable String userName,@PathVariable int age)
	{
		User u = new User();
		u.setUserName(userName);
		u.setAge(age);
		u.setDate(new Date());
		return u;
	}
	
	@RequestMapping(value="find/{userName}/{age}")
	public Map<String,User> find(@PathVariable String userName,@PathVariable int age)
	{
		Map<String,User> m = new HashMap<>();
		for(int i=0;i<10;i++)
		{
			User u = new User();
			u.setUserName(userName+i);
			u.setAge(age+i);
			u.setDate(new Date());
			m.put("id->"+i, u);
		}
		
		return m;
	}
}
