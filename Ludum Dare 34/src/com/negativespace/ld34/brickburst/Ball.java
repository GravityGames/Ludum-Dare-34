package com.negativespace.ld34.brickburst;

import java.awt.Component;
import java.awt.Graphics2D;

import com.negativespace.ld34.GameObject;

public class Ball extends GameObject{

	public float moveSpeed = 8f;

	public float angle = 45f;

	public Ball(int x, int y){
		super(x, y, 32, 32);
	}

	@Override
	public void update(){

		if(x<0){
			if(angle>0){
				angle=90;
			}else{
				angle=-90;
			}
			
			x=0;
		}

		if(x>1888){
			if(angle>0){
				angle=45;
			}else{
				angle=-45;
			}
			x=1888;
		}
		
		if(y<0){
			angle *= -1;
			y=0;
		}
		
		if(y>1080){
			BrickBurstController.gameOver=true;
			BrickBurstController.won = false;
		}

		y-=moveSpeed*Math.sin(angle);
		x-=moveSpeed*Math.cos(angle);

		for(int j=0; j<BrickBurstController.bricks[0].length; j++){
			for(int i=0; i<BrickBurstController.bricks.length; i++){
				if(!BrickBurstController.bricks[i][j].destroyed){
					if(x+width>BrickBurstController.bricks[i][j].x && x<BrickBurstController.bricks[i][j].x+BrickBurstController.bricks[i][j].width &&
							y+height>BrickBurstController.bricks[i][j].y && y<BrickBurstController.bricks[i][j].y+BrickBurstController.bricks[i][j].height){
						BrickBurstController.bricks[i][j].destroy();
						//angle = -Math.abs((x+16)-(BrickBurstController.bricks[i][j].x+(BrickBurstController.bricks[i][j].width/2)))/45f;
						if(x+16>=BrickBurstController.bricks[i][j].x+(BrickBurstController.bricks[i][j].width/2)){
							if(y+16>BrickBurstController.bricks[i][j].y+Brick.height){
								angle = -90;
							}else{
								angle = 90;
							}
							
						}else{
							if(y+16>BrickBurstController.bricks[0][BrickBurstController.bricks[0].length-1].y+Brick.height){
								angle = -45;
							}else{
								angle = 45;
							}
						}
					}
				}
			}
		}

		if(x+width>BrickBurstController.paddle.x && x<BrickBurstController.paddle.x+BrickBurstController.paddle.width &&
				y+height>BrickBurstController.paddle.y && y<BrickBurstController.paddle.y+BrickBurstController.paddle.height){

			//angle = Math.abs((x+16)-(BrickBurstController.paddle.x+(BrickBurstController.paddle.width/2)))/45f;
			if(x+16>BrickBurstController.paddle.x+(BrickBurstController.paddle.width/2)){
				angle = 90;
			}else{
				angle = 45;
			}
		}
	}
	
	@Override
	public void render(Graphics2D g, Component c){
		g.drawImage(BrickBurstController.brickburstgraphics, (int)x, (int)y, (int)x+width, (int)y+height, 256, 64, 288, 96, null);
	}

}
