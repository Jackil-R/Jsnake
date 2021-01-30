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


public class GamePanel extends Pane {

    private final GraphicsContext gc;
    private Food food;
    private Snake snake;
    private boolean gameOver;
    private int score = 0;
    private double speed = 1;
    private int level = 0;

    public GamePanel(Canvas canvas) {
        getChildren().add(canvas);
        this.gc = canvas.getGraphicsContext2D();
        drawBackground(gc);
    }

    public void startGame(){
        snake = new Snake();
        food = new Food();
        generateFood();
        setOnKeyPressed(e -> snake.keyPressed(e.getCode()));
        resetScore();
        resetSeconds();
        resetLevel();
        resetGameOver();
    }



    public void run(Timeline timeline) {
        if(gameOver) {
            gameOverMessage(gc);
        }else{
            drawBackground(gc);
            snake.drawSnake(gc);
            food.drawFood(gc);
            snake.move();
            eatFood(timeline);
            updateScore();
            updateLevel();
            gameOver();
        }
    }

    private void eatFood(Timeline timeline) {
        if (snake.getHead().x == food.getX() && snake.getHead().getY() == food.getY()) {
            snake.increaseBody(new Point(-1, -1));
            generateFood();
            score += 1;
            if(score % 2 == 0){
                timeline.setRate(speed+0.2);
                level += 1;
            }
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

    private void updateLevel(){
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Digital-7", 35));
        gc.fillText("Level: " + level, 560, 35);
    }

    public int getScore(){
        return score;
    }

    private void resetScore(){
        score=0;
    }

    private void resetSeconds() { speed = 1; }

    private void resetLevel(){ level = 0; }

    private void resetGameOver(){
        gameOver = false;
    }

    public boolean isGameOver(){
        return gameOver;
    }
}
