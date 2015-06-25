package com.versatile.model;

import javax.swing.JPanel;

import com.jme3.app.SimpleApplication;
import com.jme3.system.JmeCanvasContext;
import com.jme3.system.lwjgl.LwjglCanvas;

public class GraphicPane extends SimpleApplication{

	private String modelPath;
	private JPanel panel;
	public GraphicPane(JPanel panel) {
		this.panel = panel;
	}
	
	public void setModel(String filePath) {
		modelPath = filePath;
		updateModel();
	}
	
	private void updateModel() {
		createCanvas();
		JmeCanvasContext ctx = (JmeCanvasContext) getContext();
		ctx.setSystemListener(this);
		panel.add(ctx.getCanvas());
		startCanvas();
	}

	@Override
	public void simpleInitApp() {
		// TODO Auto-generated method stub
		startCanvas();
	}
	
}
