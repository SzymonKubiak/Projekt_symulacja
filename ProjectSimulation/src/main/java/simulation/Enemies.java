package simulation;

public abstract class Enemies extends ObjectsOnBoard {

	public Enemies(IMap map, int sightRange, int movementSpeed, int visibilityRange) {
		super(map, sightRange, movementSpeed);
		this.visibilityRange = visibilityRange;
		super.isActive = false;                   //domyslnie beda ustawiane na nieaktywne (zarowno wilk jak i zlodziei)
	}
	int visibilityRange;
	int myTime;
	public abstract void attack();
	
	
	
	



}
