
package com.example.lab2.Objects;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.Serializable;


public class Sprite implements Serializable {
    private Image spriteSheet;
    private int frameWidth;
    private int frameHeight;


    private Objects objects;

    private int currentFrame;
    private int numFrames;
    private double xPos;
    private double yPos;

    public Sprite(Objects objects, int numFrames, double xPos, double yPos) {
        this.objects = objects;
        this.spriteSheet = objects.getImage();
        this.frameWidth = (int) objects.getImage().getWidth();
        this.frameHeight = (int) objects.getImage().getHeight() + 40;
        this.numFrames = numFrames;
        this.xPos = xPos;
        this.yPos = yPos;

    }

    public void update() {
        currentFrame = (currentFrame + 1) % numFrames;
    }

    public void render() {
        GraphicsContext gc = objects.getCanvas().getGraphicsContext2D();
        this.render(gc);
    }

    private void render(GraphicsContext gc) {
        gc.clearRect(0, 0, objects.getCanvas().getWidth(), objects.getCanvas().getHeight());
        int x = currentFrame * frameWidth;
        int y = 0;
        gc.fillText(objects.e.getName(), 0, 10);

        gc.setFill(Color.GREEN);
        gc.fillRect(0, 20, (double) objects.e.getHealth() / objects.e.maxHealth * frameWidth, (double) 20);
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 60, (double) objects.e.getArmor() / objects.e.maxArmor * frameWidth, (double) 20);

        gc.drawImage(spriteSheet, x, y, frameWidth, frameHeight, 0, 100, frameWidth, frameHeight);
    }

    public void changeActive() {
        GraphicsContext gc = objects.getCanvas().getGraphicsContext2D();
        if (objects.isActive()) {
            ColorAdjust grayScale = new ColorAdjust();
            grayScale.setSaturation(-1.0);
            gc.setEffect(grayScale);
        } else {
            gc.setEffect(null);
        }
        render();
    }
}

