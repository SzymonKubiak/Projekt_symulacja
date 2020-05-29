package simulation;

import java.util.List;
import java.util.TreeMap;

public class Wolf extends Enemies {

	public Wolf(IMap map, int sightRange, int movementSpeed, float visibilityRange) {
		super(map, sightRange, movementSpeed, visibilityRange);
	}
	public Wolf(IMap map) { //dodatkowy konstruktor, ktory sam przypisze domyslne wartosci do sightRange, movementSpeed i visibilityRange
		this(map, 4, 4, 0.8F);
	}
	

	public void attackSheep(Position sheepPosition) {
		map.getObject(sheepPosition).disappear();
		map.changePosition(this, sheepPosition);
	}
	
	@Override
	public void makeTurn() {
		
		if(this.isActive) {
			for(int i=0; i<movementSpeed; i++) {
				if(this.isAnySheepInRange()) {
					Sheep sheep = this.getTheNearestSheepInRange();
					Position sheepPosition = sheep.getPosition();
					int squaredDistance = map.squaredDistanceBetweenPositions(this.getPosition(), sheepPosition);
					if(squaredDistance == 1) {
						this.attackSheep(sheepPosition);
						break;
					}
					else {
						this.moveCloseToGoal(sheepPosition);
					}
				}
				else {
					this.makeMove();
				}
			}	
		}
		if(Starter.getActualIteration()==0) {                                            //jesli poczatek iteracji
			this.myTime = RandomGenerator.giveRandomNumber( Starter.getNumberOfIter() );      //dla numerOfIteration=5 wylosuje liczbe od 0 do 4 
			}
		
		if(Starter.getActualIteration()==this.myTime) {                       //jesli aktualna iteracja pokryje sie z jego wylosowana iteracja ma sie pojawic na mapie
			if(map.isFreePlaceOnEdge()) {                                //sprawdzanie, czy ma gdzie sie pojawic
				while(!map.setPosition(this, RandomGenerator.giveRandomPositionEnemy( map.getSize() )));
				this.isActive = true;
			}
			else {
				this.myTime++;
			}		
		}
		
	}
	
	public boolean isAnySheepInRange() {
		List<IObjectsOnBoard> objectsInRangeList = map.objectsInRangeList(this.getPosition(), this.sightRange);  //przypisanie listy obiektow w zasiegu
		if(objectsInRangeList.size() != 0) {                                                                     //jesli lista nie jest pusta
			for(IObjectsOnBoard o : objectsInRangeList) {                                                        //poszukiwanie owiec w liscie
				if(o instanceof Sheep) return true;
			}
		}    	
		return false;
	}
	
	public Sheep getTheNearestSheepInRange() {
		List<IObjectsOnBoard> objectsInRangeList = map.objectsInRangeList(this.getPosition(), this.sightRange);    //pobranie listy obiektow w zasiegu
		if(objectsInRangeList.size() == 0) return null;                                         //jesli pobrana lista jest pusta, zwroc null
		TreeMap<Integer, Sheep> sheepsInRangeMap = new TreeMap<>();                             //utworzenie TreeMap, ktora sortuje klucze (w tym przypadku kluczami sa liczby calkowite)
		for(IObjectsOnBoard obj : objectsInRangeList) {                                         //sprawdzamy, czy w liscie sa owce
			if( obj instanceof Sheep ) {                                                        //jesli tak dodajemy do mapy (odleglosc^2, owca)
				int squaredDistance = map.squaredDistanceBetweenPositions(this.getPosition(), obj.getPosition());
				sheepsInRangeMap.put(squaredDistance, (Sheep)obj );
			}
		}
		if(sheepsInRangeMap.size() == 0) return null;                                            //jesli nie bylo owiec, zwracamy null
		else {                                                     
			return sheepsInRangeMap.firstEntry().getValue();                                     //zwracamy najblizsza owce z mapy (czyli pierwsza w mapie)
		}
	}
	
	public void moveCloseToGoal(Position goalPosition){                                                //ruch zblizony do celu (odleglosc od celu wieksza od 1)
		if(map.isAnyEmptyFieldAround(this.getPosition())) {                                            //jesli nie bedzie wolnego pola to nic nie zrobi
			TreeMap<Integer, Position> positionsMap = new TreeMap<>();
			for(int i = 1; i<=4; i++) {                                                                //sprawdzanie odleglosci od celu po zajeciu, mozliwej pozycji
				Position position = this.getPosition().positionAfterMove(i, map.getSize());
				if((position!=null) && (map.getObject(position)==null)) {                              //jesli istnieje taka pozycja na mapie i nie jest zajeta
					int squaredDistance= map.squaredDistanceBetweenPositions(position, goalPosition);  //obliczanie odleglosci do kwadratu
					positionsMap.put(squaredDistance, position);                                       //dodanie do mapy (odleglosc^2, pozycja)
				}
			}
			Position newPosition = positionsMap.firstEntry().getValue();                               //wybranie pozycji najblizej celu, czyli pierwszej wartosci w mapie
			map.changePosition(this, newPosition);
		}
	}
	
	@Override
	public String toString() {
		return "W";
	}

}
