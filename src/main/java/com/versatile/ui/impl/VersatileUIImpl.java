package com.versatile.ui.impl;

import java.awt.Component;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.versatile.backend.VersatileBackend;
import com.versatile.ui.VersatileUI;

public class VersatileUIImpl implements VersatileUI {

	private VersatileBackend backend;
	private JFrame frame;
	private JPanel main;
	private Map<String, Component> components;
	
	public VersatileUIImpl() {
		createFrame();
	}
	
	private void createFrame() {
		createMenuBar();
		createModelPicker();
		createConfigurations();
		createParameters();
		createConditionals();
		createButtons();
	}
	
	private void createMenuBar() {
		
	}
	
	private void createModelPicker() {
		
	}
	
	private void createConfigurations() {
		
	}
	
	private void createParameters() {
		
	}
	
	private void createConditionals() {
		
	}
	
	private void createButtons() {
		
	}
	
	
	public void setBackend(VersatileBackend backend) {
		// TODO Auto-generated method stub
		this.backend = backend;
	}

	public String getSelectedModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public double[] getInputs() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSelectedConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isCounterbalanceSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getCounterBalanceWeight() {
		// TODO Auto-generated method stub
		return false;
	}

}
