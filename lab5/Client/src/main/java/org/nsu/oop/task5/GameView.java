package org.nsu.oop.task5;

import org.nsu.oop.task5.Model.Ball;
import org.nsu.oop.task5.Model.Entity;
import org.nsu.oop.task5.Model.Feed;
import org.nsu.oop.task5.Model.GameModel;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameView extends JPanel {
    private GameModel model;
    private BufferedImage frameBuffer;
    private int width;
    private int height;
    private final Timer renderTimer;
    private final int fps = 60;

    public GameView(GameModel model, int width, int height) {
        this.model = model;
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        frameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        setBackground(Color.WHITE);
        renderTimer = new Timer(1000 / fps, e -> render());
        renderTimer.start();
    }

    public void render() {
        Graphics2D g2d = frameBuffer.createGraphics();
        drawFrame(g2d);
        g2d.dispose();
        repaint();
    }

    void renderEntity(Graphics2D g2d, Entity entity) {
        if (entity instanceof Feed) {
            g2d.setColor(entity.intToColor(((Feed) entity).getIndex()));
            int centerX = (int) (entity.getX() - entity.getSize() / 2);
            int centerY = (int) (entity.getY() - entity.getSize() / 2);
            g2d.fillOval(centerX, centerY, (int) Math.round(entity.getSize()), (int) Math.round(entity.getSize()));
        } else if (entity instanceof Ball) {
            int index = ((Ball) entity).getIndex();
            double x = entity.getX();
            double y = entity.getY();
            double size = entity.getSize();
            g2d.setColor(entity.intToColor(index));
            int centerX = (int) (x - size / 2);
            int centerY = (int) (y - size / 2);
            g2d.fillOval(centerX, centerY, (int) Math.round(size), (int) Math.round(size));
            if (index == 0) {
                g2d.setColor(Color.WHITE);
                String numberString = "YOU";

                Font font = new Font("Arial", Font.BOLD, (int) ((double) Math.round(1.4 * size / numberString.length()))); // Выбираем шрифт и размер
                g2d.setFont(font);

                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(numberString);
                int textHeight = fm.getHeight();
                int textX = centerX + ((int) Math.round(size) - textWidth) / 2;
                int textY = centerY + ((int) Math.round(size) - textHeight) / 2 + fm.getAscent();
                g2d.drawString(numberString, textX, textY);
            } else {
                g2d.setColor(Color.WHITE);
                String numberString = String.valueOf(index);


                Font font = new Font("Arial", Font.BOLD, (int) ((double) Math.round(1.4 * size / numberString.length()))); // Выбираем шрифт и размер
                g2d.setFont(font);
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(numberString);

                int textHeight = fm.getHeight();
                int textX = centerX + ((int) Math.round(size) - textWidth) / 2;
                int textY = centerY + ((int) Math.round(size) - textHeight) / 2 + fm.getAscent();
                g2d.drawString(numberString, textX, textY);
            }
        }
    }

    private void drawFrame(Graphics2D g2d) {
        g2d.clearRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.getHSBColor(0, 0, 0.95F));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        List<Feed> feeds = model.getFeeds();
        if (feeds != null && !feeds.isEmpty()) {
            for (Feed feed : feeds) {
                if (feed.getIndex() != 0) {
                    renderEntity(g2d, feed);
                }
            }
            renderEntity(g2d, feeds.get(0));
        }
        List<Ball> balls = model.getBalls();
        if (balls != null && !balls.isEmpty()) {
            for (Ball ball : balls) {
                if (ball.getIndex() != 0) {
                    renderEntity(g2d, ball);
                }
            }
            renderEntity(g2d, balls.get(0));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(frameBuffer, 0, 0, null);
    }
}
