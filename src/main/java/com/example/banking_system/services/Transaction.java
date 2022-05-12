package com.example.banking_system.services;

import com.opencsv.bean.CsvBindByName;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Transaction {
    @CsvBindByName(column = "id", required = true)
    private String id;
    @CsvBindByName(column = "type", required = true)
    private String type;
    @CsvBindByName(column = "amount", required = true)
    private int amount;

    public Transaction(){}

    public Transaction(String type, int amount, String client_id) throws IOException, URISyntaxException {
        this.type = type;
        this.amount = amount;
        this.setAutoId(client_id);
    }
    public Transaction(String type, int amount) {
        this.type = type;
        this.amount = amount;
        this.id = "1";
    }

    public String getId() {
        return id;
    }

    public void setAutoId(String client_id) throws IOException, URISyntaxException {
        this.id = String.valueOf(ObjectFinder.getTransactions(client_id).size()+1);
    }

    public void setId(String id){
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return  id +"," +amount + "," +type + "\n";
    }
}
