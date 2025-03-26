package Project;

import javax.swing.*;

public class DrawingApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DrawingFrame frame = new DrawingFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }
}