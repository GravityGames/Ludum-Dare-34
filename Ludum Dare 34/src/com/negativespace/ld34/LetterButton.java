package com.negativespace.ld34;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;

public class LetterButton {
	
	public int x, y, width, height;
	public char value;
	
	public LetterButton(int x, int y, int width, int height, char value){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.value=value;
	}
	
	public void render(Graphics2D g, Component c){
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		g.setColor(Color.WHITE);
		g.drawString(Character.toString(value), x+10, y+50);
	}

}
