package com.negativespace.ld34;

import javax.swing.JFrame;

public class Main {
	
	public static int width=800, height=600;
	public static String windowName = "Ludum Dare 34";
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setTitle(windowName);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

}
