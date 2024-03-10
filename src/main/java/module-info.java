module co.edu.uptc.gui.restaurant_system {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens co.edu.uptc.restaurantsystem.gui to javafx.fxml;
    exports co.edu.uptc.restaurantsystem.gui;
}