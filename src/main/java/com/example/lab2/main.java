package com.example.lab2;

import com.example.lab2.Controllers.initMicro;
import com.example.lab2.Objects.Object;
import com.example.lab2.Objects.Position;
import com.example.lab2.Objects.macroObjects.macro1;
import com.example.lab2.Objects.macroObjects.macro2;
import com.example.lab2.Objects.macroObjects.macroBase;
import com.example.lab2.Objects.microObjects.smallBiter;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    public static ArrayList<macroBase> macroObjects = new ArrayList<>();
    public static Stage stage;
    public static Scene scene;

    public static Group root;
    public static Scanner in;

    static {
        CANVAS_WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
        CANVAS_HEIGHT = (int) (Screen.getPrimary().getBounds().getHeight());
        in = new Scanner(System.in);
        System.out.println("static method initialized");
    }

    public static void moveAll(double directionR) {
        Entities.forEach(En -> En.move(directionR));
    }

    public static void renderAll() {

        Entities.forEach(En -> En.getSprite().render());
        macroObjects.forEach(macro->{
            macro.draw();
        });
        for (Object En: Entities) {
            for(macroBase macro: macroObjects)
            {
                if(En.getCanvas().getBoundsInParent().intersects(
                        macro.getCanvas().getBoundsInParent())){
                    macro.addEntity(En.e);
                    macro.giveArmor(En.e);
                }
            }
        }
    }

    public static void createEntity(String name, int x, int y, int health, int damage, int armor) {
        Object temp = new Object(new smallBiter(name, health, damage, armor), x, y);
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
                    case DELETE:
                        if (event.isControlDown()) {
                            System.out.println("delete all entities from DELETE+CTRL button");
                            main.deleteEntities();
                            break;
                        }
                        break;
                    case UP:
                        if (event.isControlDown()) {
                            root.setTranslateY(root.getTranslateY() - 10);
                            break;
                        }
                        main.moveAll(Math.PI / 2);
                        break;
                    case DOWN:
                        if (event.isControlDown()) {
                            root.setTranslateY(root.getTranslateY() + 10);
                            break;
                        }
                        main.moveAll(Math.PI * 1.5);
                        break;
                    case LEFT:
                        if (event.isControlDown()) {
                            root.setTranslateX(root.getTranslateX() - 10);
                            break;
                        }
                        main.moveAll(Math.PI * 0);
                        break;
                    case RIGHT:
                        if (event.isControlDown()) {
                            root.setTranslateX(root.getTranslateX() + 10);
                            break;
                        }
                        main.moveAll(Math.PI);
                        break;
                    case ESCAPE:
                        main.changeEntityActive();
                        break;
                }
            }
        });
        macro1 a = new macro1(new Position(100,200));
        macro2 b = new macro2(new Position(600,200));

        macroObjects.add(a);
        macroObjects.add(b);

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}