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


public class TransferController {
    private Parent root;
    private Stage stage;
    private Scene scene;
    private Client client;

    @FXML
    private TextField txtf_acc_no;
    @FXML
    private TextField txtf_amount;

    @FXML
    public void cancel_to_ClientDash(ActionEvent event) throws IOException {
        //some_logic
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/banking_system/views/client-dash.fxml"));
        ClientDashController contr = new ClientDashController();
        contr.setClient(client);
        loader.setController(contr);
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void transfer_to_ClientDash(ActionEvent event) throws IOException, CsvException, URISyntaxException {
        Client c = ObjectFinder.findClient(txtf_acc_no.getText());
        if(c != null){
            try {
                if(client.getAccount().decrease_balance(Integer.parseInt(txtf_amount.getText()))){
                    c.getAccount().increase_balance(Integer.parseInt(txtf_amount.getText()));
                    ObjectFinder.update(c);
                    Transaction transaction = new Transaction("Money Transfer", Integer.valueOf(txtf_amount.getText()), c.getId());
                    List<Transaction> t = List.of(new Transaction[]{transaction});
                    ObjectFinder.writeTransaction(t, c.getId());
                    alert(Alert.AlertType.INFORMATION, "Success", "Transaction Executed Successfully");
                } else{
                    alert(Alert.AlertType.ERROR, "Failure!", "Insufficient Balance");
                }

            } catch (NumberFormatException | CsvValidationException ex){
                System.out.println(ex.getStackTrace());
                alert(Alert.AlertType.ERROR, "Error!", "Invalid Input Amount!!");
            }
        } else {
            alert(Alert.AlertType.ERROR, "Error!", "No Account Found!!");
        }
    }


    public void alert(Alert.AlertType alertType, String title, String msg){
        Alert al = new Alert(alertType);
        al.setTitle(title);
        al.setHeaderText(msg);
        al.showAndWait();
    }


    public void setClient(Client cl){
        client = cl;
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
