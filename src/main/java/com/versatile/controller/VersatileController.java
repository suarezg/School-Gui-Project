package com.versatile.controller;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.versatile.model.VersatileModel;
import com.versatile.view.VersatileUI;

public class VersatileController implements ActionListener {
	
	private VersatileModel model;
	private VersatileUI ui;
	
	public VersatileController(VersatileModel model, VersatileUI ui) {
		this.model = model;
		this.ui = ui;
	}
	
	public void run() {
		
	}
	
	public void clear() {
		Map<String, Component> components = model.getParameters();
		Iterator iterator = components.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			Component comp = (Component) entry.getValue();
			if ( comp instanceof JRadioButton ) {
				JRadioButton radioButton = (JRadioButton) comp;
				radioButton.setSelected(false);
			}
			else if ( comp instanceof JTextField ) {
				JTextField textField = (JTextField) comp;
				textField.setText("");
			}
			else if ( comp instanceof JComboBox ) {
				JComboBox menu = (JComboBox) comp;
				menu.setSelectedIndex(0);
			}
			
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
 