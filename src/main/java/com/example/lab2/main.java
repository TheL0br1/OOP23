
package com.example.lab2;


import com.example.lab2.Controllers.initMicro;
import com.example.lab2.Objects.Objects;
import com.example.lab2.Objects.Position;
import com.example.lab2.Objects.macroObjects.macroBase;
import com.example.lab2.Objects.macroObjects.nuclearReactor;
import com.example.lab2.Objects.macroObjects.steamEngine;
import com.example.lab2.Objects.macroObjects.steamTurbine;
import com.example.lab2.Objects.microObjects.smallBiter;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


//52, 13, 17, 26, 40
//39,77,91,11,25
public class main extends Application {

    public static final int CANVAS_WIDTH;
    public static final int CANVAS_HEIGHT;
    public static ArrayList<Objects> Entities = new ArrayList<>();
    public static ArrayList<macroBase> macroObjects = new ArrayList<>();
    public static Stage stage;
    public static Scene scene;
    public static Canvas miniMapCanvas;
    public static BorderPane menu = new BorderPane();
    public static BorderPane minimap;

    public static Group mainRoot = new Group();
    private static double zoom;


    public static Group root = new Group();
    public static Scanner in;
    public static BufferedWriter writer;

    static {
        zoom = 1;
        CANVAS_WIDTH = (int) (2.5 * 1280);
        CANVAS_HEIGHT = 5 * 720;
        in = new Scanner(System.in);
        System.out.println("static method initialized");
        try {
            writer = new BufferedWriter(new FileWriter("log.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void moveAll(double directionR) {
        Entities.forEach(En -> {
            try {
                En.move(directionR);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void renderAll() {

        Entities.forEach(En -> En.getSprite().render());
        macroObjects.forEach(macroBase::draw);
        for (Objects En : Entities) {
            for (macroBase macro : macroObjects) {
                if (En.getCanvas().getBoundsInParent().intersects(
                        macro.getCanvas().getBoundsInParent())) {
                    if (!macro.isContains(En.e)) {
                        macro.addEntity(En.e);
                    }
                    macro.giveArmor(En.e);
                }
            }
        }
    }

    private static void serializeImage() {

        menu.setVisible(false);
        GraphicsContext gc = miniMapCanvas.getGraphicsContext2D();
        gc.drawImage(stage.getScene().snapshot(null), 5, 0, 200 - 5, 260);
        gc.setStroke(Color.RED);
        gc.setLineWidth(5.0);
        gc.strokeRect(0, 0, miniMapCanvas.getWidth(), miniMapCanvas.getHeight() - 40);
        menu.setVisible(true);
    }


    public static void createEntity(String name, int x, int y, int health, int damage, int armor) throws IOException {
        Objects temp = new Objects(new smallBiter(name, health, damage, armor), x, y);
        Entities.add(temp);
        System.out.println(temp.e.toString());
    }

    public static void changeEntityActive() {
        Entities.forEach(En -> {
            En.getSprite().changeActive();
            En.setActive(!En.isActive());
        });

    }

    public static void deleteEntities() {
        Entities.forEach(En -> {
            root.getChildren().remove(En.getCanvas());
            try {
                writer.write("Delete micro: " + En.toString()+"\n");
                main.writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Rectangle rect = new Rectangle(
                    En.getCanvas().getLayoutX() + En.getCanvas().getTranslateX(),
                    En.getCanvas().getLayoutY() + En.getCanvas().getTranslateY(),
                    En.getCanvas().getWidth(),
                    En.getCanvas().getHeight());
            rect.setFill(null);
            rect.setStroke(Color.RED);
            rect.setStrokeWidth(3);
            root.getChildren().add(rect);

        });
        Entities.clear();
    }


    @Override
    public void start(Stage stage) {
        main.stage = stage;
        stage.setWidth(main.CANVAS_WIDTH);
        stage.setHeight(main.CANVAS_HEIGHT);
        stage.setY(0);
        stage.setX(0);
        stage.setTitle("lab3 Barasiy");

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 99_999_999_9L / 30) {
                    renderAll();
                }
            }
        };
        AnimationTimer timer2 = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 99_999_999_9L / 5) {
                    lastUpdate = now;
                    serializeImage();
                }
            }
        };
        timer2.start();

        // Запускаем таймер
        timer.start();
        miniMapCanvas = new Canvas(200, 300);


        menu.setTop(miniMapCanvas);
        mainRoot.getChildren().add(menu);
        mainRoot.getChildren().add(root);

        scene = new Scene(mainRoot, CANVAS_WIDTH, CANVAS_HEIGHT, Color.WHITE);
        menu.setLayoutY(CANVAS_HEIGHT / 5 - miniMapCanvas.getHeight() - 30 + 40);

        scene.setOnScroll(event -> {
            double delta = event.getDeltaY();
            root.translateZProperty().set(root.getTranslateZ() + delta);
        });
        scene.setOnMouseClicked(mouseEvent -> {
            System.out.println("x: " + mouseEvent.getSceneX());
            System.out.println("y: " + mouseEvent.getSceneY());

        });
        scene.setOnKeyPressed(event ->
        {
            {
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
                            main.deleteEntities();
                        }
                    }
                    case DOWN -> {
                        if (event.isControlDown() && stage.getY() - 40 > -CANVAS_HEIGHT + 720) {
                            stage.setY(stage.getY() - 40);
                            Image bottle = new Image(main.class.getResourceAsStream("bottle.png"), 25, 80, true, true);
                            GraphicsContext gc = miniMapCanvas.getGraphicsContext2D();
                            gc.drawImage(bottle, 60, 220, 85, 300);
                            menu.setTranslateY(menu.getTranslateY() + 40);

                            break;
                        }
                        main.moveAll(Math.PI / 2);
                    }
                    case UP -> {
                        if (event.isControlDown() && stage.getY() + 15 < -0 + 15) {
                            menu.setTranslateY(menu.getTranslateY() - 15);

                            stage.setY(stage.getY() + 15);

                            break;
                        }
                        main.moveAll(Math.PI * 1.5);
                    }
                    case LEFT -> {
                        if (event.isControlDown() && root.getTranslateX() - 10 > 0) {
                            root.setTranslateX(root.getTranslateX() - 10);
                            break;
                        }
                        main.moveAll(Math.PI * 0);
                    }
                    case RIGHT -> {
                        if (event.isControlDown() && root.getTranslateX() + 10 + 1280 < CANVAS_WIDTH) {
                            root.setTranslateX(root.getTranslateX() + 10);
                            break;
                        }
                        main.moveAll(Math.PI);
                    }
                    case ESCAPE -> main.changeEntityActive();
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
                }
            }
        });
        steamEngine a = new steamEngine(new Position(0, 10));
        steamTurbine b = new steamTurbine(new Position(900, 500));
        nuclearReactor c = new nuclearReactor(new Position(900, 10));
        stage.setScene(scene);

        macroObjects.add(a);
        macroObjects.add(b);
        macroObjects.add(c);


        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}