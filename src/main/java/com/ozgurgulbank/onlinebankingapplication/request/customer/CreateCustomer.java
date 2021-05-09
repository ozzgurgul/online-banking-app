package com.ozgurgulbank.onlinebankingapplication.request.customer;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class CreateCustomer {

    private String name;
    private String surname;
    private LocalDate birthDate;
    private String idNo;

}
