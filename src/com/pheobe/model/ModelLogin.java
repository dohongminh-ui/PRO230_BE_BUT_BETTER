package com.pheobe.model;

public class ModelLogin extends Customer {
    
    public ModelLogin() {
        super();
    }

    public ModelLogin(String email, String password) {
        super();
        setEmail(email);
        setPassword(password);
    }
}