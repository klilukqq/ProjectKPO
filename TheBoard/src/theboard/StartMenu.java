package theboard;

import java.awt.event.*;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import javax.swing.text.*;
import java.awt.*;
import javax.swing.*;

public class StartMenu extends JFrame{
    private static int WIDTH = 800;
    private static int HEIGHT = 600;
    private static String name = null;
    JFormattedTextField ipField;
    JFormattedTextField nameField;
    JSlider thiknessSlider;
    JLabel valueLabel;
    String userName = "user";
    
    public static void main(String[] args) {
        new StartMenu();
        //new MainFrame(800, 800);
        //new Test().setVisible(true);
    }
    
    public StartMenu() {
        //final JFrame a = new JFrame("");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(720,330,300, 280);
        JLabel c = new JLabel("Введите имя:");
        c.setBounds(60,20,90,20);
        
        final JTextField b = new JTextField(userName);
        b.setDocument(new LimitedDocument(13));
        b.setBounds(140,20,100,20);
        //создать
        final JButton createButton = new JButton("Создать");
	createButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
                userName = b.getText();
		new MainFrame(WIDTH, HEIGHT, userName);
                MainFrame.udpManager.create();
		new Thread(MainFrame.udpManager).start();
                setVisible(false);
                dispose();
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
        
        
	// Подключиться;
	final JButton connectButton = new JButton("Подключится");
	connectButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
                userName = b.getText();
                new MainFrame(WIDTH, HEIGHT, userName);
                MainFrame.udpManager.connectToServer(ipField.getText());
		new Thread(MainFrame.udpManager).start();
                setVisible(false);
                dispose();
	    }
	});
        connectButton.setBounds(70,140,150,30);
        
        
        ipField = new JFormattedTextField();
	final String pattern = "###.###.###.###";
	try {
	    ipField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(pattern)));
	} catch (ParseException ex) {
	    ipField.setText("127.000.000.001");
	    ex.printStackTrace();
	}
	ipField.setText("127.000.000.001");
        ipField.setBounds(70,175,150,30);

        
        add(editComboBox);
        add(ipField);
        add(c);
        add(b);
        add(createButton);
        add(connectButton);
        setLayout(null);
        setVisible(true);
        //System.out.print("\nВведите логин ");
    }
    
}
