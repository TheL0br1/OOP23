package com.example.lab2;

import javafx.scene.image.Image;

enum Action {
    ATTACK,
    DEFENSE,
    NEUTRAL,
    RUN
}
public class Small_biter {


    private static int count;
    private Position position;
    private double directionR = 0;
    private double speed = 0;
    private int id;
    private Action action;
    private static final int MaxSpeed;

    public final static  String name = "Small_byter";
    private final Image spriteImage = new Image(getClass().getResource("Small_biter.png").toExternalForm());

    static{
        count = 1;
        MaxSpeed = 100;
        System.out.println("Class statis initialization");
    }
    public Small_biter(int posX, int posY) {
        position.X = posX;
        position.Y = posY;
        action = Action.NEUTRAL;
        count++;
        id = count;

    }
    public Small_biter() {
        this(0,0);
    }
    static private class Position {
        public int X ;
        public int Y ;
        // standard getters and setters
    }

}
