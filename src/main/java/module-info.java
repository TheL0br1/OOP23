module com.example.lab2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    opens com.example.lab2 to javafx.fxml;
    exports com.example.lab2;

    exports com.example.lab2.Objects;
    opens com.example.lab2.Objects to javafx.fxml;
    exports com.example.lab2.Objects.microObjects;
    opens com.example.lab2.Objects.microObjects to javafx.fxml;
    exports com.example.lab2.Controllers;
    opens com.example.lab2.Controllers to javafx.fxml;

    requires java.desktop;


}
