package theboard;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author bondar
 */

//Fully checked Kappa
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

    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
