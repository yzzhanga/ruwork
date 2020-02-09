package com.ruwork.web.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileData {

    private  List<Sale>  saleData= new ArrayList();
    private  List<Customer>  customerData= new ArrayList();


    public FileData(List<List<List<String>>> datas) {

        List<List<String>> customers = datas.get(0);
        List<List<String>> sales =   datas.get(1);

     fillSales(sales);
     fillCustomers(customers);

    }

    private void fillCustomers(List<List<String>> rows) {

        for (List<String> cells : rows) {
            Customer customerEntity = new Customer();
            customerEntity.setId(UUID.randomUUID().toString());
            customerEntity.setName(cells.get(0));
            customerEntity.setCellphone(cells.get(1));
            customerEntity.setType(cells.get(2));
            customerEntity.setDesc(cells.get(3));
            customerEntity.setSaleCode(cells.get(4));
            customerData.add(customerEntity);

        }

    }

    private void fillSales(List<List<String>> rows) {

        for (List<String> cells : rows) {
            Sale sale = new Sale();
            sale.setId(UUID.randomUUID().toString());
            sale.setName(cells.get(0));
            sale.setSaleCode(cells.get(1));
            sale.setCellphone(cells.get(2));
            sale.setIdentity(cells.get(3));
            saleData.add(sale);


        }

    }

    public List<Sale> getSaleData() {
        return saleData;
    }

    public List<Customer> getCustomerData() {
        return customerData;
    }
}
