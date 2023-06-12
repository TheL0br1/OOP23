package com.example.kursova.Additional;

import com.example.kursova.Objects.Objects;
import com.example.kursova.Objects.macroObjects.macroBase;
import com.example.kursova.Objects.microObjects.bigBiter;
import com.example.kursova.Objects.microObjects.mediumBiter;
import com.example.kursova.Objects.microObjects.smallBiter;
import com.example.kursova.main;
import javafx.animation.AnimationTimer;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;

public class MyFunctions {


    public static ArrayList<AnimationTimer> timers = new ArrayList<>();

    public static void createEntity(String type, String name, int x, int y, int health, int damage, int armor) throws IOException {
        Objects temp = null;
        switch (type) {
            case "smallBiter":
                temp = new Objects(new smallBiter(name, health, damage, armor), x, y);
                break;
            case "mediumBiter":
                temp = new Objects(new mediumBiter(name, health, damage, armor), x, y);
                break;
            case "bigBiter":
                temp = new Objects(new bigBiter(name, health, damage, armor), x, y);
                break;
        }
        if (temp == null) {
            return;
        }
        main.Entities.add(temp);
        System.out.println(temp.e.toString());
    }

    public static void changeEntityActive() {
        main.Entities.forEach(En -> {
            En.getSprite().changeActive();
            En.setActive(!En.isActive());
        });

    }

    public static void deleteEntities() {
        main.Entities.forEach(En -> {
            main.root.getChildren().remove(En.getCanvas());
            try {
                main.writer.write("Delete micro: " + En + "\n");
                main.writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        main.Entities.clear();
    }

    public static void moveAll(double directionR) {
        main.Entities.forEach(En -> {
            try {
                En.move(directionR);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void renderAll() {

        main.Entities.forEach(En -> En.getSprite().render());
        main.macroObjects.forEach(macroBase::draw);
        for (Objects En : main.Entities) {
            for (macroBase macro : main.macroObjects) {
                if (macro.getArmor() <= 0) {
                    macro.getCanvas().getGraphicsContext2D().clearRect(0,0, macro.getCanvas().getWidth(),macro.getCanvas().getHeight());
                    main.macroObjects.remove(macro);
                    main.root.getChildren().remove(macro.getCanvas());
                }
                if (En.getCanvas().getBoundsInParent().intersects(
                        macro.getCanvas().getBoundsInParent())) {
                    if (!macro.isContains(En.e)) {
                        macro.addEntity(En.e);
                    }
                    macro.giveArmor(En.e);
                }
            }
            for (Objects En2 : main.Entities) {
                if (En == En2) {
                    continue;
                }
                if (En.getCanvas().getBoundsInParent().intersects(
                        En2.getCanvas().getBoundsInParent())) {
                    En.e.setHealth(En.e.getHealth() + 3);
                    En2.e.setHealth(En2.e.getHealth() + 3);

                }
            }
        }
        main.menu.toFront();
        try {
            main.writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void serializeImage() {

        main.menu.setVisible(false);
        GraphicsContext gc = main.miniMapCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, main.miniMapCanvas.getWidth(), main.miniMapCanvas.getHeight());
        gc.drawImage(main.root.snapshot(new SnapshotParameters(), null), 5, 5, 200 - 5, 200 - 5);
        gc.setStroke(Color.RED);
        gc.setLineWidth(5.0);
        gc.strokeRect(0, 0, main.miniMapCanvas.getWidth(), main.miniMapCanvas.getHeight());
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(3);
        double x = -main.relateX * main.root.getTranslateX();
        double y = main.relateY * main.root.getTranslateY();
        gc.strokeRect(-main.relateX * main.root.getTranslateX(), -main.relateY * main.root.getTranslateY(), 82, 50);
        main.menu.setVisible(true);
    }

    public static void deleteMove() {
        timers.forEach(timer -> {
            timer.stop();
        });
        timers.clear();
    }

    public static void moveRandom() {
        timers.forEach(animationTimer -> {
            animationTimer.stop();
        });
        timers.clear();
        timers.add(new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 99_999_999_9L / 3) {
                    lastUpdate = now;

                    main.Entities.forEach(En -> {
                        double R = Math.toRadians(main.random.nextInt() % 360);
                        try {
                            En.move(R);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
        });
        for (AnimationTimer timer : timers) {
            timer.start();

        }

    }

    public static void moveToMacro() {
        main.Entities.forEach(en -> {
            int minDist = 1 << 29;
            int index = -1;
            for (int i = 0; i < main.macroObjects.size(); i += 1) {
                if (Math.pow(en.getCanvas().getLayoutX() - main.macroObjects.get(i).getCanvas().getLayoutX(), 2) + Math.pow(en.getCanvas().getLayoutY() - main.macroObjects.get(i).getCanvas().getLayoutY(), 2) < minDist) {
                    minDist = (int) (Math.pow(en.getCanvas().getLayoutX() - main.macroObjects.get(i).getCanvas().getLayoutX(), 2) + Math.pow(en.getCanvas().getLayoutY() - main.macroObjects.get(i).getCanvas().getLayoutY(), 2));
                    index = i;
                }
            }
            int finalIndex = index;
            AnimationTimer timer = new AnimationTimer() {
                final double x1 = main.macroObjects.get(finalIndex).getCanvas().getLayoutX();
                final double y1 = main.macroObjects.get(finalIndex).getCanvas().getLayoutY();
                final Objects en1 = en;
                final double x2 = (x1 - en.getCanvas().getLayoutX()) / 10;
                final double y2 = (y1 - en.getCanvas().getLayoutY()) / 10;
                int i = 0;
                private long lastUpdate = 0;

                @Override
                public void handle(long now) {
                    if (now - lastUpdate >= 99_999_999_9L / 20) {
                        lastUpdate = now;
                        en.getCanvas().setLayoutX(en.getCanvas().getLayoutX() + x2);
                        en.getCanvas().setLayoutY(en.getCanvas().getLayoutY() + y2);

                        i++;
                        if (i == 10) {
                            stop();
                        }
                    }
                }
            };
            timer.start();
        });
    }

}
