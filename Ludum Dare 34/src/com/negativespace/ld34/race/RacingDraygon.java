package com.negativespace.ld34.race;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;

import com.negativespace.ld34.Constants;
import com.negativespace.ld34.Game;
import com.negativespace.ld34.GameObject;

public class RacingDraygon extends GameObject{
	
	public short speed, strength, agility, intelligence, stamina;
	
	public byte color, eyecolor;
	public byte currentEyes=0, currentBody=0, currentFace=0;
	public boolean gender;
	
	public RacingDraygon(float x, float y, short speed, short strength, short agility, short intelligence, short stamina){
		super(x, y, 128, 128);
		this.speed = speed;
		this.strength = strength;
		this.agility = agility;
		this.intelligence = intelligence;
		this.stamina = stamina;
		this.color=(byte)Game.random.nextInt(Constants.COLOR_PINK+1);
		this.eyecolor=(byte)Game.random.nextInt(Constants.COLOR_PINK+1);
		this.gender=Game.random.nextBoolean();
	}
	
	public RacingDraygon(float x, float y, short speed, short strength, short agility, short intelligence, short stamina, byte color, byte eyecolor, boolean gender){
		super(x, y, 128, 128);
		this.speed = speed;
		this.strength = strength;
		this.agility = agility;
		this.intelligence = intelligence;
		this.stamina = stamina;
		this.color=color;
		this.eyecolor=eyecolor;
		this.gender=gender;
	}
	
	@Override
	public void update(){
		y+=2+(speed/100f);
	}
	
	@Override
	public void render(Graphics2D g, Component c){
		g.drawImage(Game.draygonBodies, (int)x+Game.draygonBodyFrames[color][currentBody].offsetX-RaceController.scrollX, (int)y+Game.draygonBodyFrames[color][currentBody].offsetY-RaceController.scrollY, (int)x+Game.draygonBodyFrames[color][currentBody].offsetX+Game.draygonBodyFrames[color][currentBody].width-RaceController.scrollX, (int)y+Game.draygonBodyFrames[color][currentBody].offsetY+Game.draygonBodyFrames[color][currentBody].height-RaceController.scrollY, Game.draygonBodyFrames[color][currentBody].x, Game.draygonBodyFrames[color][currentBody].y, Game.draygonBodyFrames[color][currentBody].x+Game.draygonBodyFrames[color][currentBody].width, Game.draygonBodyFrames[color][currentBody].y+Game.draygonBodyFrames[color][currentBody].height, null);
		g.drawImage(Game.draygonFaces, (int)x+Game.draygonFaceFrames[color][currentFace].offsetX-RaceController.scrollX, (int)y+Game.draygonFaceFrames[color][currentFace].offsetY-RaceController.scrollY, (int)x+Game.draygonFaceFrames[color][currentFace].offsetX+Game.draygonFaceFrames[color][currentFace].width-RaceController.scrollX, (int)y+Game.draygonFaceFrames[color][currentFace].offsetY+Game.draygonFaceFrames[color][currentFace].height-RaceController.scrollY, Game.draygonFaceFrames[color][currentFace].x, Game.draygonFaceFrames[color][currentFace].y, Game.draygonFaceFrames[color][currentFace].x+Game.draygonFaceFrames[color][currentFace].width, Game.draygonFaceFrames[color][currentFace].y+Game.draygonFaceFrames[color][currentFace].height, null);

		if(currentEyes==3){
			if(gender){
				g.drawImage(Game.draygonEyes, (int)x+Game.draygonEyeFrames[color][currentEyes].offsetX-RaceController.scrollX, (int)y+Game.draygonEyeFrames[color][currentEyes].offsetY-RaceController.scrollY, (int)x+Game.draygonEyeFrames[color][currentEyes].offsetX+Game.draygonEyeFrames[color][currentEyes].width-RaceController.scrollX, (int)y+Game.draygonEyeFrames[color][currentEyes].offsetY+Game.draygonEyeFrames[color][currentEyes].height-RaceController.scrollY, Game.draygonEyeFrames[color][currentEyes].x, Game.draygonEyeFrames[color][currentEyes].y, Game.draygonEyeFrames[color][currentEyes].x+Game.draygonEyeFrames[color][currentEyes].width, Game.draygonEyeFrames[color][currentEyes].y+Game.draygonEyeFrames[color][currentEyes].height, null);
			}else{
				g.drawImage(Game.draygonEyes, (int)x+Game.draygonEyeFrames[color][currentEyes+6].offsetX-RaceController.scrollX, (int)y+Game.draygonEyeFrames[color][currentEyes+6].offsetY-RaceController.scrollY, (int)x+Game.draygonEyeFrames[color][currentEyes+6].offsetX+Game.draygonEyeFrames[color][currentEyes+6].width-RaceController.scrollX, (int)y+Game.draygonEyeFrames[color][currentEyes+6].offsetY+Game.draygonEyeFrames[color][currentEyes+6].height-RaceController.scrollY, Game.draygonEyeFrames[color][currentEyes+6].x, Game.draygonEyeFrames[color][currentEyes+6].y, Game.draygonEyeFrames[color][currentEyes+6].x+Game.draygonEyeFrames[color][currentEyes+6].width, Game.draygonEyeFrames[color][currentEyes+6].y+Game.draygonEyeFrames[color][currentEyes+6].height, null);
			}
			}else{
				if(gender){
					g.drawImage(Game.draygonEyes, (int)x+Game.draygonEyeFrames[eyecolor][currentEyes].offsetX-RaceController.scrollX, (int)y+Game.draygonEyeFrames[eyecolor][currentEyes].offsetY-RaceController.scrollY, (int)x+Game.draygonEyeFrames[eyecolor][currentEyes].offsetX+Game.draygonEyeFrames[eyecolor][currentEyes].width-RaceController.scrollX, (int)y+Game.draygonEyeFrames[eyecolor][currentEyes].offsetY+Game.draygonEyeFrames[eyecolor][currentEyes].height-RaceController.scrollY, Game.draygonEyeFrames[eyecolor][currentEyes].x, Game.draygonEyeFrames[eyecolor][currentEyes].y, Game.draygonEyeFrames[eyecolor][currentEyes].x+Game.draygonEyeFrames[eyecolor][currentEyes].width, Game.draygonEyeFrames[eyecolor][currentEyes].y+Game.draygonEyeFrames[eyecolor][currentEyes].height, null);
				}else{
					g.drawImage(Game.draygonEyes, (int)x+Game.draygonEyeFrames[eyecolor][currentEyes+6].offsetX-RaceController.scrollX, (int)y+Game.draygonEyeFrames[eyecolor][currentEyes+6].offsetY-RaceController.scrollY, (int)x+Game.draygonEyeFrames[eyecolor][currentEyes+6].offsetX+Game.draygonEyeFrames[eyecolor][currentEyes+6].width-RaceController.scrollX, (int)y+Game.draygonEyeFrames[eyecolor][currentEyes+6].offsetY+Game.draygonEyeFrames[eyecolor][currentEyes+6].height-RaceController.scrollY, Game.draygonEyeFrames[eyecolor][currentEyes+6].x, Game.draygonEyeFrames[eyecolor][currentEyes+6].y, Game.draygonEyeFrames[eyecolor][currentEyes+6].x+Game.draygonEyeFrames[eyecolor][currentEyes+6].width, Game.draygonEyeFrames[eyecolor][currentEyes+6].y+Game.draygonEyeFrames[eyecolor][currentEyes+6].height, null);
				}
			}
	}

}
