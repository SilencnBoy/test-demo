package com.xul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@RequestMapping(value="/index",method={RequestMethod.GET,RequestMethod.POST})
	public String index(Model model){
		return "index";
	}
}
