package com.ozgurgulbank.onlinebankingapplication.entity.customer;


import com.ozgurgulbank.onlinebankingapplication.entity.card.CreditCard;
import com.ozgurgulbank.onlinebankingapplication.entity.account.DepositAccount;
import com.ozgurgulbank.onlinebankingapplication.entity.account.ColorfulAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String surname;
    private LocalDate birthDate;
    private String idNo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_id",referencedColumnName = "id")
    private Info info;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_id",referencedColumnName = "id")
    private CreditCard creditCard;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<ColorfulAccount> colorfulAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deposit_id",referencedColumnName = "id")
    private DepositAccount depositAccount;


}
