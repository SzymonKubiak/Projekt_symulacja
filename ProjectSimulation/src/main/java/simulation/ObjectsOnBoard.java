package simulation;

import java.util.TreeMap;

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
    if(map.isAnyEmptyFieldAround(this.getPosition()))
    	{
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
    
    
    protected void moveCloseToGoal(Position goalPosition){                                                //ruch zblizony do celu (odleglosc od celu wieksza od 1)
		if(map.isAnyEmptyFieldAround(this.getPosition())) {                                            //jesli nie bedzie wolnego pola to nic nie zrobi
			TreeMap<Integer, Position> positionsMap = new TreeMap<>();
			for(int i = 1; i<=4; i++) {
				Position position = this.getPosition().positionAfterMove(i, map.getSize());
				if((position!=null) && (map.getObject(position)==null)) {                              //jesli istnieje taka pozycja na mapie i nie jest zajeta
					int squaredDistance= map.squaredDistanceBetweenPositions(position, goalPosition);  //obliczanie odleglosci do kwadratu
					positionsMap.put(squaredDistance, position);                                       //dodanie do mapy (odleglosc^2, pozycja)
				}
			}
			Position newPosition = positionsMap.firstEntry().getValue();
			map.changePosition(this, newPosition);
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
