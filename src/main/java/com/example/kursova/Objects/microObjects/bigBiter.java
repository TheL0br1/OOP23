package com.example.kursova.Objects.microObjects;

import java.io.IOException;

public class bigBiter extends mediumBiter {
    public bigBiter(String name, int health, int damage, int armor) throws IOException {
        super(name, health, damage, armor);
    }

    public bigBiter(smallBiter a) throws IOException {
        super(a);
    }

    public bigBiter() throws IOException {
    }

}

