package com.ruwork.web.dal;

import com.ruwork.web.entity.Customer;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Log
public class CustomerDAO {

    @Autowired
    private CustomerRepository customerRepository;


        public Customer save(Customer customer){

            customerRepository.save(customer);


            return customer;
        }




        public Page<Customer> queryBySaleAndPage(String saleCode,int page,int size){
            Pageable pageArgs = PageRequest.of(page,size).first();
            return customerRepository.findBySaleCode(saleCode,pageArgs);
        }

}
