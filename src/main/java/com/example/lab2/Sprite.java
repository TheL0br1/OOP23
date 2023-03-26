
package com.example.lab2;



import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Sprite {
    private Image spriteSheet;
    private int frameWidth;
    private int frameHeight;
    private int currentFrame;
    private int numFrames;
    private double xPos;
    private double yPos;

    public Sprite(Image spriteSheet, int frameWidth, int frameHeight, int numFrames, double xPos, double yPos) {
        this.spriteSheet = spriteSheet;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.numFrames = numFrames;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void update() {
        currentFrame = (currentFrame + 1) % numFrames;
    }
    public void render(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, main.CANVAS_WIDTH, main.CANVAS_HEIGHT);
        this.render(gc);
    }

    private void render(GraphicsContext gc) {
        int x = currentFrame * frameWidth;
        int y = 0;
        gc.drawImage(spriteSheet, x, y, frameWidth, frameHeight, xPos, yPos, frameWidth, frameHeight);
    }
}

