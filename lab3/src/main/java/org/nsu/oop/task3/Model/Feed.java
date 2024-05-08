package org.nsu.oop.task3.Model;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class Feed extends Entity {
    public Feed(double x, double y, int size, int index) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
    public void draw(Graphics g) {
        g.setColor(intToColor(index));
        int centerX = (int) (x - size / 2);
        int centerY = (int) (y - size / 2);
        g.fillOval(centerX, centerY, (int) Math.round(size), (int) Math.round(size));
    }
}