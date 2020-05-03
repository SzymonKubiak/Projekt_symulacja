package simulation;

public abstract class ObjectsOnBoard implements IObjectsOnBoard {
	public ObjectsOnBoard(IMap map, int sightRange, int movementSpeed) {
		this.map = map;
		position = RandomGenerator.giveRandomPosition(map.getSize()); //nie wiemy czy pozycja jest zajeta trzeba to dopracowac		
		this.sightRange = sightRange;
		this.movementSpeed = movementSpeed;
	}
	
	IMap map;
	Position position;
	int sightRange;
	int movementSpeed;
	


	@Override
	public void setMap(IMap map) {
		this.map = map;	
	}

	@Override
	public IMap getMap() {
		return this.map;
	}
	
	@Override
	public void disappear() {
		//TODO 
	}

	@Override
	public Position getPosition() {
		return this.position;
	}
	
}
