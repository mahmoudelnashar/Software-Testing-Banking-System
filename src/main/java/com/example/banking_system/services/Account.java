package com.example.banking_system.services;

import com.opencsv.bean.CsvBindByName;

public class Account {
    @CsvBindByName(column = "balance", required = true)
    private int balance;
    @CsvBindByName(column = "account_number", required = true)
    private String id;

    public Account(int balance, String id) {
        this.balance = balance;
        this.id = id;
    }

    public Account(){}

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean decrease_balance(int amount){
        if(amount < 0){
            return false;
        }
        if(amount <= this.balance){
            balance -= amount;
            return true;
        } else {
            return false;
        }

    }

    public boolean increase_balance(int amnt){
        if(amnt < 0){
            return false;
        }
        this.balance += amnt;
        return true;
    }
}
