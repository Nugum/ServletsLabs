package com.my.servlet;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String u_login;
    private String u_password;

    public User(){ }
    public User(String u_login, String u_password){

        this.u_login = u_login;
        this.u_password = u_password;
    }
    public User(int id, String u_login, String u_password){

        this.id = id;
        this.u_login = u_login;
        this.u_password = u_password;
    }

    public int getId() {
        return id;
    }

    public String getU_login() {
        return u_login;
    }

    public void setU_login(String u_login) {
        this.u_login = u_login;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }
}
