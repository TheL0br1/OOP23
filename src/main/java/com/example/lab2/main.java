package com.example.lab2;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
//52, 13, 17, 26, 40
public class main extends Application {

    public static final int CANVAS_WIDTH;
    public static final int CANVAS_HEIGHT;
    public static ArrayList<Small_biter> Entities = new ArrayList<Small_biter>();
    public static Image spriteImage;

    public static Stage stage;
    public static Scene scene;

    public static Group root;
    public static Scanner in;
    static{
        CANVAS_WIDTH = (int)Screen.getPrimary().getBounds().getWidth();
        CANVAS_HEIGHT = (int)(Screen.getPrimary().getBounds().getHeight());
        in =  new Scanner(System.in);
        System.out.println("static metod initializated");
    }
    public static void create_entity(int x, int y){
        Entities.add( new Small_biter(x,y));
    }
    @Override
    public void start(Stage stage) throws Exception {
        main.stage = stage;
        stage.setWidth(main.CANVAS_WIDTH);
        stage.setHeight(main.CANVAS_HEIGHT);
        Parent root1 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Hello World");
        Scene input = new Scene(root1,main.CANVAS_HEIGHT,main.CANVAS_WIDTH);


        root = new Group();
        scene = new Scene(root, Color.WHITE);
        System.out.println(scene.getHeight());
        System.out.println(scene.getWidth());

        stage.setScene(scene);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                for ( Small_biter biter: main.Entities ){
                    biter.getSprite().render(biter.canvas);
                }
                {
                switch (event.getCode()) {
                    case INSERT:
                        if (event.isControlDown()) {
                            try {
                                System.out.println("handler");
                                initMicro.display();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                }
            }
        }});
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}