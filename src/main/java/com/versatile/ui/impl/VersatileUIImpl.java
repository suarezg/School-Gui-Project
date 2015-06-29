package com.versatile.ui.impl;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.versatile.backend.VersatileBackend;
import com.versatile.ui.VersatileUI;
import com.versatile.utils.AppConstants;

public class VersatileUIImpl implements VersatileUI {

	private VersatileBackend backend;
	private JFrame frame;
	private JPanel main;
	private Map<String, Component> components;
	
	public VersatileUIImpl() {
		createFrame();
	}
	
	private void createFrame() {
		frame = new JFrame();
		main = new JPanel(new MigLayout());
		createMenuBar();
		createModelPicker();
		createConfigurations();
		createParameters();
		createConditionals();
		createButtons();
		frame.add(main);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
	
	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
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
		
		JMenu helpMenu = new JMenu("Help");
		JMenuItem about = new JMenuItem("About");
		about.addActionListener(aboutListener);
		helpMenu.add(about);
		menuBar.add(helpMenu);
				
		frame.setJMenuBar(menuBar);
	}
	
	private void createModelPicker() {
		JComboBox modelPicker = new JComboBox(AppConstants.USABLE_MODELS);
		modelPicker.setName("Model Picker");
		//modelPicker.addActionListener();
		main.add(modelPicker);
		components.put("Model Picker", modelPicker);
		
	}
	
	private void createConfigurations() {
		
	}
	
	private void createParameters() {
		
	}
	
	private void createConditionals() {
		
	}
	
	private void createButtons() {
		
	}
	
	
	public void setBackend(VersatileBackend backend) {
		// TODO Auto-generated method stub
		this.backend = backend;
	}

	public String getSelectedModel() {
		String model = "";
		JComboBox modelPicker = (JComboBox) components.get("Model Picker");
		model = (String) modelPicker.getSelectedItem();
		return model;
	}

	public double[] getInputs() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSelectedConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isCounterbalanceSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getCounterBalanceWeight() {
		// TODO Auto-generated method stub
		return false;
	}

	
	private ActionListener openListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//OpenFile.openFile(model.getParameters(), frame, null);
		}
	};

	private ActionListener saveListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//SaveFile.save(model.getParameters(), frame, null);
		}
	};
	
	private ActionListener exitListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
	};
	
	private ActionListener aboutListener = new ActionListener() {
		public void actionPerformed(ActionEvent e){
			JOptionPane.showMessageDialog(frame, AppConstants.ABOUT_MESSAGE);
		}
	};
	
	private ActionListener runListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			backend.evaluate();
		}		
	};

		
}



