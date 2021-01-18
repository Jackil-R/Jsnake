package com.SnakeGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;

public class Main extends Application {

    // TODO: start to button press, display message, slightly dim window
    // TODO: button to pause/unpause the game
    // TODO: button to exit the game
    // TODO: levels to implement (speed increase with time, display level)
    // TODO: display a light grid in the background
    // TODO: proper snake body texture (head tail bends)
    // TODO: background texture
    // TODO: board boundary
    // TODO: highscore in one session
    // TODO: highscore all time (persisted high score)
    // TODO: Better UI design

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
