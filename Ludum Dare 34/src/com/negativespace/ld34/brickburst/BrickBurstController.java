package com.negativespace.ld34.brickburst;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.negativespace.ld34.Draygon;
import com.negativespace.ld34.Game;
import com.negativespace.ld34.MouseInput;

public class BrickBurstController {
	
	public static Brick[][] bricks = new Brick[13][8];
	
	public static ArrayList<ItemDrop>itemdrops = new ArrayList<ItemDrop>();
	
	public static Paddle paddle;
	
	public static Ball ball;
	
	public static boolean gameOver = false;
	public static boolean won = false;
	public boolean showingWinText = true;
	
	public float winTextX=1920;
	
	public static int coinsCollected = 0;
	
	public static Image brickburstgraphics;
	
	public BrickBurstController(Draygon d){
		ImageIcon bbg = new ImageIcon("graphics/brickburst.png");
		brickburstgraphics = bbg.getImage();
		gameOver = false;
		won = false;
		for(int j=0; j<bricks[0].length; j++){
			for(int i=0; i<bricks.length; i++){
				System.out.println(bricks.length);
				bricks[i][j] = new Brick(128+(i*128), 128+(j*64));
			}
		}
		paddle = new Paddle(750, 888, d.color, d.eyecolor, d.gender);
		ball = new Ball(930, 856);
	}
	
	public void input(){
		if(!gameOver){
		paddle.input();
		}else{
			if(!showingWinText){
				if(MouseInput.leftClicked && !Game.fadingOut){
					if(won){
						Game.addCoins(coinsCollected+300);
					}else{
						Game.addCoins(coinsCollected);
					}
					Game.screenToWarp = Game.DRAYGON_HABITAT;
					Game.fadingOut = true;
				}
			}
		}
	}
	
	public void update(){
		if(!gameOver){
			paddle.update();
			ball.update();
		}
		for(int i=0; i<itemdrops.size(); i++){
			itemdrops.get(i).update();
		}
		
		brickLoop:
		for(int j=0; j<bricks[0].length; j++){
			for(int i=0; i<bricks.length; i++){
				if(!bricks[i][j].destroyed){
					break brickLoop;
				}
				if(i==bricks.length-1 && j == bricks[0].length-1){
					gameOver = true;
					won = true;
				}
			}
		}
	}
	
	public void render(Graphics2D g, Component c){
		for(int j=0; j<bricks[0].length; j++){
			for(int i=0; i<bricks.length; i++){
				bricks[i][j].render(g, c);
			}
		}
		
		for(int i=0; i<itemdrops.size(); i++){
			itemdrops.get(i).render(g, c);
		}
		
		paddle.render(g, c);
		
		ball.render(g, c);
		
		if(gameOver){
			g.setColor(Color.gray);
			g.fillRect(0, 340, 1920, 400);
			g.setFont(Game.WinFont);
			g.setColor(Color.white);
			if(showingWinText){
				if(won){
					g.drawString("YOU WIN!", winTextX, 550);
				}else{
					g.drawString("YOU LOST.", winTextX, 550);
				}
				winTextX-=10;
				if(winTextX<-800){
					showingWinText=false;
				}
			}else{
				g.drawString("Results", 700, 400);
				g.drawString("Coins - " + Integer.toString(coinsCollected), 600, 550);
				if(won){
				g.drawString("Win Bonus - 300", 600, 650);
				}
				g.drawString("Click Screen to Return", 400, 850);
			}
			
		}
	}

}
