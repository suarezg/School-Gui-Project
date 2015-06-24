package com.versatile.model;

import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SaveFile {

	
	public static void save(Map<String, Component> components, JFrame frame, String startPath) {
		JFileChooser jfc = new JFileChooser();
		int result = jfc.showSaveDialog(frame);
		if ( result != JFileChooser.CANCEL_OPTION) {
			File file = jfc.getSelectedFile();
			if (validSave(jfc, file)) 
				writeFile(components, file);
			
		}
	}
	
	private static boolean validSave(JFileChooser jfc, File file) {
		boolean canSave = false;
		boolean fileExists = file.exists();
		int confirm;
		if (fileExists) {
			confirm = JOptionPane.showConfirmDialog(jfc, "A file with this name already exits. "+
						"Would you like to Overwrite?");
			if (confirm == JOptionPane.OK_OPTION)
				canSave = true;
		}
		else {
			canSave = true;
		}
		return canSave;
	}
	
	private static void writeFile(Map<String, Component> components, File file) {
		try {
			FileWriter writer = new FileWriter(file);
			Iterator iterator = components.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry entry = (Entry) iterator.next();
				String key = (String) entry.getKey();
				Component comp = (Component) entry.getValue();
				if  (comp instanceof JComboBox) {
					JComboBox menu = (JComboBox) comp;
					writer.append(key+": " + menu.getSelectedItem()
							+System.getProperty("line.separator"));	
				}
				else if ( comp instanceof JRadioButton) {
					JRadioButton radio = (JRadioButton) comp;
					writer.append(key+": " + radio.isSelected() 
							+System.getProperty("line.separator"));
				}
				else if ( comp instanceof JTextField) {
					JTextField textfield = (JTextField) comp;
					writer.append(key+": " + textfield.getText() 
							+System.getProperty("line.separator"));
				}	
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
