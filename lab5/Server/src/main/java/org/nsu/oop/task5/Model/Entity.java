package org.nsu.oop.task5.Model;

import java.awt.*;

public abstract class Entity {
    protected double x;
    protected double y;
    public double size;
    protected int index;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Color intToColor(int i){
        return Color.getHSBColor((float) (i*7777 % 1000) / 1000F, 0.6F,0.9F);
    }

}
