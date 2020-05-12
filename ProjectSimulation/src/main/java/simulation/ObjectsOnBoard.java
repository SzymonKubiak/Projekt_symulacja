package simulation;

public abstract class ObjectsOnBoard implements IObjectsOnBoard {
	public ObjectsOnBoard(IMap map, int sightRange, int movementSpeed) {
		this.map = map;		
		this.sightRange = sightRange;
		this.movementSpeed = movementSpeed;
	}
	
	IMap map;
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
		map.delateObject(this);                                //usuniecie z hashmapy i tablicy
		Starter.getObjectList().remove(this);                  //usuniecie z listy
	}

	@Override
	public Position getPosition() {
		return map.getObjectPosition(this);
	}
	


protected void makeMove() {
	
	
	int moveDirection;
	Position newPosition;
	do {
		
		do {
			moveDirection=RandomGenerator.giveRandomMove(); //losujemy kierunek przemieszczenia
		} while(!map.isTheMoveProperly(this.getPosition(), moveDirection)); // dopoki nie bêdzie poprawny - nie wyjdzie poza mape

	newPosition= new Position(this.getPosition().positionAfterMove(moveDirection)); //obliczenie nowej pozycji
		
	} while(map.setPosition(this, newPosition));
	 
}
}
