package simulation;

public abstract class Enemies extends ObjectsOnBoard {

	public Enemies(IMap map, Position position, int sightRange, int movementSpeed, int visibilityRange) {
		super(map, position, sightRange, movementSpeed);
		this.visibilityRange = visibilityRange;
	}
	int visibilityRange;
	boolean isActive = false;  //domyslnie beda ustawiane na nieaktywne (zarowno wilk jak i zlodziei)
	public abstract void attack(IObjectsOnBoard object);
}
