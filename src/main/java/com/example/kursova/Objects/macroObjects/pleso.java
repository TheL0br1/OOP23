package com.example.kursova.Objects.macroObjects;

import com.example.kursova.Objects.Position;
import com.example.kursova.main;
import javafx.scene.image.Image;

public class pleso extends perekat {
    private static final double multiplication = 3;
    private static final Image image = new Image(main.class.getResourceAsStream("pleso.jpg"), 200, 0, true, true);

    public pleso(Position position) {
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
