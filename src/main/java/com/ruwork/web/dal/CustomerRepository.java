package com.ruwork.web.dal;

import com.ruwork.web.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository extends Repository<Customer,String> {

    List<Customer> findCustomersByCellphone(String cellphone);

    Page<Customer> findBySaleCode(String saleCode, Pageable pageable);

    Customer save(Customer entity);

    Optional<Customer> findById(String primaryKey);

    Iterable<Customer> findAll();

    long count();

    void delete(Customer entity);

    boolean existsById(String primaryKey);



}
