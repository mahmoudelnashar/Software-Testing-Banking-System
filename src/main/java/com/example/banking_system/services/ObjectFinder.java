package com.example.banking_system.services;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ObjectFinder {
    public static List<Client> clients = new ArrayList<>();
    public static List<Transaction> transactions = new ArrayList<>();
    private static String client_path;

    public static void init() throws FileNotFoundException, URISyntaxException {
        client_path = Paths.get(ObjectFinder
                .class.getResource("/com/example/banking_system/database/clients.csv")
                .toURI()).toFile().getAbsolutePath();
        FileReader file = new FileReader(client_path);

        clients = new CsvToBeanBuilder(file).withType(Client.class).build().parse();
    }

    public static List<Transaction> getTransactions(String client_id) throws IOException, URISyntaxException {
        String source = Paths.get(ObjectFinder.class.getResource("/com/example/banking_system/database").toURI()).toFile().getAbsolutePath();
        String path = source+"/"+client_id+".csv";

        transactions = new CsvToBeanBuilder(new FileReader(path))
                .withType(Transaction.class).build().parse();

        return transactions;
    }
    public static List<Transaction> getTransactions() throws FileNotFoundException, URISyntaxException {
        return transactions;
    }
    public static Client findClient(String username, String pass){
        for(Client c: clients){
            if(c.getUsername().equals(username) && c.getPass().equals(pass)) {
                return c;
            }
        }
        return null;
    }

    public static Client findClient(String ssn){
        for(Client c: clients){
            if(c.getId().equals(ssn)) {
                return c;
            }
        }
        return null;
    }
    public static void writeClient(List<Client> beans) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, URISyntaxException {
        Writer writer = new FileWriter(client_path, true);
        for(Client cl: beans){
            writer.write(cl.toString());
        }
        writer.close();
    }

    public static void update(Client c) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(client_path));
        String [] nextLine;
        int cnt = 0;
        String searchWord = c.getId(); // field contains integer?
        while ((nextLine = reader.readNext()) != null) {
            if(nextLine[0].equals(searchWord)){
                nextLine[0] = c.getId();
                nextLine[1] = c.getName();
                nextLine[2] = c.getEmail();
                nextLine[3] = c.getUsername();
                nextLine[4] = c.getMob();
                nextLine[5] = c.getTele();
                nextLine[6] = c.getAddr();
                nextLine[7] = c.getOccupation();
                nextLine[8] = String.valueOf(c.getSalary());
                nextLine[9] = c.getPass();
                nextLine[10] = c.getAccount().getId();
                nextLine[11] = String.valueOf(c.getAccount().getBalance());
                nextLine[12] = c.getMart();
                reader.close();
                break;
            }
            cnt++;
        }
        CSVReader reader2 = new CSVReader(new FileReader(client_path));
        ArrayList<String[]> arr = new ArrayList<>(reader2.readAll());
        reader2.close();
        CSVWriter writer = new CSVWriter(new FileWriter(client_path));
        arr.set(cnt, nextLine);
        writer.writeAll(arr);
        writer.close();
    }

    public static void writeTransaction(List<Transaction> beans, String client_id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, URISyntaxException {
        transactions.addAll(beans);
        String source = Paths.get(ObjectFinder.class.getResource("/com/example/banking_system/database").toURI()).toFile().getAbsolutePath();
        String path = source+"/"+client_id+".csv";
        File f = new File(path);
        if(!f.exists()){
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            fw.write("id,amount,type" + "\n");
            fw.close();
        }
        FileWriter writer = new FileWriter(path, true);
        for(Transaction t: beans){
            writer.write(t.toString());
        }
        writer.close();
    }
}
