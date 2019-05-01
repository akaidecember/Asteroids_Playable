package Objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.GameWorld;

import Interfaces.ICollider;
import Interfaces.IDrawable;
import Interfaces.ISteerable;

public class PlayerShip extends Movable implements ISteerable, IDrawable, ICollider{

	//Attributes for the class PlayerShip
	private int[] boundary;
	private int size;
	private int missileCount;
	private int steerDegrees;
	private final int maxMissileCount = 10;
	private SteerableMissileLauncher psLauncher;
	
	//Behaviours for the class PlayerShip
	
	public PlayerShip() {
		
		this.getLocation().setX(GameWorld.getWidth()/2);					//Setting the initial position of PS to the center of the screen 
		this.getLocation().setY(GameWorld.getHeight()/2);				//Spawn the player ship to the center of the screen
		this.setSpeed(0);												//Default speed is set to zero
		this.setDirection(0);											//Setting the default direction to 0 degrees
		this.setMissileCount(maxMissileCount);							//Setting the default missile max to 10
		this.setColor(ColorUtil.rgb(255, 255, 0));						//Color set to yellow
		psLauncher = new SteerableMissileLauncher(this.getSpeed(), this.getDirection(), this.getLocation());
		steerDegrees = 10;
		size = 30;
		boundary = new int[4];
				
	}
	
	//Method to move to default location (hyper space jump functionality) for the player ship
	public void defaultLocationPs() {
		
		this.getLocation().setX(GameWorld.getWidth()/2);
		this.getLocation().setY(GameWorld.getHeight()/2);
		updateSL();
		
	}
	
	//Method to update the speed and the direction of the steerable missile launcher
	public void updateSL() {
		
		//psLauncher.updateLauncher(this.getSpeed(), this.getDirection());
		psLauncher.updateLauncher(this.getSpeed(), this.getLocation());
		//Update the location too!!!

		
	}
	
	//Method to increase the speed of the player ship
	public void increaseSpeedPs() {
		
		setSpeed(this.getSpeed() + 1);
		updateSL();
		
	}
	
	//Method to decrease the speed of the player ship
	public void decreaseSpeedPs() {
		
		setSpeed(this.getSpeed() - 1);
		updateSL();
		
	}
	
	//Get the current missile count for the player ship
	public int getMissileCount() {
		
		return this.missileCount;
		
	}
	
	//Method to set the missile count for the player ship
	public void setMissileCount(int newMissileCount) {
		
		if(newMissileCount <= maxMissileCount && newMissileCount >= 0)
			this.missileCount = newMissileCount;
		
	}
	
	//Method to steer the player ship left
	public void steerLeft() {
		
		setDirection(this.getDirection() + steerDegrees);
		
	}
	
	//Method to steer the player ship right
	public void steerRight() {
		
		setDirection(this.getDirection() - steerDegrees);
		
	}
	
	//Method to steer the launcher right
	public void steerLauncherRight() {
		
		psLauncher.steerRight();
		
	}
	
	//Method to steer the launcher left
	public void steerLauncherLeft() {
		
		psLauncher.steerLeft();
		
	}
	
	//Method to reload the ship to its maximum capacity
	public void setMaxMissiles() {
		
		this.setMissileCount(this.maxMissileCount);
		
	}
	
	//Method to over-ride the toString method
	public String toString() {
		
		String desc = "Player Ship: " + super.toString() + " missiles=" + this.getMissileCount() + psLauncher.toString() + "\n";
		return desc;
		
	}

	//Applying rectangle-bounded collision algorithm
	@Override
	public boolean collidesWith(ICollider gameObject) {

		int[] myBoundary = this.getBoundaries();
		int[] foreignBoundary = gameObject.getBoundaries();
		
		int myLeft, myRight, myTop, myBottom;
		int fLeft, fRight, fTop, fBottom;
		
		myLeft = myBoundary[0];
		myRight = myBoundary[1];
		myTop = myBoundary[2];
		myBottom = myBoundary[3];
		
		fLeft = foreignBoundary[0];
		fRight = foreignBoundary[1];
		fTop = foreignBoundary[2];
		fBottom = foreignBoundary[3];
		
		if( (myRight < fLeft || myLeft > fRight) || (fTop < myBottom || myTop < fBottom) )
			return false;								//No collision, so return false
		else
			return true;								//Collision, so return true
		
	}

	@Override
	public void draw(Graphics g, Point2D pCmpRelPrnt) {
		
		//Getting the location relative to the local component
		int x = (int)(pCmpRelPrnt.getX() + this.getLocation().getX());
		int y = (int)(pCmpRelPrnt.getY() + this.getLocation().getY());
		
		//Setting the color for the graphics object
		g.setColor(this.getColor());
		
		//Getting l,r,t,b for collision
		int left = x - (this.size/3*2);
		int right = x + (this.size/3*2);
		int top1 = y + (this.size/3*2);
		int bottom = y - (this.size/3*2);
		
		//Creating the points for drawing the shape
		Point2D top = new Point2D(x,y + size/3*2);
		Point2D bottomLeft = new Point2D(x - (size/3*2), y - (size/3*2));
		Point2D bottomRight = new Point2D(x + (size/3*2), y - (size/3*2));
		
		//Storing the points in integer array for drawing the triangle
		int[] xPoints = new int[] {(int) top.getX(), (int)bottomLeft.getX(), (int)bottomRight.getX()};
		int[] yPoints = new int[] {(int) top.getY(), (int)bottomLeft.getY(), (int)bottomRight.getY()};
		
		//Drawing and colouring the triangles
		g.drawPolygon(xPoints, yPoints, 3);
		g.fillPolygon(xPoints, yPoints, 3);
		
		psLauncher.draw(g, pCmpRelPrnt);
		
		//Filling the boundary array
		boundary[0] = left;
		boundary[1] = right;
		boundary[2] = top1;
		boundary[3] = bottom;
		
	}

	@Override
	public int[] getBoundaries() {
	
		return boundary;
	}

	@Override
	public void handleCollision() {

		this.toggleFlag();
		
	}
	
	public boolean getCollisionFlag() {
		
		return this.getRemoveFlag();
		
	}

	public int getLauncherDirection() {
		
		return this.psLauncher.getDirection();
		
	}

	@Override
	public void drawSelected(Graphics g, Point2D pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawNonSelected(Graphics g, Point2D pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
	}
	
}
