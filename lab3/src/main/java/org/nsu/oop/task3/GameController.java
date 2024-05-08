package org.nsu.oop.task3;
import org.nsu.oop.task3.Model.Ball;
import org.nsu.oop.task3.Model.GameModel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;


public class GameController implements MouseMotionListener {
    private GameModel model;
    private GameView view;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private int mouseX = 0;
    private int mouseY = 0;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        view.addMouseMotionListener(this);
        view.setFocusable(true);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println(String.valueOf(e.getX()) + " " + String.valueOf(e.getY()));
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void startGame(GameModel model, GameView view) {

        view.render();
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}