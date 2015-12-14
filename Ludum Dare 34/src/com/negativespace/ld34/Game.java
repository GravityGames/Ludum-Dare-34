package com.negativespace.ld34;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.newdawn.easyogg.OggClip;

import com.negativespace.ld34.brickburst.BrickBurstController;
import com.negativespace.ld34.race.RaceController;
import com.negativespace.ld34.showdown.ShowdownController;

public class Game extends JPanel implements Runnable{

	public static Random random = new Random();

	public static boolean mouseAlreadyPressed;

	public int minuteTimer = 3600;
	public static final int MINUTE = 3600;

	public static Font GUIFont;
	public static Font WinFont;

	public static int areaWidth = 1920, areaHeight = 1080;

	public static int coins = 0;
	public static final int MAX_COINS = 99999999;

	public BrickBurstController brickburst;
	public ShowdownController showdown;
	public RaceController race;

	public boolean isDraygonSelected = false;
	public int draygonSelected = 0;
	public boolean inMinigamesMenu = false;
	public boolean inInventory = false;
	public boolean inCompetitionsMenu = false;
	public boolean inAnotherScreen = false;
	public boolean inPetSelectionScreen = false;
	public boolean inPetNamingScreen = false;
	public boolean inShop=false;
	public byte shopMenu = 0;
	public String currentItemName = "";
	public byte currentItem=0;
	public int currentItemPrice=0;
	
	public int[] inventory = new int[4];

	public static final byte ITEM_EGG_RED=1;
	public static final byte ITEM_EGG_ORANGE=2;
	public static final byte ITEM_EGG_YELLOW=3;
	public static final byte ITEM_EGG_GREEN=4;
	public static final byte ITEM_EGG_BLUE=5;
	public static final byte ITEM_EGG_PURPLE=6;
	public static final byte ITEM_EGG_BLACK=7;
	public static final byte ITEM_EGG_WHITE=8;
	public static final byte ITEM_EGG_GREY=9;
	public static final byte ITEM_EGG_PINK=10;
	public static final byte ITEM_BOOST_SPEED=11;
	public static final byte ITEM_BOOST_STRENGTH=12;
	public static final byte ITEM_BOOST_AGILITY=13;
	public static final byte ITEM_BOOST_IQ=14;

	public boolean petsActive = true;
	public static byte screenToWarp = 0;
	public byte currentScreen = 0;

	public static final byte DRAYGON_HABITAT = 0;
	public static final byte MINIGAME_MATCH = 1;
	public static final byte MINIGAME_BRICK_BURST = 2;
	public static final byte MINIGAME_SHOWDOWN = 3;
	public static final byte COMPETITION_RACE = 4;
	public static final byte COMPETITION_QUIZ = 5;

	public static boolean fadingOut=false;
	boolean fadingIn=false;
	public int fadeAlpha = 0;

	public Button[][] draygonButtons = new Button[6][3];
	public ArrayList<LetterButton> nameButtons = new ArrayList<LetterButton>();

	JFrame frame;
	BufferedImage screen = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_ARGB);
	public boolean isRunning=true;
	public int scrollX = 0, scrollY = 0;

	public static ArrayList<Draygon>pets = new ArrayList<Draygon>();

	public static Image draygonEyes, draygonFaces, draygonBodies, draygonEggs, shopItems;

	public static Frame draygonEyeFrames[][] = new Frame[Constants.COLOR_PINK+1][12];
	public static Frame draygonFaceFrames[][] = new Frame[Constants.COLOR_PINK+1][1];
	public static Frame draygonBodyFrames[][] = new Frame[Constants.COLOR_PINK+1][1];
	public static Frame draygonEggFrames[] = new Frame[Constants.COLOR_PINK+1];
	
	static OggClip music1 = null, music2 = null;

	public Game(JFrame frame){
		this.frame = frame;

		ImageIcon temp = new ImageIcon("graphics/draygon_eyes.png");
		draygonEyes = temp.getImage();
		temp = new ImageIcon("graphics/draygon_faces.png");
		draygonFaces = temp.getImage();
		temp = new ImageIcon("graphics/draygon_bodies.png");
		draygonBodies = temp.getImage();
		temp = new ImageIcon("graphics/draygon_eggs.png");
		draygonEggs = temp.getImage();
		temp = new ImageIcon("graphics/shop_items.png");
		shopItems = temp.getImage();

		draygonEggFrames[0] = new Frame(0, 0, 84, 122, 0, 0);
		draygonEggFrames[1] = new Frame(85, 0, 84, 122, 0, 0);
		draygonEggFrames[2] = new Frame(170, 0, 84, 122, 0, 0);
		draygonEggFrames[3] = new Frame(255, 0, 84, 122, 0, 0);
		draygonEggFrames[4] = new Frame(340, 0, 84, 122, 0, 0);
		draygonEggFrames[5] = new Frame(425, 0, 84, 122, 0, 0);
		draygonEggFrames[6] = new Frame(0, 123, 84, 122, 0, 0);
		draygonEggFrames[7] = new Frame(85, 123, 84, 122, 0, 0);
		draygonEggFrames[8] = new Frame(170, 123, 84, 122, 0, 0);
		draygonEggFrames[9] = new Frame(255, 123, 84, 122, 0, 0);

		draygonEyeFrames[0][0] = new Frame(0, 0, 73, 29, 24, 12);
		draygonEyeFrames[1][0] = new Frame(0, 0, 73, 29, 24, 12);
		draygonEyeFrames[2][0] = new Frame(0, 0, 73, 29, 24, 12);
		draygonEyeFrames[3][0] = new Frame(0, 0, 73, 29, 24, 12);
		draygonEyeFrames[4][0] = new Frame(0, 0, 73, 29, 24, 12);
		draygonEyeFrames[5][0] = new Frame(0, 0, 73, 29, 24, 12);
		draygonEyeFrames[6][0] = new Frame(0, 0, 73, 29, 24, 12);
		draygonEyeFrames[7][0] = new Frame(0, 0, 73, 29, 24, 12);
		draygonEyeFrames[8][0] = new Frame(0, 0, 73, 29, 24, 12);
		draygonEyeFrames[9][0] = new Frame(0, 0, 73, 29, 24, 12);

		draygonEyeFrames[0][1] = new Frame(75, 0, 73, 29, 24, 12);
		draygonEyeFrames[1][1] = new Frame(75, 0, 73, 29, 24, 12);
		draygonEyeFrames[2][1] = new Frame(75, 0, 73, 29, 24, 12);
		draygonEyeFrames[3][1] = new Frame(75, 0, 73, 29, 24, 12);
		draygonEyeFrames[4][1] = new Frame(75, 0, 73, 29, 24, 12);
		draygonEyeFrames[5][1] = new Frame(75, 0, 73, 29, 24, 12);
		draygonEyeFrames[6][1] = new Frame(75, 0, 73, 29, 24, 12);
		draygonEyeFrames[7][1] = new Frame(75, 0, 73, 29, 24, 12);
		draygonEyeFrames[8][1] = new Frame(75, 0, 73, 29, 24, 12);
		draygonEyeFrames[9][1] = new Frame(75, 0, 73, 29, 24, 12);
		
		draygonEyeFrames[0][2] = new Frame(150, 0, 73, 29, 24, 12);
		draygonEyeFrames[1][2] = new Frame(150, 0, 73, 29, 24, 12);
		draygonEyeFrames[2][2] = new Frame(150, 0, 73, 29, 24, 12);
		draygonEyeFrames[3][2] = new Frame(150, 0, 73, 29, 24, 12);
		draygonEyeFrames[4][2] = new Frame(150, 0, 73, 29, 24, 12);
		draygonEyeFrames[5][2] = new Frame(150, 0, 73, 29, 24, 12);
		draygonEyeFrames[6][2] = new Frame(150, 0, 73, 29, 24, 12);
		draygonEyeFrames[7][2] = new Frame(150, 0, 73, 29, 24, 12);
		draygonEyeFrames[8][2] = new Frame(150, 0, 73, 29, 24, 12);
		draygonEyeFrames[9][2] = new Frame(150, 0, 73, 29, 24, 12);

		draygonEyeFrames[0][3] = new Frame(225, 0, 73, 29, 24, 12);
		draygonEyeFrames[1][3] = new Frame(225, 0, 73, 29, 24, 12);
		draygonEyeFrames[2][3] = new Frame(225, 0, 73, 29, 24, 12);
		draygonEyeFrames[3][3] = new Frame(225, 0, 73, 29, 24, 12);
		draygonEyeFrames[4][3] = new Frame(225, 0, 73, 29, 24, 12);
		draygonEyeFrames[5][3] = new Frame(225, 0, 73, 29, 24, 12);
		draygonEyeFrames[6][3] = new Frame(225, 0, 73, 29, 24, 12);
		draygonEyeFrames[7][3] = new Frame(225, 0, 73, 29, 24, 12);
		draygonEyeFrames[8][3] = new Frame(225, 0, 73, 29, 24, 12);
		draygonEyeFrames[9][3] = new Frame(225, 0, 73, 29, 24, 12);

		draygonEyeFrames[0][4] = new Frame(300, 0, 73, 29, 24, 12);
		draygonEyeFrames[1][4] = new Frame(300, 0, 73, 29, 24, 12);
		draygonEyeFrames[2][4] = new Frame(300, 0, 73, 29, 24, 12);
		draygonEyeFrames[3][4] = new Frame(300, 0, 73, 29, 24, 12);
		draygonEyeFrames[4][4] = new Frame(300, 0, 73, 29, 24, 12);
		draygonEyeFrames[5][4] = new Frame(300, 0, 73, 29, 24, 12);
		draygonEyeFrames[6][4] = new Frame(300, 0, 73, 29, 24, 12);
		draygonEyeFrames[7][4] = new Frame(300, 0, 73, 29, 24, 12);
		draygonEyeFrames[8][4] = new Frame(300, 0, 73, 29, 24, 12);
		draygonEyeFrames[9][4] = new Frame(300, 0, 73, 29, 24, 12);

		draygonEyeFrames[0][5] = new Frame(375, 0, 73, 29, 24, 12);
		draygonEyeFrames[1][5] = new Frame(375, 0, 73, 29, 24, 12);
		draygonEyeFrames[2][5] = new Frame(375, 0, 73, 29, 24, 12);
		draygonEyeFrames[3][5] = new Frame(375, 0, 73, 29, 24, 12);
		draygonEyeFrames[4][5] = new Frame(375, 0, 73, 29, 24, 12);
		draygonEyeFrames[5][5] = new Frame(375, 0, 73, 29, 24, 12);
		draygonEyeFrames[6][5] = new Frame(375, 0, 73, 29, 24, 12);
		draygonEyeFrames[7][5] = new Frame(375, 0, 73, 29, 24, 12);
		draygonEyeFrames[8][5] = new Frame(375, 0, 73, 29, 24, 12);
		draygonEyeFrames[9][5] = new Frame(375, 0, 73, 29, 24, 12);
		
		draygonEyeFrames[0][6] = new Frame(450, 0, 84, 38, 20, 4);
		draygonEyeFrames[1][6] = new Frame(450, 0, 84, 38, 20, 4);
		draygonEyeFrames[2][6] = new Frame(450, 0, 84, 38, 20, 4);
		draygonEyeFrames[3][6] = new Frame(450, 0, 84, 38, 20, 4);
		draygonEyeFrames[4][6] = new Frame(450, 0, 84, 38, 20, 4);
		draygonEyeFrames[5][6] = new Frame(450, 0, 84, 38, 20, 4);
		draygonEyeFrames[6][6] = new Frame(450, 0, 84, 38, 20, 4);
		draygonEyeFrames[7][6] = new Frame(450, 0, 84, 38, 20, 4);
		draygonEyeFrames[8][6] = new Frame(450, 0, 84, 38, 20, 4);
		draygonEyeFrames[9][6] = new Frame(450, 0, 84, 38, 20, 4);
		
		draygonEyeFrames[0][7] = new Frame(536, 0, 84, 38, 20, 4);
		draygonEyeFrames[1][7] = new Frame(536, 0, 84, 38, 20, 4);
		draygonEyeFrames[2][7] = new Frame(536, 0, 84, 38, 20, 4);
		draygonEyeFrames[3][7] = new Frame(536, 0, 84, 38, 20, 4);
		draygonEyeFrames[4][7] = new Frame(536, 0, 84, 38, 20, 4);
		draygonEyeFrames[5][7] = new Frame(536, 0, 84, 38, 20, 4);
		draygonEyeFrames[6][7] = new Frame(536, 0, 84, 38, 20, 4);
		draygonEyeFrames[7][7] = new Frame(536, 0, 84, 38, 20, 4);
		draygonEyeFrames[8][7] = new Frame(536, 0, 84, 38, 20, 4);
		draygonEyeFrames[9][7] = new Frame(536, 0, 84, 38, 20, 4);
		
		draygonEyeFrames[0][8] = new Frame(622, 0, 84, 38, 20, 4);
		draygonEyeFrames[1][8] = new Frame(622, 0, 84, 38, 20, 4);
		draygonEyeFrames[2][8] = new Frame(622, 0, 84, 38, 20, 4);
		draygonEyeFrames[3][8] = new Frame(622, 0, 84, 38, 20, 4);
		draygonEyeFrames[4][8] = new Frame(622, 0, 84, 38, 20, 4);
		draygonEyeFrames[5][8] = new Frame(622, 0, 84, 38, 20, 4);
		draygonEyeFrames[6][8] = new Frame(622, 0, 84, 38, 20, 4);
		draygonEyeFrames[7][8] = new Frame(622, 0, 84, 38, 20, 4);
		draygonEyeFrames[8][8] = new Frame(622, 0, 84, 38, 20, 4);
		draygonEyeFrames[9][8] = new Frame(622, 0, 84, 38, 20, 4);
		
		draygonEyeFrames[0][9] = new Frame(708, 0, 84, 38, 20, 4);
		draygonEyeFrames[1][9] = new Frame(708, 0, 84, 38, 20, 4);
		draygonEyeFrames[2][9] = new Frame(708, 0, 84, 38, 20, 4);
		draygonEyeFrames[3][9] = new Frame(708, 0, 84, 38, 20, 4);
		draygonEyeFrames[4][9] = new Frame(708, 0, 84, 38, 20, 4);
		draygonEyeFrames[5][9] = new Frame(708, 0, 84, 38, 20, 4);
		draygonEyeFrames[6][9] = new Frame(708, 0, 84, 38, 20, 4);
		draygonEyeFrames[7][9] = new Frame(708, 0, 84, 38, 20, 4);
		draygonEyeFrames[8][9] = new Frame(708, 0, 84, 38, 20, 4);
		draygonEyeFrames[9][9] = new Frame(708, 0, 84, 38, 20, 4);
		
		draygonEyeFrames[0][10] = new Frame(794, 0, 84, 38, 20, 4);
		draygonEyeFrames[1][10] = new Frame(794, 0, 84, 38, 20, 4);
		draygonEyeFrames[2][10] = new Frame(794, 0, 84, 38, 20, 4);
		draygonEyeFrames[3][10] = new Frame(794, 0, 84, 38, 20, 4);
		draygonEyeFrames[4][10] = new Frame(794, 0, 84, 38, 20, 4);
		draygonEyeFrames[5][10] = new Frame(794, 0, 84, 38, 20, 4);
		draygonEyeFrames[6][10] = new Frame(794, 0, 84, 38, 20, 4);
		draygonEyeFrames[7][10] = new Frame(794, 0, 84, 38, 20, 4);
		draygonEyeFrames[8][10] = new Frame(794, 0, 84, 38, 20, 4);
		draygonEyeFrames[9][10] = new Frame(794, 0, 84, 38, 20, 4);
		
		draygonEyeFrames[0][11] = new Frame(880, 0, 84, 38, 20, 4);
		draygonEyeFrames[1][11] = new Frame(880, 0, 84, 38, 20, 4);
		draygonEyeFrames[2][11] = new Frame(880, 0, 84, 38, 20, 4);
		draygonEyeFrames[3][11] = new Frame(880, 0, 84, 38, 20, 4);
		draygonEyeFrames[4][11] = new Frame(880, 0, 84, 38, 20, 4);
		draygonEyeFrames[5][11] = new Frame(880, 0, 84, 38, 20, 4);
		draygonEyeFrames[6][11] = new Frame(880, 0, 84, 38, 20, 4);
		draygonEyeFrames[7][11] = new Frame(880, 0, 84, 38, 20, 4);
		draygonEyeFrames[8][11] = new Frame(880, 0, 84, 38, 20, 4);
		draygonEyeFrames[9][11] = new Frame(880, 0, 84, 38, 20, 4);

		draygonFaceFrames[0][0] = new Frame(0, 0, 91, 65, 16, 10);
		draygonFaceFrames[1][0] = new Frame(0, 67, 91, 65, 16, 10);
		draygonFaceFrames[2][0] = new Frame(0, 134, 91, 65, 16, 10);
		draygonFaceFrames[3][0] = new Frame(0, 201, 91, 65, 16, 10);
		draygonFaceFrames[4][0] = new Frame(0, 268, 91, 65, 16, 10);
		draygonFaceFrames[5][0] = new Frame(0, 335, 91, 65, 16, 10);
		draygonFaceFrames[6][0] = new Frame(0, 402, 91, 65, 16, 10);
		draygonFaceFrames[7][0] = new Frame(0, 469, 91, 65, 16, 10);
		draygonFaceFrames[8][0] = new Frame(0, 536, 91, 65, 16, 10);
		draygonFaceFrames[9][0] = new Frame(0, 603, 91, 65, 16, 10);

		draygonBodyFrames[0][0] = new Frame(0, 0, 41, 58, 43, 70);
		draygonBodyFrames[1][0] = new Frame(0, 60, 41, 58, 43, 70);
		draygonBodyFrames[2][0] = new Frame(0, 120, 41, 58, 43, 70); 
		draygonBodyFrames[3][0] = new Frame(0, 180, 41, 58, 43, 70); 
		draygonBodyFrames[4][0] = new Frame(0, 240, 41, 58, 43, 70); 
		draygonBodyFrames[5][0] = new Frame(0, 300, 41, 58, 43, 70); 
		draygonBodyFrames[6][0] = new Frame(0, 360, 41, 58, 43, 70); 
		draygonBodyFrames[7][0] = new Frame(0, 420, 41, 58, 43, 70); 
		draygonBodyFrames[8][0] = new Frame(0, 480, 41, 58, 43, 70); 
		draygonBodyFrames[9][0] = new Frame(0, 540, 41, 58, 43, 70); 

		try {
			GUIFont=Font.createFont(Font.TRUETYPE_FONT, new File("fonts/joystix monospace.ttf")).deriveFont(48f);
			WinFont=Font.createFont(Font.TRUETYPE_FONT, new File("fonts/joystix monospace.ttf")).deriveFont(64f);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Main.keyListener = new KeyboardInput();
		frame.addKeyListener(Main.keyListener);

		Main.mouseListener = new MouseInput();

		addMouseListener(Main.mouseListener);
		addMouseMotionListener(Main.mouseListener);

		//pets.add(new Draygon(64, 64, "Dmitry", Constants.COLOR_GREEN));
		//pets.add(new Draygon(512, 64, Constants.COLOR_GREEN));

		int k = 0;
		for(int j=0; j<3; j++){
			for(int i=0; i<6; i++){
				draygonButtons[i][j] = new Button(50+(i*200), 350+(j*200), 128, 128, k);
				k++;
			}
		}

		int nameButtonX = 0, nameButtonY = 300;
		int nameButtonMin = 0, nameButtonMax = 1500;

		for(char i=0x20; i<=0x7A; i++){
			if(i==0x2E){

			}else{
				nameButtons.add(new LetterButton(nameButtonX, nameButtonY, 64, 64, i));
				if(nameButtonX+140>nameButtonMax){
					nameButtonX=nameButtonMin;
					nameButtonY+=70;
				}else{
					nameButtonX+=70;
				}

			}
		}
		
		try {
			music1 = new OggClip("DraygonHabitat.ogg");
			music2 = new OggClip("DraygonMinigame.ogg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		inventory[0] = 0;
		inventory[1] = 0;
		inventory[2] = 0;
		inventory[3] = 0;
		
		music1.loop();

		Thread t = new Thread(this);
		t.start();
	}

	public void input(){

		if(currentScreen == DRAYGON_HABITAT){

			/*if(KeyboardInput.keysPressed[KeyEvent.VK_LEFT]){

		}
		if(KeyboardInput.keysPressed[KeyEvent.VK_RIGHT]){

		}*/

			if(MouseInput.leftClicked){

				if(isDraygonSelected){
					//g2.fillRect(1380, 47, 440, 75);
					if(MouseInput.clickX*2 >= 1380 && MouseInput.clickX*2 <= 1820 &&
							MouseInput.clickY*2 >= 47 && MouseInput.clickY*2 <= 122){
						isDraygonSelected=false;
						inPetNamingScreen=true;
						petsActive = false;
					}
				}

				for(int i=0; i<pets.size(); i++){
					if((MouseInput.clickX*2)+scrollX >= pets.get(i).x && (MouseInput.clickX*2)+scrollX <= pets.get(i).x+pets.get(i).width &&
							(MouseInput.clickY*2)+scrollY >= pets.get(i).y && (MouseInput.clickY*2)+scrollY <= pets.get(i).y+pets.get(i).height){
						if(pets.get(i).inEgg){
							isDraygonSelected = false;
						}else{
							isDraygonSelected = true;
							draygonSelected = i;
						}
						break;
					}else if(i==pets.size()-1){
						isDraygonSelected = false;
					}
				}
				if(!inPetNamingScreen){
					if(!inMinigamesMenu){
						if(MouseInput.clickX*2 >= 1520 && MouseInput.clickX*2 <= 1920 &&
								MouseInput.clickY*2 >= 0 && MouseInput.clickY*2 <= 75){
							inMinigamesMenu = true;
						}
					}else{
						if((MouseInput.clickX*2) < 1300){
							inMinigamesMenu = false;
						}
						if(MouseInput.clickX*2 >= 1320 && MouseInput.clickX*2 <= 1900 &&
								MouseInput.clickY*2 >= 170 && MouseInput.clickY*2 <= 270){
							petsActive=false;
							inPetSelectionScreen=true;
							screenToWarp=MINIGAME_MATCH;
						}

						if(MouseInput.clickX*2 >= 1320 && MouseInput.clickX*2 <= 1900 &&
								MouseInput.clickY*2 >= 290 && MouseInput.clickY*2 <= 390){
							petsActive=false;
							inPetSelectionScreen=true;
							screenToWarp=MINIGAME_BRICK_BURST;
						}

						if(MouseInput.clickX*2 >= 1320 && MouseInput.clickX*2 <= 1900 &&
								MouseInput.clickY*2 >= 410 && MouseInput.clickY*2 <= 510){
							petsActive=false;
							inPetSelectionScreen=true;
							screenToWarp=MINIGAME_SHOWDOWN;
						}
					}

					if(!inCompetitionsMenu){
						if(MouseInput.clickX*2 >= 770 && MouseInput.clickX*2 <= 1170 &&
								MouseInput.clickY*2 >= 0 && MouseInput.clickY*2 <= 75){
							inCompetitionsMenu = true;
						}
					}else{
						if((MouseInput.clickX*2) < 770 || (MouseInput.clickX*2)>1170){
							inCompetitionsMenu = false;
						}
						if(MouseInput.clickX*2 >= 680 && MouseInput.clickX*2 <= 1260 &&
								MouseInput.clickY*2 >= 170 && MouseInput.clickY*2 <= 270){
							petsActive=false;
							inPetSelectionScreen=true;
							screenToWarp=COMPETITION_RACE;
						}

						if(MouseInput.clickX*2 >= 680 && MouseInput.clickX*2 <= 1260 &&
								MouseInput.clickY*2 >= 290 && MouseInput.clickY*2 <= 390){
							petsActive=false;
							inPetSelectionScreen=true;
							screenToWarp=COMPETITION_QUIZ;
						}
					}
					if(!inInventory){
						if(MouseInput.clickX*2 >= 0 && MouseInput.clickX*2 <= 400 &&
								MouseInput.clickY*2 >= 0 && MouseInput.clickY*2 <= 75){
							inInventory = true;
						}
					}else{
						if((MouseInput.clickX*2)>400){
							inInventory = false;
						}
						/*2.drawImage(shopItems, 100, 150, 228, 278, 0, 0, 128, 128, null);
						g2.drawImage(shopItems, 100, 350, 228, 478, 128, 0, 256, 128, null);
						g2.drawImage(shopItems, 100, 550, 228, 678, 0, 128, 128, 256, null);
						g2.drawImage(shopItems, 100, 750, 228, 878, 128, 128, 256, 256, null);*/
						if(MouseInput.clickX*2 >= 100 && MouseInput.clickX*2 <= 228 &&
								MouseInput.clickY*2 >= 150 && MouseInput.clickY*2 <= 278){
							if(inventory[0]>0){
							currentItem=ITEM_BOOST_SPEED;
							inPetSelectionScreen=true;
							}
						}
						if(MouseInput.clickX*2 >= 100 && MouseInput.clickX*2 <= 228 &&
								MouseInput.clickY*2 >= 350 && MouseInput.clickY*2 <= 478){
							if(inventory[1]>0){
							currentItem=ITEM_BOOST_STRENGTH;
							inPetSelectionScreen=true;
							}
						}
						if(MouseInput.clickX*2 >= 100 && MouseInput.clickX*2 <= 228 &&
								MouseInput.clickY*2 >= 550 && MouseInput.clickY*2 <= 678){
							if(inventory[2]>0){
							currentItem=ITEM_BOOST_AGILITY;
							inPetSelectionScreen=true;
							}
						}
						if(MouseInput.clickX*2 >= 100 && MouseInput.clickX*2 <= 228 &&
								MouseInput.clickY*2 >= 750 && MouseInput.clickY*2 <= 878){
							if(inventory[3]>0){
							currentItem=ITEM_BOOST_IQ;
							inPetSelectionScreen=true;
							}
						}
						
					}
					if(!inShop){
						if(MouseInput.clickX*2 >= 0 && MouseInput.clickX*2 <= 400 &&
								MouseInput.clickY*2 >= 1005 && MouseInput.clickY*2 <= 1080){
							inShop = true;
							petsActive = false;
						}
					}else{
						if(!mouseAlreadyPressed){
							mouseAlreadyPressed=true;
						if(MouseInput.clickX*2 >= 1400 && MouseInput.clickX*2 <= 1920 &&
								MouseInput.clickY*2 >= 500 && MouseInput.clickY*2 <= 600){
							shopMenu=1;
						}

						if(MouseInput.clickX*2 >= 1400 && MouseInput.clickX*2 <= 1920 &&
								MouseInput.clickY*2 >= 620 && MouseInput.clickY*2 <= 720){
							shopMenu=2;
						}

						if(MouseInput.clickX*2 >= 1400 && MouseInput.clickX*2 <= 1920 &&
								MouseInput.clickY*2 >= 740 && MouseInput.clickY*2 <= 840){
							shopMenu=0;
							inShop=false;
							petsActive=true;
						}
						
						if(shopMenu==1){

						int eggOffsetX = 0;
						int eggOffsetY = 0;

						for(byte i=0; i<10; i++){
							if(eggOffsetX==750){
								eggOffsetX=0;
								eggOffsetY+=150;
							}
							if(MouseInput.clickX*2 >= 350+eggOffsetX && MouseInput.clickX*2 <= 350+eggOffsetX+draygonEggFrames[i].width &&
									MouseInput.clickY*2 >= 400+eggOffsetY && MouseInput.clickY*2 <= 400+eggOffsetY+draygonEggFrames[i].height){
								currentItem=(byte)(i+1);
							}
							eggOffsetX+=150;
						}
						}
						
						/*2.drawImage(shopItems, 350, 550, 478, 678, 0, 0, 128, 128, null);
						g2.drawImage(shopItems, 500, 550, 628, 678, 128, 0, 256, 128, null);
						g2.drawImage(shopItems, 650, 550, 778, 678, 0, 128, 128, 256, null);
						g2.drawImage(shopItems, 800, 550, 928, 678, 128, 128, 256, 256, null);*/
						
						if(shopMenu==2){
							if(MouseInput.clickX*2 >= 350 && MouseInput.clickX*2 <= 478 &&
									MouseInput.clickY*2 >= 550 && MouseInput.clickY*2 <= 678){
								currentItem = ITEM_BOOST_SPEED;
							}
							if(MouseInput.clickX*2 >= 500 && MouseInput.clickX*2 <= 628 &&
									MouseInput.clickY*2 >= 550 && MouseInput.clickY*2 <= 678){
								currentItem = ITEM_BOOST_STRENGTH;
							}
							if(MouseInput.clickX*2 >= 650 && MouseInput.clickX*2 <= 778 &&
									MouseInput.clickY*2 >= 550 && MouseInput.clickY*2 <= 678){
								currentItem = ITEM_BOOST_AGILITY;
							}
							if(MouseInput.clickX*2 >= 800 && MouseInput.clickX*2 <= 928 &&
									MouseInput.clickY*2 >= 550 && MouseInput.clickY*2 <= 678){
								currentItem = ITEM_BOOST_IQ;
							}
						}

						//g2.fillRect(500, 900, 400, 100);

						if(shopMenu>0 && currentItem>0){
							if(MouseInput.clickX*2 >= 500 && MouseInput.clickX*2 <= 900 &&
									MouseInput.clickY*2 >= 900 && MouseInput.clickY*2 <= 1000){
								if(coins>=currentItemPrice){
									//inShop=false;
									//petsActive=true;
									coins-=currentItemPrice;

									switch(currentItem){
									case ITEM_EGG_RED:
										pets.add(new Draygon(Constants.COLOR_RED));
										break;
									case ITEM_EGG_ORANGE:
										pets.add(new Draygon(Constants.COLOR_ORANGE));
										break;
									case ITEM_EGG_YELLOW:
										pets.add(new Draygon(Constants.COLOR_YELLOW));
										break;
									case ITEM_EGG_GREEN:
										pets.add(new Draygon(Constants.COLOR_GREEN));
										break;
									case ITEM_EGG_BLUE:
										pets.add(new Draygon(Constants.COLOR_BLUE));
										break;
									case ITEM_EGG_PURPLE:
										pets.add(new Draygon(Constants.COLOR_PURPLE));
										break;
									case ITEM_EGG_BLACK:
										pets.add(new Draygon(Constants.COLOR_BLACK));
										break;
									case ITEM_EGG_WHITE:
										pets.add(new Draygon(Constants.COLOR_WHITE));
										break;
									case ITEM_EGG_GREY:
										pets.add(new Draygon(Constants.COLOR_GREY));
										break;
									case ITEM_EGG_PINK:
										pets.add(new Draygon(Constants.COLOR_PINK));
										break;
									case ITEM_BOOST_SPEED:
										inventory[0]++;
										break;
									case ITEM_BOOST_STRENGTH:
										inventory[1]++;
										break;
									case ITEM_BOOST_AGILITY:
										inventory[2]++;
										break;
									case ITEM_BOOST_IQ:
										inventory[3]++;
										break;
									default:

										break;
									}

									//shopMenu=0;
									//currentItem=0;
								}else{

								}
							}
						}


						if(currentItem==0){
							currentItemName="";
						}
						if(currentItem==1){
							currentItemName="Red Egg";
						}
						if(currentItem==2){
							currentItemName="Orange Egg";
						}
						if(currentItem==3){
							currentItemName="Yellow Egg";
						}
						if(currentItem==4){
							currentItemName="Green Egg";
						}
						if(currentItem==5){
							currentItemName="Blue Egg";
						}
						if(currentItem==6){
							currentItemName="Purple Egg";
						}
						if(currentItem==7){
							currentItemName="Black Egg";
						}
						if(currentItem==8){
							currentItemName="White Egg";
						}
						if(currentItem==9){
							currentItemName="Gray Egg";
						}
						if(currentItem==10){
							currentItemName="Pink Egg";
						}
						if(currentItem==ITEM_BOOST_SPEED){
							currentItemName="Speed Up";
						}
						if(currentItem==ITEM_BOOST_STRENGTH){
							currentItemName="Strength Up";
						}
						if(currentItem==ITEM_BOOST_AGILITY){
							currentItemName="Agility Up";
						}
						if(currentItem==ITEM_BOOST_IQ){
							currentItemName="IQ Up";
						}
						
						if(currentItem>=1 && currentItem<=10){
							currentItemPrice = 2000;
						}else{
							currentItemPrice = 100;
						}
					}
					}

					if(inPetSelectionScreen){
						ButtonLoop:
							for(int j=0; j<3; j++){
								for(int i=0; i<6; i++){
									if(MouseInput.clickX*2 >= draygonButtons[i][j].x && MouseInput.clickX*2 <= draygonButtons[i][j].x+draygonButtons[i][j].width &&
											MouseInput.clickY*2 >= draygonButtons[i][j].y && MouseInput.clickY*2 <= draygonButtons[i][j].y+draygonButtons[i][j].height){
										if(draygonButtons[i][j].value < pets.size()){
											draygonSelected = draygonButtons[i][j].value;
										}
										break ButtonLoop;
									}
								}
							}

					if(MouseInput.clickX*2 >= 1400 && MouseInput.clickX*2 <= 1700 &&
							MouseInput.clickY*2 >= 750 && MouseInput.clickY*2 <= 825){
						if(!inInventory){
						fadingOut=true;
						inMinigamesMenu=false;
						inInventory=false;
						inCompetitionsMenu=false;
						inPetSelectionScreen=false;
						}else{
							if(currentItem==ITEM_BOOST_SPEED){
								inventory[0]--;
								pets.get(draygonSelected).speedProgress+=10;
							}
							if(currentItem==ITEM_BOOST_STRENGTH){
								inventory[1]--;
								pets.get(draygonSelected).strengthProgress+=10;
							}
							if(currentItem==ITEM_BOOST_AGILITY){
								inventory[2]--;
								pets.get(draygonSelected).agilityProgress+=10;
							}
							if(currentItem==ITEM_BOOST_IQ){
								inventory[3]--;
								pets.get(draygonSelected).intelligenceProgress+=10;
							}
							inPetSelectionScreen=false;
						}
					}

					}
				}else{
					if(!mouseAlreadyPressed){
						if(MouseInput.clickX*2 >= 1400 && MouseInput.clickX*2 <= 1900 &&
								MouseInput.clickY*2 >= 700 && MouseInput.clickY*2 <= 800){
							if(pets.get(draygonSelected).name.length()>0){
								pets.get(draygonSelected).name = pets.get(draygonSelected).name.substring(0, pets.get(draygonSelected).name.length()-1);
								mouseAlreadyPressed=true;
							}
						}

						if(MouseInput.clickX*2 >= 1400 && MouseInput.clickX*2 <= 1900 &&
								MouseInput.clickY*2 >= 820 && MouseInput.clickY*2 <= 920){
							inPetNamingScreen=false;
							petsActive=true;
						}

						for(int i=0; i<nameButtons.size(); i++){
							if(MouseInput.clickX*2 >= nameButtons.get(i).x && MouseInput.clickX*2 <=nameButtons.get(i).x+nameButtons.get(i).width &&
									MouseInput.clickY*2 >= nameButtons.get(i).y && MouseInput.clickY*2 <= nameButtons.get(i).y+nameButtons.get(i).height){
								if(pets.get(draygonSelected).name.length()<10){
									pets.get(draygonSelected).name = pets.get(draygonSelected).name + Character.toString(nameButtons.get(i).value);
								}


								mouseAlreadyPressed=true;
							}
						}
					}

				}
			}


			if(petsActive){
				for(int i=0; i<pets.size(); i++){
					pets.get(i).input();
				}
			}

		}else if(currentScreen == MINIGAME_BRICK_BURST){
			brickburst.input();
		}/*else if(currentScreen == MINIGAME_SHOWDOWN){
			showdown.input();
		}*/else if(currentScreen == COMPETITION_RACE){
			race.input();
		}else{
			if(MouseInput.leftClicked){
				Game.screenToWarp = Game.DRAYGON_HABITAT;
				Game.fadingOut = true;
			}
		}

	}

	public void update(){
		if(currentScreen==0){
			if(petsActive){
				for(int i=0; i<pets.size(); i++){
					pets.get(i).update();
				}
				if(minuteTimer==0){
					minuteTimer = MINUTE;
					for(int i=0; i<pets.size(); i++){
						if(pets.get(i).inEgg){
							pets.get(i).eggTime--;
							if(pets.get(i).eggTime<=0){
								pets.get(i).inEgg=false;
							}
						}else{
							pets.get(i).lifeRemaining--;
							if(pets.get(i).lifeRemaining<=0 && !pets.get(i).isImmortal){
								pets.remove(i);
							}
						}
					}
				}

			}else{
				minuteTimer--;
			}
		}else if(currentScreen == MINIGAME_BRICK_BURST){
			brickburst.update();
		}/*else if(currentScreen == MINIGAME_SHOWDOWN){
			showdown.update();
		}*/else if(currentScreen == COMPETITION_RACE){
			race.update();
		}

		if(fadingIn){
			if(fadeAlpha>0){
				fadeAlpha-=5;
			}else{
				fadingIn=false;
			}
		}

		if(fadingOut){
			if(fadeAlpha<255){
				fadeAlpha+=5;
			}else{
				fadingOut=false;
				fadingIn=true;
				if(screenToWarp==DRAYGON_HABITAT){
					petsActive=true;
					music1.loop();
					music2.stop();
				}else if(screenToWarp==MINIGAME_BRICK_BURST){
					brickburst = new BrickBurstController(pets.get(draygonSelected));
					music1.stop();
					music2.loop();
				}/*else if(screenToWarp==MINIGAME_SHOWDOWN){
					showdown = new ShowdownController();
					//music1.stop();
				}*/else if(screenToWarp==COMPETITION_RACE){
					race = new RaceController(pets.get(draygonSelected));
					music1.stop();
					music2.loop();
				}else{
					music1.stop();
				}
				currentScreen = screenToWarp;

			}
		}

		if(MouseInput.leftClicked==false){
			mouseAlreadyPressed=false;
		}

	}

	public void render(Graphics g, Component c){
		Graphics2D g2 = (Graphics2D) screen.getGraphics();
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(0, 0, 1920, 1080);

		g2.setFont(GUIFont);

		//GameObject render
		//if(petsActive){
		if(currentScreen == 0){
			for(int i=0; i<pets.size(); i++){
				pets.get(i).render(g2, c);
			}
			//}

			if(isDraygonSelected){
				g2.setColor(Color.GRAY);
				g2.fillRect(1300, 20, 600, 1040);
				g2.setColor(Color.DARK_GRAY);
				g2.fillRect(1380, 47, 440, 75);
				g2.setColor(Color.WHITE);
				g2.drawString(pets.get(draygonSelected).name, 1400, 100);
				g2.drawString("Speed", 1500, 200);
				g2.drawString("Strength", 1500, 350);
				g2.drawString("Agility", 1500, 500);
				g2.drawString("Intelligence", 1400, 650);
				g2.drawString("Stamina", 1500, 800);
				//g2.drawString("Hunger", 1500, 950);
				//g2.drawString("Thirst", 1500, 1100);

				String speedRank = pets.get(draygonSelected).getGrade(0);
				g2.drawString(speedRank, 1330, 270);
				g2.drawString(Short.toString(pets.get(draygonSelected).speed), 1720, 270);

				String strengthRank = pets.get(draygonSelected).getGrade(1);
				g2.drawString(strengthRank, 1330, 420);
				g2.drawString(Short.toString(pets.get(draygonSelected).strength), 1720, 420);

				String agilityRank = pets.get(draygonSelected).getGrade(2);
				g2.drawString(agilityRank, 1330, 570);
				g2.drawString(Short.toString(pets.get(draygonSelected).agility), 1720, 570);

				String intelligenceRank = pets.get(draygonSelected).getGrade(3);
				g2.drawString(intelligenceRank, 1330, 720);
				g2.drawString(Short.toString(pets.get(draygonSelected).intelligence), 1720, 720);

				String staminaRank = pets.get(draygonSelected).getGrade(4);
				g2.drawString(staminaRank, 1330, 870);
				g2.drawString(Short.toString(pets.get(draygonSelected).stamina), 1720, 870);

				g2.setColor(Color.BLACK);
				g2.fillRect(1400, 220, 300, 75);
				g2.fillRect(1400, 370, 300, 75);
				g2.fillRect(1400, 520, 300, 75);
				g2.fillRect(1400, 670, 300, 75);
				g2.fillRect(1400, 820, 300, 75);
				//g2.fillRect(1400, 970, 300, 75);

				g2.setColor(Color.DARK_GRAY);
				g2.fillRect(1402, 222, 296, 71);
				g2.fillRect(1402, 372, 296, 71);
				g2.fillRect(1402, 522, 296, 71);
				g2.fillRect(1402, 672, 296, 71);
				g2.fillRect(1402, 822, 296, 71);
				//g2.fillRect(1402, 972, 296, 71);

				g2.setColor(Color.GREEN);
				g2.fillRect(1402, 222, (int)((296f/100f)*pets.get(draygonSelected).speedProgress), 71);
				g2.fillRect(1402, 372, (int)((296f/100f)*pets.get(draygonSelected).strengthProgress), 71);
				g2.fillRect(1402, 522, (int)((296f/100f)*pets.get(draygonSelected).agilityProgress), 71);
				g2.fillRect(1402, 672, (int)((296f/100f)*pets.get(draygonSelected).intelligenceProgress), 71);
				g2.fillRect(1402, 822, (int)((296f/100f)*pets.get(draygonSelected).staminaProgress), 71);
				//g2.fillRect(1402, 972, (int)((296f/100f)*pets.get(draygonSelected).hunger), 71);
			}else{
				g2.setColor(Color.GRAY);
				g2.fillRect(0, 0, 400, 75);
				g2.fillRect(1520, 0, 400, 75);
				g2.fillRect(770, 0, 400, 75);
				g2.fillRect(0, 1005, 400, 75);
				if(inMinigamesMenu){
					g2.fillRect(1300, 75, 620, 1005);
					g2.setColor(Color.DARK_GRAY);
					g2.fillRect(1320, 170, 580, 100);
					g2.fillRect(1320, 290, 580, 100);
					g2.fillRect(1320, 410, 580, 100);
				}
				if(inCompetitionsMenu){
					g2.fillRect(660, 75, 620, 1005);
					g2.setColor(Color.DARK_GRAY);
					g2.fillRect(680, 170, 580, 100);
					g2.fillRect(680, 290, 580, 100);
					//g2.fillRect(680, 410, 580, 100);
				}
				if(inInventory){
					g2.fillRect(0, 75, 620, 1005);
					g2.drawImage(shopItems, 100, 150, 228, 278, 0, 0, 128, 128, null);
					g2.drawImage(shopItems, 100, 350, 228, 478, 128, 0, 256, 128, null);
					g2.drawImage(shopItems, 100, 550, 228, 678, 0, 128, 128, 256, null);
					g2.drawImage(shopItems, 100, 750, 228, 878, 128, 128, 256, 256, null);
				}
				if(inPetSelectionScreen){
					g2.setColor(Color.GRAY);
					g2.fillRect(0, 150, 1920, 830);
					g2.setColor(Color.DARK_GRAY);
					g2.fillRect(1400, 750, 300, 75);
					for(int j=0; j<3; j++){
						for(int i=0; i<6; i++){
							if(draygonButtons[i][j].value < pets.size()){
								draygonButtons[i][j].render(g2, c);
							}
						}
					}
				}if(inPetNamingScreen){
					g2.setColor(Color.GRAY);
					g2.fillRect(0, 0, 1920, 1080);
					for(int i=0; i<nameButtons.size(); i++){
						nameButtons.get(i).render(g2, c);
					}
					g2.setColor(Color.DARK_GRAY);
					g2.fillRect(1400, 700, 500, 100);
					g2.fillRect(1400, 820, 500, 100);
					/*g2.drawImage(Game.draygonBodies, (int)1500+Game.draygonBodyFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentBody].offsetX*2, (int)200+Game.draygonBodyFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentBody].offsetY*2, (int)1500+(Game.draygonBodyFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentBody].offsetX*2)+(Game.draygonBodyFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentBody].width*2), (int)200+(Game.draygonBodyFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentBody].offsetY*2)+(Game.draygonBodyFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentBody].height*2), Game.draygonBodyFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentBody].x, Game.draygonBodyFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentBody].y, Game.draygonBodyFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentBody].x+Game.draygonBodyFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentBody].width, Game.draygonBodyFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentBody].y+Game.draygonBodyFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentBody].height, null);
					g2.drawImage(Game.draygonFaces, (int)1500+Game.draygonFaceFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentFace].offsetX*2, (int)200+Game.draygonFaceFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentFace].offsetY*2, (int)1500+(Game.draygonFaceFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentFace].offsetX*2)+(Game.draygonFaceFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentFace].width*2), (int)200+(Game.draygonFaceFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentFace].offsetY*2)+(Game.draygonFaceFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentFace].height*2), Game.draygonFaceFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentFace].x, Game.draygonFaceFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentFace].y, Game.draygonFaceFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentFace].x+Game.draygonFaceFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentFace].width, Game.draygonFaceFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentFace].y+Game.draygonFaceFrames[pets.get(draygonSelected).color][pets.get(draygonSelected).currentFace].height, null);

							if(pets.get(draygonSelected).gender){
								g.drawImage(Game.draygonEyes, (int)1500+Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes].offsetX*2, (int)200+Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes].offsetY*2, (int)1500+(Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes].offsetX*2)+(Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes].width*2), (int)200+(Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes].offsetY*2)+(Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes].height*2), Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes].x, Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes].y, Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes].x+Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes].width, Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes].y+Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes].height, null);
							}else{
								g.drawImage(Game.draygonEyes, (int)1500+Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes+6].offsetX*2, (int)200+Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes+6].offsetY*2, (int)1500+(Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes+6].offsetX*2)+(Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes+6].width*2), (int)200+(Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes+6].offsetY*2)+(Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes+6].height*2), Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes+6].x, Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes+6].y, Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes+6].x+Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes+6].width, Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes+6].y+Game.draygonEyeFrames[pets.get(draygonSelected).eyecolor][pets.get(draygonSelected).currentEyes+6].height, null);
							}*/
				}
				if(inShop){
					g2.setColor(Color.GRAY);
					g2.fillRect(0, 0, 1920, 1080);
					g2.setColor(Color.DARK_GRAY);
					g2.fillRect(1400, 500, 500, 100);
					g2.fillRect(1400, 620, 500, 100);
					g2.fillRect(1400, 740, 500, 100);
					if(shopMenu>0 && currentItem>0)
						g2.fillRect(500, 900, 400, 100);
					if(shopMenu==1){
						g2.drawImage(draygonEggs, 200+150, 400,  200+150+draygonEggFrames[0].width, 400+draygonEggFrames[0].height, draygonEggFrames[0].x, draygonEggFrames[0].y, draygonEggFrames[0].x+draygonEggFrames[0].width, draygonEggFrames[0].y+draygonEggFrames[0].height, null);
						g2.drawImage(draygonEggs,  200+300, 400,  200+300+draygonEggFrames[1].width, 400+draygonEggFrames[1].height, draygonEggFrames[1].x, draygonEggFrames[1].y, draygonEggFrames[1].x+draygonEggFrames[1].width, draygonEggFrames[1].y+draygonEggFrames[1].height, null);
						g2.drawImage(draygonEggs,  200+450, 400,  200+450+draygonEggFrames[2].width, 400+draygonEggFrames[2].height, draygonEggFrames[2].x, draygonEggFrames[2].y, draygonEggFrames[2].x+draygonEggFrames[2].width, draygonEggFrames[2].y+draygonEggFrames[2].height, null);
						g2.drawImage(draygonEggs,  200+600, 400,  200+600+draygonEggFrames[3].width, 400+draygonEggFrames[3].height, draygonEggFrames[3].x, draygonEggFrames[3].y, draygonEggFrames[3].x+draygonEggFrames[3].width, draygonEggFrames[3].y+draygonEggFrames[3].height, null);
						g2.drawImage(draygonEggs,  200+750, 400,  200+750+draygonEggFrames[4].width, 400+draygonEggFrames[4].height, draygonEggFrames[4].x, draygonEggFrames[4].y, draygonEggFrames[4].x+draygonEggFrames[4].width, draygonEggFrames[4].y+draygonEggFrames[4].height, null);
						g2.drawImage(draygonEggs,  200+150, 550,  200+150+draygonEggFrames[5].width, 550+draygonEggFrames[5].height, draygonEggFrames[5].x, draygonEggFrames[5].y, draygonEggFrames[5].x+draygonEggFrames[5].width, draygonEggFrames[5].y+draygonEggFrames[5].height, null);
						g2.drawImage(draygonEggs,  200+300, 550,  200+300+draygonEggFrames[6].width, 550+draygonEggFrames[6].height, draygonEggFrames[6].x, draygonEggFrames[6].y, draygonEggFrames[6].x+draygonEggFrames[6].width, draygonEggFrames[6].y+draygonEggFrames[6].height, null);
						g2.drawImage(draygonEggs,  200+450, 550,  200+450+draygonEggFrames[7].width, 550+draygonEggFrames[7].height, draygonEggFrames[7].x, draygonEggFrames[7].y, draygonEggFrames[7].x+draygonEggFrames[7].width, draygonEggFrames[7].y+draygonEggFrames[7].height, null);
						g2.drawImage(draygonEggs,  200+600, 550,  200+600+draygonEggFrames[8].width, 550+draygonEggFrames[8].height, draygonEggFrames[8].x, draygonEggFrames[8].y, draygonEggFrames[8].x+draygonEggFrames[8].width, draygonEggFrames[8].y+draygonEggFrames[8].height, null);
						g2.drawImage(draygonEggs,  200+750, 550,  200+750+draygonEggFrames[9].width, 550+draygonEggFrames[9].height, draygonEggFrames[9].x, draygonEggFrames[9].y, draygonEggFrames[9].x+draygonEggFrames[9].width, draygonEggFrames[9].y+draygonEggFrames[9].height, null);
					}
					if(shopMenu==2){
						g2.drawImage(shopItems, 350, 550, 478, 678, 0, 0, 128, 128, null);
						g2.drawImage(shopItems, 500, 550, 628, 678, 128, 0, 256, 128, null);
						g2.drawImage(shopItems, 650, 550, 778, 678, 0, 128, 128, 256, null);
						g2.drawImage(shopItems, 800, 550, 928, 678, 128, 128, 256, 256, null);
					}
				}
				g2.setColor(Color.WHITE);
				if(!inPetNamingScreen && !inShop){
					g2.drawString("Inventory", 20, 55);
					g2.drawString("Minigames", 1545, 55);
					g2.drawString("Contests", 810, 55);
					g2.drawString("Shop", 20, 1060);
					if(inMinigamesMenu && !inPetSelectionScreen){
						g2.drawString("Match", 1340, 240);
						g2.drawString("Brick Burst", 1340, 360);
						g2.drawString("Showdown", 1340, 480);
					}
					if(inCompetitionsMenu && !inPetSelectionScreen){
						g2.drawString("Race", 700, 240);
						g2.drawString("Quiz Show", 700, 360);
					}
					if(inInventory && !inPetSelectionScreen){
						g2.drawString("x " + inventory[0], 270, 230);
						g2.drawString("x " + inventory[1], 270, 430);
						g2.drawString("x " + inventory[2], 270, 630);
						g2.drawString("x " + inventory[3], 270, 830);
					}
					if(inPetSelectionScreen){
						g2.drawString("Please Choose a Draygon", 500, 240);
						g2.drawString(pets.get(draygonSelected).name, 1420, 340);
						g2.drawString("Okay!", 1422, 808);
					}
				}else{
					if(inPetNamingScreen){
						g2.setFont(WinFont);
						g2.drawString(pets.get(draygonSelected).name, 400, 150);
						g2.setFont(GUIFont);
						g2.drawString("Backspace", 1420, 770);
						g2.drawString("Okay", 1420, 890);
					}else if(inShop){
						g2.setFont(WinFont);
						g2.drawString("Draygon Shop", 650, 150);
						g2.setFont(GUIFont);
						g2.drawString("Your Coins: " + coins, 600, 200);
						g2.drawString("Eggs", 1420, 570);
						g2.drawString("Items", 1420, 690);
						g2.drawString("Exit", 1420, 810);
						if(shopMenu>0 && currentItem>0){
							g2.drawString(currentItemName, 500, 800);
							g2.drawString(Integer.toString(currentItemPrice), 500, 860);
							g2.drawString("Buy", 520, 960);
						}
					}
				}
			}
			/*}else if(currentScreen == 1){*/

		}else if(currentScreen == MINIGAME_BRICK_BURST){
			brickburst.render(g2, c);
		}/*else if(currentScreen == MINIGAME_SHOWDOWN){
			showdown.render(g2, c);
		}*/else if(currentScreen == COMPETITION_RACE){
			race.render(g2, c);
		}else{
			g2.setColor(Color.WHITE);
			g2.drawString("This screen is unfinished.", 400, 400);
			g2.drawString("Click the screen to return.", 400, 600);
		}

		if(fadingOut || fadingIn){
			g2.setColor(new Color(0, 0, 0, fadeAlpha));
			g2.fillRect(0, 0, 1920, 1080);
		}

		g.drawImage(screen, 0, 0, 960, 540, 0, 0, 1920, 1080, null);
	}

	public void paintComponent(Graphics g){
		//System.out.println("render");
		render(g, this);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning){
			input();
			update();
			repaint();
			//System.out.println("render");
			try {
				Thread.sleep(16);
				//Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.exit(0);
	}

	public static void addCoins(int coinsToAdd){
		if(coins + coinsToAdd < MAX_COINS){
			coins += coinsToAdd;
		}else{
			coins = MAX_COINS;
		}
	}

	byte[] readSmallBinaryFile(String aFileName) throws IOException {
		Path path = Paths.get(aFileName);
		return Files.readAllBytes(path);
	}

	void writeSmallBinaryFile(byte[] aBytes, String aFileName) throws IOException {
		Path path = Paths.get(aFileName);
		Files.write(path, aBytes); //creates, overwrites
	}
	
	public void loadProgress(){
		File f = new File("savedata/habitat.savld");
		if(f.exists()){
			try {
				byte[] saveData = readSmallBinaryFile(f.getPath());
				
				ByteBuffer bb = ByteBuffer.allocate(4);
				bb.order(ByteOrder.LITTLE_ENDIAN);
				bb.put(saveData[0]);
				bb.put(saveData[1]);
				bb.put(saveData[2]);
				bb.put(saveData[3]);
				coins = bb.getInt(0);
				bb = ByteBuffer.allocate(4);
				bb.order(ByteOrder.LITTLE_ENDIAN);
				bb.put(saveData[4]);
				bb.put(saveData[5]);
				bb.put(saveData[6]);
				bb.put(saveData[7]);
				inventory[0] = bb.getInt(0);
				bb = ByteBuffer.allocate(4);
				bb.order(ByteOrder.LITTLE_ENDIAN);
				bb.put(saveData[8]);
				bb.put(saveData[9]);
				bb.put(saveData[10]);
				bb.put(saveData[11]);
				inventory[1] = bb.getInt(0);
				bb = ByteBuffer.allocate(4);
				bb.order(ByteOrder.LITTLE_ENDIAN);
				bb.put(saveData[12]);
				bb.put(saveData[13]);
				bb.put(saveData[14]);
				bb.put(saveData[15]);
				inventory[2] = bb.getInt(0);
				bb = ByteBuffer.allocate(4);
				bb.order(ByteOrder.LITTLE_ENDIAN);
				bb.put(saveData[16]);
				bb.put(saveData[17]);
				bb.put(saveData[18]);
				bb.put(saveData[19]);
				inventory[3] = bb.getInt(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void loadDraygon(int ID){
		File f = new File("savedata/draygon" + Integer.toString(ID) + ".dld");
		if(f.exists()){
			try {
				byte[] draygonData = readSmallBinaryFile(f.getPath());

				String name = "";

				for(int i=0; i<10; i++){
					name = name + (char)draygonData[i];
				}

				byte motherColor = draygonData[10];
				byte fatherColor = draygonData[11];

				boolean gender;
				if(draygonData[12]==0){
					gender=false;
				}else{
					gender=true;
				}

				boolean isShiny;
				if(draygonData[13]==0){
					isShiny=false;
				}else{
					isShiny=true;
				}

				boolean isGhost;
				if(draygonData[14]==0){
					isGhost=false;
				}else{
					isGhost=true;
				}


				ByteBuffer bb = ByteBuffer.allocate(2);
				bb.order(ByteOrder.LITTLE_ENDIAN);
				bb.put(draygonData[15]);
				bb.put(draygonData[16]);
				short lifeRemaining = bb.getShort(0);

				boolean isImmortal;
				if(draygonData[17]==0){
					isImmortal=false;
				}else{
					isImmortal=true;
				}

				byte speedLevel = draygonData[18];
				bb = ByteBuffer.allocate(2);
				bb.order(ByteOrder.LITTLE_ENDIAN);
				bb.put(draygonData[19]);
				bb.put(draygonData[20]);
				short speed = bb.getShort(0);
				byte motherSpeedGrade = draygonData[21];
				byte fatherSpeedGrade = draygonData[22];
				byte speedGrade = draygonData[23];

				byte strengthLevel = draygonData[24];
				bb = ByteBuffer.allocate(2);
				bb.order(ByteOrder.LITTLE_ENDIAN);
				bb.put(draygonData[25]);
				bb.put(draygonData[26]);
				short strength = bb.getShort(0);
				byte motherStrengthGrade = draygonData[27];
				byte fatherStrengthGrade = draygonData[28];
				byte strengthGrade = draygonData[29];

				byte agilityLevel = draygonData[30];
				bb = ByteBuffer.allocate(2);
				bb.order(ByteOrder.LITTLE_ENDIAN);
				bb.put(draygonData[31]);
				bb.put(draygonData[32]);
				short agility = bb.getShort(0);
				byte motherAgilityGrade = draygonData[33];
				byte fatherAgilityGrade = draygonData[34];
				byte agilityGrade = draygonData[35];

				byte intelligenceLevel = draygonData[36];
				bb = ByteBuffer.allocate(2);
				bb.order(ByteOrder.LITTLE_ENDIAN);
				bb.put(draygonData[37]);
				bb.put(draygonData[38]);
				short intelligence = bb.getShort(0);
				byte motherIntelligenceGrade = draygonData[39];
				byte fatherIntelligenceGrade = draygonData[40];
				byte intelligenceGrade = draygonData[41];

				byte staminaLevel = draygonData[42];
				bb = ByteBuffer.allocate(2);
				bb.order(ByteOrder.LITTLE_ENDIAN);
				bb.put(draygonData[43]);
				bb.put(draygonData[44]);
				short stamina = bb.getShort(0);
				byte motherStaminaGrade = draygonData[45];
				byte fatherStaminaGrade = draygonData[46];
				byte staminaGrade = draygonData[47];

				byte hunger = draygonData[48];

				boolean inEgg;
				if(draygonData[49]==0){
					inEgg=false;
				}else{
					inEgg=true;
				}

				byte eggTime = draygonData[50];

				byte eyecolor = draygonData[51];

				byte motherEyeColor = draygonData[52];

				byte fatherEyeColor = draygonData[53];
				
				byte speedProgress = draygonData[54];
				
				byte strengthProgress = draygonData[55];
				
				byte agilityProgress = draygonData[56];
				
				byte intelligenceProgress = draygonData[57];
				
				byte staminaProgress = draygonData[58];

				Draygon d = new Draygon((float)random.nextInt(areaWidth-128), (float)random.nextInt(areaHeight-128), name, motherColor, 
						fatherColor, gender, isShiny, 
						isGhost, lifeRemaining, isImmortal,
						speedLevel, speed, motherSpeedGrade, fatherSpeedGrade, speedGrade,
						strengthLevel, strength, motherStrengthGrade, fatherStrengthGrade, strengthGrade,
						agilityLevel, agility, motherAgilityGrade, fatherAgilityGrade, agilityGrade,
						intelligenceLevel, intelligence, motherIntelligenceGrade, fatherIntelligenceGrade, intelligenceGrade,
						staminaLevel, stamina, motherStaminaGrade, fatherStaminaGrade, staminaGrade,
						hunger, inEgg, eggTime, eyecolor, motherEyeColor, fatherEyeColor,
						speedProgress, strengthProgress, agilityProgress, intelligenceProgress, staminaProgress);

				pets.add(d);

				//String name = nameData.toString();
				//System.out.println(name);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(ID==0){
			pets.add(new Draygon((byte)random.nextInt(Constants.COLOR_PINK+1)));
			pets.get(0).inEgg=false;
			pets.get(0).eggTime=0;
		}
	}

	public void loadDraygons(){
		for(int i=0; i<18; i++){
			loadDraygon(i);
		}
		loadProgress();
	}
	
	public void saveProgress(){
		byte[] saveData = new byte[20];
		
		saveData[0] = (byte)(coins & 0xff);
		saveData[1] = (byte)((coins >> 8) & 0xff);
		saveData[2] = (byte)((coins >> 16) & 0xff);
		saveData[3] = (byte)((coins >> 24) & 0xff);
		
		saveData[4] = (byte)(inventory[0] & 0xff);
		saveData[5] = (byte)((inventory[0] >> 8) & 0xff);
		saveData[6] = (byte)((inventory[0] >> 16) & 0xff);
		saveData[7] = (byte)((inventory[0] >> 24) & 0xff);
		
		saveData[8] = (byte)(inventory[1] & 0xff);
		saveData[9] = (byte)((inventory[1] >> 8) & 0xff);
		saveData[10] = (byte)((inventory[1] >> 16) & 0xff);
		saveData[11] = (byte)((inventory[1] >> 24) & 0xff);
		
		saveData[12] = (byte)(inventory[2] & 0xff);
		saveData[13] = (byte)((inventory[2] >> 8) & 0xff);
		saveData[14] = (byte)((inventory[2] >> 16) & 0xff);
		saveData[15] = (byte)((inventory[2] >> 24) & 0xff);
		
		saveData[16] = (byte)(inventory[3] & 0xff);
		saveData[17] = (byte)((inventory[3] >> 8) & 0xff);
		saveData[18] = (byte)((inventory[3] >> 16) & 0xff);
		saveData[19] = (byte)((inventory[3] >> 24) & 0xff);
		
		File f = new File("savedata/habitat.savld");
		try {
			writeSmallBinaryFile(saveData, f.getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveDraygon(int ID){
		byte[] draygonData = new byte[59];
		String str = pets.get(ID).name;
		byte[] name = str.getBytes();
		if(name.length<10){
			int temp = 20-name.length/2;
			for(int i=0; i<temp; i++){
				str = str + " ";
			}
			name = str.getBytes();
		}
		for(int i=0; i<10; i++){
			if(i<name.length){
				draygonData[i]=name[i];
			}
		}

		draygonData[10] = pets.get(ID).motherColor;
		draygonData[11] = pets.get(ID).fatherColor;

		if(pets.get(ID).gender){
			draygonData[12] = 1;
		}else{
			draygonData[12] = 0;
		}

		if(pets.get(ID).isShiny){
			draygonData[13] = 1;
		}else{
			draygonData[13] = 0;
		}

		if(pets.get(ID).isGhost){
			draygonData[14] = 1;
		}else{
			draygonData[14] = 0;
		}

		//draygonData[15] = pets.get(ID).lifeRemaining;

		draygonData[15] = (byte)(pets.get(ID).lifeRemaining & 0xff);
		draygonData[16] = (byte)((pets.get(ID).lifeRemaining >> 8) & 0xff);

		if(pets.get(ID).isImmortal){
			draygonData[17] = 1;
		}else{
			draygonData[17] = 0;
		}

		draygonData[18] = pets.get(ID).speedLevel;
		draygonData[19] = (byte)(pets.get(ID).speed & 0xff);
		draygonData[20] = (byte)((pets.get(ID).speed >> 8) & 0xff);
		draygonData[21] = pets.get(ID).motherSpeedGrade;
		draygonData[22] = pets.get(ID).fatherSpeedGrade;
		draygonData[23] = pets.get(ID).speedGrade;

		draygonData[24] = pets.get(ID).strengthLevel;
		draygonData[25] = (byte)(pets.get(ID).strength & 0xff);
		draygonData[26] = (byte)((pets.get(ID).strength >> 8) & 0xff);
		draygonData[27] = pets.get(ID).motherStrengthGrade;
		draygonData[28] = pets.get(ID).fatherStrengthGrade;
		draygonData[29] = pets.get(ID).strengthGrade;

		draygonData[30] = pets.get(ID).agilityLevel;
		draygonData[31] = (byte)(pets.get(ID).agility & 0xff);
		draygonData[32] = (byte)((pets.get(ID).agility >> 8) & 0xff);
		draygonData[33] = pets.get(ID).motherAgilityGrade;
		draygonData[34] = pets.get(ID).fatherAgilityGrade;
		draygonData[35] = pets.get(ID).agilityGrade;

		draygonData[36] = pets.get(ID).intelligenceLevel;
		draygonData[37] = (byte)(pets.get(ID).intelligence & 0xff);
		draygonData[38] = (byte)((pets.get(ID).intelligence >> 8) & 0xff);
		draygonData[39] = pets.get(ID).motherIntelligenceGrade;
		draygonData[40] = pets.get(ID).fatherIntelligenceGrade;
		draygonData[41] = pets.get(ID).intelligenceGrade;

		draygonData[42] = pets.get(ID).staminaLevel;
		draygonData[43] = (byte)(pets.get(ID).stamina & 0xff);
		draygonData[44] = (byte)((pets.get(ID).stamina >> 8) & 0xff);
		draygonData[45] = pets.get(ID).motherStaminaGrade;
		draygonData[46] = pets.get(ID).fatherStaminaGrade;
		draygonData[47] = pets.get(ID).staminaGrade;

		draygonData[48] = pets.get(ID).hunger;

		if(pets.get(ID).inEgg){
			draygonData[49] = 1;
		}else{
			draygonData[49] = 0;
		}

		draygonData[50] = pets.get(ID).eggTime;

		draygonData[51] = pets.get(ID).eyecolor;

		draygonData[52] = pets.get(ID).motherEyeColor;

		draygonData[53] = pets.get(ID).fatherEyeColor;
		
		draygonData[54] = pets.get(ID).speedProgress;
		
		draygonData[55] = pets.get(ID).strengthProgress;
		
		draygonData[56] = pets.get(ID).agilityProgress;
		
		draygonData[57] = pets.get(ID).intelligenceProgress;
		
		draygonData[58] = pets.get(ID).staminaProgress;


		File f = new File("savedata/draygon" + Integer.toString(ID) + ".dld");
		try {
			writeSmallBinaryFile(draygonData, f.getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveDraygons(){
		for(int i=0; i<pets.size(); i++){
			saveDraygon(i);
		}
		saveProgress();
	}

}
