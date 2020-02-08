package com.ruwork.web.dal;

import com.ruwork.web.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CustomerDAO {


    @Qualifier("elasticsearchTemplate")
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Autowired
    private CustomerRepository customerRepository;

        @PostConstruct
        public void init(){
            if (!elasticsearchTemplate.indexExists("customer")){
                elasticsearchTemplate.createIndex("customer");
            }
        }




        public Page<Customer> queryBySaleAndPage(String saleCode,int page,int size){
            PageRequest pageArgs = PageRequest.of(page,size);
            return customerRepository.findCustomersBySaleCode(saleCode,pageArgs);
        }

}
