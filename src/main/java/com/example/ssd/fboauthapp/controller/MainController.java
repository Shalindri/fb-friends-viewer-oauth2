package com.example.ssd.fboauthapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String index(){
		return "index.html";
	}
	
	@RequestMapping("/oauthlogin")
	public String index(@RequestParam("code") String code){
		return "index.html";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "index.html";
	}
	
	@RequestMapping("/main")
	public String main(){
		return "index.html";
	}
	

}
