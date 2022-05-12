package com.example.banking_system.controllers;

import com.example.banking_system.services.Client;
import com.example.banking_system.services.ObjectFinder;
import com.example.banking_system.services.Transaction;
import com.opencsv.exceptions.CsvException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BillController implements Initializable {
    Parent root;
    Stage stage;
    Scene scene;

    private Client client;

    @FXML
    private ComboBox comboBox_bill_type;
    @FXML
    private TextField txtf_amount;
    @FXML
    private TextField txtf_bill_no;

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
        stage.show();    }

    public void pay_to_ClientDash(ActionEvent event) throws IOException, CsvException, URISyntaxException {
        String type = String.valueOf(comboBox_bill_type.getValue());
        try{
            int amount = Integer.valueOf(txtf_amount.getText());
            if(client.getAccount().decrease_balance(amount)){
                Transaction tr = new Transaction(type, amount, client.getId());
                List<Transaction> tl = List.of(new Transaction[]{tr});
                ObjectFinder.writeTransaction(tl, client.getId(), true);
                ObjectFinder.update(client);
                txtf_amount.clear();
                txtf_bill_no.clear();
                alert(Alert.AlertType.INFORMATION, "Success", "Bill Paid Successfully!");
            }
        } catch (NumberFormatException ex){
            alert(Alert.AlertType.ERROR, "Error!", "Invalid Amount!!");
        }
    }

    public void alert(Alert.AlertType alertType, String title, String msg){
        Alert al = new Alert(alertType);
        al.setTitle(title);
        al.setHeaderText(msg);
        al.showAndWait();
    }

    private void switch_scene(String resource, ActionEvent event) throws IOException {
        String path = "/com/example/banking_system/views/" + resource;
        root  = FXMLLoader.load(getClass().getResource(path));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
