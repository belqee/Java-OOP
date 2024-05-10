package org.nsu.oop.task3.Model;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

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