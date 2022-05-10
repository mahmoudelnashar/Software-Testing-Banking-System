package com.example.banking_system.services;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

public class ObjectFinder {
    public static List<Client> clients;
    public static List<Transaction> transactions;

    public static void init() throws FileNotFoundException, URISyntaxException {
        FileReader file = new FileReader(Paths.get(ObjectFinder
                .class.getResource("/com/example/banking_system/database/clients.csv")
                .toURI()).toFile().getAbsolutePath());

        clients = new CsvToBeanBuilder(file).withType(Client.class).build().parse();
    }

    public static List<Transaction> getTransactions(String client_id) throws FileNotFoundException, URISyntaxException {
        transactions = new CsvToBeanBuilder(new FileReader(Paths.get(ObjectFinder
                .class.getResource("/com/example/banking_system/database/clients.csv")
                .toURI()).toFile().getAbsolutePath()))
                .withType(Transaction.class).build().parse();

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
        File f = new File(path);
        if(!f.exists() && !f.isDirectory()){
            f.createNewFile();
            FileWriter filewriter = new FileWriter(path);
            filewriter.write("id, type, amount");
            filewriter.close();
        }
        Writer writer = new FileWriter(path);
        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
        beanToCsv.write(beans);
        writer.close();
    }
}
