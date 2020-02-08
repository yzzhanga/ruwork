package com.ruwork.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QueryController {

    @RequestMapping(path = "query")
    public String queryTable(String condition,int page,int size){


        return null;
    }

}
