package com.example.lab2.Objects.macroObjects;

import com.example.lab2.Objects.Position;
import com.example.lab2.main;
import javafx.scene.image.Image;

public class valley extends metro {
    private static Image image = new Image(main.class.getResourceAsStream("valley.png"), 200, 0, true, true);
    private static double multiplication = 2;

    public valley(Position position) {
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
