package com.xl.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplateController {
	
	@RequestMapping("/hello")
	public String helloHtml(Map<String, Object> map) {
		
		System.out.println("template");
		map.put("hello", "value");
		return "/hello.html";
	}
}
