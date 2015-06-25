package com.versatile.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.versatile.application.AppConstants;
import com.versatile.controller.VersatileController;
import com.versatile.model.OpenFile;
import com.versatile.model.SaveFile;
import com.versatile.model.VersatileModel;

import net.miginfocom.swing.MigLayout;

public class VersatileUI {
	private JFrame frame;
	private JPanel panel;
	VersatileModel model;

	private static String openFile = "";	
	
	public VersatileUI() {
		model = new VersatileModel(this);
		createFrame();
	}
	
	
	private void createFrame() {
		frame = new JFrame("Versatile - Buhler Industries");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(
			//	"\\com\\versatile\\application\\images\\logo.png")));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent event) {
				
			}
		});
		panel = new JPanel(new MigLayout());
		model.addParameter("Main Panel", panel);
		createMenuBar();
		//createFileLabel();
		createModelPicker();
		//createConsolePopupMenu();
		createConfigs();
		createInputs();
		createButtons();
		createConditionals();
		frame.add(panel);
		frame.setVisible(true);
	}
	
	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setName("Menu Bar");
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenuItem exit = new JMenuItem("Exit");
		exit.setMnemonic(KeyEvent.VK_F4);
		exit.addActionListener(exitListener);
		JMenuItem open = new JMenuItem("Open");
		open.addActionListener(openListener);
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(saveListener);
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(exit);
		
		JMenu helpMenu =  new JMenu("Help");
		JMenuItem about = new JMenuItem("About");
		about.addActionListener(aboutListener);
		helpMenu.add(about);
		
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		model.addParameter(menuBar.getName(), menuBar);
		frame.setJMenuBar(menuBar);
	}
	
	private ActionListener openListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			OpenFile.openFile(model.getParameters(), frame, null);
		}
	};

	private ActionListener saveListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			SaveFile.save(model.getParameters(), frame, null);
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
			JOptionPane.showMessageDialog(frame,
					AppConstants.ABOUT_MESSAGE);
		}
	};
	
	private void createModelPicker() {
		JComboBox modelList = new JComboBox(AppConstants.USABLE_MODELS);
		modelList.setName("Model List");
		model.addParameter(modelList.getName(), modelList);
		panel.add(modelList, "span");
	}
	
	
	private void createConfigs() {
		ButtonGroup group = new ButtonGroup();
		List<JRadioButton> configs = new ArrayList<JRadioButton>();
		for (int i = 0; i < AppConstants.CONFIGURATIONS.length; i++) {
			JRadioButton radio = new JRadioButton(AppConstants.CONFIGURATIONS[i]);
			radio.setName(AppConstants.CONFIGURATIONS[i]);
			configs.add(radio);
			group.add(configs.get(i));
			JPanel panel = (JPanel) model.getParameters().get("Main Panel");
			if (i == (AppConstants.CONFIGURATIONS.length -1))
				panel.add(configs.get(i), "wrap");
			else
				panel.add(configs.get(i));
			model.addParameter(configs.get(i).getName(), configs.get(i));
		}
	}
	
	private void createInputs() {
		final int INPUT_LENGTH = 100;
		final int INPUT_HEIGHT = 25;
		JTextField input1 = new JTextField("0");
		JTextField input2 = new JTextField("0");
		input1.setName("Input 1");
		input2.setName("Input 2");
		input1.setMinimumSize(new Dimension(INPUT_LENGTH, INPUT_HEIGHT));
		input1.setMaximumSize(new Dimension(INPUT_LENGTH, INPUT_HEIGHT));
		input2.setMinimumSize(new Dimension(INPUT_LENGTH, INPUT_HEIGHT));
		input2.setMaximumSize(new Dimension(INPUT_LENGTH, INPUT_HEIGHT));
		model.addParameter(input1.getName(), input1);
		model.addParameter(input2.getName(), input2);
		panel.add(input1);
		panel.add(input2,"wrap");
	}
	
	private void createButtons() {
		JButton run = new JButton("RUN");
		JButton clear = new JButton("CLEAR");
		
		panel.add(run);
		panel.add(clear, "wrap");
	}
	
	private void createConditionals() {
		final int INPUT_LENGTH = 100;
		final int INPUT_HEIGHT = 25;
		JCheckBox hasCounter = new JCheckBox("Has CounterBalance?");
		hasCounter.setName("Counter Balance Checkbox");
		JTextField counterValue = new JTextField();
		counterValue.setMinimumSize(new Dimension(INPUT_LENGTH, INPUT_HEIGHT));
		counterValue.setName("Counter Balance Value");
		counterValue.setVisible(false);	
		
		model.addParameter(hasCounter.getName(), hasCounter);
		model.addParameter(counterValue.getName(), counterValue);
		panel.add(hasCounter);
		panel.add(counterValue, "wrap");
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				VersatileUI ui = new VersatileUI();
				VersatileModel model = new VersatileModel(ui);
				VersatileController controller = new VersatileController(model, ui);
			}
		});
	}
}
