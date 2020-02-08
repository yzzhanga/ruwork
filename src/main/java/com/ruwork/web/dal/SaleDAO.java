package com.ruwork.web.dal;

import com.ruwork.web.entity.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SaleDAO {


    @Autowired
    private SaleRepository saleRepository;



    public void save(Sale sale){

        saleRepository.save(sale);
    }

    public Sale querySaleByPhoneAndIdentity(String salePhone,String identity){

        return  saleRepository.findSaleByCellphoneAndIdentity(salePhone,identity);
    }



}
