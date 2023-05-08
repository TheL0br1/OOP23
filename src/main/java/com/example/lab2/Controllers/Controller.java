package com.example.lab2.Controllers;

import com.example.lab2.main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    public TextField damage;
    public TextField health;
    public TextField positionX;
    public TextField positionY;

    public TextField name;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Controler.initialize");

    }
    @FXML
    private void createEntity(ActionEvent event){
        System.out.println("init new entity");
        main.createEntity(name.getText(),
                Integer.parseInt(positionX.getText()),
                Integer.parseInt(positionY.getText()),
                Integer.parseInt(health.getText()),
                Integer.parseInt(damage.getText()));
    }

    public void deleteEntity(ActionEvent actionEvent) {
        main.deleteEntities();
    }
    public void changeActiveAll(ActionEvent actionEvent){
        main.changeEntityActive();
    }

    public void printEntity(ActionEvent actionEvent) {
        main.Entities.forEach(En -> System.err.println(En.toString()));
    }
}