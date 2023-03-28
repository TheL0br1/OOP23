package com.example.lab2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Scanner;
//52, 13, 17, 26, 40
public class main extends Application {

    public static final int CANVAS_WIDTH;
    public static final int CANVAS_HEIGHT;
    public static final String spriteDirectory;
    public static ArrayList<Small_biter> Entities;
    public static final Image spriteImage;

    public static Scanner in;
    static{
        CANVAS_WIDTH = 460;
        CANVAS_HEIGHT = 500;
        in =  new Scanner(System.in);
        System.out.println("static metod initializated");
        spriteDirectory = System.getProperty("user.dir") + "\\src\\main\\sprites";
        spriteImage = new Image("file:" +spriteDirectory+ "\\Small_biter.png");
    }
    public static void create_entity(int x, int y){
        Entities.add( new Small_biter(x,y));
    }
    @Override
    public void start(Stage stage) throws Exception{

        Parent root1 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene input = new Scene(root1,500,500);

        Canvas canvas = new Canvas(400, 300);

        Sprite sprite = new Sprite(spriteImage, 120, 120, 2 , 100, 100);
        sprite.render(canvas);
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