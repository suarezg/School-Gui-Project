package com.versatile.application;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Validator {

	public static boolean checkConfig(List<JRadioButton> configs) {
		boolean oneSelected = false;
		for (int i = 0; (i < configs.size()) && !oneSelected; i++) {
			if ( configs.get(i).isSelected() == true ) {
				oneSelected = true;
			}
		}
		return oneSelected;
	}
	
	public static boolean checkModel(JComboBox models) {
		boolean selected = false;
		String selectedModel = (String) models.getSelectedItem();
		if ( !selectedModel.contains("Select One") ) {
			selected = true;
		}
		return selected;
	}
	
	public static boolean checkParams(List<JTextField> parameters) {
		boolean valid = true;
		for (int i = 0; (i < parameters.size()) && valid; i++) {
			try{
				int value = Integer.parseInt(parameters.get(i).getText());
				if ( value < 0 ) 
					valid = false;
			}
			catch(NumberFormatException e) {
				valid = false;
			}
		}
		return valid;
	}
	
}
