package com.negativespace.ld34;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;

public class GameObject {
	
	public float x, y;
	public int width, height;
	
	public GameObject(float x, float y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void input(){
		
	}
	
	public void update(){
		
	}
	
	public void render(Graphics2D g, Component c){
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, width, height);
	}

}
