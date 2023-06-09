package com.example.kursova.Objects.macroObjects;

import com.example.kursova.Objects.Position;
import com.example.kursova.main;
import javafx.scene.image.Image;

public class perekat extends obmil {
    private static final Image image = new Image(main.class.getResourceAsStream("perekat.jpg"), 200, 0, true, true);
    private static final double multiplication = 2;

    public perekat(Position position) {
        super(position, image);
    }

    public double getMultiplication() {
        return multiplication;
    }


    @Override
    Image getImage() {
        return image;
    }

}
