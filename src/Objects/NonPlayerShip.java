package Objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

import Interfaces.ICollider;
import Interfaces.IDrawable;
import Interfaces.ISelectable;

public class NonPlayerShip extends Movable implements IDrawable, ISelectable, ICollider{

	//Attributes for the class Non Player Ship---------------------------------------------------------------------------
	private int[] boundary;
	private boolean selected;
	private int size;
	private int npsMissileCount;
	private MissileLauncher npsLauncher;
	
	//Behaviours for the class NonPlayerShip-----------------------------------------------------------------------------
	
	//Constructor for the class NonPlayerShip
	public NonPlayerShip() {
		
		//Setting the size of the nps to either 10 or 20 (small or large)
		if((getRandom().nextInt(20 + 1) + 10) % 2 == 0)
			size = 25;
		else
			size = 35;
			
		this.npsLauncher = new MissileLauncher(this.getSpeed(), this.getDirection(), this.getLocation());
		this.setNpsMissileCount(2);
		
		//Setting the color to maroon
		this.setColor(ColorUtil.rgb(153, 0, 76));	
		
		boundary = new int[4];
		
	}
	
	//Method to return the NPS missile count
	public int getNpsMissileCount() {
		
		return this.npsMissileCount;
		
	}
	
	//Method to set the number of missiles for the non=-player ship
	public void setNpsMissileCount(int newCount) {
		
		if(newCount <= 2 && newCount > 0)
			this.npsMissileCount = newCount;
		
	}
	
	//Method to get the size of the ship
	public int getSize() {
		
		return this.size;
		
	}
		
	//Method to override the toString() method for the class NonPlayerShip
	public String toString() {
		
		String desc = "Non-Player Ship: " + super.toString() + " size=" + this.getSize() + "\n";
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
		
		int x = (int) pPtrRelPrnt.getX();
		int y = (int) pPtrRelPrnt.getY();
		
		int newX = (int) (pCmpRelPrnt.getX() + (int)this.getLocation().getX());
		int newY = (int) (pCmpRelPrnt.getY() + (int)this.getLocation().getY());
		
		if( (newX <= x && x <= newX + getSize()) && (newY <= y&& y <= newY + getSize()) )
			return false;
		else
			return true;

	}

	@Override
	public void setSelected(boolean select) {

		selected = select;
		
	}

	@Override
	public boolean isSelected() {
		
		return selected;
		
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
	
	public boolean getCOllisionFlag() {
		
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
			g.drawRect(x - this.size/4, y - this.size/2, this.size, this.size);		
			g.fillRect(x - this.size/4, y - this.size/2, this.size, this.size);			

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
			g.drawRect(x - this.size/4, y - this.size/2, this.size, this.size);		
			g.fillRect(x - this.size/4, y - this.size/2, this.size, this.size);	

		boundary[0] = left;
		boundary[1] = right;
		boundary[2] = top;
		boundary[3] = bottom;
		
	}
	
}
