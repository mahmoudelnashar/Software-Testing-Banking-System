package com.example.banking_system.controllers;

import com.example.banking_system.services.Client;
import com.example.banking_system.services.ObjectFinder;
import com.example.banking_system.services.Transaction;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class NewClientController {

    private Parent root;
    private Stage stage;
    private Scene scene;

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
    private TextField textf_deposit_amnt;
    @FXML
    private TextField textf_name;
    @FXML
    private TextField textf_ssn;


    @FXML
    public void save_click(ActionEvent e) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, URISyntaxException {
        Client c = null;
        try{
            c = new Client(textf_ssn.getText(), textf_name.getText(), textf_addr.getText(), textf_mob.getText(), textf_user.getText(), textf_pass.getText(), textf_email.getText());
        } catch (NullPointerException ex){
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Please Fill All The Required Fields!!");
            alert.showAndWait();
            return;
        }
        if(!(textf_deposit_amnt.getText().equals(""))){
            try{
                c.getAccount().setBalance(Integer.valueOf(textf_deposit_amnt.getText()));
            } catch (NumberFormatException ex){
                System.out.println(ex.getStackTrace());
                alert("Please Enter a Valid Number!!");
                return;
            }
        }
        if(!(textf_occ.getText().equals(""))){
            c.setOccupation(textf_occ.getText());
        }
        if(!(textf_salary.getText().equals(""))){
            try{
                c.setSalary(Integer.valueOf(textf_deposit_amnt.getText()));
            } catch (NumberFormatException ex){
                System.out.println(ex.getStackTrace());
                alert("Please Enter A Valid Number!!");
                return;
            }
        }
        if(!(textf_tele.getText().equals(""))){
            c.setTele(textf_tele.getText());
        }
        if(!(textf_mart.getText().equals(""))){
            c.setMart(textf_mart.getText());
        }
        if(c != null){
            List<Client> l = List.of(new Client[]{c});
            Transaction tr = new Transaction("Account Opening", Integer.valueOf(textf_deposit_amnt.getText()));
            List<Transaction> tl = List.of(new Transaction[]{tr});
            ObjectFinder.writeClient(l);
            ObjectFinder.writeTransaction(tl, c.getId());
            clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Client Added Successfully!!");
            alert.showAndWait();
        }
    }

    @FXML
    public void cancel_click(ActionEvent e) throws IOException {
        switch_scene("admin-dash.fxml", e);

    }
    private void clear(){
        textf_ssn.clear();
        textf_user.clear();
        textf_email.clear();
        textf_mob.clear();
        textf_email.clear();
        textf_addr.clear();
        textf_mart.clear();
        textf_pass.clear();
        textf_deposit_amnt.clear();
        textf_tele.clear();
        textf_occ.clear();
        textf_salary.clear();
        textf_name.clear();

    }
    private void switch_scene(String resource, ActionEvent event) throws IOException {
        String path = "/com/example/banking_system/views/" + resource;
        root  = FXMLLoader.load(getClass().getResource(path));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 800);
        stage.setScene(scene);
        stage.show();
    }

    private void alert(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText(msg);
        alert.showAndWait();
    }
}
