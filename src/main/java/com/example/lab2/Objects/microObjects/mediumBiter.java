package com.example.lab2.Objects.microObjects;

import java.io.IOException;

public class mediumBiter extends smallBiter {

    public mediumBiter(String name, int health, int damage, int armor) throws IOException {
        super(name, health, damage, armor);
    }

    public mediumBiter(smallBiter a) throws IOException {
        super(a);
    }

    public mediumBiter() throws IOException {
    }

    @Override
    protected mediumBiter clone() throws CloneNotSupportedException {
        return (mediumBiter) super.clone();
    }
}
