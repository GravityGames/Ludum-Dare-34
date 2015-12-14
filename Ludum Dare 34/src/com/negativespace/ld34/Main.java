package com.negativespace.ld34;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	
	public static int width=960, height=540;
	public static String windowName = "Draygon Habitat";
	
	public static KeyListener keyListener;
	public static MouseInput mouseListener;
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		//frame.setSize(width, height);
		frame.setTitle(windowName);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Game g = new Game(frame);
		
		frame.add(g);
		g.setPreferredSize(new Dimension(width, height));
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		g.loadDraygons();
		
		frame.setVisible(true);
		
		WindowListener exitListener = new WindowAdapter() {

		    @Override
		    public void windowClosing(WindowEvent e) {
		        /*int confirm = JOptionPane.showOptionDialog(
		             null, "Are You Sure to Close Application?", 
		             "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {*/
		        	g.saveDraygons();
		           System.exit(0);
		        /*}*/
		    }
		};
		frame.addWindowListener(exitListener);
		
	}

}
