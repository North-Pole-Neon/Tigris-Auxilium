package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);
        primaryStage.setTitle("Hello");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
