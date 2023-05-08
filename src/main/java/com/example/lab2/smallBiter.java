package com.example.lab2;

import java.io.*;


enum Action {
    ATTACK,
    DEFENSE,
    NEUTRAL,
    RUN
}

public class smallBiter implements Serializable {


    private static int count;
    private double directionR = 0;
    private double speed = 0;
    private final int id;
    private Action action;

    public final static int maxHealth;
    public final static int maxDamage;

    private int health;
    private int damage;

    public String toString() {
        return "smallBiter{" +
                "directionR=" + directionR +
                ", speed=" + speed +
                ", id=" + id +
                ", action=" + action +
                ", health=" + health +
                ", damage=" + damage +
                ", active=" + active +
                ", name='" + name + '\'' +
                '}';
    }

    private boolean active;
    private static final int MaxSpeed;

    private String name;


    static {
        count = 0;
        MaxSpeed = 100;
        maxDamage = 100;
        maxHealth = 1000;
        System.out.println("Class statis initialization");
    }

    public smallBiter(String name, int health, int damage) {
        this.name = name;
        setAction(Action.NEUTRAL);
        setActive(true);
        id = count;
        this.setHealth(health);
        this.setDamage(damage);
        count++;
    }
    public smallBiter(smallBiter a){
        this(a.getName(), a.getHealth(), a.getDamage());
    }
    public smallBiter() {
        this("Small_byter", maxHealth, maxDamage);
    }

    public String getName() {
        return name;
    }



    public void setDirection(double degreeR) {
        setDirectionR(degreeR);
    }

    public double getDirection() {
        return getDirectionR();
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Action getAction(Action action) {
        return action;
    }

    public int getId() {
        return id;
    }



    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }




    static public smallBiter deepCopy(smallBiter prototype) throws IOException, ClassNotFoundException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(prototype);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        return ((smallBiter)in.readObject());

    }

    public boolean equals(smallBiter a, smallBiter b) {
        return a == b;
    }





    public double getDirectionR() {
        return directionR;
    }

    public void setDirectionR(double directionR) {
        this.directionR = directionR;
    }

    public Action getAction() {
        return action;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
