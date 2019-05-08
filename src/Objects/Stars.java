package Objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

import Interfaces.IDrawable;

public class Stars extends Fixed implements IDrawable{
	
	//Behaviours for the class Stars---------------------------------------------
	
	//Constructor
	public Stars() {
		
		this.setColor(ColorUtil.WHITE);
		
	}

	@Override
	public void draw(Graphics g, Point2D pCmpRelPrnt) {
		
		int x = (int)(pCmpRelPrnt.getX() + this.getLocation().getX());
		int y = (int)(pCmpRelPrnt.getY() + this.getLocation().getY());
		
		
		g.setColor(this.getColor());
		//g.drawLine(x, y, x + 5, y + 5);
		g.drawArc(x, y, 5, 5, 0, 359);
		g.fillArc(x, y, 5, 5, 0, 359);
		
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
