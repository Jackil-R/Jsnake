package com.SnakeGame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.SplitPane;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import static com.SnakeGame.Commons.HEIGHT;
import static com.SnakeGame.Commons.WIDTH;

public class MainPanel extends SplitPane {
    private final GamePanel gamePanel;
    private final SidePanel sidePanel;
    private final Timeline timeline;

    public MainPanel() throws IOException, URISyntaxException {
        this.sidePanel = new SidePanel();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        this.gamePanel = new GamePanel(canvas);
        gamePanel.setMaxHeight(Commons.HEIGHT);
        gamePanel.setMinWidth(Commons.WIDTH);
        gamePanel.setOnKeyPressed(gamePanel.getOnKeyPressed());
        gamePanel.setOnKeyReleased(gamePanel.getOnKeyReleased());
        timeline = new Timeline(new KeyFrame(Duration.millis(250), e -> {
            try {
                run();
            } catch (IOException | URISyntaxException ioException) {
                ioException.printStackTrace();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        sidePanel.startButton.setOnMouseClicked(e -> {
            gamePanel.startGame();
            gamePanel.requestFocus();
            sidePanel.pauseButton.setDisable(false);
            sidePanel.resetButton.setDisable(false);
            sidePanel.startButton.setDisable(true);
            timeline.play();
        });
        sidePanel.pauseButton.setOnMouseClicked(e -> {
            if (sidePanel.pauseButton.getText().equals("Pause")) {
                sidePanel.pauseButton.setText("Unpause");
                timeline.pause();
            } else {
                sidePanel.pauseButton.setText("Pause");
                gamePanel.requestFocus();
                timeline.play();
            }
        });
        sidePanel.resetButton.setOnMouseClicked(e -> {
            gamePanel.startGame();
            gamePanel.requestFocus();
            timeline.play();
        });

        getItems().addAll(sidePanel,gamePanel);
    }

    private void run() throws IOException, URISyntaxException {
        gamePanel.run(timeline);
        if(gamePanel.isGameOver()){
            sidePanel.startButton.setDisable(false);
            sidePanel.pauseButton.setDisable(true);
            sidePanel.resetButton.setDisable(true);
            if(gamePanel.getScore() > sidePanel.getHighScore()){
                sidePanel.updateHighScore(gamePanel.getScore());
            }

            if(gamePanel.getScore() > sidePanel.getAllTimeHighScore()){
                sidePanel.updateAllTimeHighScore(gamePanel.getScore());
            }
        }

    }


}
