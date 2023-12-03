package com.example.demo.entity;

import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;
@Data
@SessionScope 
 public class LoginUser {
    private String name;
    private String pass;
}