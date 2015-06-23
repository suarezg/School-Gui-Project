package com.versatile.view;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.versatile.application.AppConstants;

import net.miginfocom.swing.MigLayout;

public class VersatileUI {
	private JFrame frame;
	private JPanel panel;
	private JMenuBar menuBar;
	private JPopupMenu popupMenu;
	private JFileChooser chooser;
	private JLabel fileLabel;
	private JLabel imageLabel;
	private JComboBox modelList;
	private List<JTextField> params; 
	private List<JRadioButton> configs;
	private JTextArea consoleOut;
	
	private static String openFile = "";
	
	
	public VersatileUI() {
		createFrame();
	}
	
	private void createFrame() {
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
		//createFileLabel();
		createModelPicker();
		//createConsolePopupMenu();
		createConfigs();
		
		frame.setVisible(true);
	}
	
	private void createMenuBar() {
		menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenuItem exit = new JMenuItem("Exit");
		exit.setMnemonic(KeyEvent.VK_F4);
		//exit.addActionListener(exitListener);
		
		JMenuItem open = new JMenuItem("Open");
		//open.addActionListener(openListener);
		JMenuItem save = new JMenuItem("Save");
		JMenuItem saveAs = new JMenuItem("Save As");
		//saveAs.addActionListener(saveListener);
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(saveAs);
		fileMenu.add(exit);
		menuBar.add(fileMenu);
		
		frame.setJMenuBar(menuBar);
	}
	
	private void createFileLabel() {
		
	}
	
	private void createModelPicker() {
		modelList = new JComboBox(AppConstants.USABLE_MODELS);
		panel.add(modelList, "span");
	}
	
	private void createConsolePopupMenu() {
		
	}
	
	private void createConfigs() {
	
		ButtonGroup group = new ButtonGroup();
		configs = new ArrayList<JRadioButton>();
		for (int i = 0; i < AppConstants.CONFIGURATIONS.length; i++) {
			configs.add(new JRadioButton(AppConstants.CONFIGURATIONS[i]));
			group.add(configs.get(i));
			if (i == (AppConstants.CONFIGURATIONS.length -1))
				panel.add(configs.get(i), "wrap");
			else
				panel.add(configs.get(i));
		}
	}
	
	public double[] getParams() {
		double[] inputs = new double[params.size()];
		
		return inputs;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				VersatileUI ui = new VersatileUI();
			}
		});
	}
}
