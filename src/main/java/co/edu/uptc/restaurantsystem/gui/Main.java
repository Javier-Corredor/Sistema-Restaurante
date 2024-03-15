package co.edu.uptc.restaurantsystem.gui;

import co.edu.uptc.restaurantsystem.persistence.Database;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage stage;


    public void start(Stage primaryStage) {
        Database.getDatabase();
        stage = primaryStage;
        showLogin();
    }

    public static void showLogin() {
        LoginPanel loginPanel = new LoginPanel();
        Scene scene = new Scene(loginPanel, 300, 300);
        stage.setTitle("Inicio de Sesión");
        stage.setScene(scene);
        stage.show();
        loginPanel.playTransition();
    }

    public static void main(String[] args) {
        launch(args);
    }

}