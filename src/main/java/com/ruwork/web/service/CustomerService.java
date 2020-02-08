package com.ruwork.web.service;

import com.ruwork.web.dal.CustomerDAO;
import com.ruwork.web.entity.Customer;
import com.ruwork.web.model.CustomerModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerDAO customerDAO;

    public Page<CustomerModel> queryCustomerBySale(String saleCode,int page,int size ){
       Page<Customer> result =  customerDAO.queryBySaleAndPage(saleCode, page, size);
        List<CustomerModel> models = result.get().map(e->cover(e)).collect(Collectors.toList());
        Page<CustomerModel> customerModels = new PageImpl(models, PageRequest.of(page, size), result.getSize());
        return customerModels;

    }

    private CustomerModel cover(Customer customer) {
        CustomerModel model = new CustomerModel();
        BeanUtils.copyProperties(customer,model);
        return  model;
    }


}
