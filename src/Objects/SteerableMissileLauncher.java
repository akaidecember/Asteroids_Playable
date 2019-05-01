package Objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

import Interfaces.IDrawable;
import Interfaces.ISteerable;

public class SteerableMissileLauncher extends MissileLauncher implements ISteerable, IDrawable{

	//Attributes for the class SteerableMissileLauncher

	private int steerDegrees;	
	
	//Behaviours for the class SteerableMissileLauncher
	
	//Default constructor for the class SteerableMissileLauncher
	public SteerableMissileLauncher(int newSpeed, int newDirection, Point2D newLocation) {
		
		super(newSpeed, newDirection, newLocation);							//Calling the super constructor to initialize its speed and direction with that of the ps
		steerDegrees = 15;
		this.setColor(ColorUtil.rgb(255, 255, 255));						//Setting the color to blue
		
	}
	
	//Method to steer the player ship left
	public void steerLeft() {
		
		setDirection(getDirection() + steerDegrees);
		
	}
	
	//Method to steer the player ship right
	public void steerRight() {
		
		setDirection(getDirection() - steerDegrees);
		
	}
	
	//Method to override the toString method of the class
	public String toString() {
		
		return " Missile launcher dir = " + this.getDirection();
		
	}

	@Override
	public void draw(Graphics g, Point2D pCmpRelPrnt) {
		
		int x = (int)(pCmpRelPrnt.getX() + this.getLocation().getX());
		int y = (int)(pCmpRelPrnt.getY() + this.getLocation().getY());
		
		double angle = Math.toRadians(90 - this.getDirection());
		double delX = Math.cos(angle);
		double delY = Math.sin(angle);
		
		g.setColor(this.getColor());
		g.drawLine(x, y, (int)(x + (50 * delX)), (int)(y + (50 * delY)));
				
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
