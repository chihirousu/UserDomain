package com.example.demo.entity;

import jakarta.persistence.Id;
//import org.hibernate.validator.constraints.Length; 
//import org.hibernate.validator.constraints.Range; 
//import org.springframework.data.annotation.Id;
//import jakarta.validation.constraints.NotBlank; 
//import jakarta.validation.constraints.Pattern; 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 
//新規登録する際のデータを格納するエンティティ
@Data //getter・setterを自動生成
@NoArgsConstructor //コンストラクタを自動生成
@AllArgsConstructor //全引数に対する初期価値を引数に取るコンストラクタを自動生成 
public class RegisterUser {
	@Id //今回はnameを主キーとする
	private String name;
	private int age;
	private String pass;
}