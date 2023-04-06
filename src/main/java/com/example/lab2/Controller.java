package com.example.lab2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    public TextField damage;
    public TextField health;
    public TextField positionX;
    public TextField positionY;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Controler.initialize");

    }
    @FXML
    private void createEntity(ActionEvent event){
        System.out.println("init new entity");
        main.create_entity(Integer.parseInt(positionX.getText()), Integer.parseInt(positionY.getText()));
    }

    public void deleteEntity(ActionEvent actionEvent) {
        main.root.getChildren().clear();
        main.Entities.clear();
    }

    public void printEntity(ActionEvent actionEvent) {
        main.Entities.forEach(En -> {System.err.println(En.toString());});
    }
}