package com.SnakeGame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, URISyntaxException {
        SplitPane splitPane = new MainPanel();
        splitPane.setFocusTraversable(true);

        Scene scene = new Scene(splitPane);
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
