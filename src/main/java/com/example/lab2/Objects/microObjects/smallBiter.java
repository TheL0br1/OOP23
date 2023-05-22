package com.example.lab2.Objects.microObjects;

import com.example.lab2.main;

import java.io.*;


enum Action {
    ATTACK,
    DEFENSE,
    NEUTRAL,
    RUN
}

public class smallBiter implements Serializable, Comparable<smallBiter>, Cloneable {


    private static int count;
    private double directionR = 0;
    private double speed = 50;
    private final int id;
    private Action action;



    private int armor;
    public final static int maxHealth;
    public final static int maxDamage;
    public final static int maxArmor;

    private int health;
    private int damage;



    private boolean active;
    private static final int MaxSpeed;

    private String name;


    static {
        count = 0;
        MaxSpeed = 100;
        maxDamage = 100;
        maxHealth = 1000;
        maxArmor = 2000;
        System.out.println("Class statis initialization");
    }

    public smallBiter(String name, int health, int damage, int armor) throws IOException {
        this.setName(name);
        this.setAction(Action.NEUTRAL);
        this.setArmor(armor);
        setActive(true);
        id = count;
        this.setHealth(health);
        this.setDamage(damage);
        count++;
        main.writer.write("Added new micro: " + this.toString()+"\n");
        main.writer.flush();

    }
    public smallBiter(smallBiter a) throws IOException {
        this(a.getName(), a.getHealth(), a.getDamage(),a.getArmor());
    }
    public smallBiter() throws IOException {
        this("Small_byter", maxHealth, maxDamage, maxArmor);
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

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    static public smallBiter clone(smallBiter prototype) throws IOException, ClassNotFoundException {

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

    @Override
    public String toString() {
        return "smallBiter{" +
                "directionR=" + directionR +
                ", speed=" + speed +
                ", id=" + id +
                ", action=" + action +
                ", armor=" + armor +
                ", health=" + health +
                ", damage=" + damage +
                ", active=" + active +
                ", name='" + name + '\'' +
                '}';
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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(smallBiter o) {
        return Integer.compare(this.getId(), o.getId());
    }



}
