package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import Sound.*;
import java.util.Observer;
import Interfaces.IMovable;
import Interfaces.ISelectable;
import Interfaces.ICollider;
import Interfaces.IGameWorld;
import Interfaces.IIterator;
import Collection.GameCollection;
import Objects.Asteroid;
import Objects.Fixed;
import Objects.GameObject;
import Objects.Missile;
import Objects.Movable;
import Objects.NonPlayerShip;
import Objects.PlayerShip;
import Objects.SpaceStation;
import OtherForms.ClosingApp;
import OtherForms.GameOverApp;

public class GameWorld extends Observable implements IGameWorld{

	//Attributes for the class GameWorld----------------------------------------------------------------
	
	private GameCollection worldList;
	private ArrayList<GameObject> crashedList;
	private int life;
	private int score;
	private int time;
	private static int gameWidth;
	private static int gameHeight;
	private GameSound sounds;
	private boolean isPaused;
	
	//Behaviours for the class GameWorld----------------------------------------------------------------
	
	//Default Constructor for the class GameWorld 
	public GameWorld() {
		
		//Calls init to initialize the procedures for running the game
		init();
		
	}
	
	public void init() {
		
		//ArrayList of the type GameObject to store all the objects created for the game
		worldList = new GameCollection();

		//Also initializing the destroyedList for storing the crashed objects
		crashedList = new ArrayList<GameObject>();

		//Initializing the gameSound attribute
		sounds = new GameSound();
		
		//Unpausing the game
		this.setPaused(false);
		
		//Setting the scores, time and life for the start of the game
		score = 0;
		time = 0;
		life = 3;
		
		System.out.println("Initialized world for the game");
		
		callObserver();
		
	}
	
	//Method to set the observer
	public void setObserver(Observer obs) {
		
		this.addObserver(obs);
		
	}
	
	//method to call the observer
	public void callObserver() {
		
		this.setChanged();
		GameWorldProxy g = new GameWorldProxy(this);
		this.notifyObservers(g);
		
	}
	
	//Method to return the collection of Objects
	public GameCollection getObjects() {
		
		return this.worldList;
		
	}

	//Method to add an asteroid to the game
	public void addAsteroid() {
		
		Object gameObject = new Object();
		gameObject = new Asteroid();
		worldList.add((Asteroid)gameObject);
		System.out.println("New asteroid created");
		callObserver();
		
	}
	
	//Method to add a non-player ship to the game
	public void addNps() {
		Object gameObject = new Object();
		gameObject = new NonPlayerShip();
		worldList.add((NonPlayerShip)gameObject);
		System.out.println("New non player ship created");
		callObserver();
		
	}
	
	//Method to add a space-station to the game
	public void addStation() {
		Object gameObject = new Object();
		gameObject = new SpaceStation();
		worldList.add((SpaceStation)gameObject);
		System.out.println("New space station created");
		callObserver();
		
	}
	
	//Method to add a player ship to the game
	public void addPs() {
		Object gameObject = new Object();
		if(checkForPs())
			System.out.println("Error! Cannot create more than one player ship\n");			//Enforcing that only one player ship should exist at any time
		else {
			gameObject = new PlayerShip();
			worldList.add((PlayerShip)gameObject);
			System.out.println("New player ship created");
			callObserver();
		}
		
	}
	
	//Method to increase the speed of the player ship
	public void increasePsSpeed() {
		
		IIterator iterator = worldList.getIterator();
		Object tempObject = new Object();
		
		if(checkForPs()) {
			
			while(iterator.hasNext()) {
				
				tempObject = iterator.getNext();
				
				if(tempObject instanceof PlayerShip) {
					
					System.out.println("PS Speed increased");
					((PlayerShip)tempObject).increaseSpeedPs();
					
				}
				
			}
			callObserver();
		}
		else
			System.out.println("Error! Player ship not found. Create one first.\n");
		
	}
	
	//Method to decrease the speed of the player ship
	public void decreasePsSpeed() {
		
		IIterator iterator = worldList.getIterator();
		Object tempObject = new Object();
		
		if(checkForPs()) {
			
			while(iterator.hasNext()) {
				
				tempObject = iterator.getNext();
				
				if(tempObject instanceof PlayerShip) {
					
					System.out.println("PS Speed decreased");
					((PlayerShip)tempObject).decreaseSpeedPs();
					
				}
				
			}
			callObserver();
		}
		else
			System.out.println("Error! Player ship not found. Create one first.\n");
		
	}
	
	//Method to turn the Player ship left by a small amount
	public void turnPsLeft() {
		
		IIterator iterator = worldList.getIterator();
		Object tempObject = new Object();
		
		if(checkForPs()) {
			
			while(iterator.hasNext()) {
				
				tempObject = iterator.getNext();
				
				if(tempObject instanceof PlayerShip) {
					
					System.out.println("turned PS left");
					((PlayerShip)tempObject).steerLeft();
					
				}
				
			}
			callObserver();
		}
		else
			System.out.println("Error! Player ship not found. Create one first.\n");
		
		
	}
	
	//Method to turn the player ship right by a small amount
	public void turnPsRight() {
		
		IIterator iterator = worldList.getIterator();
		Object tempObject = new Object();
		
		if(checkForPs()) {
			
			while(iterator.hasNext()) {
				
				tempObject = iterator.getNext();
				
				if(tempObject instanceof PlayerShip) {
					
					System.out.println("turned PS right");
					((PlayerShip)tempObject).steerRight();
					
				}
				
			}
			callObserver();
		}
		else
			System.out.println("Error! Player ship not found. Create one first.\n");
		
		
	}
	
	//Method to let the user turn the launcher left
	public void turnLauncherLeft() {
		
		IIterator iterator = worldList.getIterator();
		Object tempObject = new Object();
		
		if(checkForPs()) {
			
			while(iterator.hasNext()) {
				
				tempObject = iterator.getNext();
				
				if(tempObject instanceof PlayerShip) {
					
					sounds.moveLauncherSound();									//Playing the sound for turning launcher
					System.out.println("Turned PS missile launcher left");
					((PlayerShip)tempObject).steerLauncherLeft();
					
				}
				
			}

			callObserver();
		}		
		else
			System.out.println("Error! Player ship not found. Create one first.\n");
		
	}
	
	//Method to let the user turn the launcher right
	public void turnLauncherRight() {
		
		IIterator iterator = worldList.getIterator();
		Object tempObject = new Object();
		
		if(checkForPs()) {
			
			while(iterator.hasNext()) {
				
				tempObject = iterator.getNext();
				
				if(tempObject instanceof PlayerShip) {
					
					sounds.moveLauncherSound();									//Playing the sound for turning launcher
					System.out.println("Turned PS missile launcher Right");
					((PlayerShip)tempObject).steerLauncherRight();
					
				}
				
			}

			callObserver();
		}		
		else
			System.out.println("Error! Player ship not found. Create one first.\n");
		
	}
	
	//Method to fire out a missile from the player ship
	public void firePs() {
		Object gameObject = new Object();
		IIterator iterator = worldList.getIterator();
		Object tempObject = new Object();
		
		if(checkForPs()) {
			
			while(iterator.hasNext()) {
				
				tempObject = iterator.getNext();
				
				if(tempObject instanceof PlayerShip) {
					
					PlayerShip ps = (PlayerShip)tempObject;
					
					if(ps.getMissileCount() > 0) {
						
						//Setting the missile with the speed, direction and location of the PS for the start
						gameObject = new Missile(ps.getSpeed(), ps.getLauncherDirection(), ps.getLocation(), 0);
						worldList.add((Missile)gameObject);
						
						//Decrementing the missile count for the player ship
						ps.setMissileCount(ps.getMissileCount() - 1);
						
						//Playing sound for firing missile
						sounds.missileLaunchSound();
						System.out.println("PS missile fired");
						callObserver();
						
					}
					else {
							
							//Playing noAmmo sounds
							sounds.noAmmoSound();
							
							System.out.println("Your ship has run out of missiles! Please reload at the Space Station\n");
							callObserver();
							
						}
				}
			}
		}
		else
			System.out.println("Error! Player ship not found. Create one first\n");
		

	}
	
	//Method to fire out a missile from the Non player ship
	public void fireNps() {
		Object gameObject = new Object();
		IIterator objIterator = worldList.getIterator();
		Object currentObj = new Object();
		
		while(objIterator.hasNext()) {
			
			currentObj = objIterator.getNext();
			if(currentObj instanceof NonPlayerShip) {
				
				if( ((NonPlayerShip) currentObj).getNpsMissileCount() > 0 ) {
					
					gameObject =  new Missile( ((NonPlayerShip)currentObj).getSpeed(), ((NonPlayerShip)currentObj).getDirection(), ((NonPlayerShip)currentObj).getLocation(), 1);
					worldList.add((Missile)gameObject);
					
					//Decrement the missile count
					((NonPlayerShip) currentObj).setNpsMissileCount(((NonPlayerShip) currentObj).getNpsMissileCount() - 1);
					
					//Playing sound for firing missile
					sounds.missileLaunchSound();
					
					System.out.println("NPS missile fired");
					
									
				}
				else
					System.out.println("The non player ship has run out of missiles!\n");
				
			}

		}
		callObserver();
		
	}
	
	//Method to instantly make the player ship jump to the center of the screen a.k.a hyperjump
	public void hyperJump() {
		
		IIterator iterator = worldList.getIterator();
		Object tempObject = new Object();
		
		if(checkForPs()) {
				
			while(iterator.hasNext()) {
				
				tempObject = iterator.getNext();
				
				if(tempObject instanceof PlayerShip) {
					
					((PlayerShip)tempObject).defaultLocationPs();
					
					//Playing the sound for hyperjump
					sounds.jumpSound();
					System.out.println("PS hyperjump!");
					callObserver();
				}
				
			}
			
		}
		else
			System.out.println("Error! Player ship not found. Create one first.\n");
		
	}
	
	//Method to load a new supply of missiles to the Player ship
	public void loadMissiles() {
		
		IIterator iterator = worldList.getIterator();
		Object tempObject = new Object();
		
		if(checkForStation()) {
			
			if(checkForPs()) {
				while(iterator.hasNext()) {
					
					tempObject = iterator.getNext();
					
					if(tempObject instanceof PlayerShip) {
						
						((PlayerShip)tempObject).setMaxMissiles();
						
						//Playing the sounds for reload
						sounds.reloadSound();
						
						System.out.println("Missiles loaded");
						callObserver();
					}
				}
	
			}
			else
				System.out.println("Error! Player ship not found. Create one first.\n");
		}
		else {
			System.out.println("Np space station in the game !!!\n");
		}
				
		
	}
	
	private boolean checkForStation() {
		
		IIterator objIterator = worldList.getIterator();
		Object currentObj = new Object();
	
		while(objIterator.hasNext()) {
				
			currentObj = (GameObject)objIterator.getNext();
			if(currentObj instanceof SpaceStation)
				return true;
				
		}

		return false;	
		
	}

	/*Method to tell the game world that the time or the 'game clocked' has ticked. Each tick of the 
	 * game clock tells all movable objects to update their positions, every missile's fuel level
	 * is reduced by one and any missiles which are depleted must be removed. Each space station toggles 
	 * its blinking light if the tick is zero and the game time elapsed is incremented by one. 
	 */
	public void tick(int elapsed) {
		
		IIterator iterator = worldList.getIterator();
		Object current = new Object();
		
		time++;
		System.out.println("Game Clock ticked");
		
			
			while(iterator.hasNext()) {
				
				current = iterator.getNext();
				if(current instanceof IMovable) {
					
					IMovable mObj = (IMovable) current;
					mObj.move(elapsed);
					
					//Check if the current instance of the GameObject is a missile or not
					if(mObj instanceof Missile) {
						
						Missile tempMissile = (Missile) current;
						if(tempMissile.getFuel() == 0) {
							tempMissile.toggleFlag();
							crashedList.add(tempMissile);
						}
						else
							tempMissile.decreaseFuelLevel();
						
					}
				
				}
				else if(current instanceof Fixed) {
					
					SpaceStation spaceStation = (SpaceStation) current;
					if(spaceStation.getBlinkRate() % (Math.abs(elapsed / time)) == 0)
						spaceStation.toggleBlink();
					
				}

			}
			
		checkCollision();
		//callObserver();
		
	}
	
	private void checkCollision() {
		
		//Reinitialize the iterator for handling collisions
		IIterator iterator = worldList.getIterator();
		IIterator iterator2;
		
		while(iterator.hasNext()) {
			
			//Obtain a ICollider object
			Object currentObject = iterator.getNext();

			iterator2 = worldList.getIterator();
			
			while(iterator2.hasNext()) {

				Object otherObject = iterator2.getNext();
				
				if(otherObject instanceof ICollider && currentObject instanceof ICollider) {
					
					if(((ICollider)otherObject).getRemoveFlag() == true)
						break;
					if(otherObject != currentObject)
						if(((ICollider)currentObject).collidesWith((ICollider)otherObject)) {
						
							if(checkProperCollision((ICollider)currentObject, (ICollider)otherObject)) {
							
								((ICollider)currentObject).handleCollision();
								((ICollider)otherObject).handleCollision();

								if(currentObject instanceof PlayerShip || otherObject instanceof PlayerShip ) {
		
										sounds.shipCrashSound();
										decrementLife();
									
								}
								else {
									
									sounds.collisionSound();
									if(currentObject instanceof Missile || otherObject instanceof Missile) {
										
											if(currentObject instanceof Missile && ((Missile) currentObject).getMissileFlag() == 0) {
												
												if(otherObject instanceof Asteroid)
													this.score += 50;
												else if(otherObject instanceof NonPlayerShip)
													this.score += 100;
												
											}
											else if(otherObject instanceof Missile && ((Missile) otherObject).getMissileFlag() == 0) {
												
												if(currentObject instanceof Asteroid)
													this.score += 50;
												else if(currentObject instanceof NonPlayerShip)
													this.score += 100;
												
											}
											
									}
									
								}
							
						}
						
					}
				
				}
			}
		}
		handleCollidedObjects();
		//callObserver();
	}

	private boolean checkProperCollision(ICollider currentObject, ICollider otherObject) {
		
		if(currentObject instanceof PlayerShip && otherObject instanceof Missile) {			
			if(((Missile)otherObject).getMissileFlag() == 0)
				return false;
		}
		else if(currentObject instanceof Missile && otherObject instanceof PlayerShip) {
			if(((Missile)currentObject).getMissileFlag() == 0)
				return false;
		}
		else if(currentObject instanceof Missile && otherObject instanceof Missile)
			return false;
		else if(currentObject instanceof NonPlayerShip && otherObject instanceof Missile) {		
			if(((Missile)otherObject).getMissileFlag() == 1)
				return false;
		}
		else if(currentObject instanceof Missile && otherObject instanceof NonPlayerShip) {
			if(((Missile)currentObject).getMissileFlag() == 1)
				return false;
		}
		else if(currentObject instanceof PlayerShip && otherObject instanceof Missile) {	
			if(((Missile)otherObject).getMissileFlag() == 1)
				return true;
		}
		else if(currentObject instanceof Missile && otherObject instanceof PlayerShip) {
			if(((Missile)currentObject).getMissileFlag() == 1)
				return true;
		}
		
		return true;
		
	}

	private void decrementLife() {
		
		//Decrement life
		life--;
		
		System.out.println("	}\r\n" + 
				"\r\n" + 
				"	private void handleCollidedObjects() {\r\n" + 
				"		\r\n" + 
				"		IIterator iterator = worldList.getIterator();\r\n" + 
				"		Object object = new Object();\r\n" + 
				"		\r\n" + 
				"		while(iterator.hasNext()) {\r\n" + 
				"			\r\n" + 
				"			object = iterator.getNext();\r\n" + 
				"			\r\n" + 
				"			if(object instanceof ICollider) {\r\n" + 
				"				\r\n" + 
				"				if(((ICollider) object).getRemoveFlag() == true)\r\n" + 
				"				\r\n" + 
				"					crashedList.add((GameObject)object);\r\n" + 
				"				\r\n" + 
				"			}\r\n" + 
				"			\r\n" + 
				"		}\r\n" + 
				"		\r\n" + 
				"		removeCollidedObjects();\r\n" + 
				"		//callObserver();\r\n" + 
				"		");
		
		//If life reaches zero, call gameOver()
		if(life == 0)
			gameOver();
		
	}

	private void handleCollidedObjects() {
		
		IIterator iterator = worldList.getIterator();
		Object object = new Object();
		
		while(iterator.hasNext()) {
			
			object = iterator.getNext();
			
			if(object instanceof ICollider) {
				
				if(((ICollider) object).getRemoveFlag() == true)
				
					crashedList.add((GameObject)object);
				
			}
			
		}
		
		removeCollidedObjects();
		//callObserver();
		
	}

	private void removeCollidedObjects() {
		
		Iterator iterator = crashedList.iterator();
		
		while(iterator.hasNext()) {
			
			worldList.remove((GameObject)iterator.next());
			
		}
		
		crashedList.clear();
		callObserver();
		
	}

	/*Method to print the current state of the game, which includes current score, number of missiles,
	 * current time elapsed.
	 */
	public void print() {
		
		IIterator iterator = worldList.getIterator();
		Object tempObject = new Object();
		
		System.out.println("\nGame State:\n*********************************************************************************************************\n");
		System.out.println("Current Score: " + score + "\n");
		
		if(checkForPs())
			while(iterator.hasNext()) {
				
				tempObject = iterator.getNext();
				
				if(tempObject instanceof PlayerShip)
					System.out.println("Number of missiles in player ship: " + ((PlayerShip)tempObject).getMissileCount() + "\n");

			}
		else
			System.out.println("Number of missiles in player ship: [Playership not initialized]\n");
		
		System.out.println("Time elapsed: " + time + "\n");
		System.out.println("Lives remaining: " + life + "\n");
		System.out.println("*********************************************************************************************************\n");
	}
	
	// Method to print the current world state
	public void map(){
		
		if(worldList.size() == 0) {
			
			System.out.println("There are no objects in the game!\n\n");
			
		}
		else {
			
			IIterator iterator = worldList.getIterator();
			
			while(iterator.hasNext()){
				
				System.out.print(iterator.getNext());
				
			}
			
			System.out.println();
			
		}
		
	}
	
	//Method to quit the game. This method asks the user for confirmation before exiting from the game
	public void quit() {	
		
		System.out.println("Quit option selected");
		new ClosingApp();
		
	}
	
	//Method to initiate the game over sequence. This is called whenever the number of lives for the player has reacher 0
	public void gameOver() {
		
		//Playing gameOver Sound
		sounds.gameOverSound();
		
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx     Game Over !!!     xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		new GameOverApp(getPoints(), getTime());
		
	}
	
	//Method to return the missile count for the PS
	public int getMissileCount() {
		
		IIterator iterator = worldList.getIterator();
		Object tempObject = new Object();
		int result = 0;
		
		if(checkForPs()) {
			
			while(iterator.hasNext()) {
				
				tempObject = iterator.getNext();
				
				if(tempObject instanceof PlayerShip) {
					
					result =  ((PlayerShip)tempObject).getMissileCount();
					
				}
				
			}
			
		}
		else
			result = 0;
		
		return result;

	}
	
	//Method to return the lives for the player
	public int getLives() {
		
		return this.life;
		
	}
	
	//Method to return the points for the player
	public int getPoints() {
		
		return this.score;
		
	}
	
	///Method to return the time
	public int getTime() {
		
		return this.time;
		
	}

	//Method to determine if the sound attribute is true or false
	@Override
	public boolean checkSound() {
		
		return (sounds.getSound() ? true : false);
		
	}
	
	//returns the height of the canvas
	public static int getHeight() {
		
		return gameHeight;
		
	}
	
	//returns the width of the canvas
	public static int getWidth() {
		
		return gameWidth;
		
	}
	
	//sets the height of the canvas
	@SuppressWarnings("static-access")
	public void setHeight(int height) {
		
		this.gameHeight = height;
		
	}
	
	//sets the width of the canvas
	@SuppressWarnings("static-access")
	public void setWidth(int width) {
		
		this.gameWidth = width;
		
	}
	
	//toggles sound to true or false
	public void soundToggle() {
		
		sounds.soundToggle();
		callObserver();
		
	}
	
	//checks if a playerShip exists in the gameWorld
	private boolean checkForPs() {
		
		IIterator objIterator = worldList.getIterator();
		Object currentObj = new Object();
	
		while(objIterator.hasNext()) {
				
			currentObj = (GameObject)objIterator.getNext();
			if(currentObj instanceof PlayerShip)
				return true;
				
		}

		return false;	
		
	}
	
	public void setVolume(int v) {
		
		sounds.setVol(v);
		
	}
	
	public void setBGVolume(int bV) {
		
		sounds.setBGVol(bV);
		
	}
	
	public int getBGVolume() {
		
		return sounds.getBGVol();
		
	}
	
	public int getVolume() {
		
		return sounds.getVol();
		
	}
	
	public boolean getSoundOn() {
		
		return sounds.getSound();
		
	}
	
	public void setPaused(boolean chk) {
		
		if(chk == true)
			sounds.pauseMusic();
		else {
			sounds.playMusic();
			unselectObjects();
		}
		isPaused = chk;

		
	}
	
	private void unselectObjects() {

		IIterator iterator = worldList.getIterator();
		Object temp = new Object();
		
		while(iterator.hasNext()) {
			
			temp = iterator.getNext();
			
			if(temp instanceof ISelectable) {
				
				((ISelectable)temp).setSelected(false);
				
			}
			
		}
		
	}

	public boolean isPaused() {
		
		return isPaused;
		
	}

}
