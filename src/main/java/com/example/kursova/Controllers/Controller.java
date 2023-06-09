package com.example.kursova.Controllers;

import com.example.kursova.Additional.MyFunctions;
import com.example.kursova.main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    public TextField damage;
    public TextField health;
    public TextField positionX;
    public TextField positionY;

    public TextField name;
    public ChoiceBox typeChoice;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Controller.initialize");
        typeChoice.getItems().addAll("ikra", "malok", "fish");


    }
    @FXML
    private void createEntity(ActionEvent ignoredEvent) throws IOException {
        System.out.println("init new entity");
        MyFunctions.createEntity(typeChoice.getValue().toString(), name.getText(),
                Integer.parseInt(positionX.getText()),
                Integer.parseInt(positionY.getText()),
                Integer.parseInt(health.getText()),
                Integer.parseInt(damage.getText()), 0);
    }

    public void deleteEntity(ActionEvent ignoredActionEvent) {
        MyFunctions.deleteEntities();
    }
    public void changeActiveAll(ActionEvent ignoredActionEvent){
        MyFunctions.changeEntityActive();
    }

    public void printEntity(ActionEvent ignoredActionEvent) {
        main.Entities.forEach(En -> System.err.println(En.toString()));
    }
}