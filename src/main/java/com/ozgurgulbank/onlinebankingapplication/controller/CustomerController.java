package com.ozgurgulbank.onlinebankingapplication.controller;


import com.ozgurgulbank.onlinebankingapplication.entity.customer.Customer;
import com.ozgurgulbank.onlinebankingapplication.request.customer.CreateCustomerRequest;
import com.ozgurgulbank.onlinebankingapplication.request.customer.update.UpdateAddressRequest;
import com.ozgurgulbank.onlinebankingapplication.request.customer.update.UpdateInfoRequest;
import com.ozgurgulbank.onlinebankingapplication.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {


    @Autowired
    private ICustomerService customerService;


    @PostMapping
    public ResponseEntity<Object> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){

        return customerService.createCustomer(createCustomerRequest);

    }

    @GetMapping
    public List<Customer> findAllCustomers(){

        return customerService.findAll();

    }

    @GetMapping("/find-customer/{identificationNumber}")
    public Customer findByIdentification(@PathVariable String identificationNumber){

        return customerService.findByIdentificationNumber(identificationNumber);
    }

    @DeleteMapping("/{identificationNumber}")
    public ResponseEntity<Object> deleteByIdNo(@PathVariable String identificationNumber){

        return customerService.deleteByIdentificationNumber(identificationNumber);
    }

    @PutMapping("/address/{idNo}")
    public ResponseEntity<Object> updateAddress(@RequestBody UpdateAddressRequest request, @PathVariable String idNo){

        return customerService.updateAddress(request,idNo);
    }

    @PutMapping("/info/{idNo}")
    public ResponseEntity<Object> updateInfo(@RequestBody UpdateInfoRequest request,@PathVariable String idNo){

        return customerService.updateInfo(request, idNo);
    }

}
