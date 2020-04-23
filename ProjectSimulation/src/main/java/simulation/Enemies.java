package simulation;

public abstract class Enemies extends ObjectsOnBoard {

	boolean isActive;
	int visibilityRange;
	public abstract void attack(IObjectsOnBoard object);
}
