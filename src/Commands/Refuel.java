package Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class Refuel extends Command{

	//Attribute for the class AddAsteroid
	private GameWorld g;
	
	//Default constructor
	public Refuel(GameWorld gw) {
		
		super("Refuel");
		g = gw;
		
	}
	
	public void actionPerformed(ActionEvent E) {

		//if(E.getKeyEvent() != -1) 
			//g.refuel();
		
	}
	
}
