package com.SnakeGame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.awt.*;

import static com.SnakeGame.Commons.*;


public class GameController extends Pane {

    private final GraphicsContext gc;
    private final Canvas canvas;
    private Food food;
    private Snake snake;
    private Timeline timeline;
    private boolean gameOver;
    private int score;
    private double speed =1;

    public GameController() {
        canvas = new Canvas(WIDTH, HEIGHT);
        getChildren().add(canvas);
        this.gc = canvas.getGraphicsContext2D();
        drawBackground(gc);
    }

    public void startGame(){
        snake = new Snake();
        food = new Food();
        generateFood();
        setOnKeyPressed(e -> snake.keyPressed(e.getCode()));
        timeline = new Timeline(new KeyFrame(Duration.millis(250), e -> run(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void pauseGame(){
        timeline.pause();
    }

    public void unpauseGame(){
        timeline.play();
    }

    public void reset(){
        System.out.println("resetting game");
        resetScore();
        resetSeconds();
        resetGameOver();
        startGame();
    }


    private void run(GraphicsContext gc) {
        if(gameOver) {
            gameOverMessage(gc);
            timeline.stop();
        }else{
            drawBackground(gc);
            snake.drawSnake(gc);
            food.drawFood(gc);
            snake.move();
            eatFood();
            updateScore();
            gameOver();
        }
    }

    private void eatFood() {

        if (snake.getHead().x == food.getX() && snake.getHead().getY() == food.getY()) {
            snake.increaseBody(new Point(-1, -1));
            generateFood();
            timeline.setRate(speed+0.1);
            score += 1;
        }
    }

    private void generateFood() {
        start:
        while (true) {
            int foodX = (int) (Math.random() * ROWS);
            int foodY = (int) (Math.random() * COLUMNS);

            for (Point snake : snake.getBody()) {
                if (snake.getX() == foodX && snake.getY() == foodY) {
                    continue start;
                }
            }
            food.generateFood(foodX,foodY);
            break;
        }
    }

    public void gameOver() {
        if (snake.getHead().x < 0 || snake.getHead().y < 0 || snake.getHead().x * SQUARE_SIZE >= WIDTH || snake.getHead().y * SQUARE_SIZE >= HEIGHT) {
            gameOver = true;
        }

        for (int i = 1; i < snake.getBody().size(); i++) {
            if (snake.getHead().x == snake.getBody().get(i).getX() && snake.getHead().getY() == snake.getBody().get(i).getY()) {
                gameOver = true;
                break;
            }
        }
    }

    public void gameOverMessage(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.setFont(new Font("Digital-7", 50));
        gc.fillText("Game Over", WIDTH / 3.5, HEIGHT >> 1);
    }

    private void drawBackground(GraphicsContext gc) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("4c5b6b"));
                } else {
                    gc.setFill(Color.web("687482"));
                }
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void updateScore() {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Digital-7", 35));
        gc.fillText("Score: " + score, 10, 35);
    }

    public int getScore(){
        return score;
    }

    private void resetScore(){
        score=0;
    }

    private void resetSeconds() {
        speed =1;
    }

    private void resetGameOver(){
        gameOver = false;
    }
}
