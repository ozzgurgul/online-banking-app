package com.ozgurgulbank.onlinebankingapplication.entity.card;

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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    private String cardNo;
    private String ccv;


}
