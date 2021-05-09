package com.ozgurgulbank.onlinebankingapplication.repository;

import com.ozgurgulbank.onlinebankingapplication.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findById(long customerId);
    Customer findByIdNo(String identificationNo);
    ResponseEntity<Object> deleteByIdNo(String identificationNo);
}
