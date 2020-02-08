package com.ruwork.web.dal;

import com.ruwork.web.entity.Sale;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface SaleRepository extends Repository<Sale,String> {

     Sale findSaleByCellphoneAndIdentity(String cellPhone,String identity);

     Sale save(Sale sale);

     Optional<Sale> findById(String primaryKey);

     Iterable<Sale> findAll();

     long count();

     void delete(Sale entity);

     boolean existsById(String primaryKey);




}
