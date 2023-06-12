package com.example.kursova.Objects.macroObjects;

import com.example.kursova.Objects.Position;
import com.example.kursova.main;
import javafx.scene.image.Image;

public class fishStore extends biteStore {
    private static final Image image = new Image(main.class.getResourceAsStream("fishStore.jpg"), 200, 0, true, true);
    private static final double multiplication = 2;

    public fishStore(Position position) {
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
