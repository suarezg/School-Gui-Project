package com.versatile.ui;

import com.versatile.backend.VersatileBackend;

public interface VersatileUI {

	/**Sets the Backend for the UI
	 * 
	 * @param backend
	 */
	public void setBackend(VersatileBackend backend);
	
	/**Returns a <code>String</code> indicating the model selected in the dropdown menu
	 * 
	 * @return the model selected in the model picker
	 */
	public String getSelectedModel();
	
	/**Returns the values that are set in the input fields that are currently set in the UI
	 *
	 * @return an array containing the input values 
	 */
	public double[] getInputs();
	
	/**Returns the configuration that is set in the UI
	 * 
	 * @return the configuration of the selected model
	 */
	public String getSelectedConfiguration();
	
	/**Checks to see if the counterbalance checkbox is selected in the 
	 * 
	 * @return <code>true</code> if the counterbalance checkbox is selected in the UI
	 * 	<code>false</code> if the checkbox is not selected
	 */
	public boolean isCounterbalanceSelected();
	
	/**
	 * 
	 * @return the value that is set in the counterbalance value field when the counterbalance checkbox is selected
	 */
	public double getCounterBalanceWeight();

}
