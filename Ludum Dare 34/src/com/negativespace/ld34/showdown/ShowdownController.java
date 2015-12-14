package com.negativespace.ld34.showdown;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;

import com.negativespace.ld34.Game;
import com.negativespace.ld34.MouseInput;

public class ShowdownController{
	
	boolean activated = false;
	boolean roundLost = false;
	boolean roundWon = false;
	
	public int reactionTimer;
	public static final int MIN_REACTION_TIME = 300;
	public static final int MAX_REACTION_TIME = 1000;
	
	public int round = 0;
	public int roundEndTimer = 60;
	
	public ShowdownController(){
		reactionTimer = Game.random.nextInt(MAX_REACTION_TIME-MIN_REACTION_TIME)+MIN_REACTION_TIME;
	}
	
	public void input(){
		if(MouseInput.leftClicked){
			if(activated && !roundLost){
				roundWon=true;
			}
		}
	}
	
	public void update(){
		if(reactionTimer>0){
			reactionTimer--;
		}else{
			activated = true;
		}
	}
	
	public void render(Graphics2D g, Component c){
		g.setColor(Color.green);
		if(!roundLost){
		g.fillRect(400, 700, 128, 128);
		}
		if(!roundWon){
		g.fillRect(1420, 700, 128, 128);
		}
		if(activated && !roundWon && !roundLost){
			g.setColor(Color.red);
			g.fillRect(896, 200, 128, 128);
		}
	}

}
