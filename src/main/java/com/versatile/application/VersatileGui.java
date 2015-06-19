package com.versatile.application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;


public class VersatileGui {

	private static JFrame frame;
	private static JPanel panel;
	private static JMenuBar menuBar;
	private static JPopupMenu popUpMenu;
	private static JFileChooser fileChooser;
	private static JLabel fileLabel;
	private static JLabel imageLabel;
	private static JTextField input1;
	private static JTextField input2;
	private static JTextArea consoleOut;
	private static String models[] = new String[] {"Select One...","Model 1", "Model 2", "Model 3",
													"Model 4", "Model 5", "Model 6"};
	private static String openFile = "";
	
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
		createMenuBar();
		createFileLabel();
		createModelPicker();
		createConsolePopupMenu();
		//createRadios();
		createConfigs();
		createInputs();
		createButtons();
		createImageFrame();
		createTextArea();
		frame.add(panel);
		frame.pack();
		
	}
	
	/*--------------------------------MENU BAR AND LISTENERS-----------------------------------*/
	private static void createMenuBar() {
		menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenuItem exit = new JMenuItem("Exit");
		exit.setMnemonic(KeyEvent.VK_F4);
		exit.addActionListener(exitListener);
		
		JMenuItem open = new JMenuItem("Open");
		open.addActionListener(openListener);
		JMenuItem save = new JMenuItem("Save");
		JMenuItem saveAs = new JMenuItem("Save As");
		saveAs.addActionListener(saveListener);
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(saveAs);
		fileMenu.add(exit);
		menuBar.add(fileMenu);
		
		frame.setJMenuBar(menuBar);
	}
	
	private static ActionListener exitListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			System.exit(0); //close JVM
		}
	};
	
	private static ActionListener openListener = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			fileChooser = new JFileChooser(AppConstants.PROJECT_DIRECTORY+"\\target");
			fileChooser.showOpenDialog(frame);
		}
	};
	
	private static ActionListener saveListener = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			;
			fileChooser = new JFileChooser(AppConstants.PROJECT_DIRECTORY+"\\target");
			fileChooser.showSaveDialog(frame);
		}
		
	};
	/*-----------------------------------------------------------------------------*/
	
	/*---------------------------FILE LABEL------------------------------*/
	private static void createFileLabel() {
		fileLabel = new JLabel("File Open: " + openFile);
		
		panel.add(fileLabel, "dock north");
		
	}
	/*-----------------------------------------------------------*/
	
	/*---------------------------POPUP MENU----------------------------------*/
	private static void createConsolePopupMenu() {
		popUpMenu = new JPopupMenu();
		JMenuItem clear = new JMenuItem("Clear");
		clear.addActionListener(consoleListener);
		
	}
	
	private static MouseAdapter mouseListener = new MouseAdapter() {
		public void mousePressed(MouseEvent event) {
			maybeShowPopup(event);
		}
		
		public void mouseReleased(MouseEvent event) {
			maybeShowPopup(event);
		}
		
		private void maybeShowPopup(MouseEvent e) {
	        if (e.isPopupTrigger()) {
	            popUpMenu.show(e.getComponent(),
	                       e.getX(), e.getY());
	        }
	    }
	};
	
	private static ActionListener consoleListener = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	};
	/*----------------------------------------------------------------*/
	
	
	/*-----------------------------MODEL PICKER-------------------------------------*/
	private static void createModelPicker() {
		JComboBox modelList = new JComboBox(models);
		modelList.addActionListener(modelListener);
		panel.add(modelList, "span");

	}
	
	private static ActionListener modelListener = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			final String searchProperty = "selectedItemReminder=";
			String properties =  e.getSource().toString();
			String selectedItem = properties.substring(properties.indexOf(searchProperty) + searchProperty.length()
					, properties.lastIndexOf("]"));
	
			if ( selectedItem.equals("Select One...") ) {
				updateImage("");
			}
			else {
				updateImage("src/main/resources/com/versatile/application/images/" 
						+selectedItem+".jpg");
			}
		}
		
	};
	/*-----------------------------------------------------------------------------------*/
	
	/*-------------------------------CONFIGURATION RADIO BUTTONS---------------------------------------*/
	private static void createConfigs() {
		JRadioButton config1 = new JRadioButton("Configuration 1");
		JRadioButton config2 = new JRadioButton("Configuration 2");
		JRadioButton config3 = new JRadioButton("Configuration 3");
		ButtonGroup group = new ButtonGroup();
		
		group.add(config1);
		group.add(config2);
		group.add(config3);
		
		panel.add(config1);
		panel.add(config2);
		panel.add(config3,"wrap");
	}
	/*----------------------------------------------------------------------------------*/
	
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
	
	/*------------------------BUTTONS----------------------------*/
	
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
					consoleOut.append(result+"");
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
				}
			}
			
			
		});
		calculateThread.start();
		
		
	}
	
	private static void clearInputs() {
		input1.setText("");
		input2.setText("");
	}
	
	/*---------------------------------------------------------------------*/
	
	/*-----------------------INPUT PARAMETERS-------------------------*/
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
	/*------------------------------------------------------------------------*/	
	
	
	private static void createImageFrame() {
		imageLabel = new JLabel();
		panel.add(imageLabel, "span");
	}
	
	/*------------------------------CONSOLE OUTPUT---------------------------------------*/
	private static void createTextArea() {
		final int CONSOLE_LENGTH = 1000;
		final int CONSOLE_HEIGHT = 100;
		consoleOut = new JTextArea();
		consoleOut.setVisible(true);
		consoleOut.setEditable(false);
		consoleOut.addMouseListener(mouseListener);
		JScrollPane scroll = new JScrollPane(consoleOut);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setMinimumSize(new Dimension(CONSOLE_LENGTH, CONSOLE_HEIGHT));
		scroll.setMaximumSize(new Dimension(CONSOLE_LENGTH, CONSOLE_HEIGHT));
		
		panel.add(scroll, "dock south");
	}
	/*----------------------------------------------------------------------------------------*/
	
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
