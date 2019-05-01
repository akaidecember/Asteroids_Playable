package Objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;
import Interfaces.ICollider;
import Interfaces.IDrawable;
import Interfaces.ISelectable;

public class Missile extends Movable implements ISelectable, IDrawable, ICollider{

	//Attributes for the class Missile------------------------------------------------------------------------------------------------------------
	
	private int[] boundary;									//l,r,t,b
	private boolean selected;
	private int fuelLevel;
	private int missileFlag;								//If the flag is 0, then it belongs to player ship. If it is 1, then it belongs to non player ship
	private int size;
	private boolean contains;
	
	//Behaviours for the class Missile------------------------------------------------------------------------------------------------------------
	
	//Parameterized constructor
	public Missile(int newSpeed , int newDirection , Point2D newLocation , int newFlag) {
		
		contains = false;
		size = 10;
		this.setFuel(100);
		this.missileFlag = newFlag;								//0 for ps, 1 for nps
		this.setDirection(newDirection);
		this.setSpeed(newSpeed + 2);
		Point2D myMissileLoc = new Point2D(newLocation.getX(),newLocation.getY());
		this.setLocation(myMissileLoc);
		boundary = new int[4];
		
		if(missileFlag == 1)
			this.setColor(ColorUtil.rgb(255, 128, 0));			//Setting the color to orange			
		else
			this.setColor(ColorUtil.rgb(255, 255, 255));		//Setting the color to white
				
	}
	
	//Default constuctor
	public Missile(){
	
	}
	
	//Getter method for flag
	public int getMissileFlag() {
		
		return this.missileFlag;
		
	}
	
	//Getter and setter methods
	public int getFuel() {
		
		return this.fuelLevel;
		
	}
	
	public void setFuel(int newFuel) {
		
		if(newFuel <= 100 && newFuel > -1)
			this.fuelLevel = newFuel;
		
	}
	
	//Method to decrease the fuel level by one
	public void decreaseFuelLevel() {
		
		setFuel(getFuel() - 1); 
		
	}
	
	//Method to return the size of the missile
	public int getSize() {
		
		return this.size;
		
	}
	//toString method for the class
	public String toString() {
		
		String desc = "";
		if(missileFlag == 0)
			desc = "PS's missile: ";
		else if(missileFlag == 1)
			desc = "NPS's missile: ";
		desc += super.toString() + " fuel=" + this.getFuel() + "\n";
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
	public boolean contains(Point2D pPtrRelPrnt, Point2D pCmpRelPrnt) {
		
		int x = (int) pCmpRelPrnt.getX();
		int y = (int) pCmpRelPrnt.getY();
		
		int newX = (int) (pCmpRelPrnt.getX() + (int)this.getLocation().getX());
		int newY = (int) (pCmpRelPrnt.getY() + (int)this.getLocation().getY());
		
		if( (newX <= x && x <= newX + getSize()) && (newY <= y&& y <= newY + getSize()) )
			this.contains = false;
		else
			this.contains = true;
		
		return contains;
		
	}

	@Override
	public void setSelected(boolean select) {
		
		this.selected = select;
		
	}

	@Override
	public boolean isSelected() {
		
		return this.selected;
		
	}

	@Override
	public void draw(Graphics g, Point2D pCmpRelPrnt) {

		
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

	@Override
	public void drawSelected(Graphics g, Point2D pCmpRelPrnt) {
		
		//Getting the location relative to the local component
		int x = (int)(pCmpRelPrnt.getX() + this.getLocation().getX());
		int y = (int)(pCmpRelPrnt.getY() + this.getLocation().getY());
		
		int left = x - this.size/4;
		int right = x + this.size/4;
		int top = y + this.size/2;
		int bottom = y - this.size/2;

			g.setColor(ColorUtil.LTGRAY);
			g.drawRect(x - this.size/4, y - this.size/2, this.size/10, this.size);		
			g.fillRect(x - this.size/4, y - this.size/2, this.size/10, this.size);

		boundary[0] = left;
		boundary[1] = right;
		boundary[2] = top;
		boundary[3] = bottom;
	}

	@Override
	public void drawNonSelected(Graphics g, Point2D pCmpRelPrnt) {
		
		//Getting the location relative to the local component
		int x = (int)(pCmpRelPrnt.getX() + this.getLocation().getX());
		int y = (int)(pCmpRelPrnt.getY() + this.getLocation().getY());
		
		int left = x - this.size/4;
		int right = x + this.size/4;
		int top = y + this.size/2;
		int bottom = y - this.size/2;
		
			g.setColor(this.getColor());
			g.drawRect(x - this.size/4, y - this.size/2, this.size/10, this.size);		
			g.fillRect(x - this.size/4, y - this.size/2, this.size/10, this.size);	

		
		boundary[0] = left;
		boundary[1] = right;
		boundary[2] = top;
		boundary[3] = bottom;
	}
}
