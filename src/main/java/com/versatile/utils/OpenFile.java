package com.versatile.utils;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class OpenFile {

	public static void openFile(List<JRadioButton> radioButtons, List<JTextField> textfields,
								List<JComboBox> menus, List<JCheckBox> checkboxes, JFrame frame, String startPath) {
		JFileChooser jfc = new JFileChooser();
		int result = jfc.showOpenDialog(frame);
		if ( result == JFileChooser.APPROVE_OPTION ) {
			File file = jfc.getSelectedFile();
			String fileName = file.getName();
			if (fileName.endsWith("txt")) {
				readFile(radioButtons, textfields, menus, checkboxes,
						file);
			}
			frame.repaint();
		}
	}
	
	private static void readFile(List<JRadioButton> radioButtons, List<JTextField> textfields, 
									List<JComboBox> menus, List<JCheckBox> checkboxes, File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				fillComponent(line, radioButtons, menus, textfields,
								checkboxes);
				line = reader.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void fillComponent(String line, List<JRadioButton> radioButtons, List<JComboBox> menus,
									List<JTextField> textfields, List<JCheckBox> checkboxes) {
		String name = line.substring(0, line.indexOf(":")).trim();
		String value = line.substring(line.indexOf(":")+1).trim();
		fillRadios(radioButtons, name, value);
		fillCheckBox(checkboxes, name, value);
		fillTextField(textfields, name, value);
		fillMenus(menus, name, value);
	}
	
	private static boolean fillRadios(List<JRadioButton> radioButtons, String name, String value) {
		boolean found = false;
		for (int i = 0; (i < radioButtons.size()) && !found; i++) {
			if (radioButtons.get(i).getName().equals(name)) {
				found = true;
				radioButtons.get(i).setSelected(Boolean.parseBoolean(value));
			}
		}
		return found;
	}
	
	private static boolean fillCheckBox(List<JCheckBox> checkboxes, String name, String value) {
		boolean found = false;
		for (int i = 0; (i < checkboxes.size()) && !found; i++) {
			if (checkboxes.get(i).getName().equals(name)) {
				found = true;
				checkboxes.get(i).setSelected(Boolean.parseBoolean(value));
			}
		}
		return found;
	}
	
	private static boolean fillTextField(List<JTextField> textfields, String name, String value) {
		boolean found = false;
		for (int i = 0; (i < textfields.size()) && !found; i++) {
			if (textfields.get(i).getName().equals(name)) {
				found = true;
				textfields.get(i).setText(value);
			}
		}
		return found;
	}
	
	private static boolean fillMenus(List<JComboBox> menus, String name, String value) {
		boolean found = false;
		for (int i = 0; (i < menus.size()) && !found; i++) {
			if (menus.get(i).getName().equals(name)) {
				found = true;
				menus.get(i).setSelectedItem(value);
			}
		}
		return found;
	}
	
}
