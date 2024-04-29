package org.nsu.oop.task3.Model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private List<Ball> balls; // Список шариков в игре

    public GameModel() {
        balls = new ArrayList<>();
        balls.add(new Ball(100, 100, 30, Color.RED));
        balls.add(new Ball(200, 200, 20, Color.BLUE));
        balls.add(new Ball(300, 300, 40, Color.GREEN));
    }

    // Метод для получения списка шариков
    public List<Ball> getBalls() {
        return balls;
    }
    public void updateBallPosition(int dx, int dy) {
        for (Ball ball : balls) {
            ball.setX(ball.getX() + dx);
            ball.setY(ball.getY() + dy);
        }
    }
}