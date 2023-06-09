package com.example.kursova.Objects.macroObjects;

import com.example.kursova.Objects.Position;
import com.example.kursova.Objects.microObjects.ikra;
import com.example.kursova.main;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

abstract public class macroBase {
    private final ArrayList<ikra> entities;
    private Position position;
    private int armor=10000;

    abstract double getMultiplication();
    private static final double multiplication = 0;
    private int speedTransmission=5;
    private Canvas canvas;
    abstract Image getImage();
    private final Image image;

    public macroBase(Position position, Image image) {
        this.armor = (int) (armor * getMultiplication() );
        this.speedTransmission*=getMultiplication();
        this.image = image;
        this.position = position;
        this.entities = new ArrayList<>();
        setCanvas(new Canvas(image.getWidth(), image.getHeight()+50));
        getCanvas().setLayoutX(position.X);
        getCanvas().setLayoutY(position.Y);
        main.root.getChildren().add(getCanvas());
        draw();

    }
    public void draw(){
        GraphicsContext gc = getCanvas().getGraphicsContext2D();
        gc.clearRect(0, 0, getCanvas().getWidth(), getCanvas().getHeight());
        gc.fillText(this.getClass().getSimpleName(), 10, 10);
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 15, (double)armor/(10000*getMultiplication()) * getCanvas().getWidth(), 22);
        gc.drawImage(getImage(), 0, 25);
    }


    public Canvas getCanvas() {
        return canvas;
    }

    public void giveArmor(ikra en) {
        if (getArmor() > 0 && en.isActive() && isContains(en)) {
            en.setArmor(en.getArmor() + speedTransmission);
            setArmor(getArmor() - speedTransmission);
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


    public void addEntity(ikra ent) {
        this.entities.add(ent);
    }

    public void removeEntity(ikra ent) {
        this.entities.remove(ent);
    }

    public boolean isContains(ikra ent) {
        for (ikra a : this.entities) {
            if (a.equals(ent)) {
                return true;
            }
        }
        return false;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}
