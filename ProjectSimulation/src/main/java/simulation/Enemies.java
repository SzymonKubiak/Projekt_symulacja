package simulation;

public abstract class Enemies extends ObjectsOnBoard {

	public Enemies(IMap map, int sightRange, int movementSpeed, float visibilityRange) {
		super(map, sightRange, movementSpeed);
		this.visibilityRange = visibilityRange;
		super.isActive = false;                   //domyslnie beda ustawiane na nieaktywne (zarowno wilk jak i zlodziei)
	}
	private float visibilityRange;
	int myTime;
	public float getVisibilityRange(){
		return this.visibilityRange;
	}
	
	
	
	



}
