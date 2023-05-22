package com.example.lab2;

import com.example.lab2.Controllers.initMicro;
import com.example.lab2.Objects.Object;
import com.example.lab2.Objects.Position;
import com.example.lab2.Objects.macroObjects.macro1;
import com.example.lab2.Objects.macroObjects.macro2;
import com.example.lab2.Objects.macroObjects.macro3;
import com.example.lab2.Objects.macroObjects.macroBase;
import com.example.lab2.Objects.microObjects.micro1;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Screen;
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
    public static ArrayList<Object> Entities = new ArrayList<>();
    public static ArrayList<macroBase> macroObjects = new ArrayList<>();
    public static Stage stage;
    public static Scene scene;

    public static Group root;
    public static Scanner in;
    public static BufferedWriter writer;

    static {
        CANVAS_WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
        CANVAS_HEIGHT = (int) (Screen.getPrimary().getBounds().getHeight());
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

    public static void renderAll() {

        Entities.forEach(En -> En.getSprite().render());
        macroObjects.forEach(macroBase::draw);
        for (Object En: Entities) {
            for(macroBase macro: macroObjects)
            {
                if(En.getCanvas().getBoundsInParent().intersects(
                        macro.getCanvas().getBoundsInParent())){
                    if(!macro.isContains(En.e)){
                        macro.addEntity(En.e);
                    }
                    macro.giveArmor(En.e);
                }
            }
        }
    }

    public static void createEntity(String name, int x, int y, int health, int damage, int armor) throws IOException {
        Object temp = new Object(new micro1(name, health, damage, armor), x, y);
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
                    case UP -> {
                        if (event.isControlDown()) {
                            root.setTranslateY(root.getTranslateY() - 10);
                            break;
                        }
                        main.moveAll(Math.PI / 2);
                    }
                    case DOWN -> {
                        if (event.isControlDown()) {
                            root.setTranslateY(root.getTranslateY() + 10);
                            break;
                        }
                        main.moveAll(Math.PI * 1.5);
                    }
                    case LEFT -> {
                        if (event.isControlDown()) {
                            root.setTranslateX(root.getTranslateX() - 10);
                            break;
                        }
                        main.moveAll(Math.PI * 0);
                    }
                    case RIGHT -> {
                        if (event.isControlDown()) {
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
        macro1 a = new macro1(new Position(0,0));
        macro2 b = new macro2(new Position(900,500));
        macro3 c = new macro3(new Position(900,0));


        macroObjects.add(a);
        macroObjects.add(b);
        macroObjects.add(c);

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}