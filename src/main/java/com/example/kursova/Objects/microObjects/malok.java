package com.example.kursova.Objects.microObjects;

import java.io.IOException;

public class malok extends ikra {

    public malok(String name, int health, int damage, int armor) throws IOException {
        super(name, health, damage, armor);
    }

    public malok(ikra a) throws IOException {
        super(a);
    }

    public malok() throws IOException {
    }

    @Override
    protected malok clone() throws CloneNotSupportedException {
        return (malok) super.clone();
    }
}
