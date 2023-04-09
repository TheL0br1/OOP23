package com.example.lab2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Collection;
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
        main.create_entity(name.getText(),
                Integer.parseInt(positionX.getText()),
                Integer.parseInt(positionY.getText()),
                Integer.parseInt(health.getText()),
                Integer.parseInt(damage.getText()));
    }

    public void deleteEntity(ActionEvent actionEvent) {
        main.Entities.forEach(En -> {
            main.root.getChildren().remove(En.canvas);
            Rectangle rect = new Rectangle(
                    En.canvas.getLayoutX(),
                    En.canvas.getLayoutY(),
                    En.canvas.getWidth(),
                    En.canvas.getHeight());
            rect.setFill(null);
            rect.setStroke(Color.RED);
            rect.setStrokeWidth(3);
            main.root.getChildren().add(rect);



        });
        main.Entities.clear();
    }

    public void printEntity(ActionEvent actionEvent) {
        main.Entities.forEach(En -> System.err.println(En.toString()));
    }
}