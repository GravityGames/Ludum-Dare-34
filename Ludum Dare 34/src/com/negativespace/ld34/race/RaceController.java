package com.negativespace.ld34.race;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.Iterator;

import com.negativespace.ld34.Draygon;
import com.negativespace.ld34.Game;
import com.negativespace.ld34.MouseInput;

import tiled.core.Map;
import tiled.core.MapLayer;
import tiled.core.MapObject;
import tiled.core.ObjectGroup;
import tiled.core.TileLayer;
import tiled.core.TileSet;
import tiled.io.TMXMapReader;

public class RaceController {
	
	public byte currentCup = 0;
	public static final byte CUP_DRAGON = 0;
	public static final byte CUP_ANCIENT = 1;
	public static final byte CUP_TORNADO = 2;
	
	public byte currentDifficulty = 0;
	
	public byte currentRacer = 0;
	
	public TMXMapReader courseReader = new TMXMapReader();
	public Map course;
	
	public TileLayer tilesLayer;
	public ObjectGroup objectsLayer;
	
	public int[][] tiles;
	
	public static int scrollX, scrollY;
	
	public RacingDraygon[] racers = new RacingDraygon[8];
	public byte[] ranking = new byte[8];
	public boolean raceOver;
	
	public short[][]racerStats = new short[8][5];
	
	public FinishLine finishline;
	
	int coinsCollected = 0;
	
	public static final short COINS_1ST = 2000;
	public static final short COINS_2ND = 1000;
	public static final short COINS_3RD = 500;
	public static final short COINS_4TH = 300;
	public static final short COINS_5TH = 150;
	public static final short COINS_6TH = 50;
	public static final short COINS_7TH = 10;
	public static final short COINS_8TH = 0;
	
	public static final short MIN_STATS_EASY = 100;
	public static final short MAX_STATS_EASY = 250;
	
	boolean alreadyLeftClicked = false;
	
	TileSet tileset;
	
	public RaceController(Draygon d){
		
		racerStats[0][0]=d.speed;
		racerStats[0][1]=d.strength;
		racerStats[0][2]=d.agility;
		racerStats[0][3]=d.intelligence;
		racerStats[0][4]=d.stamina;
		
		for(int i=1; i<8; i++){
			racerStats[i][0]=(short)(Game.random.nextInt(MAX_STATS_EASY-MIN_STATS_EASY)+MIN_STATS_EASY);
			racerStats[i][1]=(short)(Game.random.nextInt(MAX_STATS_EASY-MIN_STATS_EASY)+MIN_STATS_EASY);
			racerStats[i][2]=(short)(Game.random.nextInt(MAX_STATS_EASY-MIN_STATS_EASY)+MIN_STATS_EASY);
			racerStats[i][3]=(short)(Game.random.nextInt(MAX_STATS_EASY-MIN_STATS_EASY)+MIN_STATS_EASY);
			racerStats[i][4]=(short)(Game.random.nextInt(MAX_STATS_EASY-MIN_STATS_EASY)+MIN_STATS_EASY);
		}
		
		try {
			File f = new File("maps/race_dragon.tmx");
			course = courseReader.readMap(f.getAbsolutePath());
			
			tileset = course.getTileSets().get(0);
			
			tilesLayer = (TileLayer)course.getLayer(0);
			objectsLayer = (ObjectGroup)course.getLayer(1);
			
			Iterator<MapObject> ite = objectsLayer.getObjects();
			while(ite.hasNext()){
				MapObject object = ite.next();
				System.out.println(object.getType());
				if(object.getType().equalsIgnoreCase("draygon_spawn")){
					int rID = Integer.parseInt(object.getProperties().getProperty("spawn_id"));
					if(rID==0){
						racers[rID] = new RacingDraygon((float)object.getX(), (float)object.getY(), racerStats[rID][0], racerStats[rID][1], racerStats[rID][2], racerStats[rID][3], racerStats[rID][4], d.color, d.eyecolor, d.gender);
					}else{
						racers[rID] = new RacingDraygon((float)object.getX(), (float)object.getY(), racerStats[rID][0], racerStats[rID][1], racerStats[rID][2], racerStats[rID][3], racerStats[rID][4]);
					}
					
				}else if(object.getType().equalsIgnoreCase("obj_finish_line")){
					finishline = new FinishLine((float)object.getX(), (float)object.getY(), (float)object.getWidth(), (float)object.getHeight());
					System.out.println((float)object.getY() + "   " + racers[0].y);
				}
			}
			
			
			tiles = new int[tilesLayer.getWidth()][tilesLayer.getHeight()];
			
			System.out.println(tilesLayer.getTileAt(0, 0).getId());
			
			for(int j=0; j<tilesLayer.getHeight(); j++){
				for(int i=0; i<tilesLayer.getWidth(); i++){
					tiles[i][j]=tilesLayer.getTileAt(i, j).getId();
				}
			}
			//tiles.
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void input(){
		for(int i=0; i<racers.length; i++){
			racers[i].input();
		}
		
		if(raceOver){
			if(MouseInput.leftClicked && !Game.fadingOut){
				alreadyLeftClicked=true;
				Game.addCoins(coinsCollected);
				Game.screenToWarp = Game.DRAYGON_HABITAT;
				Game.fadingOut = true;
			}
		}
	}
	
	public void update(){
		if(!MouseInput.leftClicked){
			alreadyLeftClicked=false;
		}
		for(int i=0; i<racers.length; i++){
			racers[i].update();
		}
		if(racers[currentRacer].y>476 && racers[currentRacer].y<(tiles[0].length*128)-604){
			scrollY=(int)racers[currentRacer].y-476;
		}else if(racers[currentRacer].y<=476){
			scrollY=0;
		}else{
			scrollY=(tiles[0].length*128)-1080;
		}
		if(!raceOver){
		if(racers[currentRacer].y>finishline.y){
			System.out.println(racers[currentRacer].y + "   " + finishline.y);
			raceOver=true;
			float[] yPos = new float[8];
			for(int i=0; i<8; i++){
				yPos[i] = racers[i].y;
			}
			
			Arrays.sort(yPos);
			
			for(int i=7; i>=0; i--){
				for(int j=0; j<8; j++){
					if(racers[j].y==yPos[i]){
						ranking[Math.abs(i-7)]=(byte)j;
						if(j==0){
							switch(Math.abs(i-7)){
							case 0:
								coinsCollected=COINS_1ST;
								break;
							case 1:
								coinsCollected=COINS_2ND;
								break;
							case 2:
								coinsCollected=COINS_3RD;
								break;
							case 3:
								coinsCollected=COINS_4TH;
								break;
							case 4:
								coinsCollected=COINS_5TH;
								break;
							case 5:
								coinsCollected=COINS_6TH;
								break;
							case 6:
								coinsCollected=COINS_7TH;
								break;
							case 7:
								coinsCollected=COINS_8TH;
								break;
							}
						}
					}
				}
			}
			
		}else{
			
		}
		}
	}
	
	public void render(Graphics2D g, Component c){
		for(int j=0; j<tiles[0].length; j++){
			for(int i=0; i<tiles.length; i++){
				if(j*128 >= scrollY-128 && j*128 <= 1920+scrollY){
				g.drawImage(tileset.getTile(tiles[i][j]).getImage(), i*128-scrollX, j*128-scrollY, null);
				//g.fillRect(i*128-scrollX, j*128-scrollY, 128, 128);
				}
			}
		}
		for(int i=0; i<racers.length; i++){
			racers[i].render(g, c);
		}
		
		if(raceOver){
			for(int i=0; i<8; i++){
				g.drawImage(Game.draygonBodies, (int)128+Game.draygonBodyFrames[racers[ranking[i]].color][racers[ranking[i]].currentBody].offsetX, (int)Game.draygonBodyFrames[racers[ranking[i]].color][racers[ranking[i]].currentBody].offsetY+(i*128), (int)128+Game.draygonBodyFrames[racers[ranking[i]].color][racers[ranking[i]].currentBody].offsetX+Game.draygonBodyFrames[racers[ranking[i]].color][racers[ranking[i]].currentBody].width, (int)Game.draygonBodyFrames[racers[ranking[i]].color][racers[ranking[i]].currentBody].offsetY+Game.draygonBodyFrames[racers[ranking[i]].color][racers[ranking[i]].currentBody].height+(i*128), Game.draygonBodyFrames[racers[ranking[i]].color][racers[ranking[i]].currentBody].x, Game.draygonBodyFrames[racers[ranking[i]].color][racers[ranking[i]].currentBody].y, Game.draygonBodyFrames[racers[ranking[i]].color][racers[ranking[i]].currentBody].x+Game.draygonBodyFrames[racers[ranking[i]].color][racers[ranking[i]].currentBody].width, Game.draygonBodyFrames[racers[ranking[i]].color][racers[ranking[i]].currentBody].y+Game.draygonBodyFrames[racers[ranking[i]].color][racers[ranking[i]].currentBody].height, null);
				g.drawImage(Game.draygonFaces, (int)128+Game.draygonFaceFrames[racers[ranking[i]].color][racers[ranking[i]].currentFace].offsetX, (int)Game.draygonFaceFrames[racers[ranking[i]].color][racers[ranking[i]].currentFace].offsetY+(i*128), (int)128+Game.draygonFaceFrames[racers[ranking[i]].color][racers[ranking[i]].currentFace].offsetX+Game.draygonFaceFrames[racers[ranking[i]].color][racers[ranking[i]].currentFace].width, (int)Game.draygonFaceFrames[racers[ranking[i]].color][racers[ranking[i]].currentFace].offsetY+Game.draygonFaceFrames[racers[ranking[i]].color][racers[ranking[i]].currentFace].height+(i*128), Game.draygonFaceFrames[racers[ranking[i]].color][racers[ranking[i]].currentFace].x, Game.draygonFaceFrames[racers[ranking[i]].color][racers[ranking[i]].currentFace].y, Game.draygonFaceFrames[racers[ranking[i]].color][racers[ranking[i]].currentFace].x+Game.draygonFaceFrames[racers[ranking[i]].color][racers[ranking[i]].currentFace].width, Game.draygonFaceFrames[racers[ranking[i]].color][racers[ranking[i]].currentFace].y+Game.draygonFaceFrames[racers[ranking[i]].color][racers[ranking[i]].currentFace].height, null);

				if(racers[ranking[i]].currentEyes==3){
					if(racers[ranking[i]].gender){
						g.drawImage(Game.draygonEyes, (int)128+Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes].offsetX, (int)Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes].offsetY+(i*128), (int)128+Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes].offsetX+Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes].width, (int)Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes].offsetY+Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes].height-RaceController.scrollY+(i*128), Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes].x, Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes].y, Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes].x+Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes].width, Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes].y+Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes].height, null);
					}else{
						g.drawImage(Game.draygonEyes, (int)128+Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes+6].offsetX, (int)Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes+6].offsetY+(i*128), (int)128+Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes+6].offsetX+Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes+6].width, (int)Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes+6].offsetY+Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes+6].height-RaceController.scrollY+(i*128), Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes+6].x, Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes+6].y, Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes+6].x+Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes+6].width, Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes+6].y+Game.draygonEyeFrames[racers[ranking[i]].color][racers[ranking[i]].currentEyes+6].height, null);
					}
					}else{
						if(racers[ranking[i]].gender){
							g.drawImage(Game.draygonEyes, (int)128+Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes].offsetX, (int)Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes].offsetY+(i*128), (int)128+Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes].offsetX+Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes].width, (int)Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes].offsetY+Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes].height+(i*128), Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes].x, Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes].y, Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes].x+Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes].width, Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes].y+Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes].height, null);
						}else{
							g.drawImage(Game.draygonEyes, (int)128+Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes+6].offsetX, (int)Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes+6].offsetY+(i*128), (int)128+Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes+6].offsetX+Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes+6].width, (int)Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes+6].offsetY+Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes+6].height+(i*128), Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes+6].x, Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes+6].y, Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes+6].x+Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes+6].width, Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes+6].y+Game.draygonEyeFrames[racers[ranking[i]].eyecolor][racers[ranking[i]].currentEyes+6].height, null);
						}
					}
			}
			
			g.setColor(Color.white);
			
			for(int i=0; i<8; i++){
				g.drawString("#" + Integer.toString(i+1), 250, 40+(i*128));
			}
			
			g.drawString("Click Screen to Continue", 500, 540);
		}
	}

}
