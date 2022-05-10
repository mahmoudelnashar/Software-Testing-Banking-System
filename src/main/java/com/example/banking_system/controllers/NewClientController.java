package com.example.banking_system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NewClientController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    public void cancel_click(ActionEvent e) throws IOException {
        switch_scene("admin-dash.fxml", e);

    }

    @FXML
    public void save_click(ActionEvent e) throws IOException {
        switch_scene("admin-dash.fxml", e);

    }
    private void switch_scene(String resource, ActionEvent event) throws IOException {
        String path = "/com/example/banking_system/views/" + resource;
        root  = FXMLLoader.load(getClass().getResource(path));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 800);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void add_user(ActionEvent e){

    }
    public void add_email(ActionEvent e){

    }
    public void add_addr(ActionEvent e){

    }
    public void add_pass(ActionEvent e){

    }public void add_tele(ActionEvent e){

    }public void add_mob(ActionEvent e){

    }
}
