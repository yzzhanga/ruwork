package com.ruwork.web.controller;

import com.ruwork.web.model.CustomerModel;
import com.ruwork.web.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QueryController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(path = "query")
    @ResponseBody
    public Page<CustomerModel> queryTable(String saleCode, int page, int size){
        return customerService.queryCustomerBySale(saleCode, page, size);
    }

}
