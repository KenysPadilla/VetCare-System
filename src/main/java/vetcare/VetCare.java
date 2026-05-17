package vetcare;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class VetCare extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("VetCare System - JavaFX funcionando");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 400, 200);
        stage.setTitle("VetCare System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}