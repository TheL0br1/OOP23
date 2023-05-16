package com.example.lab2.Objects.macroObjects;

import com.example.lab2.Objects.Position;
import com.example.lab2.main;
import javafx.scene.image.Image;

public class macro1 extends macroBase{
    private static Image  image = new Image(main.class.getResourceAsStream("steamEngine.png"), 200, 00, true, true);
    private static double multiplication = 1.0;
    protected macro1(Position position, Image image1) {
        super(position, image1);
    }
    public macro1(Position position){
        this(position,image);
    }

    public double getMultiplication() {
        return multiplication;
    }


    @Override
    Image getImage() {
        return image;
    }



}
