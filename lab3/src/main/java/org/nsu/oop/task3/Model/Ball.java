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
    public void draw(Graphics g) {
        g.setColor(intToColor(index));
        int centerX = (int) (x - size / 2);
        int centerY = (int) (y - size / 2);
        g.fillOval(centerX, centerY, (int) Math.round(size), (int) Math.round(size));

        if (index == 0) {
            g.setColor(Color.WHITE);
            String numberString = "УЙЦУЙУЙЦУЙЦУ";

            Font font = new Font("Arial", Font.BOLD, (int)((double)Math.round(1.4*size/numberString.length()) )); // Выбираем шрифт и размер
            g.setFont(font);

            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(numberString);
            int textHeight = fm.getHeight();
            int textX = centerX + ((int) Math.round(size ) - textWidth) / 2;
            int textY = centerY + ((int) Math.round(size ) - textHeight) / 2 + fm.getAscent();
            g.drawString(numberString, textX, textY);
        }else  {
            g.setColor(Color.WHITE);
            String numberString = String.valueOf(index);


            Font font = new Font("Arial", Font.BOLD, (int)((double)Math.round(1.4*size/numberString.length()) )); // Выбираем шрифт и размер
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(numberString);
            
            int textHeight = fm.getHeight();
            int textX = centerX + ((int) Math.round(size ) - textWidth) / 2;
            int textY = centerY + ((int) Math.round(size ) - textHeight) / 2 + fm.getAscent();
            g.drawString(numberString, textX, textY);
        }
    }
}