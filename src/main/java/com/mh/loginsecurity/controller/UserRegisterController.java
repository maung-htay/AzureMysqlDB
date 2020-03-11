package com.mh.loginsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mh.loginsecurity.model.UserInfo;
import com.mh.loginsecurity.repository.LoginSecurityRepo;

@Controller
public class UserRegisterController {

	@Autowired
	public LoginSecurityRepo myLoginSecurityRepo;
	
	@RequestMapping(value="/user_register",method = RequestMethod.GET)
	public String UserRegisterGetMethod(Model model) {
		
		model.addAttribute("userInfo", new UserInfo());
		return "user_register";
		
	}
	
	@RequestMapping(value="/user_register", params = "register",method = RequestMethod.POST)
	public String UserRegisterPostMethod(Model model,@ModelAttribute UserInfo userInfo) {
		if(userInfo.getUserName().trim().equals("") && userInfo.getUserPassword().trim().equals("")) {
			model.addAttribute("registerError","registerError");
			return "user_register";
		}
		myLoginSecurityRepo.save(userInfo);
		return "login";
	}
	
	@RequestMapping(value="/user_register", params = "cancel",method = RequestMethod.POST)
	public String UserRegisterPostMethodCancel(Model model,@ModelAttribute UserInfo userInfo) {
		model.addAttribute("userInfo", new UserInfo());
		return "login";
	}
	
}
