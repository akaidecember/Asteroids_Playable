package Objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;

public class MissileLauncher extends Movable{
	
	//Default constructor for the class MissileLauncher
	public MissileLauncher(int newSpeed, int newDirection, Point2D newLocation) {
		
		this.setDirection(newDirection);
		this.setSpeed(newSpeed);
		this.setColor(ColorUtil.rgb(204, 204, 0));								//Setting the color to dark gold
		this.setLocation(newLocation);
		
	}
	
	//Method to update the speed and direction of the launcher if any changes occur to the ships
	public void updateLauncher(int newSpeed, Point2D location) {

		this.setSpeed(newSpeed);
		this.setLocation(location);
		//this.setDirection(newDirection);
		
	}
	
}
