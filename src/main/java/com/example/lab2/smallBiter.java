package com.example.lab2;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.*;


enum Action {
    ATTACK,
    DEFENSE,
    NEUTRAL,
    RUN
}

public class smallBiter implements Serializable {


    private static int count;
    private Position position = new Position(0, 0);
    private double directionR = 0;
    private double speed = 0;
    private final int id;
    private Action action;

    public final static int maxHealth;
    public final static int maxDamage;

    private int health;
    private int damage;

    private boolean active;
    private static final int MaxSpeed;
    private boolean isDragging = false;

    public Canvas canvas;

    private String name;
    private final Image spriteImage = new Image(getClass().getResource("Small_biter.png").toExternalForm());
    private Sprite sprite;

    static {
        count = 0;
        MaxSpeed = 100;
        maxDamage = 100;
        maxHealth = 1000;
        System.out.println("Class statis initialization");
    }

    public smallBiter(String name, int posX, int posY, int health, int damage) {
        position.X = posX;
        position.Y = posY;
        this.name = name;
        action = Action.NEUTRAL;
        active = true;
        id = count;
        this.health = health;
        this.damage = damage;
        count++;
        canvas = new Canvas(spriteImage.getWidth(), spriteImage.getHeight() + 40);
        canvas.setLayoutX(posX);
        canvas.setLayoutY(posY);
        main.root.getChildren().add(canvas);

        canvas.setOnMousePressed(event -> {
            if(!active) { return; }
            if (event.isSecondaryButtonDown()) { // Перевірка нажаття правої кнопки миші
                position.X = (int) event.getX();
                position.Y = (int) event.getY();
                isDragging = true;

            }
            if (event.isPrimaryButtonDown()) {
                main.root.getChildren().remove(canvas);
                Rectangle rect = new Rectangle(
                        canvas.getLayoutX() + canvas.getTranslateX(),
                        canvas.getLayoutY() + canvas.getTranslateY(),
                        canvas.getWidth(),
                        canvas.getHeight());
                rect.setFill(null);
                rect.setStroke(Color.RED);
                rect.setStrokeWidth(3);
                main.root.getChildren().add(rect);
                main.Entities.remove(this);
            }
        });

        canvas.setOnMouseDragged(event -> {
            if(!active) { return; }

            if (isDragging) {
                // Перевірка нажаття правої кнопки миші
                double offsetX = (event.getX() - position.X) / 1.5;
                double offsetY = (event.getY() - position.Y) / 1.5;
                position.X = (int) event.getX();
                position.Y = (int) event.getY();
                canvas.setTranslateX(canvas.getTranslateX() + offsetX);
                canvas.setTranslateY(canvas.getTranslateY() + offsetY);

            }
        });
        canvas.setOnMouseReleased(event -> {
            if(!active) { return; }
            isDragging = false;
        });
        sprite = new Sprite(this, 320, 1, 0, 0);
        sprite.render();

    }

    public smallBiter() {
        this("Small_byter", 0, 0, maxHealth, maxDamage);
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        position.X = x;
        position.Y = y;
    }

    public void setPositionX(int y) {
        setPosition(position.X, y);
    }

    public void setPositionY(int x) {
        setPosition(x, position.Y);
    }

    public void setDirection(double degreeR) {
        directionR = degreeR;
    }

    public double getDirection() {
        return directionR;
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

    public Sprite getSprite() {
        return sprite;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public Image getImage() {
        return spriteImage;
    }

    public void move(int t) {
        move(t, directionR);
    }

    public void move(int t, double dir) {

        position.X = (int) (t * Math.cos(dir) * speed);
        position.Y = (int) (t * Math.sin(dir) * speed);

    }

    public smallBiter deepCopy(smallBiter prototype) throws IOException, ClassNotFoundException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(prototype);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        return (smallBiter) in.readObject();


    }

    public boolean equals(smallBiter a, smallBiter b) {
        return a == b;
    }

    public String toString() {
        return name + ", located at: " + this.position.X + " - X, " + this.position.Y + " - Y. My id: " + this.id;
    }

    static private class Position {
        public int X = 0;
        public int Y = 0;

        public Position(int x, int y) {
            X = x;
            Y = y;
        }

        // standard getters and setters
    }

}
