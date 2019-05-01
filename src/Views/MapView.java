package Views;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.GameWorld;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;
import Collection.GameCollection;
import Interfaces.IGameWorld;
import Interfaces.IIterator;
import Interfaces.ISelectable;
import Objects.Asteroid;
import Objects.GameObject;
import Objects.Missile;
import Interfaces.ICollider;
import Interfaces.IDrawable;

public class MapView extends Container implements Observer{

	//Attributes for the class MapView-----------------------------------------------------------------------------------------
	private IGameWorld proxy;
	//private GameCollection objects;
	
	//Behaviours for the class MapView-----------------------------------------------------------------------------------------
	public MapView(GameWorld proxy) {
		
		this.proxy = proxy;
		//this.objects = this.proxy.getObjects();
		
	}
	
	@Override
	public void update(Observable observable, Object data) {

		if(data instanceof IGameWorld)
			proxy = (IGameWorld)data;
		
		//this.objects = proxy.getObjects();
		
		setWidth(GameWorld.getWidth());
		setHeight(GameWorld.getHeight());
		
		this.repaint();
		proxy.map();
		
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		Point2D pCmpRelPrnt = new Point2D(getX(), getY());
		IIterator iterator = proxy.getObjects().getIterator();
		
		while(iterator.hasNext()) {

			Object object = (GameObject)iterator.getNext();
			
			//Will only call the draw function of the object if the object itself implements IDrawable
			if(object instanceof IDrawable) {
				if(object instanceof ISelectable) {
					
					if( ((ISelectable)object).isSelected() == true )
						((IDrawable)object).drawSelected(g, pCmpRelPrnt);
					else
						((IDrawable)object).drawNonSelected(g, pCmpRelPrnt);
				}
				else {
					
					((IDrawable)object).draw(g, pCmpRelPrnt);
					
				}
			}
			
		}
		
	}
	
	@Override
	public void pointerPressed(int x, int y) {
		
		x -= getParent().getAbsoluteX();
		y -= getParent().getAbsoluteY();
		Point2D pPtrRelPrnt = new Point2D(x,y);
		Point2D pCmpRelPrnt = new Point2D(getX(),getY());
		
		if(proxy.isPaused() == true) {
			
			System.out.println("Game currently paused");
			
			IIterator iterator = proxy.getObjects().getIterator();
			
			while(iterator.hasNext()) {
				
				Object object = (GameObject) iterator.getNext();
				
				if(object instanceof ISelectable) {
					
					if(((ISelectable)object).contains(pPtrRelPrnt, pCmpRelPrnt)) {
						
						((ISelectable)object).setSelected(true);
						
					}
					else
						((ISelectable)object).setSelected(false);

				}
				
				this.repaint();
				
			}
			
		}
		else
			System.out.println("The game is idle !");
		
	}

}
