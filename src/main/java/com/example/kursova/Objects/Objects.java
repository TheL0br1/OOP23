package com.example.kursova.Objects;

import com.example.kursova.Controllers.displayMicro;
import com.example.kursova.Objects.microObjects.smallBiter;
import com.example.kursova.main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Objects {
    public smallBiter e;

    private Position position = new Position(0, 0);
    private boolean isDragging = false;
    private Image spriteImage;
    private Sprite sprite;
    private Canvas canvas;
    private Position dragPosition = new Position(0, 0);

    public Objects(smallBiter e) {
        this(e, 0, 0);
    }

    public Objects(smallBiter e, int posX, int posY) {
        this.e = e;
        String image = e.getClass().getSimpleName();
        spriteImage = new Image(main.class.getResource(e.getClass().getSimpleName() + ".png").toExternalForm());
        setCanvas(new Canvas(getImage().getWidth(), getImage().getHeight() + 100));
        getPosition().X = posX;
        getPosition().Y = posY;
        e.setActive(true);
        getCanvas().setLayoutX(posX);
        getCanvas().setLayoutY(posY);
        main.root.getChildren().add(getCanvas());

        getCanvas().setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                getSprite().changeActive();
                e.setActive(!e.isActive());
            }
            if(!isActive()) { return; }
            if (event.isSecondaryButtonDown()) { // Перевірка нажаття правої кнопки миші
                dragPosition.X = (int) event.getX();
                dragPosition.Y = (int) event.getY();
                isDragging = true;

            }
            if(event.isMiddleButtonDown()){
                FXMLLoader loader = new FXMLLoader(main.class.getResource("displayMicro.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                displayMicro controller =loader.getController();
                controller.setObject(this);
                Stage window = new Stage();
                window.initModality(Modality.APPLICATION_MODAL);
                window.setTitle("Змініть данний мікрооб'єкт");
                Scene scene = new Scene(root);
                window.setScene(scene);
                window.showAndWait();
            }

        });
        getCanvas().setOnMouseDragged(event -> {
            if(!isActive()) { return; }

            if (isDragging) {
                // Перевірка нажаття правої кнопки миші
                getPosition().X = (int) (event.getSceneX()- dragPosition.X);
                getPosition().Y = (int) (event.getSceneY()- dragPosition.Y);
                getCanvas().setLayoutX((int) event.getSceneX()-dragPosition.X);
                getCanvas().setLayoutY((int) event.getSceneY()-dragPosition.Y);
                try {
                    main.writer.write("Move micro: " + this.toString()+"\n" );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        getCanvas().setOnMouseReleased(event -> {
            if (!isActive()) {
                return;
            }
            isDragging = false;
        });
        setSprite(new Sprite(this, 1, 0, 0));
    }

    public void move(double dir) throws IOException {
        if (!e.isActive()) return;
        getCanvas().setLayoutX(getCanvas().getLayoutX() - (int) (Math.cos(dir) * e.getSpeed()));
        getCanvas().setLayoutY(getCanvas().getLayoutY() - (int) (Math.sin(dir) * e.getSpeed()));
        getPosition().X = (int) getCanvas().getLayoutX();
        getPosition().Y = (int) getCanvas().getLayoutY();
        main.writer.write("Move micro: " + this.toString() + ". Dir: " + Double.toString(dir) + "\n");


    }
    public void move() throws IOException {
        move(e.getDirectionR());
    }
    public Position getPosition() {
        return position;
    }
    public String toString() {
        return "Objects" + ", located at: " + this.getPosition().X + " - X, " + this.getPosition().Y + " - Y. My id: " + e.getId();
    }
    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isActive() {
        return e.isActive();
    }

    public void setActive(boolean active) {
        this.e.setActive(active);
    }

    public Image getImage() {
        return spriteImage;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }


}
