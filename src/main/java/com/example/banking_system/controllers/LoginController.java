package com.example.banking_system.controllers;

import com.example.banking_system.services.Client;
import com.example.banking_system.services.ObjectFinder;
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
import java.net.URISyntaxException;


public class LoginController {
    Parent root;
    Stage stage;
    Scene scene;
    Client client;
    @FXML
    TextField textf_user;

    @FXML
    TextField textf_pass;

    @FXML
    public void login(ActionEvent event) throws IOException, URISyntaxException {
        String username=textf_user.getText(), pass=textf_pass.getText();
        ObjectFinder.init();
        System.out.println(username);
        if(username.equals("admin123") && pass.equals("admin")){
            switch_scene("admin-dash.fxml", event);
        }
        if(username != "" && pass != ""){
            client = ObjectFinder.findClient(username, pass);
            if(client == null){
                event.consume();
                return;
            }
        } else {
            event.consume();
            return;
        }

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
