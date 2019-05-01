package Enum;

public enum SpeedEnum {

	MaxSpeed(20), MinSpeed(5);
	
	public final int speed;
	
	private SpeedEnum(int newSpeed){
		
		this.speed = newSpeed;
		
	}
	
	public int getSpeed() {
		
		return this.speed;
		
	}
	
}
