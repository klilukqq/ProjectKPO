package theboard;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


public class DrawablePanel extends JPanel {

    private final MainFrame mainFrame;

    public DrawablePanel(MainFrame app, int width, int height) {
	this.mainFrame = app;
	setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	mainFrame.paintDrawablePanel(g);
    }
}
