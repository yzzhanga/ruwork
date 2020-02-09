package com.ruwork.web.controller;


import com.ruwork.web.model.SaleModel;
import com.ruwork.web.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private SaleService saleService;


    @RequestMapping(path = { "/login" })
    public String showLogin(){
        return "login";
    }
    @RequestMapping(path = { "/check" })
    @ResponseBody
    public boolean check( SaleModel saleModel){
        return saleService.check(saleModel);
    }



}
