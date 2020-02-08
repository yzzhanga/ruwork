package com.ruwork.web.dal;

import com.ruwork.web.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class CustomerDAO {

    @Autowired
    private CustomerRepository customerRepository;


        public Customer save(Customer customer){

            customerRepository.save(customer);


            return customer;
        }




        public Page<Customer> queryBySaleAndPage(String saleCode,int page,int size){
            PageRequest pageArgs = PageRequest.of(page,size);
            return customerRepository.findCustomersBySaleCode(saleCode,pageArgs);
        }

}
