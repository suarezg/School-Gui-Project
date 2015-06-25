package com.versatile.ui;

import com.versatile.backend.VersatileBackend;

public interface VersatileUI {

	public void setBackend(VersatileBackend backend);
	
	public String getSelectedModel();
	
	public double[] getInputs();
	
	public String getSelectedConfiguration();
	
	public boolean isCounterbalanceSelected();
	
	public boolean getCounterBalanceWeight();

}
