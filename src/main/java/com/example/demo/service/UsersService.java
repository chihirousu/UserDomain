package com.example.demo.service;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.RegisterUser; 
//データベースの操作を行うメソッドを実行するためのサービスのためのインターフェース
public interface UsersService { 
	//ログインフォームに入力されたuser_idとpassがDBに存在するか確認
LoginUser loginCheck(LoginUser loginUser); 
//ログインフォームに入力されたnameとpassがDBに存在するか確認(RegisterUser型を 受け取る)
boolean registerCheck(RegisterUser registerUser); 
//新規会員登録フォームに入力された会員データをDBに保存
boolean createNewAccount(RegisterUser user);
}