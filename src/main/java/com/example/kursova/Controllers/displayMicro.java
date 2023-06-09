package com.example.kursova.Controllers;

import com.example.kursova.Additional.MyFunctions;
import com.example.kursova.Objects.Objects;
import com.example.kursova.Objects.microObjects.ikra;
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
    private Objects objects;
    public void changeHp(MouseEvent ignoredMouseEvent) {
        objects.e.setHealth(Integer.parseInt(health.getText()));
        health.setPromptText(Integer.toString(objects.e.getHealth()));
    }

    public void changeArmor(MouseEvent ignoredMouseEvent) {
       // entity.setHealth(Integer.parseInt(health.getText()));
        objects.e.setArmor(Integer.parseInt(armor.getText()));
        armor.setPromptText(armor.getText());
    }

    public void changeName(MouseEvent ignoredMouseEvent) {
        objects.e.setName(name.getText());
        name.setPromptText(objects.e.getName());

    }


    public void setObject(Objects objects) {
        this.objects = objects;
        canvas.setWidth(objects.getImage().getWidth());
        canvas.setHeight(objects.getImage().getHeight());

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(objects.getImage(), 0, 0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("displayMicro.initialize");

    }

    public void changeDamage(MouseEvent ignoredMouseEvent) {
        objects.e.setDamage(Integer.parseInt(damage.getText()));
        damage.setPromptText(Integer.toString(objects.e.getDamage()));
    }
    public void deepCopyObject() throws IOException, ClassNotFoundException {
        ikra a = ikra.clone(objects.e);
        MyFunctions.createEntity(a.getClass().getSimpleName(), a.getName(), 0, 0, a.getHealth(), a.getDamage(), a.getArmor());
    }

}
