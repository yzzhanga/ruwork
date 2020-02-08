package com.ruwork.web.dal;

import com.ruwork.web.entity.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class SaleDAO {

    @Qualifier("elasticsearchTemplate")
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Autowired
    private SaleRepository saleRepository;


    @PostConstruct
    public void init(){
        if (!elasticsearchTemplate.indexExists("sale")){
            elasticsearchTemplate.createIndex("sale");
        }
    }

    public void save(Sale sale){

        saleRepository.save(sale);
    }

    public Sale querySaleByPhoneAndIdentity(String salePhone,String identity){

        return  saleRepository.findSaleByCellphoneAndIdentity(salePhone,identity);
    }



}
