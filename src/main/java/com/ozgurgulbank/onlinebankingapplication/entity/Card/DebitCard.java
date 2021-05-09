package com.ozgurgulbank.onlinebankingapplication.entity.card;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ozgurgulbank.onlinebankingapplication.entity.account.DepositAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DebitCard extends Card {


    private double balance;

    @OneToOne(mappedBy = "debitCard")
    @JsonIgnore
    private DepositAccount depositAccount;

}
