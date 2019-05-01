package Objects;

import Enum.SpeedEnum;
import Enum.DirectionEnum;

import Interfaces.IMovable;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.GameWorld;

public abstract class Movable extends GameObject implements IMovable{

	//Attributes for the class Movable--------------------------------------------------------------------------------
	
	private int speed;
	private int direction;
	private boolean removeFlag;
	
	//Flag to determine if the object is going out of Bounds for reversal or not
	private boolean reverseFlagX;
	private boolean reverseFlagY;
	private int revertx = 1; // <------------------- Initialize
	private int reverty = 1; // <------------------- Initialize 
	
	//Behaviours for the class Movable--------------------------------------------------------------------------------
	
	//Constructor for the class Movable
	public Movable() {
		
		this.reverseFlagX = false;
		this.reverseFlagY = false;
		this.removeFlag = false;
		this.speed = getRandom().nextInt(SpeedEnum.MaxSpeed.speed);
		this.direction = getRandom().nextInt(DirectionEnum.MaxDegrees.degrees);
		
	}
	
	//Method to toggle if the object is to be removed
	public void toggleFlag() {
		
		removeFlag = true;
		
	}
	
	public void toggleReverseFlagX() {
		
		reverseFlagX = !reverseFlagX;
		
	}
	
	public void toggleReverseFlagY() {
		
		reverseFlagY = !reverseFlagY;
		
	}
	
	//Method to return the removeFlag for removal marking
	public boolean getRemoveFlag() {
		
		return this.removeFlag;
		
	}
	
	//Method to implement the move() method
	public void move(long elapsed) {
			
		Point2D loc = this.getLocation();
		// Please check here - See lecture note Module 11, Slide 10
		double deltaX = Math.cos(Math.toRadians(90 - direction)) * this.getSpeed() *  ((double)elapsed / 1000)  ; // cos(90-heading)
		double deltaY = Math.sin(Math.toRadians(90 - direction)) * this.getSpeed() *  ((double)elapsed / 1000) ; // sin(90-heading)
		deltaX = Math.round(deltaX);  // for printing the proper output
		deltaY = Math.round(deltaY);
		double finalValueX = loc.getX() + deltaX * revertx;
		double finalValueY = loc.getY() + deltaY * reverty;
		loc.setX((float)finalValueX); // update x & y
		loc.setY((float)finalValueY);
		setLocation(loc);
		// if((loc.getX()+getSize() >= getWidth() )|| (loc.getX() <0))
		if((loc.getX() >= GameWorld.getWidth() )|| (loc.getX() <0))
		{
		   revertx = -1 * revertx;
		}
		// if((loc.getY()+getSize() >= getHeight()) || (loc.getY() < 0))
		if((loc.getY() >= GameWorld.getHeight()) || (loc.getY() < 0))
		{
		   reverty = -1 * reverty;
		}
		
		/*
		double deltaX = Math.cos(Math.toRadians(90 - direction)) * this.getSpeed() *  ((double)elapsed / 1000)  ;
		double deltaY = Math.sin(Math.toRadians(90 - direction)) * this.getSpeed() *  ((double)elapsed / 1000) ;
		deltaX = Math.round(deltaX);  
		deltaY = Math.round(deltaY);

		finalValueX = loc.getX() + deltaX ;
		finalValueY= loc.getY() + deltaY ;
		
		//Handle bouncing gameObjects
		if(finalValueX >= GameWorld.getWidth() || (loc.getX() < 0)) {
			toggleReverseFlagX();
		}
		if(finalValueY >= GameWorld.getHeight() || (loc.getY() < 0))
			toggleReverseFlagY();
		
		if(reverseFlagX == true)
			finalValueX = loc.getX() - deltaX ;
		else
			finalValueX = loc.getX() + deltaX ;
		
		if(reverseFlagY == true)
			finalValueY= loc.getY() - deltaY ;
		else
			finalValueY= loc.getY() + deltaY ;

		loc.setX((float)finalValueX); 
		loc.setY((float)finalValueY);
		*/
		setLocation(loc);
		
	}
	
	//Method to get the direction
	public int getDirection() {
		
		return this.direction;
		
	}
	
	//Method to set the direction
	public void setDirection(int newDirection) {
		
		if(newDirection > 359)
			newDirection -= 360;
		else if(newDirection < 0)
			newDirection += 360;
		
		this.direction = newDirection;
		
	}
	
	//Method to get the speed
	public int getSpeed() {
		
		return this.speed;
		
	}
	
	//Method to set new speed
	public void setSpeed(int newSpeed) {
		
		if(newSpeed <= 15 && newSpeed >= 0)
			this.speed = newSpeed;
		
	}
	
	//Method to override the default toString()
	public String toString() {
		
		String desc = super.toString() + " speed=" + this.getSpeed() + " dir=" + this.getDirection();
		return desc;
		
	}
}
