package Objects;

import java.util.Arrays;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;
import Interfaces.ICollider;
import Interfaces.IDrawable;
import Interfaces.ISelectable;

public class Asteroid extends Movable implements IDrawable, ISelectable, ICollider{
	
	//Attributes for the class Asteroid----------------------------------------------------------------------------------------------
	
	private int[] boundary;							//l,r,t,b
	private int[] xCalcPoints;
	private int[] yCalcPoints;
	private int[] xSidePoints;
	private int[] ySidePoints;
	private int sidePoints;
	private int size;	
	private boolean selected;

	//Behaviours for the class Asteroid----------------------------------------------------------------------------------------------
	
	//Default constructor for the class Asteroid
	public Asteroid() {

		selected = false;
		size = getRandom().nextInt(25) + 25;					   						//Setting the size to a random value between 6 and 30
		this.setColor(ColorUtil.rgb(128, 128, 128));									//Setting the color to grey
		sidePoints = this.getRandom().nextInt(5) + 10;									//Setting the number of side points (corners) for the asteroid
		boundary = new int[4];
		
		//Initializing the arrays
		xSidePoints = new int[sidePoints];
		ySidePoints = new int[sidePoints];
		xCalcPoints = new int[sidePoints];
		yCalcPoints = new int[sidePoints];
		
		for(int i=1; i < sidePoints; i++) {
			
			xCalcPoints[i] = (int)(this.getRandom().nextInt(20) - 10 + size * Math.cos((360/sidePoints * i)));
			yCalcPoints[i] = (int)(this.getRandom().nextInt(20) - 10 + size * Math.sin((360/sidePoints * i)));
			
		}
				
	}
	
	//Getter method for the class Asteroid
	public int getSize() {
		
		return this.size;
		
	}
	
	//Method to over-rode the default toString method
	public String toString(){
		
		String desc = "Asteroid: " + super.toString() + " size=" + this.getSize() + "\n";
		return desc;
		
	}

	@Override
	public void setSelected(boolean select) {

		selected = select;
		
	}

	@Override
	public boolean isSelected() {
		
		return selected;
		
	}

	//Method to draw the asteroid
	@Override
	public void draw(Graphics g, Point2D pCmpRelPrnt) {

		
	}
	
	//Method to update the boundaries of asteroid for rectangle bounding collision
	private void updateBoundaries(int[] xSidePoints, int[] ySidePoints) {
		
		Arrays.sort(xSidePoints);
		Arrays.sort(ySidePoints);
		
		boundary[0] = xSidePoints[0];
		boundary[1] = xSidePoints[sidePoints-1];
		boundary[2] = ySidePoints[sidePoints-1];
		boundary[3] = ySidePoints[0];
		
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

		int x = (int) pPtrRelPrnt.getX();
		int y = (int) pPtrRelPrnt.getY();
		
		int newX = (int) (pCmpRelPrnt.getX() + (int)this.getLocation().getX());
		int newY = (int) (pCmpRelPrnt.getY() + (int)this.getLocation().getY());
		
		if( (newX <= x && x <= newX + getSize()) && (newY <= y && y <= newY + getSize()) )
			return false;
		else
			return true;
		
	}

	@Override
	public int[] getBoundaries() {
	
		return this.boundary;
		
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

		 
		//Setting the first points to the origin of the asteroid
		 xSidePoints[0] = x;
		 ySidePoints[0] = y;
		 
		 //Shifting all the edges of the asteroid
		 for(int i=1; i< this.sidePoints; i++) {
			 
			 xSidePoints[i] = xSidePoints[0] + xCalcPoints[i];
			 ySidePoints[i] = ySidePoints[0] + yCalcPoints[i];
			 
		 }

			 //Drawing and filling the asteroid
			 g.setColor(ColorUtil.LTGRAY);
			 g.drawPolygon(xSidePoints, ySidePoints, sidePoints);
			 g.fillPolygon(xSidePoints, ySidePoints, sidePoints);
		 
		 updateBoundaries(xSidePoints, ySidePoints);
		
	}

	@Override
	public void drawNonSelected(Graphics g, Point2D pCmpRelPrnt) {
		
		//Getting the location relative to the local component
		int x = (int)(pCmpRelPrnt.getX() + this.getLocation().getX());
		int y = (int)(pCmpRelPrnt.getY() + this.getLocation().getY());

		 
		//Setting the first points to the origin of the asteroid
		 xSidePoints[0] = x;
		 ySidePoints[0] = y;
		 
		 //Shifting all the edges of the asteroid
		 for(int i=1; i< this.sidePoints; i++) {
			 
			 xSidePoints[i] = xSidePoints[0] + xCalcPoints[i];
			 ySidePoints[i] = ySidePoints[0] + yCalcPoints[i];
			 
		 }
			 		
			 //Drawing and filling the asteroid
			 g.setColor(this.getColor());
			 g.drawPolygon(xSidePoints, ySidePoints, sidePoints);
			 g.fillPolygon(xSidePoints, ySidePoints, sidePoints);
		 
		 updateBoundaries(xSidePoints, ySidePoints);
		
	}
	
}
