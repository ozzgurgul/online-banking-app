package com.ozgurgulbank.onlinebankingapplication.repository;

import com.ozgurgulbank.onlinebankingapplication.entity.card.DebitCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitRepository extends JpaRepository<DebitCard,Long> {

    DebitCard findDebitCardByCardNo(String debitCardNo);

}
