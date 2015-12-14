package com.negativespace.ld34;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;

import com.negativespace.ld34.brickburst.BrickBurstController;

public class Draygon extends GameObject{

	public static final float moveSpeed = 2f;

	public String name;

	public byte color;
	public byte motherColor;
	public byte fatherColor;

	public byte eyecolor;
	public byte motherEyeColor;
	public byte fatherEyeColor;

	public boolean isShiny;

	public boolean gender;

	public boolean isGhost;

	public short lifeRemaining=1200;

	public boolean isImmortal;

	public int currentEyes=0, currentFace, currentBody;

	public short speed, strength, agility, intelligence, stamina;
	public byte speedLevel, strengthLevel, agilityLevel, intelligenceLevel, staminaLevel;
	public byte speedGrade, strengthGrade, agilityGrade, intelligenceGrade, staminaGrade;
	public byte motherSpeedGrade, motherStrengthGrade, motherAgilityGrade, motherIntelligenceGrade, motherStaminaGrade;
	public byte fatherSpeedGrade, fatherStrengthGrade, fatherAgilityGrade, fatherIntelligenceGrade, fatherStaminaGrade;
	public byte speedProgress, strengthProgress, agilityProgress, intelligenceProgress, staminaProgress;
	public static final byte F_RANK = 0;
	public static final byte D_RANK = 1;
	public static final byte C_RANK = 2;
	public static final byte B_RANK = 3;
	public static final byte A_RANK = 4;
	public static final byte STAR_RANK = 5;

	public static final byte F_RANK_LEVEL_UP = 5;
	public static final byte D_RANK_LEVEL_UP = 8;
	public static final byte C_RANK_LEVEL_UP = 11;
	public static final byte B_RANK_LEVEL_UP = 15;
	public static final byte A_RANK_LEVEL_UP = 19;
	public static final byte STAR_RANK_LEVEL_UP = 25;

	public byte element;
	public static final byte ELEMENT_FIRE = 0;
	public static final byte ELEMENT_ICE = 1;
	public static final byte ELEMENT_WATER = 2;
	public static final byte ELEMENT_GROUND = 3;
	public static final byte ELEMENT_AIR = 4;
	public static final byte ELEMENT_LIGHT = 5;
	public static final byte ELEMENT_DARKNESS = 6;
	public static final byte ELEMENT_LIFE = 7;
	public static final byte ELEMENT_DEATH = 8;
	public static final byte ELEMENT_POISON = 9;
	public static final byte ELEMENT_THUNDER = 10;

	public byte hunger, thirst;

	public byte state;
	public static final byte STATE_STANDING = 0;
	public static final byte STATE_WANDERING = 1;
	public static final byte STATE_SWIMMING = 2;
	public static final byte STATE_MOVETOTARGET = 3;
	public static final byte STATE_SINGING = 4;
	public int stateTimer=100;
	public byte moveDirection;
	public float targetX, targetY;

	public static final int MINIMUM_STANDING_TIME = 20;
	public static final int MAXIMUM_STANDING_TIME = 400;

	public static final byte DIRECTION_NORTH = 0;
	public static final byte DIRECTION_NORTHEAST = 1;
	public static final byte DIRECTION_EAST = 2;
	public static final byte DIRECTION_SOUTHEAST = 3;
	public static final byte DIRECTION_SOUTH = 4;
	public static final byte DIRECTION_SOUTHWEST = 5;
	public static final byte DIRECTION_WEST = 6;
	public static final byte DIRECTION_NORTHWEST = 7;
	public static final int MINIMUM_WANDER_TIME = 20;
	public static final int MAXIMUM_WANDER_TIME = 400;

	public boolean inEgg = false;
	public byte eggTime = 0;

	public Draygon(float x, float y, String name, byte color){
		super(x, y, 128, 128);
		this.name = name;
		this.color = color;
		this.state = STATE_STANDING;
		this.moveDirection = 4;
		this.speedProgress = 10;
		this.agilityProgress = 20;
		this.strengthProgress = 30;
		this.intelligenceProgress = 40;
		this.staminaProgress = 50;
		this.speed=260;
	}

	public Draygon(byte color){
		super(Game.random.nextInt(Game.areaWidth), Game.random.nextInt(Game.areaHeight), 128, 128);
		this.name="";
		this.color = color;
		this.motherColor = color;
		this.fatherColor = color;
		this.gender=Game.random.nextBoolean();
		this.isShiny=false;
		this.isGhost=false;
		this.lifeRemaining=1200;
		this.isImmortal=false;
		this.speedLevel=1;
		this.speed=0;
		this.speedGrade=(byte)Game.random.nextInt(STAR_RANK+1);
		this.motherSpeedGrade=speedGrade;
		this.fatherSpeedGrade=speedGrade;
		this.strengthLevel=1;
		this.strength=0;
		this.strengthGrade=(byte)Game.random.nextInt(STAR_RANK+1);
		this.motherStrengthGrade=strengthGrade;
		this.fatherStrengthGrade=strengthGrade;
		this.agilityLevel=1;
		this.agility=0;
		this.agilityGrade=(byte)Game.random.nextInt(STAR_RANK+1);
		this.motherAgilityGrade=agilityGrade;
		this.fatherAgilityGrade=agilityGrade;
		this.intelligenceLevel=1;
		this.intelligence=0;
		this.intelligenceGrade=(byte)Game.random.nextInt(STAR_RANK+1);
		this.motherIntelligenceGrade=intelligenceGrade;
		this.fatherIntelligenceGrade=intelligenceGrade;
		this.staminaLevel=1;
		this.stamina=0;
		this.staminaGrade=(byte)Game.random.nextInt(STAR_RANK+1);
		this.motherStaminaGrade=staminaGrade;
		this.fatherStaminaGrade=staminaGrade;
		this.hunger=0;
		this.inEgg = true;
		this.eggTime = 1;
		this.eyecolor = (byte)Game.random.nextInt(Constants.COLOR_PINK+1);
		this.motherEyeColor = eyecolor;
		this.fatherEyeColor = eyecolor;
	}

	public Draygon(float x, float y, byte color){
		super(x, y, 128, 128);
		this.name = "";
		this.color = color;
		this.motherColor = color;
		this.fatherColor = color;

		int i = Game.random.nextInt(2);
		if(i==0){
			this.gender = false;
		}else{
			this.gender = true;
		}
		this.element = (byte)Game.random.nextInt(ELEMENT_THUNDER+1);
		this.state = STATE_STANDING;
		this.speedGrade = (byte)Game.random.nextInt(STAR_RANK+1);
		this.strengthGrade = (byte)Game.random.nextInt(STAR_RANK+1);
		this.agilityGrade = (byte)Game.random.nextInt(STAR_RANK+1);
		this.intelligenceGrade = (byte)Game.random.nextInt(STAR_RANK+1);
		this.staminaGrade = (byte)Game.random.nextInt(STAR_RANK+1);
	}

	public Draygon(float x, float y, String name, byte motherColor, 
			byte fatherColor, boolean gender, boolean isShiny, 
			boolean isGhost, short lifeRemaining, boolean isImmortal,
			byte speedLevel, short speed, byte motherSpeedGrade, byte fatherSpeedGrade, byte speedGrade,
			byte strengthLevel, short strength, byte motherStrengthGrade, byte fatherStrengthGrade, byte strengthGrade,
			byte agilityLevel, short agility, byte motherAgilityGrade, byte fatherAgilityGrade, byte agilityGrade,
			byte intelligenceLevel, short intelligence, byte motherIntelligenceGrade, byte fatherIntelligenceGrade, byte intelligenceGrade,
			byte staminaLevel, short stamina, byte motherStaminaGrade, byte fatherStaminaGrade, byte staminaGrade,
			byte hunger, boolean inEgg, byte eggTime, byte eyecolor, byte motherEyeColor, byte fatherEyeColor,
			byte speedProgress, byte strengthProgress, byte agilityProgress, byte intelligenceProgress, byte staminaProgress){
		super(x, y, 128, 128);
		this.name=name;
		this.motherColor=motherColor;
		this.fatherColor=fatherColor;
		this.color = determineColor(motherColor, fatherColor);
		this.gender=gender;
		this.isShiny=isShiny;
		this.isGhost=isGhost;
		this.lifeRemaining=lifeRemaining;
		this.isImmortal=isImmortal;
		this.speedLevel=speedLevel;
		this.speed=speed;
		this.motherSpeedGrade=motherSpeedGrade;
		this.fatherSpeedGrade=fatherSpeedGrade;
		this.speedGrade=speedGrade;
		this.strengthLevel=strengthLevel;
		this.strength=strength;
		this.motherStrengthGrade=motherStrengthGrade;
		this.fatherStrengthGrade=fatherStrengthGrade;
		this.strengthGrade=strengthGrade;
		this.agilityLevel=agilityLevel;
		this.agility=agility;
		this.motherAgilityGrade=motherAgilityGrade;
		this.fatherAgilityGrade=fatherAgilityGrade;
		this.agilityGrade=agilityGrade;
		this.intelligenceLevel=intelligenceLevel;
		this.intelligence=intelligence;
		this.motherIntelligenceGrade=motherIntelligenceGrade;
		this.fatherIntelligenceGrade=fatherIntelligenceGrade;
		this.intelligenceGrade=intelligenceGrade;
		this.staminaLevel=staminaLevel;
		this.stamina=stamina;
		this.motherStaminaGrade=motherStaminaGrade;
		this.fatherStaminaGrade=fatherStaminaGrade;
		this.staminaGrade=staminaGrade;
		this.hunger=hunger;
		this.inEgg=inEgg;
		this.eggTime=eggTime;
		this.eyecolor=eyecolor;
		this.motherEyeColor=motherEyeColor;
		this.fatherEyeColor=fatherEyeColor;
		this.speedProgress=speedProgress;
		this.strengthProgress=strengthProgress;
		this.agilityProgress=agilityProgress;
		this.intelligenceProgress=intelligenceProgress;
		this.staminaProgress=staminaProgress;
	}

	@Override
	public void update(){
		//this.x++;
		switch(state){
		case STATE_STANDING:
			if(!inEgg){
			if(stateTimer<=0){
				moveDirection = (byte)Game.random.nextInt(DIRECTION_NORTHWEST+1);
				if(x<=0){
					moveDirection = DIRECTION_EAST;
				}
				if(y<=0){
					moveDirection = DIRECTION_SOUTH;
				}
				if(x>=Game.areaWidth-width){
					moveDirection = DIRECTION_WEST;
				}
				if(y>=Game.areaHeight-height){
					moveDirection = DIRECTION_NORTH;
				}
				stateTimer = Game.random.nextInt(MAXIMUM_WANDER_TIME-MINIMUM_WANDER_TIME)+MINIMUM_WANDER_TIME;
				state = STATE_WANDERING;
			}else{
				stateTimer--;
			}
			}
			break;

		case STATE_WANDERING:
		case STATE_SWIMMING:
			if(x<=0 || y<=0 || x>=Game.areaWidth-width || y>=Game.areaHeight-height){
				stateTimer = Game.random.nextInt(MAXIMUM_STANDING_TIME-MINIMUM_STANDING_TIME)+MINIMUM_STANDING_TIME;
				state=STATE_STANDING;
			}
			switch(moveDirection){
			case DIRECTION_NORTH:
				y-=moveSpeed;
				break;
			case DIRECTION_NORTHEAST:
				y-=Math.sin(moveSpeed);
				x+=Math.sin(moveSpeed);
				break;
			case DIRECTION_EAST:
				x+=moveSpeed;
				break;
			case DIRECTION_SOUTHEAST:
				y+=Math.sin(moveSpeed);
				x+=Math.sin(moveSpeed);
				break;
			case DIRECTION_SOUTH:
				y+=moveSpeed;
				break;
			case DIRECTION_SOUTHWEST:
				y+=Math.sin(moveSpeed);
				x-=Math.sin(moveSpeed);
				break;
			case DIRECTION_WEST:
				x-=moveSpeed;
				break;
			case DIRECTION_NORTHWEST:
				y-=Math.sin(moveSpeed);
				x-=Math.sin(moveSpeed);
				break;
			}

			stateTimer--;

			if(stateTimer<=0){
				stateTimer = Game.random.nextInt(MAXIMUM_STANDING_TIME-MINIMUM_STANDING_TIME)+MINIMUM_STANDING_TIME;
				state=STATE_STANDING;
			}

			break;

		case STATE_MOVETOTARGET:
			if(x<targetX && y<targetY && Math.abs(x-targetX) > moveSpeed && Math.abs(y-targetY) > moveSpeed){
				y+=Math.sin(moveSpeed);
				x+=Math.sin(moveSpeed);
			}else if(x>targetX && y<targetY && Math.abs(x-targetX) > moveSpeed && Math.abs(y-targetY) > moveSpeed){
				y+=Math.sin(moveSpeed);
				x-=Math.sin(moveSpeed);
			}else if(x<targetX && y>targetY && Math.abs(x-targetX) > moveSpeed && Math.abs(y-targetY) > moveSpeed){
				y-=Math.sin(moveSpeed);
				x+=Math.sin(moveSpeed);
			}else if(x>targetX && y>targetY && Math.abs(x-targetX) > moveSpeed && Math.abs(y-targetY) > moveSpeed){
				y-=Math.sin(moveSpeed);
				x-=Math.sin(moveSpeed);
			}else if(x<targetX && Math.abs(x-targetX) > moveSpeed){
				x+=moveSpeed;
			}else if(x>targetX && Math.abs(x-targetX) > moveSpeed){
				x-=moveSpeed;
			}else if(y<targetY && Math.abs(y-targetY) > moveSpeed){
				y+=moveSpeed;
			}else if(y>targetY && Math.abs(y-targetY) > moveSpeed){
				y-=moveSpeed;
			}else{
				x=targetX;
				y=targetY;
			}
			if(x==targetX && y==targetY){
				stateTimer = Game.random.nextInt(MAXIMUM_STANDING_TIME-MINIMUM_STANDING_TIME)+MINIMUM_STANDING_TIME;
				state = STATE_STANDING;
			}
			break;

		default:

			break;
		}

		if(x<0){
			x=0;
		}

		if(y<0){
			y=0;
		}

		if(x>Game.areaWidth-width){
			x=Game.areaWidth-width;
		}

		if(y>Game.areaHeight-height){
			y=Game.areaHeight-height;
		}
		
		if(speedProgress>=100 && speedLevel<100){
			speedLevel+=1;
			switch(speedGrade){
			case F_RANK:
				speed+=F_RANK_LEVEL_UP;
				break;
			case D_RANK:
				speed+=D_RANK_LEVEL_UP;
				break;
			case C_RANK:
				speed+=C_RANK_LEVEL_UP;
				break;
			case B_RANK:
				speed+=B_RANK_LEVEL_UP;
				break;
			case A_RANK:
				speed+=A_RANK_LEVEL_UP;
				break;
			case STAR_RANK:
				speed+=STAR_RANK_LEVEL_UP;
				break;
				default:
					speed+=5;
					break;
			}
			speedProgress-=100;
		}
		
		if(strengthProgress>=100 && strengthLevel<100){
			strengthLevel+=1;
			switch(strengthGrade){
			case F_RANK:
				strength+=F_RANK_LEVEL_UP;
				break;
			case D_RANK:
				strength+=D_RANK_LEVEL_UP;
				break;
			case C_RANK:
				strength+=C_RANK_LEVEL_UP;
				break;
			case B_RANK:
				strength+=B_RANK_LEVEL_UP;
				break;
			case A_RANK:
				strength+=A_RANK_LEVEL_UP;
				break;
			case STAR_RANK:
				strength+=STAR_RANK_LEVEL_UP;
				break;
				default:
					strength+=5;
					break;
			}
			strengthProgress-=100;
		}
		
		if(agilityProgress>=100 && agilityLevel<100){
			agilityLevel+=1;
			switch(agilityGrade){
			case F_RANK:
				agility+=F_RANK_LEVEL_UP;
				break;
			case D_RANK:
				agility+=D_RANK_LEVEL_UP;
				break;
			case C_RANK:
				agility+=C_RANK_LEVEL_UP;
				break;
			case B_RANK:
				agility+=B_RANK_LEVEL_UP;
				break;
			case A_RANK:
				agility+=A_RANK_LEVEL_UP;
				break;
			case STAR_RANK:
				agility+=STAR_RANK_LEVEL_UP;
				break;
				default:
					agility+=5;
					break;
			}
			agilityProgress-=100;
		}
		
		if(intelligenceProgress>=100 && intelligenceLevel<100){
			intelligenceLevel+=1;
			switch(intelligenceGrade){
			case F_RANK:
				intelligence+=F_RANK_LEVEL_UP;
				break;
			case D_RANK:
				intelligence+=D_RANK_LEVEL_UP;
				break;
			case C_RANK:
				intelligence+=C_RANK_LEVEL_UP;
				break;
			case B_RANK:
				intelligence+=B_RANK_LEVEL_UP;
				break;
			case A_RANK:
				intelligence+=A_RANK_LEVEL_UP;
				break;
			case STAR_RANK:
				intelligence+=STAR_RANK_LEVEL_UP;
				break;
				default:
					intelligence+=5;
					break;
			}
			intelligenceProgress-=100;
		}

	}

	@Override
	public void render(Graphics2D g, Component c){
		switch(color){
		case Constants.COLOR_RED:
			g.setColor(Color.RED);
			break;
		case Constants.COLOR_ORANGE:
			g.setColor(Color.ORANGE);
			break;
		case Constants.COLOR_YELLOW:
			g.setColor(Color.YELLOW);
			break;
		case Constants.COLOR_GREEN:
			g.setColor(Color.GREEN);
			break;
		case Constants.COLOR_BLUE:
			g.setColor(Color.BLUE);
			break;
		case Constants.COLOR_PURPLE:
			g.setColor(Color.CYAN);
			break;
		case Constants.COLOR_BLACK:
			g.setColor(Color.BLACK);
			break;
		case Constants.COLOR_WHITE:
			g.setColor(Color.WHITE);
			break;
		case Constants.COLOR_GREY:
			g.setColor(Color.GRAY);
			break;
		case Constants.COLOR_PINK:
			g.setColor(Color.PINK);
			break;
		}

		//g.fillRect((int)x, (int)y, width, height);
		if(inEgg){
			g.drawImage(Game.draygonEggs, (int)x, (int)y, (int)x+Game.draygonEggFrames[color].width, (int)y+Game.draygonEggFrames[color].height, Game.draygonEggFrames[color].x, Game.draygonEggFrames[color].y, Game.draygonEggFrames[color].x+Game.draygonEggFrames[color].width, Game.draygonEggFrames[color].y+Game.draygonEggFrames[color].height, null);
		}else{
			g.drawImage(Game.draygonBodies, (int)x+Game.draygonBodyFrames[color][currentBody].offsetX, (int)y+Game.draygonBodyFrames[color][currentBody].offsetY, (int)x+Game.draygonBodyFrames[color][currentBody].offsetX+Game.draygonBodyFrames[color][currentBody].width, (int)y+Game.draygonBodyFrames[color][currentBody].offsetY+Game.draygonBodyFrames[color][currentBody].height, Game.draygonBodyFrames[color][currentBody].x, Game.draygonBodyFrames[color][currentBody].y, Game.draygonBodyFrames[color][currentBody].x+Game.draygonBodyFrames[color][currentBody].width, Game.draygonBodyFrames[color][currentBody].y+Game.draygonBodyFrames[color][currentBody].height, null);
			g.drawImage(Game.draygonFaces, (int)x+Game.draygonFaceFrames[color][currentFace].offsetX, (int)y+Game.draygonFaceFrames[color][currentFace].offsetY, (int)x+Game.draygonFaceFrames[color][currentFace].offsetX+Game.draygonFaceFrames[color][currentFace].width, (int)y+Game.draygonFaceFrames[color][currentFace].offsetY+Game.draygonFaceFrames[color][currentFace].height, Game.draygonFaceFrames[color][currentFace].x, Game.draygonFaceFrames[color][currentFace].y, Game.draygonFaceFrames[color][currentFace].x+Game.draygonFaceFrames[color][currentFace].width, Game.draygonFaceFrames[color][currentFace].y+Game.draygonFaceFrames[color][currentFace].height, null);

			if(currentEyes==3){
				if(gender){
					g.drawImage(Game.draygonEyes, (int)x+Game.draygonEyeFrames[color][currentEyes].offsetX, (int)y+Game.draygonEyeFrames[color][currentEyes].offsetY, (int)x+Game.draygonEyeFrames[color][currentEyes].offsetX+Game.draygonEyeFrames[color][currentEyes].width, (int)y+Game.draygonEyeFrames[color][currentEyes].offsetY+Game.draygonEyeFrames[color][currentEyes].height, Game.draygonEyeFrames[color][currentEyes].x, Game.draygonEyeFrames[color][currentEyes].y, Game.draygonEyeFrames[color][currentEyes].x+Game.draygonEyeFrames[color][currentEyes].width, Game.draygonEyeFrames[color][currentEyes].y+Game.draygonEyeFrames[color][currentEyes].height, null);
				}else{
					g.drawImage(Game.draygonEyes, (int)x+Game.draygonEyeFrames[color][currentEyes+6].offsetX, (int)y+Game.draygonEyeFrames[color][currentEyes+6].offsetY, (int)x+Game.draygonEyeFrames[color][currentEyes+6].offsetX+Game.draygonEyeFrames[color][currentEyes+6].width, (int)y+Game.draygonEyeFrames[color][currentEyes+6].offsetY+Game.draygonEyeFrames[color][currentEyes+6].height, Game.draygonEyeFrames[color][currentEyes+6].x, Game.draygonEyeFrames[color][currentEyes+6].y, Game.draygonEyeFrames[color][currentEyes+6].x+Game.draygonEyeFrames[color][currentEyes+6].width, Game.draygonEyeFrames[color][currentEyes+6].y+Game.draygonEyeFrames[color][currentEyes+6].height, null);
				}
				}else{
					if(gender){
						g.drawImage(Game.draygonEyes, (int)x+Game.draygonEyeFrames[eyecolor][currentEyes].offsetX, (int)y+Game.draygonEyeFrames[eyecolor][currentEyes].offsetY, (int)x+Game.draygonEyeFrames[eyecolor][currentEyes].offsetX+Game.draygonEyeFrames[eyecolor][currentEyes].width, (int)y+Game.draygonEyeFrames[eyecolor][currentEyes].offsetY+Game.draygonEyeFrames[eyecolor][currentEyes].height, Game.draygonEyeFrames[eyecolor][currentEyes].x, Game.draygonEyeFrames[eyecolor][currentEyes].y, Game.draygonEyeFrames[eyecolor][currentEyes].x+Game.draygonEyeFrames[eyecolor][currentEyes].width, Game.draygonEyeFrames[eyecolor][currentEyes].y+Game.draygonEyeFrames[eyecolor][currentEyes].height, null);
					}else{
						g.drawImage(Game.draygonEyes, (int)x+Game.draygonEyeFrames[eyecolor][currentEyes+6].offsetX, (int)y+Game.draygonEyeFrames[eyecolor][currentEyes+6].offsetY, (int)x+Game.draygonEyeFrames[eyecolor][currentEyes+6].offsetX+Game.draygonEyeFrames[eyecolor][currentEyes+6].width, (int)y+Game.draygonEyeFrames[eyecolor][currentEyes+6].offsetY+Game.draygonEyeFrames[eyecolor][currentEyes+6].height, Game.draygonEyeFrames[eyecolor][currentEyes+6].x, Game.draygonEyeFrames[eyecolor][currentEyes+6].y, Game.draygonEyeFrames[eyecolor][currentEyes+6].x+Game.draygonEyeFrames[eyecolor][currentEyes+6].width, Game.draygonEyeFrames[eyecolor][currentEyes+6].y+Game.draygonEyeFrames[eyecolor][currentEyes+6].height, null);
					}
				}
		}

	}

	public String getGrade(int stat){
		if(stat == 0){
			switch(speedGrade){
			case F_RANK:
				return "F";
			case D_RANK:
				return "D";
			case C_RANK:
				return "C";
			case B_RANK:
				return "B";
			case A_RANK:
				return "A";
			case STAR_RANK:
				return "*";

			default:
				return "null";
			}
		}
		if(stat == 1){
			switch(strengthGrade){
			case F_RANK:
				return "F";
			case D_RANK:
				return "D";
			case C_RANK:
				return "C";
			case B_RANK:
				return "B";
			case A_RANK:
				return "A";
			case STAR_RANK:
				return "*";

			default:
				return "null";
			}
		}
		if(stat == 2){
			switch(strengthGrade){
			case F_RANK:
				return "F";
			case D_RANK:
				return "D";
			case C_RANK:
				return "C";
			case B_RANK:
				return "B";
			case A_RANK:
				return "A";
			case STAR_RANK:
				return "*";

			default:
				return "null";
			}
		}
		if(stat == 3){
			switch(intelligenceGrade){
			case F_RANK:
				return "F";
			case D_RANK:
				return "D";
			case C_RANK:
				return "C";
			case B_RANK:
				return "B";
			case A_RANK:
				return "A";
			case STAR_RANK:
				return "*";

			default:
				return "null";
			}
		}
		if(stat == 4){
			switch(staminaGrade){
			case F_RANK:
				return "F";
			case D_RANK:
				return "D";
			case C_RANK:
				return "C";
			case B_RANK:
				return "B";
			case A_RANK:
				return "A";
			case STAR_RANK:
				return "*";

			default:
				return "null";
			}
		}
		return "";
	}

	public byte determineColor(byte motherColor, byte fatherColor){
		if(motherColor==fatherColor){
			return motherColor;
		}
		if((motherColor==Constants.COLOR_RED && fatherColor==Constants.COLOR_YELLOW) || (motherColor==Constants.COLOR_YELLOW && fatherColor==Constants.COLOR_RED)){
			return Constants.COLOR_ORANGE;
		}
		if((motherColor==Constants.COLOR_RED && fatherColor==Constants.COLOR_ORANGE) || (motherColor==Constants.COLOR_ORANGE && fatherColor==Constants.COLOR_RED)){
			return Constants.COLOR_RED;
		}
		if((motherColor==Constants.COLOR_ORANGE && fatherColor==Constants.COLOR_YELLOW) || (motherColor==Constants.COLOR_YELLOW && fatherColor==Constants.COLOR_ORANGE)){
			return Constants.COLOR_YELLOW;
		}
		if((motherColor==Constants.COLOR_BLUE && fatherColor==Constants.COLOR_YELLOW) || (motherColor==Constants.COLOR_YELLOW && fatherColor==Constants.COLOR_BLUE)){
			return Constants.COLOR_GREEN;
		}
		if((motherColor==Constants.COLOR_GREEN && fatherColor==Constants.COLOR_YELLOW) || (motherColor==Constants.COLOR_YELLOW && fatherColor==Constants.COLOR_GREEN)){
			return Constants.COLOR_YELLOW;
		}
		return Constants.COLOR_RED;
	}

}
