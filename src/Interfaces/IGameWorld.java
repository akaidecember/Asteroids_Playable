package Interfaces;

import Collection.GameCollection;

public interface IGameWorld {

	//Specification for all the gameWorld methods
	public void init();
	public void addAsteroid();
	public void addNps();
	public void addStation();
	public void addPs();
	public void increasePsSpeed();
	public void decreasePsSpeed();
	public void turnPsLeft();
	public void turnPsRight();
	public void turnLauncherLeft();
	public void turnLauncherRight();
	public void firePs();
	public void fireNps();
	public void hyperJump();
	public void loadMissiles();
	public void tick(int elapsed);
	public void print();
	public void map();
	public void quit();
	public void gameOver();
	public int getMissileCount();
	public int getLives();
	public int getPoints();
	public int getTime();
	public boolean checkSound();
	public void setHeight(int height);
	public void setWidth(int height);
	public void soundToggle();
	public GameCollection getObjects();
	public boolean isPaused();
	
}
