package com.versatile.application;

import java.awt.Canvas;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.concurrent.Callable;




import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import net.miginfocom.swing.MigLayout;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;

public class VersatileGui {

	private static JmeCanvasContext context;
	private static Canvas canvas;
	private static Application app;
	private static JFrame frame;
	private static JPanel panel;
	static AnimControl control;
	static AnimChannel channel;
	
	
	private static void createFrame() {
		
		frame = new JFrame("Versatile - Buhler Industries");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("src/main/resources/com/application/images/logo.png")));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent event) {
				app.stop();
			}
		});
		panel = new JPanel(new MigLayout());
		createRadios();
		frame.add(panel);
		frame.pack();
		
		createCanvas();
		//createModel();
		
	}
	
	private static void createCanvas() {
		AppSettings settings = new AppSettings(true);
		settings.setWidth(640);
		settings.setHeight(480);
		 try{
	            Class<? extends Application> clazz = (Class<? extends Application>) Class.forName("com.versatile.application.BaseApplication");
	            app = clazz.newInstance();
	        }catch (ClassNotFoundException ex){
	            ex.printStackTrace();
	        }catch (InstantiationException ex){
	            ex.printStackTrace();
	        }catch (IllegalAccessException ex){
	            ex.printStackTrace();
	        }
		
		 app.setSettings(settings);
		 app.createCanvas();
		 app.startCanvas();
		 loadModel("C:\\Users\\ginosuarez\\workspace\\versatile-gui\\src\\main\\resources\\com\\versatile\\application\\models\\Oto.mesh.xml");
		 context = (JmeCanvasContext) app.getContext();
		 canvas = context.getCanvas();
		 canvas.setSize(settings.getWidth(), settings.getHeight());		 
	}
	
	private static void loadModel(String modelPath) {
		Node player;
		SimpleApplication simpleApp = (SimpleApplication) app;
		Node root = simpleApp.getRootNode();
		refreshCanvas(root);
		player = (Node) simpleApp.getAssetManager().loadModel(modelPath);
		player.setLocalScale(0.5f);
		root.attachChild(player);
		control = player.getControl(AnimControl.class);
		channel= control.createChannel();
		channel.setAnim("stand");
	}
	
	private static void refreshCanvas(Node root) {
		List<Spatial> spatials = root.getChildren();
		for (Spatial s : spatials) {
			s.removeFromParent();
		}
	}
	
	private static void createRadios() {
	
		JRadioButton td600 = new JRadioButton("Model TD600");
		JRadioButton td700 = new JRadioButton("Model TD700");
		
		td600.addActionListener(actionListener);
		td700.addActionListener(actionListener);
		
		panel.add(td600);
		panel.add(td700, "wrap");
	}
	
	private static ActionListener actionListener = new ActionListener() {

		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			String model = event.getActionCommand();
			if (model.equals("TD600")) {
				loadModel("com/versatile/application/models/Oto.mesh.xml");
			}
			else if (model.equals("TD700")) {
				loadModel("com/versatile/application/models/Oto.mesh.xml");
			}
		}
		
	};
		
	public static void startApp() {
		app.startCanvas();
		app.enqueue(new Callable<Void>() {

			public Void call() throws Exception {
				// TODO Auto-generated method stub
				if ( app instanceof SimpleApplication ) {
					SimpleApplication simpleApp = (SimpleApplication) app;
					simpleApp.getFlyByCamera().setDragToRotate(true);
				}
				return null;
			}
			
		});
	}
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				createFrame();
				panel.add(canvas);
				frame.setVisible(true);
				
			}
		});
	}

}
