package com.example.lab2.Objects.macroObjects;

import com.example.lab2.Objects.Position;
import com.example.lab2.main;
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
