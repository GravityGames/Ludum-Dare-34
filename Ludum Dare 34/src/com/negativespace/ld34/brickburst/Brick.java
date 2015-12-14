package com.negativespace.ld34.brickburst;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;

import com.negativespace.ld34.Constants;
import com.negativespace.ld34.GameObject;
import com.negativespace.ld34.Game;

public class Brick extends GameObject{
	
	public static final int width = 128, height = 64;
	public byte color;
	public boolean destroyed;
	
	public Brick(int x, int y){
		super(x, y, width, height);
		color = (byte)Game.random.nextInt(10);
	}
	
	@Override
	public void render(Graphics2D g, Component c){
		if(!destroyed){
			switch(color){
			case Constants.COLOR_RED:
				g.drawImage(BrickBurstController.brickburstgraphics, (int)x, (int)y, (int)x+width, (int)y+height, 0, 0, 128, 64, null);
			break;
			case Constants.COLOR_ORANGE:
				g.drawImage(BrickBurstController.brickburstgraphics, (int)x, (int)y, (int)x+width, (int)y+height, 128, 0, 256, 64, null);
			break;
			case Constants.COLOR_YELLOW:
				g.drawImage(BrickBurstController.brickburstgraphics, (int)x, (int)y, (int)x+width, (int)y+height, 256, 0, 384, 64, null);
			break;
			case Constants.COLOR_GREEN:
				g.drawImage(BrickBurstController.brickburstgraphics, (int)x, (int)y, (int)x+width, (int)y+height, 384, 0, 512, 64, null);
			break;
			case Constants.COLOR_BLUE:
				g.drawImage(BrickBurstController.brickburstgraphics, (int)x, (int)y, (int)x+width, (int)y+height, 512, 0, 640, 64, null);
			break;
			case Constants.COLOR_PURPLE:
				g.drawImage(BrickBurstController.brickburstgraphics, (int)x, (int)y, (int)x+width, (int)y+height, 640, 0, 768, 64, null);
			break;
			case Constants.COLOR_BLACK:
				g.drawImage(BrickBurstController.brickburstgraphics, (int)x, (int)y, (int)x+width, (int)y+height, 768, 0, 896, 64, null);
			break;
			case Constants.COLOR_WHITE:
				g.drawImage(BrickBurstController.brickburstgraphics, (int)x, (int)y, (int)x+width, (int)y+height, 896, 0, 1024, 64, null);
			break;
			case Constants.COLOR_GREY:
				g.drawImage(BrickBurstController.brickburstgraphics, (int)x, (int)y, (int)x+width, (int)y+height, 0, 64, 128, 128, null);
			break;
			case Constants.COLOR_PINK:
				g.drawImage(BrickBurstController.brickburstgraphics, (int)x, (int)y, (int)x+width, (int)y+height, 128, 64, 256, 128, null);
			break;
			default:
				
				break;
			}
		}
	}
	
	public void destroy(){
		destroyed = true;
		int i = Game.random.nextInt(10);
		if(i==0){
			int j = Game.random.nextInt(ItemDrop.NUM_ITEMS);
			switch(j){
			case ItemDrop.ITEM_COINS_10:
				BrickBurstController.itemdrops.add(new ItemDrop((int)x, (int)y, ItemDrop.ITEM_COINS_10));
				break;
			case ItemDrop.ITEM_COINS_50:
				BrickBurstController.itemdrops.add(new ItemDrop((int)x, (int)y, ItemDrop.ITEM_COINS_50));
				break;
			case ItemDrop.ITEM_COINS_100:
				BrickBurstController.itemdrops.add(new ItemDrop((int)x, (int)y, ItemDrop.ITEM_COINS_100));
				break;
			default:
				BrickBurstController.itemdrops.add(new ItemDrop((int)x, (int)y, ItemDrop.ITEM_COINS_10));
				break;
			}
			
		}
	}

}
