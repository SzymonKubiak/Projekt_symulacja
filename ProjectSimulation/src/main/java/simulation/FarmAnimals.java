package simulation;

public abstract class FarmAnimals extends ObjectsOnBoard {

	
	public FarmAnimals(IMap map, int sightRange, int movementSpeed) {
		super(map, sightRange, movementSpeed);
		super.isActive = true;                          //domyslnie true
	}
	
	

}
