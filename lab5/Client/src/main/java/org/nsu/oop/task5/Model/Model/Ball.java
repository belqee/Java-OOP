package org.nsu.oop.task5.Model.Model;

public class Ball extends Entity {
    public Ball(double x, double y, int size, int index) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}