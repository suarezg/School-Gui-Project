package com.versatile.utils;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class OpenFile {

	public static void openFile(Map<String, Component> components, JFrame frame, String startPath) {
		JFileChooser jfc = new JFileChooser();
		int result = jfc.showOpenDialog(frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			String fileName = file.getName();
			if (fileName.endsWith("txt")) 
				readFile(components, file);
			frame.repaint();
		}
	}
	
	private static void readFile(Map<String, Component> components, File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				String key = line.substring(0, line.indexOf(":"));
				String value = line.substring(line.indexOf(":")+1);
				Component comp = components.get(key);
				fillParams(comp, key.trim(), value.trim());
				line = reader.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void fillParams(Component comp, String key, String value) {
		if ( comp != null ) {
			if (comp instanceof JRadioButton) {
				JRadioButton radio = (JRadioButton) comp;
				boolean bool = Boolean.parseBoolean(value);
				radio.setSelected(bool);
			}
			else if (comp instanceof JComboBox) {
				JComboBox menu = (JComboBox) comp;
				menu.setSelectedItem(value);
			}
			else if (comp instanceof JTextField) {
				JTextField textfield = (JTextField) comp;
				textfield.setText(value);
			}
			else if ( comp instanceof JCheckBox ) {
				JCheckBox checkbox = (JCheckBox) comp;
				boolean bool = Boolean.parseBoolean(value);
				checkbox.setSelected(bool);
			}
		}
	}
	
}
