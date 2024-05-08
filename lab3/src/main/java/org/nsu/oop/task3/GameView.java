package org.nsu.oop.task3;
import org.nsu.oop.task3.Model.Ball;
import org.nsu.oop.task3.Model.Feed;
import org.nsu.oop.task3.Model.GameModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameView extends JPanel {
    private GameModel model;
    private BufferedImage frameBuffer;
    private int width;
    private int height;
    private Timer renderTimer;
    private int fps = 60;
    public GameView(GameModel model) {
        this.model = model;
        width = 1280;
        height = 720;
        setPreferredSize(new Dimension(width, height));
        frameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        setBackground(Color.WHITE);

        // Создаем таймер рендеринга с заданным интервалом
        renderTimer = new Timer(1000 / fps, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                render(); // Вызываем метод рендеринга
            }
        });
        renderTimer.start(); // Запускаем таймер
    }


    public void render() {
        Graphics2D g2d = frameBuffer.createGraphics();
        drawFrame(g2d);
        g2d.dispose();
        repaint();
    }

    private void drawFrame(Graphics2D g2d) {
        g2d.clearRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.getHSBColor(0,0,0.95F));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        List<Feed> feeds = model.getFeeds();
        if (feeds != null && !feeds.isEmpty()) {
            for (Feed feed : feeds) {
                if (feed.getIndex() != 0){
                    feed.draw(g2d);
                }
            }
            feeds.get(0).draw(g2d);
        }
        List<Ball> balls = model.getBalls();
        if (balls != null && !balls.isEmpty()) {
            for (Ball ball : balls) {
                if (ball.getIndex() != 0){
                    ball.draw(g2d);
                }
            }
            balls.get(0).draw(g2d);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(frameBuffer, 0, 0, null);
    }

}
