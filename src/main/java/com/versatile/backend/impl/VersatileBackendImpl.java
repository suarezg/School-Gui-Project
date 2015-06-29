package com.versatile.backend.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.versatile.backend.VersatileBackend;
import com.versatile.ui.VersatileUI;


public class VersatileBackendImpl implements VersatileBackend{

	VersatileUI ui;
	public VersatileBackendImpl(VersatileUI ui) {
		this.ui = ui;
	}
	
	public void evaluate() {
		// TODO Auto-generated method stub
		String model = ui.getSelectedModel();
		String config = ui.getSelectedConfiguration();
		double[] params = ui.getInputs();
		
		/*TODO
		 * Use matlabcontrol to send these parameters to the 
		 * m file. 
		 * Interpret the result
		 */
		
	}
	

	

}
