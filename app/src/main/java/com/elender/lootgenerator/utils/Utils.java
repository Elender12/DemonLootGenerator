package com.elender.lootgenerator.utils;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String prepareString(String value){
        if(value != null){
           value =  value.trim();
           value =  value.replaceAll("^[\n\r\t]", "").replaceAll("[\n\r\t]$", "");
        }
    return value;
    }

    public static boolean isValid(String value){
        String regex = "^+[a-zA-Z0-9 ]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return  matcher.matches();
    }

}



