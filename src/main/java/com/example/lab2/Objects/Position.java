package com.example.lab2.Objects;

import java.io.Serializable;

public class Position implements Serializable {
    public int X = 0;
    public int Y = 0;

    public Position(int x, int y) {
        X = x;
        Y = y;
    }
}
