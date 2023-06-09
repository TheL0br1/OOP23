package com.example.kursova.Objects.macroObjects;

import com.example.kursova.Objects.Position;
import com.example.kursova.main;
import javafx.scene.image.Image;

public class steamTurbine extends steamEngine {
    private static Image image = new Image(main.class.getResourceAsStream("steamTurbine.png"), 200, 0, true, true);
    private static double multiplication = 2;

    public steamTurbine(Position position) {
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
