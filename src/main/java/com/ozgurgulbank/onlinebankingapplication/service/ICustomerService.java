package com.ozgurgulbank.onlinebankingapplication.service;


import com.ozgurgulbank.onlinebankingapplication.entity.customer.Customer;
import com.ozgurgulbank.onlinebankingapplication.request.customer.CreateCustomerRequest;
import com.ozgurgulbank.onlinebankingapplication.request.customer.update.UpdateAddressRequest;
import com.ozgurgulbank.onlinebankingapplication.request.customer.update.UpdateInfoRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICustomerService {

    ResponseEntity<Object> createCustomer(CreateCustomerRequest createCustomerRequest);
    List<Customer> findAll();
    Customer findByIdentificationNumber(String identificationNumber);
    ResponseEntity<Object> deleteByIdentificationNumber(String identificationNumber);
    ResponseEntity<Object> updateAddress(UpdateAddressRequest request,String idNo);
    ResponseEntity<Object> updateInfo(UpdateInfoRequest request,String idNo);

}
