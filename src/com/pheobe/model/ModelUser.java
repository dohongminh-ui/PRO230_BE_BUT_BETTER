package com.pheobe.model;

public class ModelUser extends Customer {
    
    public ModelUser() {
        super();
    }

    public ModelUser(int userID, String userName, String email, String password) {
        super();
        setIdCustomer(userID);
        setUserName(userName);
        setEmail(email);
        setPassword(password);
    }

    public int getUserID() {
        return getIdCustomer();
    }

    public void setUserID(int userID) {
        setIdCustomer(userID);
    }
}