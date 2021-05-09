package com.ozgurgulbank.onlinebankingapplication.entity.card;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ozgurgulbank.onlinebankingapplication.entity.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard extends Card {

    private LocalDate createdAt;
    private double totalLimit;
    private double currentLimit;

    @OneToOne(mappedBy = "creditCard")
    @JsonIgnore
    private Customer customer;


}
