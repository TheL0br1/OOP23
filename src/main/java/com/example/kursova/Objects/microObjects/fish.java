package com.example.kursova.Objects.microObjects;

import java.io.IOException;

public class fish extends malok {
    public fish(String name, int health, int damage, int armor) throws IOException {
        super(name, health, damage, armor);
    }

    public fish(ikra a) throws IOException {
        super(a);
    }

    public fish() throws IOException {
    }

}

