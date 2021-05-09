package com.ozgurgulbank.onlinebankingapplication.calculator;

public class ProcessingFeeCalculator {

    public double calculateFeeForAccountToAccount(double amount){

        double processingFee;

        if(amount>0 && amount<=1000){
            processingFee = 2.86;
        }
        else if(amount>1000 && amount<50000){
            processingFee = 5.73;
        }
        else {
            processingFee = 57.30;
        }

        return processingFee;
    }


}
