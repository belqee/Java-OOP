package org.nsu.oop.task3;
import org.nsu.oop.task3.Model.Ball;
import org.nsu.oop.task3.Model.GameModel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

class GameController implements KeyListener{
    private GameModel model;
    private GameView view;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        view.addKeyListener(this);
        view.setFocusable(true);
    }

    private void updateBallPosition() {
        int dx = 0, dy = 0;
        if (upPressed) dy = -1;
        if (downPressed) dy = 1;
        if (leftPressed) dx = -1;
        if (rightPressed) dx = 1;
        model.updateBallPosition(dx, dy);
        view.render();
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
        }
        updateBallPosition();
    }

    public void startGame(GameModel model, GameView view) {
        view.render();
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
        }
        updateBallPosition();
    }
    public void keyTyped(KeyEvent e) {}
}