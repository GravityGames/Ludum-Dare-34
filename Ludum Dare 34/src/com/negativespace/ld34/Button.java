package com.negativespace.ld34;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;

public class Button {
	
	public int x, y, width, height;
	public int value;
	
	public Button(int x, int y, int width, int height, int value){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.value=value;
	}
	
	public void render(Graphics2D g, Component c){
		//g.setColor(Color.BLUE);
		//g.fillRect(x, y, width, height);
		g.drawImage(Game.draygonBodies, (int)x+Game.draygonBodyFrames[Game.pets.get(value).color][Game.pets.get(value).currentBody].offsetX, (int)y+Game.draygonBodyFrames[Game.pets.get(value).color][Game.pets.get(value).currentBody].offsetY, (int)x+Game.draygonBodyFrames[Game.pets.get(value).color][Game.pets.get(value).currentBody].offsetX+Game.draygonBodyFrames[Game.pets.get(value).color][Game.pets.get(value).currentBody].width, (int)y+Game.draygonBodyFrames[Game.pets.get(value).color][Game.pets.get(value).currentBody].offsetY+Game.draygonBodyFrames[Game.pets.get(value).color][Game.pets.get(value).currentBody].height, Game.draygonBodyFrames[Game.pets.get(value).color][Game.pets.get(value).currentBody].x, Game.draygonBodyFrames[Game.pets.get(value).color][Game.pets.get(value).currentBody].y, Game.draygonBodyFrames[Game.pets.get(value).color][Game.pets.get(value).currentBody].x+Game.draygonBodyFrames[Game.pets.get(value).color][Game.pets.get(value).currentBody].width, Game.draygonBodyFrames[Game.pets.get(value).color][Game.pets.get(value).currentBody].y+Game.draygonBodyFrames[Game.pets.get(value).color][Game.pets.get(value).currentBody].height, null);
		g.drawImage(Game.draygonFaces, (int)x+Game.draygonFaceFrames[Game.pets.get(value).color][Game.pets.get(value).currentFace].offsetX, (int)y+Game.draygonFaceFrames[Game.pets.get(value).color][Game.pets.get(value).currentFace].offsetY, (int)x+Game.draygonFaceFrames[Game.pets.get(value).color][Game.pets.get(value).currentFace].offsetX+Game.draygonFaceFrames[Game.pets.get(value).color][Game.pets.get(value).currentFace].width, (int)y+Game.draygonFaceFrames[Game.pets.get(value).color][Game.pets.get(value).currentFace].offsetY+Game.draygonFaceFrames[Game.pets.get(value).color][Game.pets.get(value).currentFace].height, Game.draygonFaceFrames[Game.pets.get(value).color][Game.pets.get(value).currentFace].x, Game.draygonFaceFrames[Game.pets.get(value).color][Game.pets.get(value).currentFace].y, Game.draygonFaceFrames[Game.pets.get(value).color][Game.pets.get(value).currentFace].x+Game.draygonFaceFrames[Game.pets.get(value).color][Game.pets.get(value).currentFace].width, Game.draygonFaceFrames[Game.pets.get(value).color][Game.pets.get(value).currentFace].y+Game.draygonFaceFrames[Game.pets.get(value).color][Game.pets.get(value).currentFace].height, null);

		if(Game.pets.get(value).currentEyes==3){
			if(Game.pets.get(value).gender){
				g.drawImage(Game.draygonEyes, (int)x+Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes].offsetX, (int)y+Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes].offsetY, (int)x+Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes].offsetX+Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes].width, (int)y+Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes].offsetY+Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes].height, Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes].x, Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes].y, Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes].x+Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes].width, Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes].y+Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes].height, null);
			}else{
				g.drawImage(Game.draygonEyes, (int)x+Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes+6].offsetX, (int)y+Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes+6].offsetY, (int)x+Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes+6].offsetX+Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes+6].width, (int)y+Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes+6].offsetY+Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes+6].height, Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes+6].x, Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes+6].y, Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes+6].x+Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes+6].width, Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes+6].y+Game.draygonEyeFrames[Game.pets.get(value).color][Game.pets.get(value).currentEyes+6].height, null);
			}
			}else{
				if(Game.pets.get(value).gender){
					g.drawImage(Game.draygonEyes, (int)x+Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes].offsetX, (int)y+Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes].offsetY, (int)x+Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes].offsetX+Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes].width, (int)y+Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes].offsetY+Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes].height, Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes].x, Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes].y, Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes].x+Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes].width, Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes].y+Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes].height, null);
				}else{
					g.drawImage(Game.draygonEyes, (int)x+Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes+6].offsetX, (int)y+Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes+6].offsetY, (int)x+Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes+6].offsetX+Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes+6].width, (int)y+Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes+6].offsetY+Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes+6].height, Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes+6].x, Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes+6].y, Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes+6].x+Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes+6].width, Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes+6].y+Game.draygonEyeFrames[Game.pets.get(value).eyecolor][Game.pets.get(value).currentEyes+6].height, null);
				}
			}
	}

}
