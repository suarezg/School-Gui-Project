package com.versatile.model;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

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

}
