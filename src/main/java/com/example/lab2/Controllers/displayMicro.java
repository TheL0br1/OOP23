package com.example.lab2.Controllers;

import com.example.lab2.Objects.Object;
import com.example.lab2.Objects.microObjects.smallBiter;
import com.example.lab2.main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class displayMicro implements Initializable {

    @FXML
    public TextField armor;
    public TextField health;
    public TextField damage;
    public TextField name;
    public Canvas canvas = new Canvas();
    private Object object;
    public void changeHp(MouseEvent ignoredMouseEvent) {
        object.e.setHealth(Integer.parseInt(health.getText()));
        health.setPromptText(Integer.toString(object.e.getHealth()));
    }

    public void changeArmor(MouseEvent ignoredMouseEvent) {
       // entity.setHealth(Integer.parseInt(health.getText()));
        object.e.setArmor(Integer.parseInt(armor.getText()));
        armor.setPromptText(armor.getText());
    }
    public void changeName(MouseEvent ignoredMouseEvent) {
        object.e.setName(name.getText());
        name.setPromptText(object.e.getName());

    }




    public void setObject(Object object){
        this.object = object;
        canvas.setWidth(object.getImage().getWidth());
        canvas.setHeight(object.getImage().getHeight());

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(object.getImage(), 0,0);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("displayMicro.initialize");

    }

    public void changeDamage(MouseEvent ignoredMouseEvent) {
        object.e.setDamage(Integer.parseInt(damage.getText()));
        damage.setPromptText(Integer.toString(object.e.getDamage()));
    }
    public void deepCopyObject() throws IOException, ClassNotFoundException {
        smallBiter a = smallBiter.clone(object.e);
        main.createEntity(a.getName(),0,0,a.getHealth(),a.getDamage(),a.getArmor());
    }

}
