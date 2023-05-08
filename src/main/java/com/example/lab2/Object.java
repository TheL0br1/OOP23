package com.example.lab2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;

public class Object {
    public smallBiter e;

    private Position position = new Position(0, 0);
    private boolean isDragging = false;
    private boolean active;

    private final Image spriteImage = new Image(getClass().getResource("smallBiter.png").toExternalForm());
    private Sprite sprite;
    private Canvas canvas;
    public Object(smallBiter e){
        this(e,0,0);
    }
    public Object(smallBiter e, int posX, int posY){
        this.e=e;
        setCanvas(new Canvas(getImage().getWidth(), getImage().getHeight() + 40));
        getPosition().X = posX;
        getPosition().Y = posY;
        active=true;
        getCanvas().setLayoutX(posX);
        getCanvas().setLayoutY(posY);
        main.root.getChildren().add(getCanvas());

        getCanvas().setOnMousePressed(event -> {
            if(!isActive()) { return; }
            if (event.isSecondaryButtonDown()) { // Перевірка нажаття правої кнопки миші
                getPosition().X = (int) event.getX();
                getPosition().Y = (int) event.getY();
                isDragging = true;

            }
            if (event.isPrimaryButtonDown()) {
                main.root.getChildren().remove(getCanvas());
                Rectangle rect = new Rectangle(
                        getCanvas().getLayoutX() + getCanvas().getTranslateX(),
                        getCanvas().getLayoutY() + getCanvas().getTranslateY(),
                        getCanvas().getWidth(),
                        getCanvas().getHeight());
                rect.setFill(null);
                rect.setStroke(Color.RED);
                rect.setStrokeWidth(3);
                main.root.getChildren().add(rect);
                main.Entities.remove(this);
            }
            if(event.isMiddleButtonDown()){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("displayMicro.fxml"));
                loader.setController(new displayMicro(this));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException exc) {
                    throw new RuntimeException(exc);
                }
                Stage window = new Stage();
                window.initModality(Modality.APPLICATION_MODAL);
                window.setTitle("Створіть новий об'єкт");
                Scene scene = new Scene(root);
                window.setScene(scene);
                window.showAndWait();
            }

        });
        getCanvas().setOnMouseDragged(event -> {
            if(!isActive()) { return; }

            if (isDragging) {
                // Перевірка нажаття правої кнопки миші
                double offsetX = (event.getX() - getPosition().X) / 1.5;
                double offsetY = (event.getY() - getPosition().Y) / 1.5;
                getPosition().X = (int) event.getX();
                getPosition().Y = (int) event.getY();
                getCanvas().setTranslateX(getCanvas().getTranslateX() + offsetX);
                getCanvas().setTranslateY(getCanvas().getTranslateY() + offsetY);

            }
        });
        getCanvas().setOnMouseReleased(event -> {
            if(!isActive()) { return; }
            isDragging = false;
        });
        setSprite(new Sprite(this, 320, 1, 0, 0));
    }

    public void move(int t, double dir) {

        getPosition().X = (int) (t * Math.cos(dir) * e.getSpeed());
        getPosition().Y = (int) (t * Math.sin(dir) * e.getSpeed());

    }
    public void move(int t) {
        move(t, e.getDirectionR());
    }
    public Position getPosition() {
        return position;
    }
    public String toString() {
        return "Object" + ", located at: " + this.getPosition().X + " - X, " + this.getPosition().Y + " - Y. My id: " + e.getId();
    }
    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    static private class Position implements Serializable {
        public int X = 0;
        public int Y = 0;

        public Position(int x, int y) {
            X = x;
            Y = y;
        }

        // standard getters and setters
    }

}
