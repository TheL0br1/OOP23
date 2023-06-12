package com.example.kursova;

import com.example.kursova.Additional.MyFunctions;
import com.example.kursova.Controllers.initMicro;
import com.example.kursova.Objects.Objects;
import com.example.kursova.Objects.Position;
import com.example.kursova.Objects.macroObjects.biteStore;
import com.example.kursova.Objects.macroObjects.fishStore;
import com.example.kursova.Objects.macroObjects.fishingPlace;
import com.example.kursova.Objects.macroObjects.macroBase;
import com.example.kursova.Objects.microObjects.amateur;
import com.example.kursova.Objects.microObjects.skiled;
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
import javafx.scene.shape.Rectangle;
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
        STAGE_WIDTH = (int) (Screen.getPrimary().getBounds().getWidth());
        STAGE_HEIGHT = (int) (Screen.getPrimary().getBounds().getHeight());
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
        if (x > 3000 - STAGE_WIDTH) {
            x = 3000 - STAGE_WIDTH;
        }
        if (y > 3000 - STAGE_HEIGHT) {
            y = 3000 - STAGE_HEIGHT;
        }
        root.setTranslateX(-x);
        root.setTranslateY(-y);

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
                if (event.isControlDown()) {
                    //stage.setY(stage.getY() - 15);
                    if (root.getTranslateY() + 15 < +15) {
                        root.setTranslateY(root.getTranslateY() + 15);
                    }
                    break;
                }
                if (!event.isControlDown()) {
                    MyFunctions.moveAll(Math.PI * 1.5);
                }
            }
            case UP -> {
                if (event.isControlDown()) {
                    if (root.getTranslateY() - 15 > -3000 + STAGE_HEIGHT) {
                        root.setTranslateY(root.getTranslateY() - 15);
                    }

                    //stage.setY(stage.getY() + 15);
                    break;
                }
                if (!event.isControlDown()) {
                    MyFunctions.moveAll(Math.PI / 2);
                }

            }
            case LEFT -> {
                if (event.isControlDown()) {
                    if (root.getTranslateX() + 15 < +15) {
                        root.setTranslateX(root.getTranslateX() + 15);

                    }
                    break;
                }
                if (!event.isControlDown()) {
                    MyFunctions.moveAll(Math.PI * 0);
                }
            }
            case RIGHT -> {
                if (event.isControlDown()) {

                    if (root.getTranslateX() - 15 > -3000 + STAGE_WIDTH) {
                        root.setTranslateX(root.getTranslateX() - 15);
                    }
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
                MyFunctions.deleteMove();
            }
            case F3 -> {
                MyFunctions.moveToMacro();
            }
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        Rectangle rect = new Rectangle(3000, 3000, Color.WHITE);
        main.stage = stage;
        root.getChildren().add(rect);
        stage.setWidth(main.STAGE_WIDTH);
        stage.setHeight(main.STAGE_HEIGHT);

        stage.setTitle("lab4");

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
        miniMapCanvas = new Canvas(200, 200);


        menu.setTop(miniMapCanvas);
        mainRoot.getChildren().add(menu);
        mainRoot.getChildren().add(root);

        scene = new Scene(mainRoot, STAGE_WIDTH, STAGE_HEIGHT, Color.WHITE);
        menu.setLayoutY(STAGE_HEIGHT - miniMapCanvas.getHeight() - 60);
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
        biteStore a = new biteStore(new Position(0, 10));
        fishStore b = new fishStore(new Position(900, 500));
        fishingPlace c = new fishingPlace(new Position(900, 10));
        stage.setScene(scene);
        relateX = miniMapCanvas.getWidth() / rect.getWidth();
        relateY = miniMapCanvas.getHeight() / rect.getHeight();
        macroObjects.add(a);
        macroObjects.add(b);
        macroObjects.add(c);
        skiled a1 = new skiled();
        try {
            amateur a2 = amateur.clone(a1);
            System.out.println(a2 instanceof skiled);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}