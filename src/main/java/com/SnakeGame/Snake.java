package com.SnakeGame;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.SnakeGame.Commons.ROWS;
import static com.SnakeGame.Commons.SQUARE_SIZE;

public class Snake {

    private final List<Point> body = new ArrayList();
    private final Point head;
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private int currentDirection;
    private final ImageView image;
    private int rotate;

    public Snake() {
        for (int i = 0; i < 3; i++) {
            body.add(new Point(5, ROWS / 2));
        }
        head = body.get(0);
        this.image = new ImageView("com/SnakeGame/snake-head.png");
        this.rotate = 0;
    }


    public Point getHead() {
        return head;
    }

    public List<Point> getBody() {
        return body;
    }


    public void drawSnake(GraphicsContext gc) {
        gc.setFill(Color.GREENYELLOW);
        image.setRotate(rotate);
        image.setTranslateZ(image.getBoundsInLocal().getWidth() / 2.0);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = image.snapshot(params, null);
        gc.drawImage(rotatedImage, head.getX()  * SQUARE_SIZE, head.getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);

        for (int i = 1; i < body.size(); i++) {
            gc.fillRoundRect(body.get(i).getX() * SQUARE_SIZE, body.get(i).getY() * SQUARE_SIZE, SQUARE_SIZE - 1, SQUARE_SIZE - 1, 20, 20);
        }
    }

    public void keyPressed(KeyCode code) {
        switch (code) {
            case UP:
            case W:
                if (currentDirection != DOWN) currentDirection = UP;
                break;
            case DOWN:
            case S:
                if (currentDirection != UP) currentDirection = DOWN;
                break;
            case LEFT:
            case A:
                if (currentDirection != RIGHT) currentDirection = LEFT;
                break;
            case RIGHT:
            case D:
                if (currentDirection != LEFT) currentDirection = RIGHT;
                break;
        }
    }


    public void move() {

        for (int i = body.size() - 1; i >= 1; i--) {
            body.get(i).x = body.get(i - 1).x;
            body.get(i).y = body.get(i - 1).y;
        }

        switch (currentDirection) {
            case RIGHT:
                head.x++;
                image.setRotationAxis(Rotate.Y_AXIS);
                rotate=0;
                break;
            case LEFT:
                head.x--;
                image.setRotationAxis(Rotate.Y_AXIS);
                rotate=180;
                break;
            case UP:
                head.y--;
                image.setRotationAxis(Rotate.Z_AXIS);
                rotate=270;
                break;
            case DOWN:
                head.y++;
                image.setRotationAxis(Rotate.Z_AXIS);
                rotate=90;
                break;
        }

    }

    public void increaseBody(Point point) {
        body.add(point);
    }
}
