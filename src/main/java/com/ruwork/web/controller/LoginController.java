package com.ruwork.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {


    @RequestMapping(path = { "/login" })
    public String showLogin(){

        return "login";
    }
    @RequestMapping(path = { "/check" })
    public String check(@RequestParam String name,@RequestParam  String code,@RequestParam  String validCode){
        return "result";
    }



}
