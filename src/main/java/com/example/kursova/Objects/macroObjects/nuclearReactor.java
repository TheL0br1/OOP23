package com.example.kursova.Objects.macroObjects;

import com.example.kursova.Objects.Position;
import com.example.kursova.main;
import javafx.scene.image.Image;

public class nuclearReactor extends steamTurbine {
    private static double multiplication = 3;
    private static Image image = new Image(main.class.getResourceAsStream("nuclearReactor.png"), 200, 0, true, true);

    public nuclearReactor(Position position) {
        super(position);
    }

    public double getMultiplication() {
        return multiplication;
    }

    @Override
    Image getImage() {
        return image;
    }
}
