package Project;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingFrame extends JFrame {
    private DrawingPanel drawingPanel;
    private static final Border DEFAULT_BORDER = UIManager.getBorder("Button.border");
    private JButton lastSelectedColorButton = null;
    private JButton lastSelectedShapeButton = null;

    public DrawingFrame() {
        setTitle("Drawing Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);
        add(createControlPanel(), BorderLayout.WEST);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(15, 1));

        controlPanel.add(new JLabel("Colors:"));
        controlPanel.add(new JLabel(""));
        
        controlPanel.add(createColorButton(Color.RED));
        controlPanel.add(createColorButton(Color.GREEN));
        controlPanel.add(createColorButton(Color.BLUE));

        JButton customColorButton = new JButton("Custom Color");
        customColorButton.setFocusPainted(false);
        customColorButton.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(this, "Choose a Color", drawingPanel.getCurrentColor());
            if (selectedColor != null) {
                drawingPanel.setCurrentColor(selectedColor);
            }
        });
        controlPanel.add(customColorButton);

        controlPanel.add(new JLabel("Shapes:"));
        controlPanel.add(new JLabel(""));
        
        controlPanel.add(createShapeButton("Rectangle", DrawingPanel.ShapeType.RECTANGLE));
        controlPanel.add(createShapeButton("Oval", DrawingPanel.ShapeType.OVAL));
        controlPanel.add(createShapeButton("Line", DrawingPanel.ShapeType.LINE));

        controlPanel.add(createActionButton("Free Hand", DrawingPanel.ActionType.FREE_HAND));
        controlPanel.add(createActionButton("Eraser", DrawingPanel.ActionType.ERASER));

        JButton clearButton = new JButton("Clear All");
        clearButton.setFocusPainted(false);
        clearButton.addActionListener(e -> drawingPanel.clearAll());
        controlPanel.add(clearButton);

        JCheckBox dottedCheckbox = new JCheckBox("Dotted");
        dottedCheckbox.setFocusPainted(false);
        JCheckBox filledCheckbox = new JCheckBox("Filled");
        filledCheckbox.setFocusPainted(false);

        dottedCheckbox.addActionListener(e -> {
            if (dottedCheckbox.isSelected()) {
                filledCheckbox.setSelected(false);
                drawingPanel.setDotted(true);
            } else {
                drawingPanel.setDotted(false);
            }
        });

        filledCheckbox.addActionListener(e -> {
            if (filledCheckbox.isSelected()) {
                dottedCheckbox.setSelected(false);
                drawingPanel.setFilled(true);
            } else {
                drawingPanel.setFilled(false);
            }
        });

        controlPanel.add(new JLabel("Options:"));
        controlPanel.add(new JLabel(""));
        
        controlPanel.add(dottedCheckbox);
        controlPanel.add(filledCheckbox);

        JButton saveButton = new JButton("Save");
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(e -> drawingPanel.saveDrawing());
        controlPanel.add(saveButton);

        JButton undoButton = new JButton("Undo");
        undoButton.setFocusPainted(false);
        undoButton.addActionListener(e -> drawingPanel.undo());
        controlPanel.add(undoButton);

        JButton openButton = new JButton("Open");
        openButton.setFocusPainted(false);
        openButton.addActionListener(e -> drawingPanel.openImage());
        controlPanel.add(openButton);

        return controlPanel;
    }

    private JButton createColorButton(Color color) {
        JButton button = new JButton();
        button.setFocusPainted(false);
        button.setIcon(createColorIcon(color));
        button.addActionListener(e -> {
            drawingPanel.setCurrentColor(color);
            applyColorEffect(button);
        });
        return button;
    }

    private JButton createShapeButton(String text, DrawingPanel.ShapeType shapeType) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.addActionListener(e -> {
            drawingPanel.setCurrentShape(shapeType);
            applyShapeEffect(button);
        });
        return button;
    }

    private JButton createActionButton(String text, DrawingPanel.ActionType actionType) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.addActionListener(e -> {
            drawingPanel.setCurrentAction(actionType);
            applyShapeEffect(button);
        });
        return button;
    }

    private void applyColorEffect(JButton button) {
        if (lastSelectedColorButton != null) {
            lastSelectedColorButton.setBorder(DEFAULT_BORDER);
            lastSelectedColorButton.setBackground(UIManager.getColor("Button.background"));
        }
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        button.setBackground(Color.LIGHT_GRAY);
        lastSelectedColorButton = button;
    }

    private void applyShapeEffect(JButton button) {
        if (lastSelectedShapeButton != null) {
            lastSelectedShapeButton.setBorder(DEFAULT_BORDER);
            lastSelectedShapeButton.setBackground(UIManager.getColor("Button.background"));
        }
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));
        button.setBackground(Color.LIGHT_GRAY);
        lastSelectedShapeButton = button;
    }

    private Icon createColorIcon(Color color) {
        int size = 20;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(color);
        g2d.fillRect(0, 0, size, size);
        g2d.dispose();
        return new ImageIcon(image);
    }
}
