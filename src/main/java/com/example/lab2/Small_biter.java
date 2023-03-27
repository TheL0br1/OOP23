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
    private Position position = new Position(0,0);
    private double directionR = 0;
    private double speed = 0;
    private final int id;
    private Action action;
    private static final int MaxSpeed;

    private final static  String name = "Small_byter";
   // private final Image spriteImage = new Image(getClass().getResource("main/sprites/Small_biter.png").toExternalForm());

    static{
        count = 0;
        MaxSpeed = 100;
        System.out.println("Class statis initialization");
    }
    public Small_biter(int posX, int posY) {
        position.X = posX;
        position.Y = posY;
        action = Action.NEUTRAL;
        id = count;
        count++;

    }
    public Small_biter() {
        this(0,0);
    }
    public String getName(){
        return name;
    }
    public Position getPosition(){
        return position;
    }
    public void setPosition(int x, int y){
        position.X = x;
        position.Y = y;
    }
    public void setPositionX(int y){
        setPosition(position.X, y);
    }
    public void setPositionY(int x){
        setPosition(x, position.Y);
    }
    public void setDirection(double degreeR){
        directionR = degreeR;
    }
    public double getDirection(){
        return directionR;
    }
    public void setAction(Action action){
        this.action = action;
    }
    public Action getAction(Action action) {
        return action;
    }
    public int getId(){
        return id;
    }
    public void move(int t){
        move(t, directionR);
    }
    public void move(int t, double dir){

        position.X = (int)(t * Math.cos(dir));
        position.Y = (int)(t * Math.sin(dir));

    }
    public boolean equals(Small_biter a, Small_biter b){
        return a == b;
    }
    public String toString()
    {
        return name + ", located at: " + this.position.X + " - X, " + this.position.Y + " - Y";
    }
    static private class Position {
        public int X = 0 ;
        public int Y = 0;
        public Position(int x, int y){
                X = x;
                Y = y;
        }

        // standard getters and setters
    }

}