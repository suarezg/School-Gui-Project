package com.versatile.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL2;

public class ObjLoader {

	private List<float[]> vArray;
	private List<float[]> vnArray;
	private List<float[]> vtArray;
	private List<int[]> faceArray;
	
	public ObjLoader() {
		createArrays();
	}
	
	public void loadOBJ(GL2 gl, String filename) {
		File file = new File(filename);
		if (file.exists()) {
			readFile(file);
		}
		System.out.println(vArray);
		System.out.println(vnArray);
		System.out.println(faceArray);
		//drawOBJ();
	}
	
	public void clearArrays() {
		vArray = null;
		vnArray = null;
		vtArray = null;
		faceArray = null;
	}

	private void createArrays() {
		vArray = new ArrayList<float[]>();
		vtArray = new ArrayList<float[]>();
		vnArray = new ArrayList<float[]>();
		faceArray = new ArrayList<int[]>();
	}
	
	private void readFile(File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			String[] data;
			float[] values;
			int[] indices;
			while (line != null) {
				if (line.startsWith("#")) {
					//Is a commented line, ignore
				}
				else if(line.startsWith("v") ) {
					data = createFloatData(line);
					values = parseFloatData(data);
					vArray.add(values);
				}
				else if (line.startsWith("vn")) {
					data = createFloatData(line);
					values = parseFloatData(data);
					vnArray.add(values);
				}
				else if (line.startsWith("vt")) {
					data = createFloatData(line);
					values = parseFloatData(data);
					vtArray.add(values);
				}
				else if (line.startsWith("f")) {
					data = createFaceData(line);
					indices = parseFaceData(data);
					faceArray.add(indices);
				}		
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private String[] createFloatData(String line) {
		String[] tokens = line.trim().split("\\s+");
		String[] data = new String[tokens.length-1];
		System.arraycopy(tokens, 1, data, 0, tokens.length-1);
		return data;
	}
	
	private float[] parseFloatData(String[] data) {
		float[] values = new float[data.length];
		for (int i = 0; i < values.length; i++) {
			values[i] = Float.parseFloat(data[i]);
		}
		return values;
	}
	
	private String[] createFaceData(String line) {
		String tokens[] = line.trim().split("\\s+");
		String[] data = new String[(tokens.length-1)*2];
		for(int i = 0; i < tokens.length-1; i++) {
			String firstVal = tokens[i+1].substring(0, line.indexOf("//"));
			String secondVal = tokens[i+1].substring(line.indexOf("/")+"//".length());
			data[i*2] = firstVal;
			data[(i*2)+1] = secondVal;
		}
		return data;
	}
	
	private int[] parseFaceData(String[] data) {
		int[] indices = new int[data.length];
		for (int i = 0; i < data.length; i++) {
			indices[i] = Integer.parseInt(data[i]);
		}
		return indices;
	}
}
