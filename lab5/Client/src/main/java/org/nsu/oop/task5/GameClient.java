package org.nsu.oop.task5;
import org.nsu.oop.task5.Model.GameModel;

import javax.swing.*;
import java.io.*;
import java.net.Socket;



public class GameClient {
    private static final String SERVER_ADDRESS = "25.33.190.113";
    private static final int SERVER_PORT = 12345;

    private Socket socket;
    private ObjectOutputStream objectOut;

    private GameModel gameModel;
    private GameView gameView;
    private GameController gameController;

    public GameClient() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            objectOut = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Connected to server");

            startGame(1280, 720);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        GameClient gameClient = new GameClient();
    }



    private void startGame(int width, int height) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gameModel = new GameModel();
                gameView = new GameView(gameModel, width, height);

                JFrame frame = new JFrame();
                frame.setTitle("Agario-like Game");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.getContentPane().add(gameView);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                gameController = new GameController(gameModel, gameView);
                gameModel.InitializeField(gameView, gameController,socket, objectOut);
                gameController.startGame(gameModel, gameView);
            }
        });
    }
}