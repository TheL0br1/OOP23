package com.example.lab2.Objects.macroObjects;

import com.example.lab2.Objects.Position;
import com.example.lab2.main;
import javafx.scene.image.Image;

public class macro2 extends macro1{
    private static Image  image = new Image(main.class.getResourceAsStream("steamTurbine.png"), 200, 0, true, true);
    private static double multiplication = 100;

    public macro2(Position position) {
        super(position, image);
    }

    public double getMultiplication() {
        return multiplication;
    }

    public static void setMultiplication(double multiplication) {
        macro2.multiplication = multiplication;
    }

    @Override
    Image getImage() {
        return image;
    }

}
