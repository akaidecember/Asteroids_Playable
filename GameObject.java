package Objects;

import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.GameWorld;

/*
 * Abstract class GameObject to provide implementations for the different objects of the game
 */
public abstract class GameObject {

	//Attributes---------------------------------------------------------------
	
	private double x;
	private double y;
	private int color;
	private Random rand;
	private Point2D objectLocation;
	//private ObjectColor objColor;
	
	//Behaviours---------------------------------------------------------------
	
	//Default constructor for the class GameObject
	public GameObject() {
		
		//objColor = new ObjectColor();

		rand = new Random();
		x = (double) rand.nextInt((int)(GameWorld.getWidth()) + 1);
		y = (double) rand.nextInt((int)(GameWorld.getHeight()) + 1);
		objectLocation = new Point2D(x,y);
		
	}
	
	//Getter method for color
	public int getColor() {
		
		return this.color;
		
	}
	
	//Setter method for color
	public void setColor(int newColor){
	
		this.color = newColor;
	
	}
	
	//Method to get the random object
	public Random getRandom() {
		
		return rand;
		
	}
	
	//Method to set the location of the object by checking if the coordinates are within the specification 
	public void setLocation(Point2D newLocation) {
		
		this.objectLocation = newLocation;
			
	}
	
	//Method to return the location of the object
	public Point2D getLocation() {
		
		return this.objectLocation;
		
	}
	
	//Method to over-ride the default toString method for the class GamObject
	public String toString() {
		
		String desc = "loc=" + Math.round(this.getLocation().getX() * 10.0) / 10.0 + "," + Math.round(this.getLocation().getY() * 10.0) / 10.0 + 
				" color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]";
		return desc;
		
	}
}
