package com.versatile.ui.impl;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class ModelCanvas {

	public static void loadModel1(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		final GL2 gl = drawable.getGL().getGL2();
		//drawing top
		gl.glBegin ( GL2.GL_LINES );
		gl.glVertex3f( -0.3f, 0.3f, 0 );
		gl.glVertex3f( 0.3f,0.3f, 0 );
		
		gl.glEnd();
		//drawing bottom
		gl.glBegin( GL2.GL_LINES );
		gl.glVertex3f( -0.3f,-0.3f, 0 );
		gl.glVertex3f( 0.3f,-0.3f, 0 );
		gl.glEnd();
		//drawing the right edge
		gl.glBegin( GL2.GL_LINES );
		gl.glVertex3f( -0.3f,0.3f, 0 );
		gl.glVertex3f( -0.3f,-0.3f, 0 );
		gl.glEnd();
		//drawing the left edge
		gl.glBegin( GL2.GL_LINES );
		gl.glVertex3f( 0.3f,0.3f,0 );
		gl.glVertex3f( 0.3f,-0.3f,0 );
		gl.glEnd();
		//building roof
		//building lft dia
		gl.glBegin( GL2.GL_LINES );
		gl.glVertex3f( 0f,0.6f, 0 );
		gl.glVertex3f( -0.3f,0.3f, 0 );
		gl.glEnd();
		//building rt dia
		gl.glBegin( GL2.GL_LINES );
		gl.glVertex3f( 0f,0.6f, 0 );
		gl.glVertex3f( 0.3f,0.3f, 0 );
		gl.glEnd();
		//building door
		//drawing top
		gl.glBegin ( GL2.GL_LINES );
		gl.glVertex3f( -0.05f, 0.05f, 0 );
		gl.glVertex3f( 0.05f, 0.05f, 0 );
		
		gl.glEnd();
		//drawing the left edge
		gl.glBegin ( GL2.GL_LINES );
		gl.glVertex3f( -0.05f, 0.05f, 0 );
		gl.glVertex3f( -0.05f, -0.3f, 0 );
		gl.glEnd();
		//drawing the right edge
		gl.glBegin ( GL2.GL_LINES );
		gl.glVertex3f( 0.05f, 0.05f, 0 );
		gl.glVertex3f( 0.05f, -0.3f, 0 );
		gl.glEnd();
	}
	
	public static void loadModel2(GLAutoDrawable drawable) {
		
	}
	
	public static void loadModel3(GLAutoDrawable drawable) {
		
	}
	
	public static void loadModel4(GLAutoDrawable drawable) {
		
	}
	
	public static void loadModel5(GLAutoDrawable drawable) {
		
	}
	
	public static void loadModel6(GLAutoDrawable drawable) {
		
	}
	
}
