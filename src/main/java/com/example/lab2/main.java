package com.example.lab2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import java.util.Scanner;

public class main extends Application {

    public static final int CANVAS_WIDTH;
    public static final int CANVAS_HEIGHT;
    public static Scanner in;
    static{
        CANVAS_WIDTH = 460;
        CANVAS_HEIGHT = 500;
        in =  new Scanner(System.in);
        System.out.println("static metod initializated");
    }
    @Override
    public void start(Stage stage) throws Exception{
        Canvas canvas = new Canvas(400, 300);
        Image spriteImage = new Image(getClass().getResource("Small_biter.png").toExternalForm());
        if (spriteImage == null) {
            System.err.println("Failed to load sprite image");
            System.exit(1);
        }
        Sprite sprite = new Sprite(spriteImage, 120, 120, 2 , 100, 100);
        sprite.render(canvas);
        //ImageView spriteImageView = new ImageView(spriteImage);
        //spriteImageView.setX(100);
        //spriteImageView.setY(100
        Group root = new Group();
        Scene scene = new Scene(root,  Color.WHITE);
        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}