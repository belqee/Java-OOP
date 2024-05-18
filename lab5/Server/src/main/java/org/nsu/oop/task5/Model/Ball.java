package org.nsu.oop.task5.Model;

public class Ball extends Entity {
    BallType ballType;
    public Ball(double x, double y, int size, int index, BallType ballType) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.index = index;
        this.ballType = ballType;
    }

    public int getIndex() {
        return index;
    }

}