package com.versatile.utils;

import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SaveFile {

	public static void save(List<JRadioButton> radioButtons, List<JTextField> textfields,
							List<JComboBox> menus, List<JCheckBox> checkboxes, 
							JFrame frame, String startPath) {
		JFileChooser jfc = new JFileChooser();
		int result = jfc.showSaveDialog(frame);
		if ( result != JFileChooser.CANCEL_OPTION ) {
			File file = jfc.getSelectedFile();
			if ( validSave(jfc, file) ) {
				writeFile(radioButtons, textfields, 
							menus, checkboxes, file);
			}
		}
		
	}
	
	

	private static boolean validSave(JFileChooser jfc, File file) {
		boolean canSave = false;
		if (file.exists()) {
			int confirm = JOptionPane.showConfirmDialog(jfc, "A file with this name already exits. "+
						"Would you like to Overwrite?","Save Confirmation", JOptionPane.OK_CANCEL_OPTION, 
												JOptionPane.WARNING_MESSAGE, null);
			if (confirm == JOptionPane.OK_OPTION)
				canSave = true;
		}
		else {
			canSave = true;
		}
		return canSave;
	}
	
	private static void writeFile(List<JRadioButton> radioButtons,
			List<JTextField> textfields, List<JComboBox> menus,
			List<JCheckBox> checkboxes, File file)  {

			try {
				FileWriter writer = new FileWriter(file);
				writeRadios(writer, radioButtons);
				writeCheckboxes(writer, checkboxes);
				writeMenus(writer, menus);
				writeTextFields(writer, textfields);
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	private static void write(FileWriter writer, String name, String value) 
						throws IOException{
		writer.append(name+": ") ;
		writer.append(value);
		writer.append(System.getProperty("line.separator"));
	}


	private static void writeTextFields(FileWriter writer,
			List<JTextField> textfields) throws IOException {
		for (JTextField textfield : textfields) {
			String name = textfield.getName();
			String value = textfield.getText();
			write(writer, name, value);
		}
	}


	private static void writeMenus(FileWriter writer, List<JComboBox> menus) 
						throws IOException{
		for (JComboBox menu:menus) {
			String name = menu.getName();
			String value = (String) menu.getSelectedItem();
			write(writer, name, value);
		}
	}



	private static void writeCheckboxes(FileWriter writer,
			List<JCheckBox> checkboxes)  throws IOException {
		for (JCheckBox checkbox : checkboxes) {
			String name = checkbox.getName();
			String value = Boolean.toString(checkbox.isSelected());
			write(writer, name, value);
		}
		
	}



	private static void writeRadios(FileWriter writer,
			List<JRadioButton> radioButtons) throws IOException {
		for (JRadioButton radio : radioButtons) {
			String name = radio.getName();
			String value = Boolean.toString(radio.isSelected());
			write(writer, name, value);
		}
	}
}
