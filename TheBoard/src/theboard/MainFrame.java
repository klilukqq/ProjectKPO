package theboard;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author truebondar
 */
public class MainFrame extends JFrame{

    private final JPanel drawablePanel;
    private List<Primitive2D> myPrims = new ArrayList<Primitive2D>();
    private Color curColor = Color.BLACK;
    private int curThikness = 3;
    private int curButtonDown = -1;
    private List<Primitive2D> friendPrims = new ArrayList<Primitive2D>();
    private Color friendCurColor = Color.BLACK;
    private int friendCurThikness = 3;
    JFormattedTextField ipField;
    JSlider thiknessSlider;
    JLabel valueLabel;

    /////////////////////////////////////////////////////////
    public MainFrame(int WIDTH, int HEIGHT) 
    {
	setPreferredSize(new Dimension(WIDTH, HEIGHT));
	setResizable(false);
	setTitle("The board");
	setLocationByPlatform(true);
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
	// панель параметров рисования
	final JPanel paramsPanel = new JPanel();
	// Цвет
	final JButton colorButton = new JButton("Цвет");
	colorButton.addActionListener(changeColorListener);
	paramsPanel.add(colorButton);
	// Толщина
	final JLabel textLabel = new JLabel("Толщина:");
	paramsPanel.add(textLabel);

	valueLabel = new JLabel();
	valueLabel.setPreferredSize(new Dimension(20, 20));
	valueLabel.setText(Integer.toString(curThikness));

	thiknessSlider = new JSlider(1, 10, curThikness);
	thiknessSlider.addChangeListener(thiknessChangeListener);
	paramsPanel.add(thiknessSlider);
	paramsPanel.add(valueLabel);
	// Создать
	final JButton createButton = new JButton("Создать");
	createButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		//
		//udpManager.create();
		//new Thread(udpManager).start();
	    }
	});
	paramsPanel.add(createButton);
	// Подключиться
	final JButton connectButton = new JButton("Подключиться");

	connectButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		//
		//udpManager.connectToServer(ipField.getText());
		//new Thread(udpManager).start();
	    }
	});
	paramsPanel.add(connectButton);
        //
	ipField = new JFormattedTextField();
	final String pattern = "###.###.###.###";
	try {
	    ipField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(pattern)));
	} catch (ParseException ex) {
	    ipField.setText("127.000.000.001");
	    ex.printStackTrace();
	}
	ipField.setText("127.000.000.001");
	paramsPanel.add(ipField);
	add(paramsPanel);

	// панель рисования
	drawablePanel = new DrawablePanel(this, WIDTH, HEIGHT);
	drawablePanel.addMouseListener(new MListener());
	drawablePanel.addMouseMotionListener(new MMListener());
	add(drawablePanel);
        
        //выход
        final JButton exitButton = new JButton("Выход");
	exitButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		StartMenu.run();
                setVisible(false);
	    }
	});
	paramsPanel.add(exitButton);

	pack();
	setVisible(true);

	friendPrims.add(new Primitive2D(friendCurColor, friendCurThikness));
    }

    /////////////////////////////////////////////////////////
    public void paintDrawablePanel(Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
	g2d.clearRect(0, 0, WIDTH, HEIGHT);

	drawPrims(g2d, myPrims);
	drawPrims(g2d, friendPrims);
    }

    private void drawPrims(Graphics2D g2d, List<Primitive2D> prims) {
	for (Primitive2D prim : prims) {
	    int count = prim.getPointsCount();
	    g2d.setColor(prim.getColor());
	    BasicStroke stroke = new BasicStroke(prim.getThikness());
	    g2d.setStroke(stroke);
	    for (int i = 0; i < prim.getPointsCount() - 1; i++) {
		Point p1 = prim.getPoint(i);
		Point p2 = prim.getPoint(i + 1);
		drawLine(g2d, p1, p2);
	    }
	    if (count == 1) {
		Point p = prim.getPoint(0);
		drawLine(g2d, p, p);
	    }
	}
    }

    private void drawLine(Graphics2D g2d, Point p1, Point p2, Color color, int thikness) {
	g2d.setColor(color);
	BasicStroke stroke = new BasicStroke(thikness);
	g2d.setStroke(stroke);
	drawLine(g2d, p1, p2);
    }

    private void drawLine(Graphics2D g2d, Point p1, Point p2) {
	g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    /////////////////////////////////////////////////////////
    private void addPrimitive(List<Primitive2D> prims, Primitive2D prim, boolean isNeedSend) {
	prims.add(prim);
	//
	if (isNeedSend) {
	    //sendNewPrimitive();
	}
    }

    private void addPoint(List<Primitive2D> prims, Point p, boolean isNeedSend) {
	prims.get(prims.size() - 1).addPoint(p);
	drawablePanel.repaint();
	//
	if (isNeedSend) {
	    //sendNewPoint(p);
	}
    }

    private void removeLastPrim(List<Primitive2D> prims, boolean isNeedSend) {
	prims.remove(prims.size() - 1);
	drawablePanel.repaint();
	//
	if (isNeedSend) {
	    //sendRemovePrimitive();
	}
    }
    
    ActionListener changeColorListener = new ActionListener() {
	public void actionPerformed(ActionEvent event) {
	    Color selectedColor = JColorChooser.showDialog(getContentPane(), "Выбор цвета", Color.BLACK);
	    if (selectedColor != null) {
		curColor = selectedColor;
		//
		//sendNewColor(curColor);
	    }
	}
    };
    ChangeListener thiknessChangeListener = new ChangeListener() {
	@Override
	public void stateChanged(ChangeEvent ce) {
	    curThikness = thiknessSlider.getValue();
	    valueLabel.setText(Integer.toString(curThikness));
	    //
	    //sendNewThikness(curThikness);
	}
    };

    /////////////////////////////////////////////////////////
    
    
    
    /////////////////////////////////////////////////////////
    public class MListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	    curButtonDown = e.getButton();
	    if (e.getButton() != MouseEvent.BUTTON1) {
		return;
	    }
	    addPrimitive(myPrims, new Primitive2D(curColor, curThikness), true);
	    addPoint(myPrims, e.getPoint(), true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	    curButtonDown = e.getButton();
	    if (e.getButton() != MouseEvent.BUTTON3) {
		return;
	    }
	    if (myPrims.isEmpty()) {
		return;
	    }
	    removeLastPrim(myPrims, true);
	}
    }

    /////////////////////////////////////////////////////////
    public class MMListener implements MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent me) {
	    if (curButtonDown != MouseEvent.BUTTON1) {
		return;
	    }
	    addPoint(myPrims, me.getPoint(), true);
	}

	@Override
	public void mouseMoved(MouseEvent me) {
	}
    }

    
}
