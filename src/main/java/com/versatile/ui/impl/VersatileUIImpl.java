package com.versatile.ui.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jogamp.glg2d.GLG2DPanel;

import net.miginfocom.swing.MigLayout;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.versatile.backend.VersatileBackend;
import com.versatile.ui.VersatileUI;
import com.versatile.utils.AppConstants;
import com.versatile.utils.OpenFile;
import com.versatile.utils.SaveFile;
import com.ysystems.ycad.app.ycadv.YcadvPane;
import com.ysystems.ycad.lib.ydxf.YdxfGet;
import com.ysystems.ycad.lib.ydxf.YdxfGetBuffer;

public class VersatileUIImpl implements VersatileUI {

	private VersatileBackend backend;
	private JFrame frame;
	private JPanel main;
	private List<JRadioButton> radioButtons;
	private List<JComboBox> menus;
	private List<JTextField> textfields;
	private List<JCheckBox> checkboxes;
	private List<ButtonGroup> radioGroups;
	private NumberFormat numFormat;
	private ButtonGroup configGroup; 
	
	
	public VersatileUIImpl() {
		createFrame();
	}
	
	private void createFrame() {
		initializeLists();
		numFormat = NumberFormat.getNumberInstance();
		frame = new JFrame();
		main = new JPanel(new MigLayout());
		createMenuBar();
		createModelPicker();
		createConfigurations();
		createParameters();
		createConditionals();
		createButtons();
		createCanvas();
		frame.add(main);
		frame.setTitle("Versatile - Buhler Industries");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private void initializeLists() {
		radioButtons = new ArrayList<JRadioButton>();
		menus = new ArrayList<JComboBox>();
		textfields = new ArrayList<JTextField>();
		checkboxes = new ArrayList<JCheckBox>();
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
		save.addActionListener(saveListener);
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(exit);
		menuBar.add(fileMenu);
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		JMenuItem about = new JMenuItem("About");
		about.addActionListener(aboutListener);
		helpMenu.add(about);
		menuBar.add(helpMenu);
				
		frame.setJMenuBar(menuBar);
	}
	
	private void createModelPicker() {
		JComboBox<String> modelPicker = new JComboBox(AppConstants.USABLE_MODELS);
		modelPicker.setName("Model Picker");
		//modelPicker.addActionListener();
		main.add(modelPicker, "wrap");
		menus.add(modelPicker);
		
	}
	
	private void createConfigurations() {
		configGroup = new ButtonGroup();
		for ( int i = 0; i < AppConstants.CONFIGURATIONS.length; i++ ) {
			JRadioButton radio = new JRadioButton(AppConstants.CONFIGURATIONS[i]);
			radio.setName(AppConstants.CONFIGURATIONS[i]);
			configGroup.add(radio);
			if ( i == (AppConstants.CONFIGURATIONS.length -1))
				main.add(radio, "wrap");
			else
				main.add(radio);
			radioButtons.add(radio);
			
		}
	}
	
	private void createParameters() {
		final int INPUT_HEIGHT = 25;
		final int INPUT_LENGTH = 100;
		
		for (int i = 0; i < AppConstants.INPUT_NAMES.length; i++) {
			JFormattedTextField input = new JFormattedTextField(numFormat);
			input.setName(AppConstants.INPUT_NAMES[i]);
			input.setMinimumSize(new Dimension(INPUT_LENGTH, INPUT_HEIGHT));
			if (i == (AppConstants.INPUT_NAMES.length -1))
				main.add(input, "wrap");
			else
				main.add(input);
			textfields.add(input);
		}
	}
	
	private void createConditionals() {
		final int INPUT_LENGTH = 100;
		final int INPUT_HEIGHT = 25;
		
		JCheckBox hasCounter = new JCheckBox("Has CounterBalance");
		hasCounter.setName("Counter Balance Checkbox");
		hasCounter.addItemListener(counterListener);
		main.add(hasCounter);
		checkboxes.add(hasCounter);
		
		JFormattedTextField counterValue = new JFormattedTextField(numFormat);
		counterValue.setName("Counter Balance Value");
		counterValue.setMinimumSize(new Dimension(INPUT_LENGTH, INPUT_HEIGHT));
		counterValue.setVisible(false);
		main.add(counterValue, "wrap");
		textfields.add(counterValue);
	}
	
	private void createButtons() {
		JButton run = new JButton("Run");
		run.setName("Run");
		run.addActionListener(runListener);
		
		JButton clear = new JButton("Clear");
		clear.setName("Clear");
		clear.addActionListener(clearListener);

		main.add(run);
		main.add(clear, "wrap");
	}
	
	
	public void setBackend(VersatileBackend backend) {
		this.backend = backend;
	}

	public String getSelectedModel() {
		String model = "";
		JComboBox menu = getComboBox("Model Picker");
		if (menu != null)
			model = (String) menu.getSelectedItem();
		return model;
	}

	public double[] getInputs() {
		double[] params = new double[textfields.size()];
		for (int i = 0; i < textfields.size(); i++) {
			params[i] = Double.parseDouble(textfields.get(i).getText());
		}
		return params;
	}

	public String getSelectedConfiguration() {
		boolean found = false;
		String selectedConfig = "";
		for (int i = 0; (i < radioButtons.size()) && !found; i++) {
			if ( radioButtons.get(i).isSelected() ) {
				selectedConfig = radioButtons.get(i).getName();
				found = true;
			}
		}
		return selectedConfig;
	}

	public boolean isCounterbalanceSelected() {
		boolean isSelected = false;
		JCheckBox checkbox = getCheckBox("Counter Balance Checkbox");
		if (checkbox != null) 
			isSelected = checkbox.isSelected();
		return isSelected;
	}

	public double getCounterBalanceWeight() {
		double weight = Double.MIN_VALUE;
		JTextField textfield = getTextField("Counter Balance Value");
		weight = Double.parseDouble(textfield.getText());
		return weight;
	}
	
	/**
	 * 
	 * @param name indicating the checkbox to retrieve. Name should be unique among the components.
	 * @return <code>JCheckBox</code>
	 */
	private JCheckBox getCheckBox(String name) {
		boolean found = false;
		JCheckBox checkbox = null;
		for (int i = 0; (i < checkboxes.size()) && !found; i++) {
			if ( checkboxes.get(i).getName().equals(name) ) {
				found = true;
				checkbox = checkboxes.get(i);
			}
		}
		return checkbox;
	}
	
	/**
	 * 
	 * @param name indicating which radio button to retrieve. Name should be unique among the components.
	 * @return <code>JRadioButton</code>
	 */
	private JRadioButton getRadioButton(String name) {
		boolean found = false;
		JRadioButton radio = null;
		for (int i = 0; (i<radioButtons.size()) && !found; i++) {
			if (radioButtons.get(i).getName().equals(name)) {
				radio = radioButtons.get(i);
			}
		}
		return radio;
	}
	
	/**
	 * 
	 * @param name indicating which textfield to retrieve. Name should be unique among the components.
	 * @return <code>JTextField</code>
	 */
	private JTextField getTextField(String name) {
		boolean found = false;
		JTextField textfield = null;
		for (int i = 0; (i < textfields.size()) && !found; i++) {
			if (textfields.get(i).getName().equals(name)) {
				found = true;
				textfield = textfields.get(i);
			}
		}
		return textfield;
	}
	
	/**
	 * 
	 * @param name indicating which combobox to retrieve. Name should be unique among the components.
	 * @return <code>JComboBox</code>
	 */
	private JComboBox getComboBox(String name) {
		boolean found = false;
		JComboBox menu = null;
		for (int i = 0; (i < menus.size()) && !found; i++) {
			if (menus.get(i).getName().equals(name)) {
				found = true;
				menu = menus.get(i);
			}
		}
		return menu;
	}

	/**Listener for opening a file and populating the settings in
	 * the UI when the open menu item is clicked in the menu bar
	 */
	private ActionListener openListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			OpenFile.openFile(radioButtons, textfields, menus, 
					checkboxes, frame, null);
		}
	};

	/**Listener for saving the current UI settings in a file when 
	 * the save menu item is clicked in the menu bar
	 */
	private ActionListener saveListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			SaveFile.save(radioButtons, textfields, menus, 
					checkboxes, frame, null);
		}
	};
	
	/**Listener for exiting the UI and application when the exit
	 * menu item is clicked in the menu bar
	 */
	private ActionListener exitListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
	
	/**Listener for the about menu item to display the about message
	 * window
	 */
	private ActionListener aboutListener = new ActionListener() {
		public void actionPerformed(ActionEvent e){
			JOptionPane.showMessageDialog(frame, AppConstants.ABOUT_MESSAGE);
		}
	};
	
	
	/**Listener for evaluating the roll over/tip over result using
	 * the current settings in the UI when the 'RUN' button is clicked
	 */
	private ActionListener runListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			backend.evaluate();
		}		
	};
	
	/**Listener for clearing the current settings in the UI with the 
	 * clear button
	 */
	private ActionListener clearListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			clearSettings();
		}	
	};
	
	private void clearSettings() {
		clearRadios();
		clearCheckBoxes();
		clearTextFields();
		clearMenus();
	}
	
	private void clearRadios() {
		configGroup.clearSelection();
	}
	
	private void clearCheckBoxes() {
		for (JCheckBox checkbox : checkboxes) {
			checkbox.setSelected(false);
		}
	}
	
	private void clearTextFields() {
		for (JTextField textfield : textfields) {
			textfield.setText("");
		}
	}
	
	private void clearMenus() {
		for (JComboBox menu : menus) {
			menu.setSelectedIndex(0);
		}
	}
	
	/**Listener method for counterbalance checkbox visibility
	 * 
	 */
	private ItemListener counterListener = new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			JTextField counterField = getTextField("Counter Balance Value");
			if (isCounterbalanceSelected()) 
				counterField.setVisible(true);
			else 
				counterField.setVisible(false);
		}
	};
	
	
	
	/**canvas for openGL
	 * 
	 */
	private void createCanvas() {
		GLProfile profile = GLProfile.getDefault();
		GLProfile.initSingleton();
		GLCapabilities caps = new GLCapabilities(profile);
		
		GLCanvas canvas = new GLCanvas(caps);
		canvas.addGLEventListener(glEventListener);
		canvas.setSize(new Dimension(400, 400));
		main.add(canvas, "wrap");
	}
	
	private GLEventListener glEventListener = new GLEventListener() {

		public void display(GLAutoDrawable drawable) {
			// TODO Auto-generated method stub
			final GL2 gl = drawable.getGL().getGL2();
			//drawing top
			gl.glBegin ( GL2.GL_LINES );
			gl.glVertex3f( -0.3f, 0.3f, 0 );
			gl.glVertex3f( 0.3f,0.3f, 0 );
			
			gl.glEnd();
			//drawing bottom
			gl.glBegin( GL2.GL_LINES );
			gl.glVertex3f( -0.3f,-0.3f, 0 );
			gl.glVertex3f( 0.3f,-0.3f, 0 );
			gl.glEnd();
			//drawing the right edge
			gl.glBegin( GL2.GL_LINES );
			gl.glVertex3f( -0.3f,0.3f, 0 );
			gl.glVertex3f( -0.3f,-0.3f, 0 );
			gl.glEnd();
			//drawing the left edge
			gl.glBegin( GL2.GL_LINES );
			gl.glVertex3f( 0.3f,0.3f,0 );
			gl.glVertex3f( 0.3f,-0.3f,0 );
			gl.glEnd();
			//building roof
			//building lft dia
			gl.glBegin( GL2.GL_LINES );
			gl.glVertex3f( 0f,0.6f, 0 );
			gl.glVertex3f( -0.3f,0.3f, 0 );
			gl.glEnd();
			//building rt dia
			gl.glBegin( GL2.GL_LINES );
			gl.glVertex3f( 0f,0.6f, 0 );
			gl.glVertex3f( 0.3f,0.3f, 0 );
			gl.glEnd();
			//building door
			//drawing top
			gl.glBegin ( GL2.GL_LINES );
			gl.glVertex3f( -0.05f, 0.05f, 0 );
			gl.glVertex3f( 0.05f, 0.05f, 0 );
			
			gl.glEnd();
			//drawing the left edge
			gl.glBegin ( GL2.GL_LINES );
			gl.glVertex3f( -0.05f, 0.05f, 0 );
			gl.glVertex3f( -0.05f, -0.3f, 0 );
			gl.glEnd();
			//drawing the right edge
			gl.glBegin ( GL2.GL_LINES );
			gl.glVertex3f( 0.05f, 0.05f, 0 );
			gl.glVertex3f( 0.05f, -0.3f, 0 );
			gl.glEnd();

		}

		public void dispose(GLAutoDrawable arg0) {
			// TODO Auto-generated method stub
			
		}

		public void init(GLAutoDrawable arg0) {
			// TODO Auto-generated method stub
			
		}

		public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
				int arg4) {
			// TODO Auto-generated method stub
			
		}
		
	};
}



