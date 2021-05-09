package com.ozgurgulbank.onlinebankingapplication.entity.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ozgurgulbank.onlinebankingapplication.entity.account.Account;
import com.ozgurgulbank.onlinebankingapplication.entity.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColorfulAccount extends Account {

    @Enumerated(EnumType.STRING)
    private Currency currency;
    private int expiryDay;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

}
