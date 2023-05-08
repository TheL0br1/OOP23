package com.example.lab2;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;


public class displayMicro{

    @FXML
    public TextField armor;
    public TextField health;
    public TextField damage;
    public TextField test2;
    private Object object;
    public void changeHp(MouseEvent ignoredMouseEvent) {
        object.e.setHealth(Integer.parseInt(health.getText()));
        health.setPromptText(Integer.toString(object.e.getHealth()));
    }

    public void changeArmor(MouseEvent ignoredMouseEvent) {
       // entity.setHealth(Integer.parseInt(health.getText()));

    }



    public displayMicro(Object object) {
        System.out.println("displayMicro.initialize");
        this.object = object;

    }

    public void changeDamage(MouseEvent ignoredMouseEvent) {
        object.e.setDamage(Integer.parseInt(damage.getText()));
        damage.setPromptText(Integer.toString(object.e.getDamage()));
    }
    public void deepCopyObject() throws IOException, ClassNotFoundException {
        smallBiter a = smallBiter.deepCopy(object.e);
        main.create_entity(a.getName(),0,0,a.getHealth(),a.getDamage());
    }

}
