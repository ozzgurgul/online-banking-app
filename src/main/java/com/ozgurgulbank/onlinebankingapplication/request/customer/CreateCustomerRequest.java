package com.ozgurgulbank.onlinebankingapplication.request.customer;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ozgurgulbank.onlinebankingapplication.request.customer.CreatCustomerInfo;
import com.ozgurgulbank.onlinebankingapplication.request.customer.CreateCustomer;
import com.ozgurgulbank.onlinebankingapplication.request.customer.CreateCustomerAddress;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder(value = {"name","surname","idNo","address","info"})
public class CreateCustomerRequest {

    private CreateCustomer createCustomer;
    private CreateCustomerAddress requestAddress;
    private CreatCustomerInfo requestInfo;


}
