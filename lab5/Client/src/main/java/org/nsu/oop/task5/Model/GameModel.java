package org.nsu.oop.task5.Model;

import org.nsu.oop.task5.Dot;
import org.nsu.oop.task5.GameController;
import org.nsu.oop.task5.GameView;

import javax.swing.Timer;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;
import java.util.List;

public class GameModel {
    private List<Ball> balls;
    private List<Feed> feeds;
    private GameView gameView = null;
    private GameController gameController = null;
    private Socket socket = null;
    private ObjectOutputStream objectOut = null;
    private final int tickInterval = 1000 / 120;
    private final Timer timer;
    private final Timer feedTimer;
    private final double deltaMultiplier = 4;
    private final double deltaSec;
    private final Random random = new Random(System.currentTimeMillis());
    private int feedDiameter = 100 + random.nextInt(200);

    public GameModel() {
        balls = new ArrayList<>();
        feeds = new ArrayList<>();
        deltaSec = ((double) 1 / tickInterval) * deltaMultiplier;
        timer = new Timer(tickInterval, e -> update());
        feedTimer = new Timer(1000, e -> spawnFeed());
    }

    private void spawnFeed() {
        feedDiameter++;
        feeds.add(new Feed(random.nextInt(gameView.getWidth()), random.nextInt(gameView.getHeight()), 5 + random.nextInt(20), feedDiameter));
    }

    public void startGame() {
        timer.start();
        feedTimer.start();
    }

    public void stopGame() {
        timer.stop();
        feedTimer.stop();
    }

    public void InitializeField(GameView gameView, GameController gameController, Socket socket, ObjectOutputStream objectOut) {
        this.gameView = gameView;
        this.gameController = gameController;
        this.socket = socket;
        this.objectOut = objectOut;
        balls.add(new Ball((double) gameView.getWidth() / 2, (double) gameView.getHeight() / 2, 50, 0));
        int ballDiameter = 20 + random.nextInt(50);
        for (int i = 1; i < ballDiameter; ++i) {
            balls.add(new Ball(random.nextInt(gameView.getWidth()), random.nextInt(gameView.getHeight()), 20 + random.nextInt(20), i));
        }
        for (int i = 1; i < ballDiameter; ++i) {
            feeds.add(new Feed(random.nextInt(gameView.getWidth()), random.nextInt(gameView.getHeight()), 5 + random.nextInt(20), i));
        }
        startGame();
    }
    public void sendObject(Object object) {
        try {
            objectOut.writeObject(object);
            objectOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void update() {
        sendObject(new Dot(gameController.getMouseX(), gameController.getMouseY()));
        for (Iterator<Ball> iterator = balls.iterator(); iterator.hasNext(); ) {
            Ball ball = iterator.next();
            updateBallPosition(ball);
            if (ball.getSize() <= 0) {
                iterator.remove();
            }
        }
        feeds.removeIf(feed -> feed.getSize() <= 0);
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

    public List<Ball> getBalls() {
        return balls;
    }


    public List<Feed> getFeeds() {
        return feeds;
    }

    private void updateBallPosition(Ball ball) {
        if (ball.getSize() <= 0) return;
        double minSmall = 9999;
        Ball targetSmall = null;
        for (Ball anotherBall : balls) {
            if (anotherBall != ball && anotherBall.getSize() > 0) {
                if (anotherBall.getSize() < ball.getSize()) {
                    double distanceSmall = distance(ball, anotherBall);
                    if (distanceSmall < minSmall) {
                        targetSmall = anotherBall;
                        minSmall = distanceSmall;
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

            if (targetSmall != null && minSmall < targetSmall.getSize() * 100) {
                double VectorX = targetSmall.getX() - ball.getX();
                double VectorY = targetSmall.getY() - ball.getY();
                double length = Math.sqrt(VectorX * VectorX + VectorY * VectorY);
                ball.setX(ball.getX() + VectorX / length * deltaSec);
                ball.setY(ball.getY() + VectorY / length * deltaSec);
            } else {
                double minFeed = 9999;
                Feed targetFeed = null;
                for (Feed anotherFeed : feeds) {
                    if (anotherFeed.getSize() > 0) {
                        double distanceSmall = distance(anotherFeed, ball);
                        if (distanceSmall < minSmall) {
                            targetFeed = anotherFeed;
                            minFeed = distanceSmall;
                        }
                    }
                }
                if (targetFeed != null && minFeed < targetFeed.getSize() * 100) {
                    double VectorX = targetFeed.getX() - ball.getX();
                    double VectorY = targetFeed.getY() - ball.getY();
                    double length = Math.sqrt(VectorX * VectorX + VectorY * VectorY);
                    ball.setX(ball.getX() + VectorX / length * deltaSec);
                    ball.setY(ball.getY() + VectorY / length * deltaSec);
                } else {
                    Random random = new Random((long) (ball.getX() + ball.getY()));

                    ball.setX(ball.getX() + (random.nextInt(2)) * deltaSec);
                    ball.setY(ball.getY() + (random.nextInt(2)) * deltaSec);
                }
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
}