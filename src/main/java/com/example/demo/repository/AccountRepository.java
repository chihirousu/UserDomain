package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.RegisterUser;

@Repository 
//SpringのDIコンテナに登録 忘れるとサーバー動かない 
public class AccountRepository {
	@Autowired
	//JdbcTemplateクラスのインスタンスの注入 
	JdbcTemplate jdbcTemplate;


	//ログインフォームに入力したnameとpassが、DBに存在するかを確認するメソッド 
	//DBに該当するレコードがあった場合、そのレコードのname列を返す
	public LoginUser findAccount(LoginUser loginUser) {
		String sql = "SELECT NAME, PASSWORD FROM USERS WHERE NAME = ? AND PASSWORD = ?";
		//queryForListのメソッドの実行結果は<カラム名,値>のリストに格納される 
		//一致するレコードなかった場合はresultList = [];になる
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList
				(sql, loginUser.getName(),loginUser.getPass());
		System.out.println("resultList" + resultList);

		//		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, "さとう","1234");
		if (resultList != null) {
			for (Map<String, Object> result : resultList) {
				//resultList=[];の場合はスキップ 
				if (loginUser.getName().equals((String) result.get("name"))
						&& loginUser.getPass().equals((String) result.get("password"))) {
					System.out.println("findAccount(LoginUser " + result);	
					return loginUser;
					//sessionUserオブジェクトのnameフィールドがまだ空なので、この値をreturn
				} 
			}
		} 		return null;
	}

	public boolean findAccount(RegisterUser registerUser) {
		//引数で受け取ったregisterUserオブジェクトとuser_id,passが重複するレコードが何件あるかを検索する
		String sql = "SELECT COUNT(*) FROM USERS WHERE NAME = ?";
		//重複するレコードの件数が返却される
		int result = jdbcTemplate.queryForObject
				(sql, Integer.class, registerUser.getName());
		System.out.println("findAccount(RegisterUser" + result);
		if (result == 0) {
			System.out.println("true");
			return true; //引数で受け取ったregisterUserと同じname、passを持つレコードがDBに存在しない
		}
		System.out.println("false");
		return false; //存在する 
	}
	public boolean insert(RegisterUser registerUser) {
		String sql = "INSERT INTO USERS(NAME,PASSWORD,AGE)VALUES(?,?,?)";
		int result = jdbcTemplate.update
				(sql,registerUser.getName(),registerUser.getPass(),registerUser.getAge());
		if(result == 0) {
		return false;
		}

		return true;
		
	}
	
	
	
}