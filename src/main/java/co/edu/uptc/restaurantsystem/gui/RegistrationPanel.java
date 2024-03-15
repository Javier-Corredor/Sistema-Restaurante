package co.edu.uptc.restaurantsystem.gui;

import co.edu.uptc.restaurantsystem.model.User;
import javafx.animation.FadeTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RegistrationPanel extends GridPane {
    private TextField nameInput;
    private TextField lastNameInput;
    private TextField codeInput;
    private PasswordField passwordInput;
    private PasswordField confirmPasswordInput;
    private FadeTransition fadeTransition;

    public RegistrationPanel() {
        setBackground(GradientBackgroud.getInstance());
        setPadding(new Insets(20, 30, 20, 30));
        setVgap(10);
        setHgap(30);

        addComponents();

        fadeTransition = new FadeTransition(Duration.seconds(1), this);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
    }

    public Scene newScene() {
        return new Scene(this, 490, 360);
    }

    public void playTransition() {
        fadeTransition.play();
    }

    private void addComponents() {
        Label personalInformationLabel = new Label("Información Personal");
        personalInformationLabel.setTextFill(Color.WHITE);
        personalInformationLabel.setFont(Font.font(14));
        GridPane.setConstraints(personalInformationLabel, 0, 0);

        Label nameLabel = new Label("Name:");
        nameLabel.setTextFill(Color.WHITE);
        nameInput = new TextField();
        nameInput.setPromptText("Enter your name");
        nameInput.setPrefSize(200, 30);
        VBox nameBox = newVBox(1, 0, nameLabel, nameInput);

        Label lastNameLabel = new Label("Last Name:");
        lastNameLabel.setTextFill(Color.WHITE);
        lastNameInput = new TextField();
        lastNameInput.setPromptText("Enter your last name");
        lastNameInput.setPrefSize(200, 30);
        VBox lastNameBox = newVBox(1, 1, lastNameLabel, lastNameInput);

        Label codeLabel = new Label("Code:");
        codeLabel.setTextFill(Color.WHITE);
        codeInput = new TextField();
        codeInput.setPromptText("Enter your code");
        codeInput.setPrefSize(200, 30);
        VBox codeBox = newVBox(2, 0, codeLabel, codeInput);

        Label securityLabel = new Label("Seguridad");
        securityLabel.setTextFill(Color.WHITE);
        securityLabel.setFont(Font.font(14));
        GridPane.setConstraints(securityLabel, 0, 3);
        GridPane.setMargin(securityLabel, new Insets(20, 0, 0, 0));

        Label passwordLabel = new Label("Password:");
        passwordLabel.setTextFill(Color.WHITE);
        passwordInput = new PasswordField();
        passwordInput.setPromptText("Enter your password");
        passwordInput.setPrefSize(200, 30);
        VBox passwordBox = newVBox(4, 0, passwordLabel, passwordInput);

        Label confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordLabel.setTextFill(Color.WHITE);
        confirmPasswordInput = new PasswordField();
        confirmPasswordInput.setPromptText("Confirm your password");
        confirmPasswordInput.setPrefSize(200, 30);
        VBox confirmPasswordBox = newVBox(4, 1, confirmPasswordLabel, confirmPasswordInput);

        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        registerButton.setMinWidth(180);
        GridPane.setConstraints(registerButton, 0, 5);
        GridPane.setColumnSpan(registerButton, 2);
        GridPane.setHalignment(registerButton, HPos.CENTER);
        GridPane.setMargin(registerButton, new Insets(20, 0, 0, 0));
        registerButton.setOnAction(e -> registerButtonAction());

        getChildren().addAll(personalInformationLabel, nameBox, lastNameBox, codeBox, securityLabel,
                passwordBox, confirmPasswordBox, registerButton);
    }

    private void registerButtonAction() {
        String name = nameInput.getText();
        String lastName = lastNameInput.getText();
        String code = codeInput.getText();
        String password = passwordInput.getText();
        String confirmPassword = confirmPasswordInput.getText();

        // Validar que los campos no estén vacíos
        if (name.isBlank() || lastName.isBlank() || code.isBlank() || password.isBlank() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Debe completar todos los campos.");
            return;
        }

        if (!User.isCodeValid(code)){
            showAlert(Alert.AlertType.ERROR, "Error", "Código no válido.");
            return;
        }

        // Validar la contraseña
        if (!User.isPasswordValid(password)) {
            showAlert(Alert.AlertType.ERROR, "Error", """
                    La contraseña debe contener al menos:
                        - 8 carácteres
                        - Una letra mayúscula
                        - Una letra minúscula
                        - Un número
                        - Un carácter especial (@#$%^&+=!)""");
            return;
        }

        // Validar que las contraseñas coincidan
        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Las contraseñas no coinciden. ");
            return;
        }

        showAlert(Alert.AlertType.CONFIRMATION, "Éxito", "Registro completado.");
        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static VBox newVBox(int row, int column, Label label, TextField textField) {
        VBox vBox = new VBox(2, label, textField);
        GridPane.setConstraints(vBox, column, row);
        return vBox;
    }
}
