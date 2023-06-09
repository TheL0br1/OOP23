package com.example.lab2.Objects.macroObjects;

import com.example.lab2.Objects.Position;
import com.example.lab2.main;
import javafx.scene.image.Image;

public class metro extends macroBase {
    private static Image image = new Image(main.class.getResourceAsStream("metro.png"), 200, 00, true, true);
    private static double multiplication = 1.0;

    protected metro(Position position, Image image1) {
        super(position, image1);
    }

    public metro(Position position) {
        this(position, image);
    }

    public double getMultiplication() {
        return multiplication;
    }


    @Override
    Image getImage() {
        return image;
    }



}
