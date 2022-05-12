package com.example.banking_system.controllers;

import com.example.banking_system.services.Client;
import com.example.banking_system.services.ObjectFinder;
import com.opencsv.exceptions.CsvException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditClientController implements Initializable {

    private Parent root;
    private Stage stage;
    private Scene scene;
    private Client client;


    @FXML
    private TextField textf_pass;
    @FXML
    private TextField textf_user;
    @FXML
    private TextField textf_email;
    @FXML
    private TextField textf_mob;
    @FXML
    private TextField textf_tele;
    @FXML
    private TextField textf_addr;
    @FXML
    private TextField textf_occ;
    @FXML
    private TextField textf_salary;
    @FXML
    private TextField textf_mart;
    @FXML
    private TextField textf_name;
    @FXML
    private TextField textf_ssn;


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
    public void setClient(Client c){
        client = c;
//        init();
    }



    private void init(){
        textf_ssn.setText(client.getId());
        textf_name.setText(client.getName());
        textf_addr.setText(client.getAddr());
        textf_mob.setText(client.getMob());
        textf_tele.setText(client.getTele());
        textf_occ.setText(client.getOccupation());
        textf_salary.setText(String.valueOf(client.getSalary()));
        textf_mart.setText(client.getMart());
        textf_pass.setText(client.getPass());
        textf_email.setText(client.getEmail());
        textf_user.setText(client.getUsername());

    }

    public void save_click(ActionEvent e) throws IOException, CsvException {
        client.setUsername(textf_user.getText());
        client.setPass(textf_pass.getText());
        client.setEmail(textf_email.getText());
        client.setTele(textf_tele.getText());
        client.setAddr(textf_addr.getText());
        client.setMob(textf_mob.getText());
        ObjectFinder.update(client);
        clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Updated Successfully!!");
        alert.showAndWait();

    }

    private void clear(){
        textf_user.setDisable(true);
        textf_email.setDisable(true);
        textf_addr.setDisable(true);
        textf_pass.setDisable(true);
        textf_tele.setDisable(true);
        textf_mob.setDisable(true);

    }
//    private void switch_scene(String resource, ActionEvent event) throws IOException {
//        String path = "/com/example/banking_system/views/" + resource;
//        root  = FXMLLoader.load(getClass().getResource(path));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root, 1280, 800);
//        stage.setScene(scene);
//        stage.show();
//    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }
}
