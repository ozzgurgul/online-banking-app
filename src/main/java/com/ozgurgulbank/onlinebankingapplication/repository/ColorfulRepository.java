package com.ozgurgulbank.onlinebankingapplication.repository;

import com.ozgurgulbank.onlinebankingapplication.entity.account.ColorfulAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorfulRepository extends JpaRepository<ColorfulAccount,Long> {

    ColorfulAccount findColorfulAccountByAccountNo(String accountNo);

}
