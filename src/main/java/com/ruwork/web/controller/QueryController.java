package com.ruwork.web.controller;

import com.ruwork.web.filter.SaleAuthenticationFilter;
import com.ruwork.web.model.CustomerModel;
import com.ruwork.web.model.SaleModel;
import com.ruwork.web.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class QueryController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SaleAuthenticationFilter saleAuthenticationFilter;



    @RequestMapping(path = "query")
    @ResponseBody
    public List<CustomerModel> queryTable(Long  ticket, int page, int size){                //时间宝贵，简单起见，在前端分页，size往后端传值设置为1000
        SaleModel saleModel =  saleAuthenticationFilter.filter(ticket);
        Page<CustomerModel>  result =  customerService.queryCustomerBySale(saleModel.getSaleCode(), page, size);
        return result.getContent();
    }



}
