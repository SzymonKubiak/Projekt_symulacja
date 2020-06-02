package simulation;

import java.util.TreeMap;

public abstract class ObjectsOnBoard implements IObjectsOnBoard {
	public ObjectsOnBoard(IMap map, int sightRange, int movementSpeed) {
		this.map = map;		
		this.sightRange = sightRange;
		this.movementSpeed = movementSpeed;
	}
	
	/**
	 * @param map - obiekt implementujacy interfejs IMap, przechowujacy dane o polozeniu na mapie wszystkich obiektow symulacji.
	 */
	protected IMap map;
	/**
	 * @param sightRange - zawiera informacie o zasiegu widzenia danego obiektu.
	 */
	protected int sightRange;
	/**
	 * @param movementSpeed (predkosc ruchu) - zawiera informacje o ilosci ruchow, ktore moze wykonac na planszy podczas jednej iteracji.
	 */
	protected int movementSpeed;
	/**
	 * @param isActive pole zawierajace informacje o tym, czy dany obiekt jest aktywny.
	 */
	protected boolean isActive;
	

	/**Metoda odpoweidzialna za calkowite znikniecie obiekty z symulacji.
	 *Usuwa informacje o nim z mapy, dodaje go do listy obiektow, ktore maja zostac usuniete po zakonczeniu danej iteracji oraz zmienia jego status na nieaktywny.
	 */
	@Override
	public void disappear() {                                  
		map.deleteObject(this);                                //usuniecie z hashmapy i tablicy
		Starter.getObjectsToRemove().add(this);                //dodanie do listy obiektow, ktore maja zosatc usuniete z glownej listy po wykonaniu iteracji
		this.isActive = false;                                 //isActive == false
	}

	/**Metoda zwracajaca pozycje obiektu.
	 * Pozycja jest pobierana za posrednictwem mapy.
	 *@return Pozycja obiektu.
	 */
	@Override
	public Position getPosition() {
		return map.getObjectPosition(this);
	}
	

    /**Metoda odpowiedzialna za wykonanie ruchu na mapie.
     * Jeslli nie ma wolnego miejca w poblizu to sie nie wykona. W przeciwnym razie, RandomGenerator rozpocznie losowanie 1 z 4 mozliwych 
     * kierunkow przemieszczenia, jesli wylosowany kierunek jest wolny, RandomGenrator jest zatrzymywany i pozycja danego obiektu w mapie jest zmieniana na nowa.
     */
    protected void makeMove() {                                                                          //metoda umozliwia tylko przechodzenie na puste pola
    if(map.isAnyEmptyFieldAround(this.getPosition()))
    	{
    	int moveDirection;
    	Position newPosition;
		do {
		
			do {
				moveDirection=RandomGenerator.giveRandomMove();							                 //losujemy kierunek przemieszczenia
				} while(!map.isTheMoveProperly(this.getPosition(), moveDirection)); 	 	             //dopoki nie bedzie poprawny - nie wyjdzie poza mape

			newPosition= this.getPosition().positionAfterMove(moveDirection, map.getSize());             //obliczenie nowej pozycji
		
			} while(!map.changePosition(this, newPosition));
    	}
    }
    
    
    /**Metod wykonujaca na obiekcie ruch zblizony do celu.
     * Jesli nie bedzie wolnego miejsca obok to ruch sie nie odbedzie. W przeciwnym razie sa sprawdzane wszystkie cztery mozliwe pozycje jakie moze zajac obiekt.
     * Jesli pozycja jest wolna i nie wychodzi za mape to obliczana jest dla niej odleglosc od celu do kwadratu. Nastepnie odleglosc do kwadratu jako klucz, a dana pozycja 
     * jako wartosc, umieszczana jest we wczesniej utworzonej kolekcji typu TreeMap, ktora uporzadkowuje klucze wielkosciami. Po wykonaniu tych czynnosci dla wszystkich pozycji, wybierana
     * jest pierwsza wartosc z TreeMap, ktora jest pozycja najbardziej zblizona do naszego celu. Nastepnie nastepuje zmiana pozycji obiektu na znaleziona pozycje.
     *  
     * @param goalPosition pozycja celu
     */
    protected void moveCloseToGoal(Position goalPosition){                                            
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
    
	/**Metoda zwracajaca stan danego obiektu.
	 */
	@Override
	public boolean getState() {
		return isActive;
	}

	/**Metoda umozliwiajaca zmiane stanu obiektu.
	 */
	@Override
	public void setState(boolean state) {
		this.isActive = state;
	}
    
}
