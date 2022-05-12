package com.example.banking_system.services;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;

import java.io.FileNotFoundException;

public class Client {
    @CsvBindByName(column = "id", required = true)
    private String id;
    @CsvBindByName(column = "name", required = true)
    private String name;
    @CsvBindByName(column = "addr", required = true)
    private String addr;
    @CsvBindByName(column = "mob", required = true)
    private String mob;
    @CsvBindByName(column = "tele", required = true)
    private String tele;
    @CsvBindByName(column = "occupation")
    private String occupation;
    @CsvBindByName(column = "salary")
    private int salary;
    @CsvBindByName(column = "username", required = true)
    private String username;
    @CsvBindByName(column = "pass", required = true)
    private String pass;
    @CsvBindByName(column = "email", required = true)
    private String email;
    @CsvBindByName(column = "mart", required = true)
    private String mart;
    @CsvRecurse
    private Account account;

    public Client(){}

    public Client(String ssn,String name, String addr, String mob, String username, String pass, String email) throws FileNotFoundException {
        if(ssn.equals("") || name.equals("") || addr.equals("") || mob.equals("") || username.equals("") || pass.equals("") || email.equals("")) throw new NullPointerException();
        this.name = name;
        this.addr = addr;
        this.mob = mob;
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.id = ssn;
        this.setAccId();
    }

    public Client(String ssn, String name, String addr, String mob, String tele, String username, String pass, String email) throws FileNotFoundException {
        if(ssn.equals("") || name.equals("") || addr.equals("") || mob.equals("") || username.equals("") || pass.equals("") || email.equals("")) throw new NullPointerException();
        this.name = name;
        this.addr = addr;
        this.mob = mob;
        this.tele = tele;
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.id = ssn;
        this.setAccId();
    }

    public Client(String ssn,String name, String addr, String mob, String tele, String occupation, int salary, String username, String pass, String email) throws FileNotFoundException {
        if(ssn.equals("") || name.equals("") || addr.equals("") || mob.equals("") || username.equals("") || pass.equals("") || email.equals("")) throw new NullPointerException();
        this.name = name;
        this.addr = addr;
        this.mob = mob;
        this.tele = tele;
        this.occupation = occupation;
        this.salary = salary;
        this.username = username;
        this.pass = pass;
        this.id = ssn;
        this.setAccId();

    }

    public String getId() {
        return this.id;
    }

//    public void setId(String id) {
//        this.id = id;
//    }

    public String getMart() {
        return mart;
    }

    public void setMart(String mart) {
        this.mart = mart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setAccId() {
        account = new Account(0, this.id);
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString(){
        return this.id + ","+this.name + ","+this.email + ","+this.username + ","+this.mob + ","+this.tele + ","+this.addr + ","+this.occupation + ","+ this.salary + ","+this.pass + ","+this.account.getId() + ","+this.account.getBalance() +","+this.mart+"\n";

    }
}
