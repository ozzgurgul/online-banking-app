package com.ozgurgulbank.onlinebankingapplication.entity.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ozgurgulbank.onlinebankingapplication.entity.card.DebitCard;
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
public class DepositAccount extends Account {


    @OneToOne(mappedBy = "depositAccount")
    @JsonIgnore
    private Customer customer;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "debit_id",referencedColumnName = "id")
    private DebitCard debitCard;
}
