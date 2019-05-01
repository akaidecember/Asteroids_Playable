package Interfaces;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public interface IDrawable {

	public  void draw(Graphics g, Point2D pCmpRelPrnt);
	public  void drawSelected(Graphics g, Point2D pCmpRelPrnt);
	public  void drawNonSelected(Graphics g, Point2D pCmpRelPrnt);
	
}
