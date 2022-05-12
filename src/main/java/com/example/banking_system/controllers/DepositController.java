package com.example.banking_system.controllers;

import com.example.banking_system.services.Client;
import com.example.banking_system.services.ObjectFinder;
import com.example.banking_system.services.Transaction;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


public class DepositController {
    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    private TextField txtf_acc_no;
    @FXML
    private TextField txtf_amount;


    @FXML
    public void cancel(ActionEvent event) throws IOException {
        //some_logic
        switch_scene("admin-dash.fxml", event);
    }

    public void deposit(ActionEvent event) throws IOException, CsvException, URISyntaxException {
        //some_logic
        Client c = ObjectFinder.findClient(txtf_acc_no.getText());
        if(c != null){
            try {
                c.getAccount().increase_balance(Integer.parseInt(txtf_amount.getText()));
                ObjectFinder.update(c);
                Transaction transaction = new Transaction("Deposit", Integer.valueOf(txtf_amount.getText()), c.getId());
                List<Transaction> t = List.of(new Transaction[]{transaction});
                ObjectFinder.writeTransaction(t, c.getId());
                clear();
                alert(Alert.AlertType.INFORMATION, "Success", "Money Deposited Successfully!!");
            } catch (NumberFormatException ex){
                System.out.println(ex.getStackTrace());
                alert(Alert.AlertType.ERROR, "Error!", "Invalid Input Amount!!");
            }
        } else {
            alert(Alert.AlertType.ERROR, "Error!", "No Account Found!!");
        }
//        switch_scene("admin-dash.fxml", event);
    }

    public void alert(Alert.AlertType alertType, String title, String msg){
        Alert al = new Alert(alertType);
        al.setTitle(title);
        al.setHeaderText(msg);
        al.showAndWait();
    }

    private void clear(){
        txtf_acc_no.clear();
        txtf_amount.clear();

    }



    private void switch_scene(String resource, ActionEvent event) throws IOException {
        String path = "/com/example/banking_system/views/" + resource;
        root  = FXMLLoader.load(getClass().getResource(path));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 800);
        stage.setScene(scene);
        stage.show();
    }
}
