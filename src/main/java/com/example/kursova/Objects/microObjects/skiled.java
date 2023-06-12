package com.example.kursova.Objects.microObjects;

import java.io.IOException;

public class skiled extends amateur {

    public skiled(String name, int health, int damage, int money) throws IOException {
        super(name, health, damage, money);
    }

    public skiled(amateur a) throws IOException {
        super(a);
    }

    public skiled() throws IOException {
    }

    @Override
    protected skiled clone() throws CloneNotSupportedException {
        return (skiled) super.clone();
    }
}
