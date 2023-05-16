package com.example.carevolution;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import com.example.carevolution.Comparator.Comparator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import object.CarBlue;
import object.CarMove;

public class ListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonForGas;

    @FXML
    private Button buttonForName;
    @FXML
    private Button buttonForZ;

    @FXML
    private CheckBox cbNone;
    @FXML
    private void putChNome(){
        if(cbNone.isSelected())
        {
            cbPark.setSelected(false);
            cbRoad.setSelected(false);
        }
        else {
            cbNone.setSelected(false);
            Cheking =4;
        }
    }

    @FXML
    private CheckBox cbPark;
    @FXML
    private void putChpPark(){
        if(cbPark.isSelected())
        {
            cbNone.setSelected(false);
            cbRoad.setSelected(false);
        }
        else {
            cbPark.setSelected(false);
            Cheking =4;
        }
    }
    @FXML
    private CheckBox cbRoad;
    @FXML
    private void putChpRoad(){
        if(cbRoad.isSelected())
        {
            cbNone.setSelected(false);
            cbPark.setSelected(false);
        }
        else {
            cbRoad.setSelected(false);
            Cheking =4;
        }
    }
    @FXML
    private TextArea textList;
    @FXML
    private TextField textSourgh;
    int Cheking =4;
    @FXML
    void initialize() {
        buttonForName.setOnAction(event -> {

            radio();

            List c = new ArrayList<>();
            c.addAll(App.carBlueArrayList);
            c.addAll(App.carOrangeArrayList);
            c.addAll(App.carBlackArrayList);

            String s = textSourgh.getText();
            List filteredCars = new ArrayList<>();
            for (int i=0;i<c.size();i++) {
                CarBlue f = (CarBlue) c.get(i);

                if (f.getCarName().contains(s) ) {
                    if(Cheking ==1 && !f.isCarParking(false) && f.isEnterPut())
                        filteredCars.add(f);
                    if(Cheking ==2 && f.isCarParking(true))
                        filteredCars.add(f);
                    if(Cheking ==3 &&  !f.isEnterPut()&& !f.isCarParking(false))
                        filteredCars.add(f);
                    if(Cheking ==4)
                        filteredCars.add(f);
                }

            }
            Collections.sort(filteredCars, new Comparator<CarMove>() {
                public int compare(CarBlue car1, CarBlue car2) {
                    return car1.getCarName().compareTo(car2.getCarName());
                }
            });
            String newText = "";
            for (Object car : filteredCars) {
                newText += car.toString() + "\n";
            }
            textList.setText(newText);
        });




        buttonForGas.setOnAction(event -> {
            radio();
            List c = new ArrayList<>();
            c.addAll(App.carBlueArrayList);
            c.addAll(App.carOrangeArrayList);
            c.addAll(App.carBlackArrayList);

            try {
                int number = Integer.parseInt(textSourgh.getText());
                int g = Integer.parseInt(textSourgh.getText());

                List filteredCars = new ArrayList<>();
                for (int i = 0; i < c.size(); i++) {
                    CarBlue f = (CarBlue) c.get(i);
                    if (f.getGasCar() == g) {
                        if (Cheking == 1 && !f.isCarParking(false) && f.isEnterPut())
                            filteredCars.add(f);
                        if (Cheking == 2 && f.isCarParking(true))
                            filteredCars.add(f);
                        if (Cheking == 3 && !f.isEnterPut() && !f.isCarParking(false))
                            filteredCars.add(f);
                        if (Cheking == 4)
                            filteredCars.add(f);
                    }

                }
                Collections.sort(filteredCars, new Comparator<CarMove>() {
                    public int compare(CarBlue car1, CarBlue car2) {
                        return car1.getCarName().compareTo(car2.getCarName());
                    }
                });
                String newText = "";
                for (Object car : filteredCars) {
                    newText += car.toString() + "\n";
                }
                textList.setText(newText);
            } catch (NumberFormatException e) {
            }
        });

        buttonForZ.setOnAction(event -> {
            List c = new ArrayList<>();
            c.addAll(App.carBlueArrayList);
            c.addAll(App.carOrangeArrayList);
            c.addAll(App.carBlackArrayList);
            try {
                int number = Integer.parseInt(textSourgh.getText());
                int g = Integer.parseInt(textSourgh.getText());

                List filteredCars = new ArrayList<>();
                for (int i = 0; i < c.size(); i++) {
                    CarBlue f = (CarBlue) c.get(i);

                    if (f.getCarZ() == g) {
                        if (Cheking == 1 && !f.isCarParking(false) && f.isEnterPut())
                            filteredCars.add(f);
                        if (Cheking == 2 && f.isCarParking(true))
                            filteredCars.add(f);
                        if (Cheking == 3 && !f.isEnterPut() && !f.isCarParking(false))
                            filteredCars.add(f);
                        if (Cheking == 4)
                            filteredCars.add(f);
                    }

                }
                Collections.sort(filteredCars, new Comparator<CarMove>() {
                    public int compare(CarBlue car1, CarBlue car2) {
                        return car1.getCarName().compareTo(car2.getCarName());
                    }
                });
                String newText = "";
                for (Object car : filteredCars) {
                    newText += car.toString() + "\n";
                }
                textList.setText(newText);
            } catch (NumberFormatException e) {

            }
        });


        /*Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
        }));*/
    }
    void radio(){
        boolean X1=cbNone.isSelected(),X2=cbPark.isSelected(),X3=cbRoad.isSelected();
        if(X1)
            Cheking = 1;
        if(X2)
            Cheking = 2;
        if(X3)
            Cheking = 3;
    }

}