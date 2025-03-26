package Project;

import java.awt.*;

public class Oval extends Shape {
    private int width, height;

    public Oval(int x, int y, int width, int height, Color color, boolean filled, boolean dotted) {
        super(x, y, color, filled, dotted);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        if (filled) {
            g2d.fillOval(x, y, width, height);
        } else {
            g2d.drawOval(x, y, width, height);
        }
    }
}
