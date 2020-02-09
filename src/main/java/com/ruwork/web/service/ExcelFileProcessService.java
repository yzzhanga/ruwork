package com.ruwork.web.service;

import com.ruwork.web.dal.CustomerDAO;
import com.ruwork.web.dal.SaleDAO;
import com.ruwork.web.entity.Customer;
import com.ruwork.web.entity.FileData;
import com.ruwork.web.entity.Sale;
import com.ruwork.web.utils.ExcelResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ExcelFileProcessService {

    @Autowired
    private SaleDAO saleDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private ExcelResolver excelResolver;


    public FileData resolve(File dest) throws Exception {

        return    new FileData( excelResolver.resolve(dest)) ;
    }

    public void saveSaleData(List<Sale> saleData) {
        saleData.stream().forEach(e->{
            saleDAO.save(e);
        });

        
    }

    public void saveCustomerData(List<Customer> customerData) {
        customerData.stream().forEach(e->{
            customerDAO.save(e);
        });
    }
}
