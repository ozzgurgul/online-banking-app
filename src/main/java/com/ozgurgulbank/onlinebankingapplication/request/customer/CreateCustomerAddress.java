package com.ozgurgulbank.onlinebankingapplication.request.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerAddress {

    private String city;
    private String street;
    private String zipcode;


}
