package co.edu.uptc.restaurantsystem.gui;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginPanel extends GridPane {
    private FadeTransition fadeTransition;

    public LoginPanel() {
        setBackground(GradientBackgroud.getInstance());
        setPadding(new Insets(20, 50, 20, 50));
        setVgap(10);
        setHgap(10);

        addComponents();

        fadeTransition = new FadeTransition(Duration.seconds(1), this);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
    }

    private void addComponents() {
        Label usernameLabel = new Label("Código o email");
        usernameLabel.setPadding(new Insets(0, 0, 0, 2));
        usernameLabel.setTextFill(Color.WHITE);

        TextField usernameInput = new TextField();
        usernameInput.setMinHeight(30);
        usernameInput.setMinWidth(200);
        usernameInput.setPromptText("Ingrese su código o email");

        VBox nameBox = new VBox(2, usernameLabel, usernameInput);
        GridPane.setConstraints(nameBox, 0, 0);

        Label passwordLabel = new Label("Contraseña:");
        passwordLabel.setPadding(new Insets(0, 0, 0, 2));
        passwordLabel.setTextFill(Color.WHITE);

        PasswordField passwordInput = new PasswordField();
        passwordInput.setMinHeight(30);
        passwordInput.setMinWidth(200);
        passwordInput.setPromptText("Ingrese su contraseña");

        VBox passwordBox = new VBox(2, passwordLabel, passwordInput);
        GridPane.setConstraints(passwordBox, 0, 1);

        Button loginButton = new Button("Iniciar Sesión");
        loginButton.setMinWidth(200);
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        GridPane.setConstraints(loginButton, 0, 2);

        Button registerButton = new Button("Registrarse");
        registerButton.setMinWidth(200);
        registerButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        registerButton.setOnAction(actionEvent -> registerButtonAction());
        GridPane.setConstraints(registerButton, 0, 8);

        getChildren().addAll(nameBox, passwordBox, loginButton, registerButton);
    }

    private void registerButtonAction(){
        Stage stage = new Stage();
        stage.setTitle("Registro");
        stage.initModality(Modality.APPLICATION_MODAL);
        RegistrationPanel registrationPanel = new RegistrationPanel();
        stage.setScene(registrationPanel.newScene());
        stage.show();
        registrationPanel.playTransition();
    }

    public void playTransition() {
        fadeTransition.play();
    }
}
