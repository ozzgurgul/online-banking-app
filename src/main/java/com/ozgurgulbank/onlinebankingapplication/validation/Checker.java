package com.ozgurgulbank.onlinebankingapplication.validation;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {

    public boolean emailChecker(String email){

        String regex = "^(.+)@(.+)$";
        return email.matches(regex);

    }

    public boolean identificationNoChecker(String idNo){

        if(idNo.length()!=11 || idNo.charAt(10)%2 !=0){
            return false;
        }

        String regex = "^[0-9]+$";
        return idNo.matches(regex);

    }

    public boolean phoneNoChecker(String phoneNo){

        if(phoneNo.length()!=11){

            return false;
        }

        String regex = "^[0-9]+$";
        return phoneNo.matches(regex);

    }

    public boolean nameChecker(String name){

        String regex = "^[a-zA-Z]+$";
        return name.matches(regex);

    }

    public boolean zipCodeChecker(String zipCode){

        if(zipCode.length()!=5){

            return false;
        }

        String regex = "^[0-9]+$";
        return zipCode.matches(regex);

    }


}
