package com.example.lab2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//52, 13, 17, 26, 40
//39,77,91,11,25
public class main extends Application {

    public static final int CANVAS_WIDTH;
    public static final int CANVAS_HEIGHT;
    public static ArrayList<Object> Entities = new ArrayList<>();

    public static Stage stage;
    public static Scene scene;

    public static Group root;
    public static Scanner in;

    static {
        CANVAS_WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
        CANVAS_HEIGHT = (int) (Screen.getPrimary().getBounds().getHeight());
        in = new Scanner(System.in);
        System.out.println("static metod initializated");
    }
    public static void renderAll(){
        Entities.forEach(En->{
            En.getSprite().render();
        });
    }
    public static void create_entity(String name, int x, int y, int health, int damage) {
        Object temp = new Object(new smallBiter(name,health, damage),x,y);
        Entities.add(temp);
        System.out.println(temp.e.toString());
    }

    @Override
    public void start(Stage stage) {
        main.stage = stage;
        stage.setWidth(main.CANVAS_WIDTH);
        stage.setHeight(main.CANVAS_HEIGHT);
        stage.setTitle("джава - залупа");

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                renderAll(); // Вызываем метод обновления спрайтов
            }
        };

        // Запускаем таймер
        timer.start();
        root = new Group();
        scene = new Scene(root, Color.WHITE);

        stage.setScene(scene);
        scene.setOnKeyPressed(event ->
        {
            {
                switch (event.getCode()) {
                    case INSERT:

                        if (event.isControlDown()) {
                            try {
                                System.out.println("initMicro display");
                                initMicro.display();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case P:
                }
            }
        });
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}