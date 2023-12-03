package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.entity.LoginUser;
import com.example.demo.service.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("loginUser")

public class LoginController {

	@Autowired
	UsersService userService;

	@Autowired
	HttpSession session;
	//	
	@ModelAttribute("loginUser")
	public LoginUser setLoginUser() {
		return new LoginUser();
	}

	//ログインページに遷移するメソッド
	@GetMapping("show")
	public String loginView() {

				session.removeAttribute("loginUser");
				session.invalidate();
//				sessionStatus.setComplete();

		return "index";
	}
	//新規登録ボタンをクリックした際に、登録画面へ遷移するだけのメソッド
	@GetMapping("register")
	public String getRegisterView() {
		//	(RegisterUser registerUser,
		//			BindingResult bindingResult,Model model) 
		return "index";
	}

	@PostMapping("show")
	public String postShowView() {

		return "index";

	}

	@PostMapping("login")
	public String loginResult(LoginUser loginUser) {
		LoginUser result = userService.loginCheck(loginUser);
		if(result == null) {
			System.out.println("a");
			return "index";
		}
		System.out.println("b");
		return "loginOk";
	}

	@GetMapping("logout")
	public String logoutView() {
		return "logout";
	}

}
