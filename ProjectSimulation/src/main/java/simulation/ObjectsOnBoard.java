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
	boolean isActive;
	


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
		map.deleteObject(this);                                //usuniecie z hashmapy i tablicy
		Starter.getObjectsToRemove().add(this);                //dodanie do listy obiektow, ktore maja zosatc usuniete z glownej listy po wykonaniu iteracji
		this.isActive = false;                                 //isActive == false
	}

	@Override
	public Position getPosition() {
		return map.getObjectPosition(this);
	}
	

    protected void makeMove() {                                                      //metoda umozliwia tylko przechodzenie na puste pola
    if(map.isAnyEmptyFieldAround(this.getPosition())){
    int moveDirection;
	Position newPosition;
	do {
		
		do {
			moveDirection=RandomGenerator.giveRandomMove();							 //losujemy kierunek przemieszczenia
		} while(!map.isTheMoveProperly(this.getPosition(), moveDirection)); 	 	 //dopoki nie bedzie poprawny - nie wyjdzie poza mape

	    newPosition= this.getPosition().positionAfterMove(moveDirection, map.getSize());                //obliczenie nowej pozycji
		
	} while(!map.changePosition(this, newPosition));
	}
    }
    
	@Override
	public boolean getState() {
		return isActive;
	}

	@Override
	public void setState(boolean state) {
		this.isActive = state;
	}
    
}
