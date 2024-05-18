package org.nsu.oop.task5;

import org.nsu.oop.task5.Model.ServerModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private ObjectInputStream objectIn;
    private ServerModel serverModel;
    public ClientHandler(Socket clientSocket, ServerModel serverModel) {
        this.clientSocket = clientSocket;
        this.serverModel = serverModel;
        try {
            objectIn = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Object receivedObject;
            while ((receivedObject = objectIn.readObject()) != null) {
                if (receivedObject instanceof Dot) {
                    Dot mousePosition = (Dot) receivedObject;
                    serverModel.ProcessPlayer(mousePosition);
                    System.out.println("Mouse X: " + mousePosition.getX() + ", Y: " + mousePosition.getY());
                }
            }
        } catch (SocketException e) {
            System.out.println("Disconnected: " + clientSocket.getInetAddress().toString());
        } catch (IOException e) {
            // Обработка ошибки чтения или закрытия потока
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // Обработка ошибки невозможности найти класс для десериализации
            e.printStackTrace();
        } finally {
            try {
                if (objectIn != null) {
                    objectIn.close();
                }
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}