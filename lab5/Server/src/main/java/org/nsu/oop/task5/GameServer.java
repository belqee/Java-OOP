package org.nsu.oop.task5;
import org.nsu.oop.task5.Model.ServerModel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private static final int PORT = 12345;
    public static void main(String[] args) {
        ServerModel serverModel = new ServerModel();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serverModel.NewPlayer(clientSocket.getInetAddress());
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                new Thread(new ClientHandler(clientSocket,serverModel)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
