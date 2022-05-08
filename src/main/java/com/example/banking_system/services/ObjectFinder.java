package com.example.banking_system.services;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.List;

public class ObjectFinder {
    public static List<Client> clients;
    public static List<Transaction> transactions;

    public static void init() throws FileNotFoundException {
        clients = new CsvToBeanBuilder(new FileReader("/com/example/banking_system/database/clients.csv"))
                .withType(Client.class).build().parse();
    }

    public static List<Transaction> getTransactions(String client_id) throws FileNotFoundException {
        transactions = new CsvToBeanBuilder(new FileReader("/com/example/banking_system/database/"+client_id+".csv"))
                .withType(Client.class).build().parse();

        return transactions;
    }

    public static Client findClient(String username, String pass){
        for(Client c: clients){
            if(c.getUsername() == username && c.getPass() == pass) {
                return c;
            }
        }
        return null;
    }

    public void writeClient(List<Client> beans) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String path = "/com/example/banking_system/database/clients.csv";
        Writer writer = new FileWriter(path);
        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
        beanToCsv.write(beans);
        writer.close();
    }
    public void writeTransaction(List<Transaction> beans, String client_id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String path = "/com/example/banking_system/database/"+client_id+".csv";
        Writer writer = new FileWriter(path);
        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
        beanToCsv.write(beans);
        writer.close();
    }
}
