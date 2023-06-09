package com.example.kursova;

import com.example.kursova.Additional.MyFunctions;
import com.example.kursova.Controllers.initMicro;
import com.example.kursova.Objects.Objects;
import com.example.kursova.Objects.Position;
import com.example.kursova.Objects.macroObjects.macroBase;
import com.example.kursova.Objects.macroObjects.nuclearReactor;
import com.example.kursova.Objects.macroObjects.steamEngine;
import com.example.kursova.Objects.macroObjects.steamTurbine;
import com.example.kursova.Objects.microObjects.mediumBiter;
import com.example.kursova.Objects.microObjects.smallBiter;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


//52, 13, 17, 26, 40
//39,77,91,11,25
public class main extends Application {

    public static final int STAGE_WIDTH;
    public static final int STAGE_HEIGHT;
    public static ArrayList<Objects> Entities = new ArrayList<>();
    public static ArrayList<macroBase> macroObjects = new ArrayList<>();
    public static Stage stage;
    public static Scene scene;
    public static Canvas miniMapCanvas;
    public static BorderPane menu = new BorderPane();

    public static Group mainRoot = new Group();


    public static Group root = new Group();
    public static Scanner in;
    public static BufferedWriter writer;
    public static double relateX;
    public static double relateY;

    public static Random random = new Random();

    static {
        STAGE_WIDTH = (int) (2.5 * Screen.getPrimary().getBounds().getWidth());
        STAGE_HEIGHT = (int) (5 * Screen.getPrimary().getBounds().getHeight());
        in = new Scanner(System.in);
        System.out.println("static method initialized");
        try {
            writer = new BufferedWriter(new FileWriter("log.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void extracted(Stage stage, MouseEvent mouseEvent) {
        double x = mouseEvent.getX() / relateX;
        double y = mouseEvent.getY() / relateY;
        if (x > STAGE_WIDTH - STAGE_WIDTH / 2.5) {
            x = STAGE_WIDTH - STAGE_WIDTH / 2.5;
        }
        if (y > STAGE_HEIGHT - STAGE_HEIGHT / 5) {
            y = STAGE_HEIGHT - STAGE_HEIGHT / 5;
        }
        stage.setX(-x);
        stage.setY(-y);
        menu.setTranslateX(x);
        menu.setTranslateY(y);
    }

    private static void extracted(Stage stage, KeyEvent event) {
        switch (event.getCode()) {
            case INSERT -> {
                if (event.isControlDown()) {
                    try {
                        System.out.println("initMicro display");
                        initMicro.display();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            case DELETE -> {
                if (event.isControlDown()) {
                    System.out.println("delete all entities from DELETE+CTRL button");
                    MyFunctions.deleteEntities();
                }
            }
            case DOWN -> {
                if (event.isControlDown() && stage.getY() - 15 > -STAGE_HEIGHT + 720) {
                    stage.setY(stage.getY() - 15);
                    menu.setTranslateY(menu.getTranslateY() + 15);
                    break;
                }
                if (!event.isControlDown()) {
                    MyFunctions.moveAll(Math.PI * 1.5);
                }
            }
            case UP -> {
                if (event.isControlDown() && stage.getY() + 15 < 15) {
                    menu.setTranslateY(menu.getTranslateY() - 15);
                    stage.setY(stage.getY() + 15);
                    break;
                }
                if (!event.isControlDown()) {
                    MyFunctions.moveAll(Math.PI / 2);
                }

            }
            case LEFT -> {
                if (event.isControlDown() && stage.getX() + 15 < 0) {
                    menu.setTranslateX(menu.getTranslateX() - 15);
                    stage.setX(stage.getX() + 15);
                    break;
                }
                if (!event.isControlDown()) {
                    MyFunctions.moveAll(Math.PI * 0);
                }
            }
            case RIGHT -> {
                if (event.isControlDown() && stage.getX() - 15 > -STAGE_WIDTH + STAGE_WIDTH / 2.5) {

                    menu.setTranslateX(menu.getTranslateX() + 15);
                    //  main.moveAll(Math.PI);
                    stage.setX(stage.getX() - 15);
                    break;
                }
                if (!event.isControlDown()) {
                    MyFunctions.moveAll(Math.PI);
                }
            }
            case ESCAPE -> MyFunctions.changeEntityActive();
            case Q -> {
                if (event.isControlDown()) {
                    FXMLLoader loader = new FXMLLoader(main.class.getResource("queryMenu.fxml"));
                    Parent rootQuery;

                    try {
                        rootQuery = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Stage window = new Stage();
                    window.initModality(Modality.APPLICATION_MODAL);
                    window.setTitle("Запити");
                    Scene scene = new Scene(rootQuery);
                    window.setScene(scene);
                    window.showAndWait();
                }
            }
            case F1 -> {
                MyFunctions.moveRandom();
            }
            case F2 -> {
                MyFunctions.moveToMacro();
            }
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        main.stage = stage;
        stage.setWidth(main.STAGE_WIDTH);
        stage.setHeight(main.STAGE_HEIGHT);
        stage.setY(0);
        stage.setX(0);
        stage.setTitle("lab4 Barasiy");

        AnimationTimer timer = new AnimationTimer() {
            private final long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 99_999_999_9L / 60) {

                    MyFunctions.renderAll();
                }
            }
        };
        AnimationTimer timer2 = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 99_999_999_9L / 5) {
                    lastUpdate = now;
                    MyFunctions.serializeImage();
                }
            }
        };
        timer2.start();

        // Запускаем таймер
        timer.start();
        miniMapCanvas = new Canvas(200, 260);


        menu.setTop(miniMapCanvas);
        mainRoot.getChildren().add(menu);
        mainRoot.getChildren().add(root);

        scene = new Scene(mainRoot, STAGE_WIDTH, STAGE_HEIGHT, Color.WHITE);
        menu.setLayoutY(STAGE_HEIGHT / 5 - miniMapCanvas.getHeight() - 30);
        miniMapCanvas.setOnMouseClicked(mouseEvent -> {
            extracted(stage, mouseEvent);
        });
        scene.setOnMouseClicked(mouseEvent -> {
            System.out.println("x: " + mouseEvent.getSceneX());
            System.out.println("y: " + mouseEvent.getSceneY());

        });
        scene.setOnKeyPressed(event -> {
            extracted(stage, event);
        });
        steamEngine a = new steamEngine(new Position(0, 10));
        steamTurbine b = new steamTurbine(new Position(900, 500));
        nuclearReactor c = new nuclearReactor(new Position(900, 10));
        stage.setScene(scene);
        relateX = miniMapCanvas.getWidth() / STAGE_WIDTH;
        relateY = miniMapCanvas.getHeight() / STAGE_HEIGHT;
        macroObjects.add(a);
        macroObjects.add(b);
        macroObjects.add(c);
        mediumBiter a1 = new mediumBiter();
        try {
            smallBiter a2 = smallBiter.clone(a1);
            System.out.println(a2 instanceof mediumBiter);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}