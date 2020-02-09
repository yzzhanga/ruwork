package com.ruwork.web.service;

import com.ruwork.web.dal.SaleDAO;
import com.ruwork.web.entity.Sale;
import com.ruwork.web.model.SaleModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

    @Autowired
    private SaleDAO saleDAO;


    public SaleModel check(SaleModel saleInfo){
        Sale sale = saleDAO.querySaleByPhoneAndIdentity(saleInfo.getCellphone(), saleInfo.getIdentity());
        SaleModel saleModel = new  SaleModel();
        if (sale.getName().equals(saleInfo.getName())) {
            BeanUtils.copyProperties(sale,saleModel);
        }
            return saleModel;

    }

}
