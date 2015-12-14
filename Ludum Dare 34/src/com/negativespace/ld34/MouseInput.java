package com.negativespace.ld34;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener{
	
	public static boolean leftClicked = false;
	
	public static int clickX, clickY;
	
	public static int mouseX, mouseY;

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		System.out.println(arg0.getButton());
		if(arg0.getButton()==1){
			leftClicked=true;
		}
		clickX = arg0.getX();
		clickY = arg0.getY();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(arg0.getButton()==1){
			leftClicked=false;
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		clickX = arg0.getX();
		clickY = arg0.getY();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mouseX = arg0.getX();
		mouseY = arg0.getY();
	}

}
