package com.negativespace.ld34;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener{
	
	public static boolean keysPressed[] = new boolean[256];

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()<256){
			keysPressed[e.getKeyCode()] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()<256){
			keysPressed[e.getKeyCode()] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}