package com.ozgurgulbank.onlinebankingapplication.calculator;

public class InterestCalculator {

    double interest;

    public double getWelcomeInterest(double amount, int day, String currency){

        switch (currency) {
            case "TRY":

                interest = (amount * day * 19.75) / 36500;

                break;
            case "USD":

                interest = (amount * day * 2) / 36500;

                break;
            case "EUR":

                interest = (amount * day * 1) / 36500;

                break;
            case "GBP":

                interest = (amount * day * 1) / 36500;

                break;
            default:

                interest = (amount * day * 0.60) / 36500;
                break;
        }

        return interest;

    }

    public double getInterest(double amount, int day, String currency){

        switch (currency) {
            case "TRY":

                interest = (amount * day * 16) / 36500;

                break;
            case "USD":

                interest = (amount * day * 0.75) / 36500;

                break;
            case "EUR":

                interest = (amount * day * 0.25) / 36500;

                break;
            case "GBP":

                interest = (amount * day * 0.25) / 36500;

                break;
            default:

                interest = (amount * day * 0.50) / 36500;
                break;
        }

        return interest;

    }

}
