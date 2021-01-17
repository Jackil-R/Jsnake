package com.SnakeGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import static com.SnakeGame.Commons.*;

public class Food {

    private final Image image;
    private int x;
    private int y;

    public Food() {
        x = 0;
        y = 0;
        this.image = new Image("com/SnakeGame/ic_apple.png");
    }

    public void drawFood(GraphicsContext gc) {
        gc.drawImage(image, x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    public void generateFood(int foodX, int foodY) {
        this.x = foodX;
        this.y = foodY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
