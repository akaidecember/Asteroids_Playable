package Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;


public class TurnLauncherRight extends Command{

	//Attribute for the class TurnPsLauncher
	private GameWorld g;
	
	//Default constructor
	public TurnLauncherRight(GameWorld gw) {
		
		super("MSL Right");
		g = gw;
		
	}
	
	public void actionPerformed(ActionEvent E) {

		if(E.getKeyEvent() != -1)
			g.turnLauncherRight();
		
	}
	
}
