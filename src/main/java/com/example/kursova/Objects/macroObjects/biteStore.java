package com.example.kursova.Objects.macroObjects;

import com.example.kursova.Objects.Position;
import com.example.kursova.main;
import javafx.scene.image.Image;

public class biteStore extends macroBase {
    private static final Image image = new Image(main.class.getResourceAsStream("biteStore.png"), 200, 0, true, true);
    private static final double multiplication = 1.0;

    protected biteStore(Position position, Image image1) {
        super(position, image1);
    }

    public biteStore(Position position) {
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
