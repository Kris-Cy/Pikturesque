package com.apppro.pikturesque.Utils;

import com.apppro.pikturesque.Getting_Started.SignUpActivity;

//This will be the class I use to manipulate strings in the app
public class StringManipulator {

    //this one will take periods in usernames and replace them with spaces... easy for database relation.
    public static String addSpacesToUsername(String username){
        return username.replace(".", " ");
    }

    //this one will take spaces in usernames and replace them with periods... easy for database relation.
    public static String removeSpacesFromUsername(String username){
        return username.replace(" ", ".");
    }
}
