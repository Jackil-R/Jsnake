package com.SnakeGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        GameController root = new GameController();
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(root.getOnKeyPressed());
        scene.setOnKeyReleased(root.getOnKeyReleased());
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        InputStream iconStream = getClass().getResourceAsStream("logo.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
