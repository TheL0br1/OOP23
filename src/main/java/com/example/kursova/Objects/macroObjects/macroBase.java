package com.example.kursova.Objects.macroObjects;

import com.example.kursova.Objects.Position;
import com.example.kursova.Objects.microObjects.amateur;
import com.example.kursova.main;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

abstract public class macroBase {
    private final ArrayList<amateur> entities;
    private Position position;
    private int money = 10000;

    abstract double getMultiplication();
    private static final double multiplication = 0;
    private int speedTransmission=5;
    private Canvas canvas;
    abstract Image getImage();
    private final Image image;

    public macroBase(Position position, Image image) {
        this.money = (int) (money * getMultiplication());
        this.speedTransmission *= getMultiplication();
        this.image = image;
        this.position = position;
        this.entities = new ArrayList<>();
        setCanvas(new Canvas(image.getWidth(), image.getHeight() + 50));
        getCanvas().setLayoutX(position.X);
        getCanvas().setLayoutY(position.Y);
        main.root.getChildren().add(getCanvas());
        draw();

    }
    public void draw() {
        GraphicsContext gc = getCanvas().getGraphicsContext2D();
        gc.clearRect(0, 0, getCanvas().getWidth(), getCanvas().getHeight());
        gc.fillText(this.getClass().getSimpleName(), 10, 10);
        gc.setFill(Color.GOLD);
        gc.fillRect(0, 15, (double) money / (10000 * getMultiplication()) * getCanvas().getWidth(), 22);
        gc.drawImage(getImage(), 0, 25);
    }


    public Canvas getCanvas() {
        return canvas;
    }

    public void giveMoney(amateur en) {
        if (getMoney() > 0 && en.isActive() && isContains(en)) {
            en.setMoney(en.getMoney() + speedTransmission);
            setMoney(getMoney() - speedTransmission);
        }
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    public void addEntity(amateur ent) {
        this.entities.add(ent);
    }

    public void removeEntity(amateur ent) {
        this.entities.remove(ent);
    }

    public boolean isContains(amateur ent) {
        for (amateur a : this.entities) {
            if (a.equals(ent)) {
                return true;
            }
        }
        return false;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
