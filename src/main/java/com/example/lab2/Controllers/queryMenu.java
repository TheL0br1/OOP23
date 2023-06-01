package com.example.lab2.Controllers;

import com.example.lab2.Objects.Objects;
import com.example.lab2.Objects.macroObjects.macroBase;
import com.example.lab2.main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class queryMenu implements Initializable {


    @FXML
    public RadioButton isActive;
    public TextField armorValue;
    public TextField dependsValue;
    public TextField healthValue;
    public RadioButton isArmor;
    public boolean isArmorB = false;
    public boolean isHealthB = false;
    public boolean isDependsB = false;
    public boolean isActiveB = false;
    public RadioButton isDepends;
    public RadioButton isHealth;
    public ChoiceBox armorChoice;
    public Button doSearch;
    public ChoiceBox dependsChoice;
    public ChoiceBox healthChoice;
    public TextArea resultSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("queryMenu.initialization");
        armorChoice.getItems().addAll("<", "=", ">");
        armorChoice.setValue("=");
        dependsChoice.getItems().addAll("<", "=", ">");
        dependsChoice.setValue("=");
        healthChoice.getItems().addAll("<", "=", ">");
        healthChoice.setValue("=");
    }

    public void searchMicro(MouseEvent mouseEvent) {
        resultSearch.clear();
        for (Objects en : main.Entities) {
            if (isActiveB != en.isActive()) {
                continue;
            }
            System.out.println(en.e.toString());
            if (isHealthB) {
                switch (healthChoice.getValue().toString()) {
                    case "=":
                        if (en.e.getHealth() != Integer.parseInt(healthValue.getText())) {
                            continue;
                        }
                        break;
                    case ">":
                        if (!(en.e.getHealth() > Integer.parseInt(healthValue.getText()))) {
                            continue;
                        }
                        break;

                    case "<":
                        if (!(en.e.getHealth() < Integer.parseInt(healthValue.getText()))) {
                            continue;
                        }
                        break;
                }
            }
            if (isArmorB) {
                switch (armorChoice.getValue().toString()) {
                    case "=":
                        if (en.e.getArmor() != Integer.parseInt(armorValue.getText())) {
                            continue;
                        }
                        break;

                    case ">":
                        if (!(en.e.getArmor() > Integer.parseInt(armorValue.getText()))) {
                            continue;
                        }
                        break;

                    case "<":
                        if (!(en.e.getArmor() < Integer.parseInt(armorValue.getText()))) {
                            continue;
                        }
                        break;

                }
            }
            if (isDependsB) {
                int dependsCount = 0;
                for (macroBase macro: main.macroObjects) {
                    if(macro.isContains(en.e)){
                        dependsCount++;
                    }
                }

                switch (dependsChoice.getValue().toString()) {

                    case "=":
                        if (dependsCount != Integer.parseInt(dependsValue.getText())) {
                            continue;
                        }
                        break;

                    case ">":
                        if (!(dependsCount > Integer.parseInt(dependsValue.getText()))) {
                            continue;
                        }
                        break;


                    case "<":
                        if (!(dependsCount < Integer.parseInt(armorValue.getText()))) {
                            continue;
                        }
                        break;

                }
            }
            resultSearch.setText(resultSearch.getText()+ en.e.toString()+"\n");
        }
    }


    public void changeIsArmor(ActionEvent actionEvent) {
        isArmorB = !isArmorB;
        System.out.println(isArmorB);
    }

    public void changeIsDepends(ActionEvent actionEvent) {
        isDependsB = !isDependsB;
    }

    public void changeIsHealth(ActionEvent actionEvent) {
        isHealthB = !isHealthB;
    }

    public void isActive(ActionEvent actionEvent) {
        isActiveB = !isActiveB;
    }
}
