package com.example.lab2.Controllers;
import com.example.lab2.main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
public class initMicro {
    public static Stage window=null;
    public static Scene scene;
    public static void display() throws IOException {
        Parent root = FXMLLoader.load(main.class.getResource("initMicro.fxml"));
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Створіть новий об'єкт");
        scene = new Scene(root);
        window.setScene(scene);
        window.showAndWait();
    }
}
