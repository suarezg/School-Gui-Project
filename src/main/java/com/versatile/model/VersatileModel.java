package com.versatile.model;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.versatile.view.VersatileUI;

public class VersatileModel {

	private VersatileUI ui;	
	private Map<String, Component> components;
	
	public VersatileModel(VersatileUI ui) {
		components = new HashMap<String, Component>();
		this.ui = ui;
	}
	
	public Map<String, Component> getParameters() {
		return components;
	}	
	
	public void addParameter(String keyLabel, Component value ) {
		if ( components.get(keyLabel) ==  null ) {
			components.put(keyLabel, value);
		}
		else {
			components.replace(keyLabel, value);
		}
	}
	
	public List<JRadioButton> getRadioButtons() {
		List<JRadioButton> radioButtons = new ArrayList<JRadioButton>();
		Iterator iterator = components.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			Component comp = (Component) entry.getValue();
			if ( comp instanceof JRadioButton ) {
				radioButtons.add((JRadioButton)comp);
			}
		}
		return radioButtons;
	}
	
	public List<JTextField> getTextField() {
		List<JTextField> textFields = new ArrayList<JTextField>();
		Iterator iterator = components.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			Component comp = (Component) entry.getValue();
			if ( comp instanceof JTextField ) {
				textFields.add((JTextField) comp);
			}
		}
		return textFields;
	}
	
	public List<JComboBox> getMenus() {
		List<JComboBox> menus = new ArrayList<JComboBox>();
		Iterator iterator = components.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			Component comp = (Component) entry.getValue();
			if ( comp instanceof JComboBox ) {
				menus.add((JComboBox) comp);
			}
		}
		return menus;
	}
	
	public JRadioButton getRadioButton(String key) {
		JRadioButton radio = null;
		Component comp = (Component) components.get(key);
		if ( (comp != null) && (comp instanceof JRadioButton)) {
			radio = (JRadioButton) comp;
		}
		return radio;
	}
	
	public JTextField getTextField(String key) {
		JTextField textfield = null;
		Component comp = (Component) components.get(key);
		if ( (comp != null) && (comp instanceof JTextField) ) {
			textfield = (JTextField) comp;
		}
		return textfield;
	}
	
	public JComboBox getMenu(String key) {
		JComboBox menu = null;
		Component comp = (Component) components.get(key);
		if ( (comp != null) && (comp instanceof JComboBox) ) {
			menu = (JComboBox) comp;
		}
		return menu;
	}

}
