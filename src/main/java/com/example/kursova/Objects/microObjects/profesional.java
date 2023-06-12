package com.example.kursova.Objects.microObjects;

import java.io.IOException;

public class profesional extends skiled {
    public profesional(String name, int health, int damage, int money) throws IOException {
        super(name, health, damage, money);
    }

    public profesional(amateur a) throws IOException {
        super(a);
    }

    public profesional() throws IOException {
    }

}

