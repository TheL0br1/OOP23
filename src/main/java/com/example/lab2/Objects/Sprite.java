
package com.example.lab2.Objects;


import com.example.lab2.Objects.Object;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.Serializable;


public class Sprite implements Serializable {
    private Image spriteSheet;
    private int frameWidth;
    private int frameHeight;


    private Object object;

    private int currentFrame;
    private int numFrames;
    private double xPos;
    private double yPos;

    public Sprite(Object object, int frameHeight, int numFrames, double xPos, double yPos) {
        this.object = object;
        this.spriteSheet = object.getImage();
        this.frameWidth = (int) object.getImage().getWidth();
        this.frameHeight = (int) object.getImage().getHeight() + 40;
        this.numFrames = numFrames;
        this.xPos = xPos;
        this.yPos = yPos;

    }

    public void update() {
        currentFrame = (currentFrame + 1) % numFrames;
    }

    public void render() {
        GraphicsContext gc = object.getCanvas().getGraphicsContext2D();
        this.render(gc);
    }

    private void render(GraphicsContext gc) {
        gc.clearRect(0, 0, object.getCanvas().getWidth(), object.getCanvas().getHeight());
        int x = currentFrame * frameWidth;
        int y = 0;
        gc.fillText(object.e.getName(), 0, 10);

        gc.setFill(Color.GREEN);
        gc.fillRect(0, 20, (double) object.e.getHealth() / object.e.maxHealth * frameWidth, (double) 20);
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 60, (double) object.e.getArmor() / object.e.maxArmor * frameWidth, (double) 20);

        gc.drawImage(spriteSheet, x, y, frameWidth, frameHeight, 0, 100, frameWidth, frameHeight);
    }

    public void changeActive() {
        GraphicsContext gc = object.getCanvas().getGraphicsContext2D();
        if (object.isActive()) {
            ColorAdjust grayScale = new ColorAdjust();
            grayScale.setSaturation(-1.0);
            gc.setEffect(grayScale);
        } else {
            gc.setEffect(null);
        }
        render();
    }
}

