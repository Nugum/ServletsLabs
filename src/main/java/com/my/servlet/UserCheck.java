package com.my.servlet;

import java.util.ArrayList;

public class UserCheck {

    public static boolean isUserPasCorrect(ArrayList<User> users, String login, String password){
        for (User user: users){
            if (user.getU_login().equals(login)){
                if(user.getU_password().equals(password)){
                    return true;
                } else {return false;}
            }
        }

        return false;
    }
}
