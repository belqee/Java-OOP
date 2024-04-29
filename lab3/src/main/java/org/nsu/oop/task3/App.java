package org.nsu.oop.task3;
import org.nsu.oop.task3.Model.GameModel;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameModel model = new GameModel();
                GameView view = new GameView(model);
                GameController controller = new GameController(model, view);
                controller.startGame(model, view);
            }
        });
    }
}