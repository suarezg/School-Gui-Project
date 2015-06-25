package com.versatile.application;

import java.awt.EventQueue;

import com.versatile.backend.VersatileBackend;
import com.versatile.backend.impl.VersatileBackendImpl;
import com.versatile.ui.VersatileUI;
import com.versatile.ui.impl.VersatileUIImpl;

public class VersatileApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				VersatileUI ui = new VersatileUIImpl();
				VersatileBackend backend = new VersatileBackendImpl(ui);
				ui.setBackend(backend);
			}
		}); 
	}

}
