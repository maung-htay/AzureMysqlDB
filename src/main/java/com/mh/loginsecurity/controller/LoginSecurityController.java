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
public class LoginSecurityController {

	@Autowired
	public LoginSecurityRepo myLoginSecurityRepo;
	
	@RequestMapping(value="/login" ,method = RequestMethod.GET)
	public String LoginPageGetMethod(Model model) {
		UserInfo userInfo = new UserInfo();
		model.addAttribute("userInfo", userInfo);
		return "login";
	}
	
	@RequestMapping(value="/login" ,method = RequestMethod.POST)
	public String LoginPagePostMethod(Model model,@ModelAttribute UserInfo userInfo) {
		
		UserInfo dbUser = myLoginSecurityRepo.checkLoginUser(userInfo.getUserName(), userInfo.getUserPassword());
		if(dbUser == null) {
			model.addAttribute("logError","logError");
			return "login";
		}
		
		List<UserInfo> userinfoList = myLoginSecurityRepo.findAll();
		model.addAttribute("userinfoList", userinfoList);
		
		model.addAttribute("loginUserName", dbUser.getUserName());
		return "home";
	}
	
	@RequestMapping(value="/logout")
	public String Logout(Model model) {
		model.addAttribute("userInfo", new UserInfo());
		return "login";
		
	}
	
	
	
	
}
