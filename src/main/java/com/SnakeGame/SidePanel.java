package com.SnakeGame;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class SidePanel extends StackPane{
    protected final Button startButton = new Button("Start");
    protected final Button pauseButton = new Button("Pause");
    protected final Button exitButton = new Button("Exit");
    protected final Button resetButton = new Button("Reset");
    protected final Label highScoreLabel = new Label("High Score: 0");
    protected final Label allTimeHighScoreLabel = new Label("All Time High Score: 0");
    private int highScore = 0;
    private int allTimeHighScore =0;

    public SidePanel() throws IOException, URISyntaxException {
        resetButton.setDisable(true);
        pauseButton.setDisable(true);
        exitButton.setOnMouseClicked(e -> System.exit(0));
        startButton.setMaxWidth(Double.MAX_VALUE);
        pauseButton.setMaxWidth(Double.MAX_VALUE);
        resetButton.setMaxWidth(Double.MAX_VALUE);
        exitButton.setMaxWidth(Double.MAX_VALUE);
        highScoreLabel.setMaxWidth(Double.MAX_VALUE);
        allTimeHighScoreLabel.setMaxWidth(Double.MAX_VALUE);
        startButton.setPadding(new Insets(10,0,10,0));
        pauseButton.setPadding(new Insets(10,0,10,0));
        resetButton.setPadding(new Insets(10,0,10,0));
        exitButton.setPadding(new Insets(10,0,10,0));
        highScoreLabel.setPadding(new Insets(10,0,10,0));
        highScoreLabel.setFont(new Font("Digital-7", 25));
        allTimeHighScoreLabel.setPadding(new Insets(5,0,10,0));
        allTimeHighScoreLabel.setFont(new Font("Digital-7", 20));
        VBox layout = new VBox(startButton, pauseButton, resetButton, exitButton, highScoreLabel,allTimeHighScoreLabel);
        layout.setPadding(new Insets(10,10,0,10));
        layout.setSpacing(10);
        setMaxHeight(Commons.HEIGHT);
        setMinWidth(200);
        getChildren().add(layout);
        readHighScore();
    }

    private void readHighScore() throws IOException, URISyntaxException {
        URL url = SidePanel.class.getResource("score.txt");
        File file = new File(url.toURI());
        Scanner myReader = new Scanner(file);
        String text = "";
        while (myReader.hasNextLine()) {
            text = myReader.nextLine();
            System.out.println(text);
        }
        myReader.close();
        allTimeHighScore = Integer.parseInt(text.split(" ")[1]);
        allTimeHighScoreLabel.setText("All Time High Score: "+Integer.parseInt(text.split(" ")[1]));

    }

    private void writeHighScore(int allTimeHighScore) throws IOException, URISyntaxException {
        URL url = SidePanel.class.getResource("score.txt");
        File file = new File(url.toURI());
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("Highscore: "+ String.valueOf(allTimeHighScore));
        writer.close();
    }

    public int getHighScore() {
        return highScore;
    }
    

    public int getAllTimeHighScore() {
        return allTimeHighScore;
    }

    public void updateHighScore(int highScore) {
        this.highScore = highScore;
        this.highScoreLabel.setText("High Score: "+highScore);
    }

    public void updateAllTimeHighScore(int allTimeHighScore) throws IOException, URISyntaxException {
        this.allTimeHighScore = allTimeHighScore;
        this.allTimeHighScoreLabel.setText("All Time High Score: "+allTimeHighScore);
        writeHighScore(allTimeHighScore);
    }
}
