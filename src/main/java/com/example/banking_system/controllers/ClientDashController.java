package com.example.banking_system.controllers;

import com.example.banking_system.services.Client;
import com.example.banking_system.services.ObjectFinder;
import com.example.banking_system.services.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;


public class ClientDashController implements Initializable {
    private Parent root;
    private Stage stage;
    private Scene scene;
    private static Client client;

    @FXML
    Label welcome_label;
    @FXML
    Label account_label;

    @FXML
    public void to_pay_bill(ActionEvent event) throws IOException {
        //some_logic
        String path = "/com/example/banking_system/views/pay-bill.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        BillController contr = new BillController();
        contr.setClient(client);
        loader.setController(contr);
        root  = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void setClient(Client c){
        client = c;
    }

    @FXML
    public void to_transfer(ActionEvent event) throws IOException {
        //some_logic
        String path = "/com/example/banking_system/views/transfer.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        TransferController contr = new TransferController();
        contr.setClient(client);
        loader.setController(contr);
        root  = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 800);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void to_balance(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/banking_system/views/balance.fxml"));
        BalanceController bc = new BalanceController();
        bc.setClient(client);
        loader.setController(bc);
        root  = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 800);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void to_history(ActionEvent event) throws IOException, URISyntaxException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/com/example/banking_system/views/history.fxml"));
        HistoryController hist = new HistoryController();
        hist.setClient(client);
        loader.setController(hist);
        root  = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 800);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void to_edit(ActionEvent event) throws  IOException{
        String path = "/com/example/banking_system/views/edit-client.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        EditClientController edt = new EditClientController();
        edt.setClient(client);
        loader.setController(edt);
        root  = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 800);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcome_label.setText("Welcome, "+client.getName());
        account_label.setText("Account Number: " + client.getAccount().getId());
    }
}
