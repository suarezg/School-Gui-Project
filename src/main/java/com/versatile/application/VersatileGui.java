package com.versatile.application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;


public class VersatileGui {

	private static JFrame frame;
	private static JPanel panel;
	private static JLabel imageLabel;
	private static JTextField input1;
	private static JTextField input2;
	private static JTextArea consoleOut;
	
	private static void createFrame() {
		
		frame = new JFrame("Versatile - Buhler Industries");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
		//		"src\\main\\resources\\com\\versatile\\application\\images\\logo.png")));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent event) {
				
			}
		});
		panel = new JPanel(new MigLayout());
		createRadios();
		createInputs();
		createButtons();
		createImageFrame();
		createTextArea();
		frame.add(panel);
		frame.pack();
		
	}
	
	private static void createMenuBar() {
		
	}
	
	private static void createRadios() {
	
		JRadioButton td600 = new JRadioButton("Model TD600");
		JRadioButton td700 = new JRadioButton("Model TD700");
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(td600);
		radioGroup.add(td700);
		
		td600.addActionListener(radioListener);
		td700.addActionListener(radioListener);
		
		panel.add(td600);
		panel.add(td700, "wrap");
	}
	
	private static ActionListener radioListener = new ActionListener() {

		public void actionPerformed(ActionEvent event) {
			String model = event.getActionCommand();
			if (model.contains("TD600")) {
				updateImage("src/main/resources/com/versatile/application/images/td600.jpg");
			}
			else if (model.contains("TD700")) {
				updateImage("src/main/resources/com/versatile/application/images/td700.jpg");
			}
		}
		
	};
	
	private static void updateImage(String imagePath) {
		imageLabel.setIcon(new ImageIcon(imagePath));
		frame.repaint();
	}
	
	private static void createButtons() {
		JButton run = new JButton("Run");
		JButton clear = new JButton("Clear");
		run.addActionListener(buttonListener);
		clear.addActionListener(buttonListener);
		panel.add(run);
		panel.add(clear, "wrap");
	}
	
	private static ActionListener buttonListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			String command = event.getActionCommand();
			if (command.contains("Run")) {
				calculate();
			}
			else if (command.contains("Clear")) {
				clearInputs();
			}
			
		}
	};
	
	private static void calculate() {
		Thread calculateThread = new Thread(new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				final int stackMax = 10;
				try {
					int num1 = Integer.parseInt(input1.getText());
					int num2 = Integer.parseInt(input2.getText());
					int result = num1 + num2;
					Thread.sleep(10000);
					consoleOut.setText(result+"");
				}
				catch(NumberFormatException nfe) {
					StringBuilder errorMessage = new StringBuilder();
					StackTraceElement[] trace = nfe.getStackTrace();
					errorMessage.append("\n  "+getTimeStamp());
					for (int i =0; (i < trace.length) && (i < stackMax); i++) {
						errorMessage.append("\t"+trace[i] +"\n");
						if (i == stackMax-1) {
							errorMessage.append("\t...\n");
						}
					}
					errorMessage.append("\t...\n");
					errorMessage.append(nfe.getMessage());
					consoleOut.setForeground(Color.RED);
					consoleOut.append(errorMessage.toString());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		calculateThread.start();
		
		
	}
	
	private static void clearInputs() {
		input1.setText("");
		input2.setText("");
	}
	
	private static void createInputs() {
		final int INPUT_LENGTH = 100;
		final int INPUT_HEIGHT = 25;
		input1 = new JTextField("0");
		input2 = new JTextField("0");
		input1.setMinimumSize(new Dimension(INPUT_LENGTH, INPUT_HEIGHT));
		input1.setMaximumSize(new Dimension(INPUT_LENGTH, INPUT_HEIGHT));
		input2.setMinimumSize(new Dimension(INPUT_LENGTH, INPUT_HEIGHT));
		input2.setMaximumSize(new Dimension(INPUT_LENGTH, INPUT_HEIGHT));
		panel.add(input1);
		panel.add(input2,"wrap");
	}
		
	
	private static void createImageFrame() {
		imageLabel = new JLabel();
		panel.add(imageLabel, "span");
	}
	
	private static void createTextArea() {
		final int CONSOLE_LENGTH = 500;
		final int CONSOLE_HEIGHT = 250;
		consoleOut = new JTextArea();
		consoleOut.setVisible(true);
		consoleOut.setEditable(false);
		JScrollPane scroll = new JScrollPane(consoleOut);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setMinimumSize(new Dimension(CONSOLE_LENGTH, CONSOLE_HEIGHT));
		scroll.setMaximumSize(new Dimension(CONSOLE_LENGTH, CONSOLE_HEIGHT));
		
		panel.add(scroll, "span");
	}
	
	private static String getTimeStamp() {
		String stamp;
		Date date = new Date();
		stamp = (new Timestamp(date.getTime())).toString();
		return stamp;
	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				createFrame();
				frame.setVisible(true);			
			}
		});
	}

}
