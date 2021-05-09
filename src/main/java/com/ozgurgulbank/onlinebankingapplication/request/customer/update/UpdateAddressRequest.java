package com.ozgurgulbank.onlinebankingapplication.request.customer.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAddressRequest {

    private String city;
    private String street;
    private String zipcode;

}
