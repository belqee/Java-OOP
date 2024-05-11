package org.nsu.oop.task3;
import org.nsu.oop.task3.Model.GameModel;

import javax.swing.*;

public class App {
    private static void Start(int width, int height){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameModel gameModel = new GameModel();
                GameView gameView = new GameView(gameModel, 1280, 720);

                JFrame frame = new JFrame();
                frame.setTitle("Agario-like Game");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.getContentPane().add(gameView);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                GameController gameController = new GameController(gameModel, gameView);
                gameModel.InitializeField(gameView, gameController);
                gameController.startGame(gameModel, gameView);
            }
        });
    }

    public static void main(String[] args) {
        Start(1280,720);
    }
}