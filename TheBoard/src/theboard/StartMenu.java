package theboard;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class StartMenu extends JFrame{
    private static int WIDTH = 800;
    private static int HEIGHT = 600;
    private static String name = null;
    JFormattedTextField ipField;
    JFormattedTextField nameField;
    JSlider thiknessSlider;
    JLabel valueLabel;
    
    public static void main(String[] args) {
        run();
        //new MainFrame(800, 800);
    }
    
    public static void run() {
        final JFrame a = new JFrame("");
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.setBounds(720,330,300, 280);
        JLabel c = new JLabel("Введите имя:");
        c.setBounds(60,20,90,20);
        
        JTextField b = new JTextField("user");
        b.setBounds(140,20,100,20);
        //создать
        final JButton createButton = new JButton("Создать");
	createButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		new MainFrame(WIDTH, HEIGHT);
                a.setVisible(false);
	    }
	});
        createButton.setBounds(70,60,150,30);
        
        String[] items = {"800х600",
                        "1100х800",
                        "1900х1000"
                        };
        JComboBox editComboBox = new JComboBox(items);
        editComboBox.setEditable(true);
        editComboBox.setBounds(70,95,150,30);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox)e.getSource();
                        int item = box.getSelectedIndex();
                        if(item == 1)
                        {
                            WIDTH = 800;
                            HEIGHT = 600;
                        }
                        if(item == 1)
                        {
                            WIDTH = 1100;
                            HEIGHT = 800;
                        }
                        if(item == 2)
                        {
                            WIDTH = 1900;
                            HEIGHT = 1000;
                        }
            }
        };
        editComboBox.addActionListener(actionListener);
        
        
	// Подключиться
	final JButton connectButton = new JButton("Подключиться");

	connectButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
		//
		//udpManager.connectToServer(ipField.getText());
		//new Thread(udpManager).start();
	    }
	});
        connectButton.setBounds(70,140,150,30);
        
        
        JFormattedTextField ip = new JFormattedTextField();
	final String pattern = "###.###.###.###";
	try {
	    ip.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(pattern)));
	} catch (ParseException ex) {
	    ip.setText("127.000.000.001");
	    ex.printStackTrace();
	}
	ip.setText("127.000.000.001");
        ip.setBounds(70,175,150,30);
	
        
        a.add(editComboBox);
        a.add(ip);
        a.add(c);
        a.add(b);
        a.add(createButton);
        a.add(connectButton);
        a.setLayout(null);
        a.setVisible(true);
    }
}
