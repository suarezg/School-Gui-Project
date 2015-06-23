package com.versatile.model;

import java.util.Map;

import com.versatile.view.VersatileUI;

public class VersatileModel {

	private VersatileUI ui;
	private String model;
	private String selectedConfig;
	private String filePath;
	private String imagePath;
	private double input1;
	private double input2;
	
	private Map<String, Object> parameters;
	
	public VersatileModel(VersatileUI ui) {
		this.ui = ui;
	
	}
	
	public Map<String, ? extends Object> getParameters() {
		return parameters;
	}	
	
	public void addParameter(String keyLabel, Object value ) {
		if ( parameters.get(keyLabel) ==  null ) {
			parameters.put(keyLabel, value);
		}
		else {
			parameters.replace(keyLabel, value);
		}
	}

	
	
	
}
