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

    // Метод для отображения игрового мира
    public void render() {
        // Получаем список шариков из модели
        List<Ball> balls = model.getBalls();

        // Создаем новый объект Graphics для отрисовки на экране
        Graphics g = getGraphics();

        // Очищаем экран
        g.clearRect(0, 0, getWidth(), getHeight());

        // Проверяем, что список шариков не пуст
        if (balls != null) {
            // Отрисовываем каждый шарик
            for (Ball ball : balls) {
                ball.draw(g);
            }
        }

        // Освобождаем ресурсы Graphics
        g.dispose();
    }

}