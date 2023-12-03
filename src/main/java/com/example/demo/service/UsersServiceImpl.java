package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.RegisterUser;
import com.example.demo.repository.AccountRepository;
//データベースの操作を行うメソッドを実行するためのサービスを提供する 
@Service 
//DIの対象であること
public class UsersServiceImpl implements UsersService{
	//AccoutRepositoryを使用できるようにする
	@Autowired
	AccountRepository repository;
	//ログインフォームに入力されたnameとpassがDBに存在するか確認 
	//DBに存在していれば画面に表示するためのnameを返す
	@Override
	public LoginUser loginCheck(LoginUser loginUser) {
		LoginUser result = repository.findAccount(loginUser);
		System.out.println("名前" + result);
		return result;
	}
	//ログインフォームに入力されたnameとpassがDBに存在するか確認(ResisterUser型 を受け取る)
	@Override
	public boolean registerCheck(RegisterUser registerUser) {
		boolean result = repository.findAccount(registerUser);
		System.out.println("レジスターチェック" + result);
		return result;
//		return false;
	}
	//新規会員登録フォームに入力された会員データをDBに保存
	@Override
	public boolean createNewAccount(RegisterUser registerUser) {
		return false;
//		if(repository.findAccount(registerUser) == true) {
//			return repository.insert(registerUser); 
//	}else {
//		return repository.insert(registerUser);
//		}
	} 
}
