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
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    private Parent root;
    private Stage stage;
    private Scene scene;
    private Client client;

    @FXML
    private TableView<Transaction> table;


    public void setClient(Client cl){
        client = cl;
    }

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

    public void setTable(ObservableList<Transaction> l){
        table.setItems(l);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setTable(FXCollections.observableArrayList(ObjectFinder.getTransactions()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
