package com.versatile.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveFile {

	
	public static void save(VersatileModel model, String filePath) {
		try {
			FileWriter writer = new FileWriter(filePath);
			writer.append("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void writeInputs(VersatileModel model) {
		
	}
}
