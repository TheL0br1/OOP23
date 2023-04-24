
package com.example.lab2;



import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;



public class Sprite {
    private Image spriteSheet;
    private int frameWidth;
    private int frameHeight;


    private Small_biter entity;

    private int currentFrame;
    private int numFrames;
    private double xPos;
    private double yPos;

    private String name;
    public Sprite(Small_biter entity, int frameHeight, int numFrames, double xPos, double yPos) {
        this.entity = entity;
        this.spriteSheet = entity.getImage();
        this.frameWidth = (int)entity.getImage().getWidth();
        this.frameHeight = (int)entity.getImage().getHeight()+40;
        this.numFrames = numFrames;
        this.xPos = xPos;
        this.yPos = yPos;
        this.name = entity.getName();


    public void update() {
        currentFrame = (currentFrame + 1) % numFrames;
    }
    public void render() {
        GraphicsContext gc = entity.canvas.getGraphicsContext2D();
        this.render(gc);
    }

    private void render(GraphicsContext gc) {
         int x = currentFrame * frameWidth;
        int y = 0;
        gc.fillText(name, 0, 10);

        gc.setFill(Color.GREEN);
        gc.fillRect(0,20, (double)entity.getHealth()/entity.maxHealth * frameWidth, (double)20 );

        gc.drawImage(spriteSheet, x, y, frameWidth, frameHeight, 0, 40, frameWidth, frameHeight);
    }
}

