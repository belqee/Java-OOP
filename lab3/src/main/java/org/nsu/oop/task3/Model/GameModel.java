package org.nsu.oop.task3.Model;

import org.nsu.oop.task3.GameController;
import org.nsu.oop.task3.GameView;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class GameModel {
    private List<Ball> balls;
    private List<Feed> feeds;// Список шариков в игре
    GameView gameView = null;
    GameController gameController = null;
    private final int tickInterval = 1000 / 120;
    private final Timer timer;
    private final double deltaMultiplier = 3;
    double deltaSec = ((double) 1 / tickInterval) * deltaMultiplier;

    public GameModel() {
        balls = new ArrayList<>();
        feeds = new ArrayList<>();
        timer = new Timer(tickInterval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update(); // Вызываем метод обновления поля
            }
        });
    }

    public void startGame() {
        timer.start(); // Запускаем таймер при старте игры
    }

    public void stopGame() {
        timer.stop(); // Останавливаем таймер при окончании игры
    }

    public List<Object> getObjects() {
        List<Object> objects = new ArrayList<>();
        return objects;
    }

    public void InitializeField(GameView gameView, GameController gameController) {
        this.gameView = gameView;
        this.gameController = gameController;
        balls.add(new Ball((double) gameView.getWidth() / 2, (double) gameView.getHeight() / 2, 50, 0));
        long currentTimeMillis = System.currentTimeMillis();
        Random random = new Random(currentTimeMillis);
        int ballDiameter = 20 + random.nextInt(50);
        for (int i = 1; i < ballDiameter; ++i) {
            balls.add(new Ball(random.nextInt(gameView.getWidth()), random.nextInt(gameView.getHeight()), 20 + random.nextInt(20), i));
        }
        int feedDiameter = 100 + random.nextInt(200);
        for (int i = 1; i < ballDiameter; ++i) {
            feeds.add(new Feed(random.nextInt(gameView.getWidth()), random.nextInt(gameView.getHeight()), 5 + random.nextInt(20), i));
        }
        startGame();
    }

    int imitateJob(int c) {
        long currentTimeMillis = System.currentTimeMillis();
        Random random = new Random(currentTimeMillis);
        int sum = 0;
        c = random.nextInt(c);
        while (c > 0) {
            for (int i = 0; i < random.nextInt(10000000); ++i) {
                sum += random.nextInt(2);
            }
            --c;
        }
        return sum;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    private double distance(Ball a, Ball b) {
        double aX = a.getX();
        double aY = a.getY();
        double bX = b.getX();
        double bY = b.getY();
        double vectorX = bX - aX;
        double vectorY = bY - aY;
        return Math.sqrt(vectorX * vectorX + vectorY * vectorY);
    }

    private double distance(Feed a, Ball b) {
        double aX = a.getX();
        double aY = a.getY();
        double bX = b.getX();
        double bY = b.getY();
        double vectorX = bX - aX;
        double vectorY = bY - aY;
        return Math.sqrt(vectorX * vectorX + vectorY * vectorY);
    }

    private double distanceToMouse(Ball ball) {
        int mouseX = gameController.getMouseX();
        int mouseY = gameController.getMouseY();
        double ballX = ball.getX();
        double ballY = ball.getY();
        double dx = mouseX - ballX;
        double dy = mouseY - ballY;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private void updateBallPosition(Ball ball) {
        if (ball.getSize() <= 0) return;
        double minSmall = 2500;
        double minBig = 2500;
        double distanceSmall = -1;
        double distanceBig = -1;
        Ball targetBig = null;
        Ball targetSmall = null;
        for (Ball anotherBall : balls) {
            if (anotherBall != ball && anotherBall.getSize() > 0) {
                if (anotherBall.getSize() < ball.getSize()) {
                    distanceSmall = distance(ball, anotherBall);
                    if (distanceSmall < minSmall) {
                        targetSmall = anotherBall;
                        minSmall = distanceSmall;
                    }
                } else {
                    distanceBig = distance(ball, anotherBall);
                    if (distanceBig < minBig) {
                        targetBig = anotherBall;
                        minBig = distanceBig;
                    }
                }
            }
        }
        if (ball.getIndex() == 0) {
            double mouseX = gameController.getMouseX();
            double mouseY = gameController.getMouseY();
            double ballX = ball.getX();
            double ballY = ball.getY();
            double VectorX = (double) mouseX - ball.getX();
            double VectorY = (double) mouseY - ball.getY();
            double length = Math.sqrt(VectorX * VectorX + VectorY * VectorY);
            ball.setX(ball.getX() + VectorX / length * deltaSec);
            ball.setY(ball.getY() + VectorY / length * deltaSec);
        } else {
            if (targetSmall != null) {
                double VectorX = targetSmall.getX() - ball.getX();
                double VectorY = targetSmall.getY() - ball.getY();
                double length = Math.sqrt(VectorX * VectorX + VectorY * VectorY);
                ball.setX(ball.getX() + VectorX / length * deltaSec);
                ball.setY(ball.getY() + VectorY / length * deltaSec);
            } else {
                Random random = new Random();

                ball.setX(ball.getX() + (random.nextInt(2) - 1) * deltaSec);
                ball.setY(ball.getY() + (random.nextInt(2) - 1) * deltaSec);
            }
        }
        for (Feed feed : feeds) {
            if (distance(feed, ball) < (ball.getSize() - feed.getSize()) / 2) {
                ball.setSize(Math.sqrt((ball.getSize() * ball.getSize() + feed.getSize() * feed.getSize())));
                feed.setSize(0);
            }
        }
        if (ball.getX() > gameView.getWidth()) ball.setX(gameView.getWidth());
        if (ball.getX() < 0) ball.setX(0);
        if (ball.getY() > gameView.getHeight()) ball.setY(gameView.getHeight());
        if (ball.getY() < 0) ball.setY(0);
        if (targetSmall != null && targetSmall.getSize() > 0) {
            if (minSmall < (ball.size - targetSmall.size) / 2) {
                ball.setSize(Math.sqrt((ball.getSize() * ball.getSize() + targetSmall.getSize() * targetSmall.getSize())));
                targetSmall.setSize(0);
            }
        }
    }

    private void update() {
        List<Ball> copyOfBalls = new ArrayList<>(balls);
        List<Feed> copyOfFeeds = new ArrayList<>(feeds);
        Iterator<Ball> iterator1 = copyOfBalls.iterator();
        while (iterator1.hasNext()) {
            Ball ball = iterator1.next();
            updateBallPosition(ball);
            if (ball.getSize() <= 0) {
                iterator1.remove();
            }
        }
        copyOfFeeds.removeIf(feed -> feed.getSize() <= 0);
        feeds = copyOfFeeds;
        balls = copyOfBalls;
    }
}