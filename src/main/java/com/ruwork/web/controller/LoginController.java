package com.ruwork.web.controller;


import com.ruwork.web.filter.AuthFilter;
import com.ruwork.web.filter.SaleAuthenticationFilter;
import com.ruwork.web.model.SaleModel;
import com.ruwork.web.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private SaleAuthenticationFilter saleAuthenticationFilter;


    @RequestMapping(path = { "/login" })
    public String showLogin(){
        return "index";
    }

    @RequestMapping(path = { "/grid" })
    @AuthFilter
    public String showGrid(Long ticket, Model model){

        SaleModel saleModel =  saleAuthenticationFilter.filter(ticket);

        if (ObjectUtils.isEmpty(saleModel)){
            return "index";
        }
        model.addAttribute("ticket",ticket);

        return "grid";
    }




    @RequestMapping(path = { "/check" })
    @ResponseBody
    public Long check(@ModelAttribute SaleModel saleModel){
        SaleModel sale =  saleService.check(saleModel);
        Long ticket =  System.currentTimeMillis() ;
        saleAuthenticationFilter.setCheckCache(ticket,sale);
        return ticket;
    }




}
