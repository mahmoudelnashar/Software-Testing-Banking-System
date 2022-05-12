package com.example.banking_system.controllers;

import com.example.banking_system.services.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BalanceController implements Initializable{
    private Parent root;
    private Stage stage;
    private Scene scene;
    private int amount;
    private Client client;


    @FXML
    private Label balance_label;

    @FXML
    public void back_click(ActionEvent event) throws IOException {
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
    private void switch_scene(String resource, ActionEvent event) throws IOException {
        String path = "/com/example/banking_system/views/" + resource;
        root  = FXMLLoader.load(getClass().getResource(path));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void setClient(Client cl){
        client = cl;
    }
    public void setAmount(int balance){
        amount = balance;
        balance_label.setText("$" + amount + " EGP");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAmount(client.getAccount().getBalance());
    }
}
