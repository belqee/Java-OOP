package org.nsu.oop.task3;
import org.nsu.oop.task3.Model.Ball;
import org.nsu.oop.task3.Model.GameModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

class GameView extends JFrame {
    private GameModel model;

    public GameView(GameModel model) {
        this.model = model;
        setTitle("Agario-like Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void render() {
        List<Ball> balls = model.getBalls();
        Graphics g = getGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());
        if (balls != null) {
            for (Ball ball : balls) {
                ball.draw(g);
            }
        }

        g.dispose();
    }

}