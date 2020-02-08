package com.ruwork.web.controller;

import com.ruwork.web.model.CustomerModel;
import com.ruwork.web.model.SaleModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UploadController {

    @RequestMapping(path = "/saleupload")
    public String saleUpload(List<SaleModel> saleInfos){
        return null;


    }


    @RequestMapping(path = "/cusupload")
    public String cusUpload(List<CustomerModel> customerModels){
        return null;
    }


}
