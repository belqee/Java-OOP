package org.nsu.oop.task3.Model;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class Ball {
    private int x; // Координата x шарика
    private int y; // Координата y шарика
    private int size; // Размер шарика
    private Color color; // Цвет шарика

    public Ball(int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    // Геттеры и сеттеры для полей шарика
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // Метод для отрисовки шарика
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }
}