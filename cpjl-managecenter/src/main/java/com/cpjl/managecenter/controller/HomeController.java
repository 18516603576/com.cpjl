package com.cpjl.managecenter.controller;

 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = "/home", produces = "application/json;charset=UTF-8")
public class HomeController {
	 
	@GetMapping(value = "/index")
	public Object index() { 
		return "home/index"; 
	}

	@GetMapping(value = "/welcome")
	public String welcome() {
		return "home/welcome";
	}

}
