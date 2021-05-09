package com.ozgurgulbank.onlinebankingapplication.entity.customer;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ozgurgulbank.onlinebankingapplication.entity.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String phoneNo;

    @OneToOne(mappedBy = "info")
    @JsonIgnore
    private Customer customer;

}
