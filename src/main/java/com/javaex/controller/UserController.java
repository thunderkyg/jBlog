package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user", method = { RequestMethod.GET, RequestMethod.POST })
public class UserController {

	// AutoWired
	@Autowired
	private UserService userService;

	// LoginForm
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("[UserController.loginForm()]");
		
		return "user/loginForm";

	}
	
	// Login
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login()]");
		
		UserVo authUser = userService.getUser(userVo);
		System.out.println(authUser);
		
		if (authUser != null) {
			System.out.println("로그인 성공함");
			session.setAttribute("authUser", authUser);
			return "/main/index";
		} else {
			System.out.println("로그인 실패함");
			return "redirect:/user/loginForm?result=fail";
		}

	}
	
	// Logout
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		
		//Remove Session
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	// JoinForm
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("[UserController.joinForm()]");
		
		return "user/joinForm";
		
	}
	
	// Join
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("[UserController.join()]");
		System.out.println(userVo);
		
		userService.join(userVo);
		
		return "user/joinSuccess";
	}
	
	//Ajax(ID 중복 체크)
	@ResponseBody
	@RequestMapping(value = "/checkId", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean checkId(String id) {
		System.out.println("[UserController.checkId()]");
		System.out.println(id);
		
		return userService.checkId(id);
	}

}
