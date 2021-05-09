package com.ozgurgulbank.onlinebankingapplication.number;


import java.util.Random;

public class CreateNumber {


    public String createAccountNumber(){

        Random random = new Random();
        String accountNumber ="";

        for(int i =0; i<15; i++)
            accountNumber+=String.valueOf(random.nextInt(9));

            return accountNumber;

    }

    public String createIban(){

        Random random = new Random();
        String iban = "TR8400135";
        for(int i =0; i<15; i++)
            iban+=String.valueOf(random.nextInt(9));

        return iban;


    }

    public String createCardNo(){

        Random random = new Random();
        String cardNo = "";

        for(int i =0; i<16 ; i++)
            cardNo+=String.valueOf(random.nextInt(9));

        return cardNo;
    }

    public String createSecurityNo(){

        Random random = new Random();
        String cvc ="";

                for(int i=0; i<3;i++)
                    cvc+= String.valueOf(random.nextInt(9));

                return cvc;

    }

}
