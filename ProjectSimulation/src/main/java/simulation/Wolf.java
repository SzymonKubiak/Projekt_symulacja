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
	
	
	/** Metoda obslugujaca zdarzenie zjedzenia Owcy przez Wilka, kiedy ta znajdzie sie w zasiegu jego razenia
	 * @param sheepPosition
	 */
	private void attackSheep(Position sheepPosition) {
		map.getObject(sheepPosition).disappear();
		map.changePosition(this, sheepPosition);
	}
	
	
	@Override
	/** Glowna metoda Wilka decydujaca o jego zachowaniu w zaleznosci od warunkow.
	 * 	Jezeli Wilk jest na mapie to w zaleznosci od obecnosci Owcy w poblizu, albo ja atakuje, zbliza sie do niej,
	 * 	a gdy w zasiegu brak Owiec wykonuje dowolny ruch.
	 * 	Gdy Wilk nie zostal umieszczony na mapie, czeka na mozliwosc losowego pojawienia sie.
	 */
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
		if(Starter.getActualIteration()==0) {                                            		//jesli poczatek iteracji
			this.myTime = RandomGenerator.giveRandomNumber( Starter.getNumberOfIter() );        //dla numerOfIteration=5 wylosuje liczbe od 0 do 4 
			}
		
		if(Starter.getActualIteration()==this.myTime) {                       					//jesli aktualna iteracja pokryje sie z jego wylosowana iteracja ma sie pojawic na mapie
			if(map.isFreePlaceOnEdge()) {                               					    //sprawdzanie, czy ma gdzie sie pojawic
				while(!map.setPosition(this, RandomGenerator.giveRandomPositionEnemy( map.getSize() )));
				this.isActive = true;
			}
			else {
				this.myTime++;									// jezeli wylosowana runda pojawienia sie Wilka nadeszla, jednak nie na mapie brak miejsca, runda jest przesuwana
			}		
		}
	}
	
	
	/**	Metoda sprawdza, czy w zasiegu wzroku Wilka jest jakas Owca.
	 * @return true - jezeli jest Owca w jego zasiegu, w przeciwnym wypadku - false.
	 */
	protected boolean isAnySheepInRange() {
		List<IObjectsOnBoard> objectsInRangeList = map.objectsInRangeList(this.getPosition(), this.sightRange);  //przypisanie listy obiektow w zasiegu
		if(objectsInRangeList.size() != 0) {                                                                     //jesli lista nie jest pusta
			for(IObjectsOnBoard o : objectsInRangeList) {                                                        //poszukiwanie owiec w liscie
				if(o instanceof Sheep) return true;
			}
		}    	
		return false;
	}
	
	
	/** Metoda szuka najblizszej Owcy w zasiegu wzroku Wilka.
	 * @return obiekt Owca ktory jest najblizej.
	 */
	protected Sheep getTheNearestSheepInRange() {
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
	
	
	
	@Override
	public String toString() {
		return "W";
	}

}
