package com.example.banking_system.services;

import com.opencsv.bean.CsvBindByName;

import java.io.IOException;
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


    public Transaction(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id, String client_id) {
        try (Stream<String> stream = Files.lines(Path.of("/com/example/banking_system/database/"+client_id+".csv"), StandardCharsets.UTF_8)) {
            this.id = String.valueOf(stream.count());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
