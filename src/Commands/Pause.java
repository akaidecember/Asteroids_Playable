package Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;

public class Pause extends Command{
	
	//Attributes for the class Pause--------------------------------------------------------------------------------------------
	
	UITimer time;
	GameWorld gw;
	Game g;
	
	//Behaviours for the class Pause--------------------------------------------------------------------------------------------
	
	//Constructor
	public Pause(UITimer time, GameWorld gw, Game g) {

		super("pause");
		this.time = time;		
		this.gw = gw;				
		this.g = g;				
		
	}
	
	public void actionPerformed(ActionEvent E) {

		if(this.gw.isPaused()) {
			
			this.g.startTime(time);	
			this.gw.setPaused(false);			
			this.g.gameNotPaused();
			
		}
		
		else {
			
			this.g.stopTime(time);		
			this.gw.setPaused(true);			
			this.g.gamePaused();
			
		}
		
	}
	
}
