package co.edu.uptc.restaurantsystem.gui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public final class GradientBackgroud {
    private GradientBackgroud() {

    }

    public static Background getInstance() {
        Stop stop1 = new Stop(0, Color.BLACK); // Negro
        Stop stop2 = new Stop(1, Color.rgb(255, 215, 0, 0.8)); // Amarillo oscuro
        LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stop1, stop2);
        return new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY));
    }
}
