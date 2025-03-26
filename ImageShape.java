package Project;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageShape extends Shape {
    private BufferedImage image;

    public ImageShape(BufferedImage image, int x, int y) {
        super(x, y, Color.BLACK, false, false);
        this.image = image;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, x, y, null);
    }
}

