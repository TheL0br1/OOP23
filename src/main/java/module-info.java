module com.example.kursova {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kursova to javafx.fxml;
    exports com.example.kursova;
    exports com.example.kursova.Objects;
    opens com.example.kursova.Objects to javafx.fxml;
    exports com.example.kursova.Objects.microObjects;
    opens com.example.kursova.Objects.microObjects to javafx.fxml;
    exports com.example.kursova.Controllers;
    opens com.example.kursova.Controllers to javafx.fxml;
}