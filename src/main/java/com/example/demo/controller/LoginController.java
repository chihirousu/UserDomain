package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.RegisterUser;
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
	public String loginView(SessionStatus sessionStatus) {

		session.removeAttribute("loginUser");
		session.invalidate();
		sessionStatus.setComplete();

		return "index";
	}
	//新規登録ボタンをクリックした際に、登録画面へ遷移するだけのメソッド
	@GetMapping("register")
	public String getRegisterView(RegisterUser registerUser) { 
		return "register";
	}
	//新規登録が成功した際に一度ログインページに戻る
	//入力情報を元に既存の登録番号とかぶってないかを確認
	@PostMapping("show")
	public String postShowView(@Validated RegisterUser registerUser,
			BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			return "register";
		}
		boolean result  = userService.registerCheck(registerUser);
		if(result == true) {
			userService.createNewAccount(registerUser);
			return "index";
		}else {
			return "register";
		}
	}

	@PostMapping("login")
	public String loginResult(LoginUser loginUser) {
		LoginUser result = userService.loginCheck(loginUser);
		if(result == null) {
			return "index";
		}
		return "loginOk";
	}

	@GetMapping("logout")
	public String logoutView(LoginUser loginUser) {
		if(loginUser.getName()==null) {
			System.out.println(loginUser);
			return "redirect:show";
		}
		return "logout";
	}

}
