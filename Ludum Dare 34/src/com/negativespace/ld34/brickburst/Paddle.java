package com.negativespace.ld34.brickburst;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.negativespace.ld34.Game;
import com.negativespace.ld34.GameObject;
import com.negativespace.ld34.KeyboardInput;
import com.negativespace.ld34.MouseInput;
import com.negativespace.ld34.race.RaceController;

public class Paddle extends GameObject{
	
	public float moveSpeed = 10f;
	
	public byte color, eyecolor;
	public byte currentEyes=0, currentBody=0, currentFace=0;
	public boolean gender;
	
	public Paddle(int x, int y, byte color, byte eyecolor, boolean gender){
		super(x, y, 384, 64);
		this.color = color;
		this.eyecolor = eyecolor;
		this.gender=gender;
	}
	
	@Override
	public void input(){
		/*if(KeyboardInput.keysPressed[KeyEvent.VK_LEFT]){
			x-=moveSpeed;
		}
		if(KeyboardInput.keysPressed[KeyEvent.VK_RIGHT]){
			x+=moveSpeed;
		}*/
		
		x=MouseInput.mouseX*2;
		
		if(x<0){
			x=0;
		}
		if(x>1920-width){
			x=1920-width;
		}
	}
	
	@Override
	public void render(Graphics2D g, Component c){
		g.setColor(Color.GREEN);
		g.drawImage(BrickBurstController.brickburstgraphics, (int)x, (int)y, (int)x+width, (int)y+height, 288, 64, 672, 128, null);
		g.drawImage(Game.draygonBodies, (int)x+Game.draygonBodyFrames[color][currentBody].offsetX-RaceController.scrollX+128, (int)y+Game.draygonBodyFrames[color][currentBody].offsetY-RaceController.scrollY+64, (int)x+Game.draygonBodyFrames[color][currentBody].offsetX+Game.draygonBodyFrames[color][currentBody].width-RaceController.scrollX+128, (int)y+Game.draygonBodyFrames[color][currentBody].offsetY+Game.draygonBodyFrames[color][currentBody].height-RaceController.scrollY+64, Game.draygonBodyFrames[color][currentBody].x, Game.draygonBodyFrames[color][currentBody].y, Game.draygonBodyFrames[color][currentBody].x+Game.draygonBodyFrames[color][currentBody].width, Game.draygonBodyFrames[color][currentBody].y+Game.draygonBodyFrames[color][currentBody].height, null);
		g.drawImage(Game.draygonFaces, (int)x+Game.draygonFaceFrames[color][currentFace].offsetX-RaceController.scrollX+128, (int)y+Game.draygonFaceFrames[color][currentFace].offsetY-RaceController.scrollY+64, (int)x+Game.draygonFaceFrames[color][currentFace].offsetX+Game.draygonFaceFrames[color][currentFace].width-RaceController.scrollX+128, (int)y+Game.draygonFaceFrames[color][currentFace].offsetY+Game.draygonFaceFrames[color][currentFace].height-RaceController.scrollY+64, Game.draygonFaceFrames[color][currentFace].x, Game.draygonFaceFrames[color][currentFace].y, Game.draygonFaceFrames[color][currentFace].x+Game.draygonFaceFrames[color][currentFace].width, Game.draygonFaceFrames[color][currentFace].y+Game.draygonFaceFrames[color][currentFace].height, null);

		if(currentEyes==3){
			if(gender){
				g.drawImage(Game.draygonEyes, (int)x+Game.draygonEyeFrames[color][currentEyes].offsetX-RaceController.scrollX+128, (int)y+Game.draygonEyeFrames[color][currentEyes].offsetY-RaceController.scrollY+64, (int)x+Game.draygonEyeFrames[color][currentEyes].offsetX+Game.draygonEyeFrames[color][currentEyes].width-RaceController.scrollX+128, (int)y+Game.draygonEyeFrames[color][currentEyes].offsetY+Game.draygonEyeFrames[color][currentEyes].height-RaceController.scrollY+64, Game.draygonEyeFrames[color][currentEyes].x, Game.draygonEyeFrames[color][currentEyes].y, Game.draygonEyeFrames[color][currentEyes].x+Game.draygonEyeFrames[color][currentEyes].width, Game.draygonEyeFrames[color][currentEyes].y+Game.draygonEyeFrames[color][currentEyes].height, null);
			}else{
				g.drawImage(Game.draygonEyes, (int)x+Game.draygonEyeFrames[color][currentEyes+6].offsetX-RaceController.scrollX+128, (int)y+Game.draygonEyeFrames[color][currentEyes+6].offsetY-RaceController.scrollY+64, (int)x+Game.draygonEyeFrames[color][currentEyes+6].offsetX+Game.draygonEyeFrames[color][currentEyes+6].width-RaceController.scrollX+128, (int)y+Game.draygonEyeFrames[color][currentEyes+6].offsetY+Game.draygonEyeFrames[color][currentEyes+6].height-RaceController.scrollY+64, Game.draygonEyeFrames[color][currentEyes+6].x, Game.draygonEyeFrames[color][currentEyes+6].y, Game.draygonEyeFrames[color][currentEyes+6].x+Game.draygonEyeFrames[color][currentEyes+6].width, Game.draygonEyeFrames[color][currentEyes+6].y+Game.draygonEyeFrames[color][currentEyes+6].height, null);
			}
			}else{
				if(gender){
					g.drawImage(Game.draygonEyes, (int)x+Game.draygonEyeFrames[eyecolor][currentEyes].offsetX-RaceController.scrollX+128, (int)y+Game.draygonEyeFrames[eyecolor][currentEyes].offsetY-RaceController.scrollY+64, (int)x+Game.draygonEyeFrames[eyecolor][currentEyes].offsetX+Game.draygonEyeFrames[eyecolor][currentEyes].width-RaceController.scrollX+128, (int)y+Game.draygonEyeFrames[eyecolor][currentEyes].offsetY+Game.draygonEyeFrames[eyecolor][currentEyes].height-RaceController.scrollY+64, Game.draygonEyeFrames[eyecolor][currentEyes].x, Game.draygonEyeFrames[eyecolor][currentEyes].y, Game.draygonEyeFrames[eyecolor][currentEyes].x+Game.draygonEyeFrames[eyecolor][currentEyes].width, Game.draygonEyeFrames[eyecolor][currentEyes].y+Game.draygonEyeFrames[eyecolor][currentEyes].height, null);
				}else{
					g.drawImage(Game.draygonEyes, (int)x+Game.draygonEyeFrames[eyecolor][currentEyes+6].offsetX-RaceController.scrollX+128, (int)y+Game.draygonEyeFrames[eyecolor][currentEyes+6].offsetY-RaceController.scrollY+64, (int)x+Game.draygonEyeFrames[eyecolor][currentEyes+6].offsetX+Game.draygonEyeFrames[eyecolor][currentEyes+6].width-RaceController.scrollX+128, (int)y+Game.draygonEyeFrames[eyecolor][currentEyes+6].offsetY+Game.draygonEyeFrames[eyecolor][currentEyes+6].height-RaceController.scrollY+64, Game.draygonEyeFrames[eyecolor][currentEyes+6].x, Game.draygonEyeFrames[eyecolor][currentEyes+6].y, Game.draygonEyeFrames[eyecolor][currentEyes+6].x+Game.draygonEyeFrames[eyecolor][currentEyes+6].width, Game.draygonEyeFrames[eyecolor][currentEyes+6].y+Game.draygonEyeFrames[eyecolor][currentEyes+6].height, null);
				}
			}
	}

}
