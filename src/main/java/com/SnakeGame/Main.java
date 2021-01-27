package com.SnakeGame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.InputStream;

public class Main extends Application {

    private final Button start = new Button("Start");
    private final Button pause = new Button("Pause");
    private final Button exit = new Button("Exit");
    private final Button reset = new Button("Reset");

    // TODO: levels to implement (speed increase with time, display level)
    // TODO: highscore in one session
    // TODO: highscore all time (persisted high score)
    // TODO: Better UI design

    @Override
    public void start(Stage primaryStage) {
        reset.setDisable(true);
        pause.setDisable(true);
        GameController root = new GameController();
        root.setMaxHeight(Commons.HEIGHT);
        root.setMinWidth(Commons.WIDTH);
        root.setOnKeyPressed(root.getOnKeyPressed());
        root.setOnKeyReleased(root.getOnKeyReleased());
        start.setOnMouseClicked(e -> {
            root.startGame();
            root.requestFocus();
            pause.setDisable(false);
            reset.setDisable(false);
            start.setDisable(true);
        });
        pause.setOnMouseClicked(e -> {
            if(pause.getText().equals("Pause")){
                pause.setText("Unpause");
                root.pauseGame();
            }else{
                pause.setText("Pause");
                root.unpauseGame();
                root.requestFocus();
            }
        });
        reset.setOnMouseClicked(e -> {
            root.reset();
            root.requestFocus();
        });
        exit.setOnMouseClicked(e -> System.exit(0));
        start.setMaxWidth(Double.MAX_VALUE);
        pause.setMaxWidth(Double.MAX_VALUE);
        reset.setMaxWidth(Double.MAX_VALUE);
        exit.setMaxWidth(Double.MAX_VALUE);
        start.setPadding(new Insets(10,0,10,0));
        pause.setPadding(new Insets(10,0,10,0));
        reset.setPadding(new Insets(10,0,10,0));
        exit.setPadding(new Insets(10,0,10,0));
        VBox layout = new VBox(start,pause,reset,exit);
        layout.setPadding(new Insets(10,10,0,10));
        layout.setSpacing(10);
        Pane paneFree = new StackPane();
        paneFree.setMaxHeight(Commons.HEIGHT);
        paneFree.setMinWidth(200);
        paneFree.getChildren().add(layout);

        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(paneFree,root);
        splitPane.setFocusTraversable(true);



        Scene scene = new Scene(splitPane);
//        scene.setOnKeyPressed(root.getOnKeyPressed());
//        scene.setOnKeyReleased(root.getOnKeyReleased());
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
