package com.example.appmovilrecuperacion.ui.model;

import java.util.ArrayList;

public class User {
    public int idUser;
    public String email;
    public String password;
    public ArrayList<Game> games;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
}
