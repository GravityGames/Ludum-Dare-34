package com.negativespace.ld34.brickburst;

import java.awt.Component;
import java.awt.Graphics2D;

import com.negativespace.ld34.GameObject;

public class ItemDrop extends GameObject{
	
	float moveSpeed = 4;
	
	byte itemType;
	public static final byte NUM_ITEMS = 3;
	public static final byte ITEM_COINS_10=0;
	public static final byte ITEM_COINS_50=1;
	public static final byte ITEM_COINS_100=2;
	
	ItemDrop(int x, int y, byte itemType){
		super(x, y, 64, 64);
		this.itemType = itemType;
	}
	
	@Override
	public void update(){
		y+=moveSpeed;
		
		if(x+width>BrickBurstController.paddle.x && x<BrickBurstController.paddle.x+BrickBurstController.paddle.width &&
				y+height>BrickBurstController.paddle.y && y<BrickBurstController.paddle.y+BrickBurstController.paddle.height){
			collect();
		}
		
		if(y>1080){
			BrickBurstController.itemdrops.remove(this);
		}
	}
	
	public void render(Graphics2D g, Component c){
		switch(itemType){
		case ITEM_COINS_10:
			g.drawImage(BrickBurstController.brickburstgraphics, (int)x, (int)y, (int)x+width, (int)y+height, 672, 64, 736, 128, null);
			break;
		case ITEM_COINS_50:
			g.drawImage(BrickBurstController.brickburstgraphics, (int)x, (int)y, (int)x+width, (int)y+height, 736, 64, 800, 128, null);
			break;
		case ITEM_COINS_100:
			g.drawImage(BrickBurstController.brickburstgraphics, (int)x, (int)y, (int)x+width, (int)y+height, 800, 64, 864, 128, null);
			break;
		}
	}
	
	public void collect(){
		switch(itemType){
		case ITEM_COINS_10:
			BrickBurstController.coinsCollected+=10;
			break;
		case ITEM_COINS_50:
			BrickBurstController.coinsCollected+=50;
			break;
		case ITEM_COINS_100:
			BrickBurstController.coinsCollected+=100;
			break;
		default:
			BrickBurstController.coinsCollected+=10;
			break;
		}
		BrickBurstController.itemdrops.remove(this);
	}

}
