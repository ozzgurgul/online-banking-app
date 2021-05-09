package com.ozgurgulbank.onlinebankingapplication.repository;

import com.ozgurgulbank.onlinebankingapplication.entity.account.DepositAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<DepositAccount,Long> {

    DepositAccount findDepositAccountByAccountNo(String accountNo);
    DepositAccount findDepositAccountByIbanNo(String ibanNo);

}
