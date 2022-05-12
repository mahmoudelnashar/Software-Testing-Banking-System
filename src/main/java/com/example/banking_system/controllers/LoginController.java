package com.example.banking_system.controllers;

import com.example.banking_system.services.Client;
import com.example.banking_system.services.ObjectFinder;
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
        if(username.equals("admin123") && pass.equals("admin")){
            switch_scene("admin-dash.fxml", event);
            return;
        }
        if(!username.equals("") && !pass.equals("")){
            client = ObjectFinder.findClient(username, pass);
            if(client == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Invalid Username/Password");
                alert.showAndWait();
                return;
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/banking_system/views/client-dash.fxml"));
                ClientDashController contr = new ClientDashController();
                loader.setController(contr);
                contr.setClient(client);
                ObjectFinder.getTransactions(client.getId());
                root = loader.load();
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root, 1280, 800);
                stage.setScene(scene);
                stage.show();
            }
        } else {
            event.consume();
            return;
        }
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
