package com.example.banking_system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class TransferController {
    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    TextField textf_user;

    @FXML
    public void cancel_to_ClientDash(ActionEvent event) throws IOException {
        //some_logic
        switch_scene("client-dash.fxml", event);
    }

    public void transfer_to_ClientDash(ActionEvent event) throws IOException {
        //some_logic
        switch_scene("client-dash.fxml", event);
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
