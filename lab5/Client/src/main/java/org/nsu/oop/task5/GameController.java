package org.nsu.oop.task5;
import org.nsu.oop.task5.Model.GameModel;

import java.awt.event.*;


public class GameController implements MouseMotionListener, MouseListener {
    private GameModel model;
    private GameView view;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private int mouseX = 0;
    private int mouseY = 0;

    public Dot getMousePos(){
        return new Dot(mouseX,mouseY);
    }

    public GameController() {
        view.addMouseMotionListener(this);
        view.setFocusable(true);
    }

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
    @Override
    public void mousePressed(MouseEvent e) {
        int button = e.getButton();
        switch (button) {
            case MouseEvent.BUTTON1: // ЛКМ

                break;
            case MouseEvent.BUTTON3: // ПКМ

                break;

        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        // кнопка мыши отпускается
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
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