package simulation;

public abstract class Enemies extends ObjectsOnBoard {

	public Enemies(IMap map, int sightRange, int movementSpeed, int visibilityRange) {
		super(map, sightRange, movementSpeed);
		this.visibilityRange = visibilityRange;
	}
	int visibilityRange;
	boolean isActive = false;  //domyslnie beda ustawiane na nieaktywne (zarowno wilk jak i zlodziei)
	public abstract void attack(IObjectsOnBoard object);
	
	
	
	



}
