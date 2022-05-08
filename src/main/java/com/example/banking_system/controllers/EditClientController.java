package com.example.banking_system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class EditClientController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private TextField textf_user;
    @FXML
    private TextField textf_pass;
    @FXML
    private TextField textf_email;
    @FXML
    private TextField textf_mob;
    @FXML
    private TextField textf_tele;
    @FXML
    private TextField textf_addr;


    public void back_click(ActionEvent e) throws IOException {
        switch_scene("client-dash.fxml", e);

    }

    public void save_click(ActionEvent e) throws IOException {
        switch_scene("client-dash.fxml", e);

    }
    private void switch_scene(String resource, ActionEvent event) throws IOException {
        String path = "/com/example/banking_system/views/" + resource;
        root  = FXMLLoader.load(getClass().getResource(path));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void edit_user(MouseEvent e){
        textf_user.setDisable(false);
    }
    public void edit_email(MouseEvent e){
        textf_email.setDisable(false);
    }
    public void edit_addr(MouseEvent e){
        textf_addr.setDisable(false);
    }
    public void edit_pass(MouseEvent e){ textf_pass.setDisable(false); }
    public void edit_tele(MouseEvent e){ textf_tele.setDisable(false); }
    public void edit_mob(MouseEvent e){ textf_mob.setDisable(false); }
}
