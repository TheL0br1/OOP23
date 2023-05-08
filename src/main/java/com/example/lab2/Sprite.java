
package com.example.lab2;



import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.Serializable;


public class Sprite implements Serializable {
    private Image spriteSheet;
    private int frameWidth;
    private int frameHeight;


    private  transient Object object;

    private int currentFrame;
    private int numFrames;
    private double xPos;
    private double yPos;

    private String name;
    public Sprite(Object object, int frameHeight, int numFrames, double xPos, double yPos) {
        this.object = object;
        this.spriteSheet = object.getImage();
        this.frameWidth = (int) object.getImage().getWidth();
        this.frameHeight = (int) object.getImage().getHeight() + 40;
        this.numFrames = numFrames;
        this.xPos = xPos;
        this.yPos = yPos;
        this.name = object.e.getName();

    }
    public void update() {
        currentFrame = (currentFrame + 1) % numFrames;
    }
    public void render() {
        GraphicsContext gc = object.getCanvas().getGraphicsContext2D();
        this.render(gc);
    }

    private void render(GraphicsContext gc) {
        gc.clearRect(0,0,object.getCanvas().getWidth(), object.getCanvas().getHeight());
         int x = currentFrame * frameWidth;
        int y = 0;
        gc.fillText(name, 0, 10);

        gc.setFill(Color.GREEN);
        gc.fillRect(0,20, (double)object.e.getHealth()/object.e.maxHealth * frameWidth, (double)20 );

        gc.drawImage(spriteSheet, x, y, frameWidth, frameHeight, 0, 40, frameWidth, frameHeight);
    }
}

