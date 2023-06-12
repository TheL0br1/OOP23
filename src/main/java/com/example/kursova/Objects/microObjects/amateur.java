package com.example.kursova.Objects.microObjects;

import com.example.kursova.main;

import java.io.*;


enum Action {
    ATTACK,
    DEFENSE,
    NEUTRAL,
    RUN
}

public class amateur implements Serializable, Comparable<amateur>, Cloneable {


    public final static int maxMoney;
    public final static int maxHealth;
    public final static int maxDamage;
    private static final int MaxSpeed;
    private static int count;

    static {
        count = 0;
        MaxSpeed = 100;
        maxDamage = 100;
        maxHealth = 1000;
        maxMoney = 2000;
        System.out.println("Class statis initialization");
    }

    private final int id;
    private double directionR = 0;
    private double speed = 50;
    private Action action;
    private int health;
    private int damage;
    private boolean active;
    private String name;

    private int money;

    public amateur(String name, int health, int damage, int money) throws IOException {
        this.setName(name);
        this.setAction(Action.NEUTRAL);
        this.setMoney(money);
        setActive(true);
        id = count;
        this.setHealth(health);
        this.setDamage(damage);
        count++;
        main.writer.write("Added new micro: " + this + "\n");
        main.writer.flush();

    }

    public amateur(amateur a) throws IOException {
        this(a.getName(), a.getHealth(), a.getDamage(), a.getMoney());
    }

    public amateur() throws IOException {
        this("Small_byter", maxHealth, maxDamage, maxMoney);
    }

    static public amateur clone(amateur prototype) throws IOException, ClassNotFoundException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(prototype);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        return ((amateur) in.readObject());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDirection() {
        return getDirectionR();
    }

    public void setDirection(double degreeR) {
        setDirectionR(degreeR);
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

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean equals(amateur a, amateur b) {
        return a == b;
    }

    @Override
    public String toString() {
        return "amateur{" +
                "directionR=" + directionR +
                ", speed=" + speed +
                ", id=" + id +
                ", action=" + action +
                ", money=" + money +
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

    public void setAction(Action action) {
        this.action = action;
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

    @Override
    public int compareTo(amateur o) {
        return Integer.compare(this.getId(), o.getId());
    }


}
