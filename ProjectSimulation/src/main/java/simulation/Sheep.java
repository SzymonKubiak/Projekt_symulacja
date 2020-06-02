package simulation;

import java.util.TreeMap;
import java.util.List;

public class Sheep extends FarmAnimals {
	
	public Sheep(IMap map, int sightRange, int movementSpeed) {
		super(map, sightRange, movementSpeed);
		this.multiplicationPoints=0;
	}
	public Sheep(IMap map) { //ustawienie dodatkowego konstruktora, ktory sam przypisze domyslne wartosci do sightRange i movementSpeed
		this(map, 1, 1);  
	}
	
	/** multiplicationPoints to zmienna okreslajaca zdolnosc Owcy do wykonania akcji rozmnazania.
	 */
	private int multiplicationPoints;
	
	
	
	/** Metoda obsluguje zdarzenie, kiedy Owca bedzie mogla zjesc Trawe, ktora jest w poblizu.
	 * 	Jako argument przyjmuje pozycje Trawy @param grassPosition.
	 * 	Dodaje punkt do multiplicationPoints oraz wywoluje metode disappear na zjedzonej Trawie.
	 */
	private void eatGrass(Position grassPosition)	//argumentem jest pozycja Trawy
	{
		this.multiplicationPoints++;	            // zjedzenie trawy dodaje jeden punkt do rozmnazania
		map.getObject(grassPosition).disappear();	// wywoluje metode disappear na obiekcie Trawy
	}
	
	
	
	/** Metoda sprawdza czy dana Owca spelnia kryteria do rozmnazania: ma 10 pkt multiplicationPoints i czy jest obok niej na mapie wolne miejsce.
	 * 	Jezeli kryteria zostaly spelnione, w losowym wolnym miejscu obok Owcy powstaje kolejna.
	 * 	multiplicationPoints Owcy, ktora sie rozmnozyla zostaja zresetowane.
	 * 	@return True - jezeli rozmnozenie zaszlo; False - jezeli ktorys z warunkow nie zostal spelniony.
	 */
	private boolean multiplicationTry()
	{
		if(this.multiplicationPoints>=10 && map.isAnyEmptyFieldAround(this.getPosition()) )  // czy jest przynajmniej 10 punktow i jakies wolne miejsce obok
		{	
			IObjectsOnBoard newSheep = new Sheep(map);
			Position newPosition;
			int newDirection;
		do 
		{
			do  
			{	
			newDirection = RandomGenerator.giveRandomMove();	
			} while(!map.isTheMoveProperly(map.getObjectPosition(this), newDirection));  //losuje taki ruch, ktory nie wyjdzie poza mape wzgledem starej owcy
			
		newPosition= this.getPosition().positionAfterMove(newDirection, map.getSize()); //przypisanie pozycji, kotra nie wyjdzie poza mape
		
		} while(!map.setPosition(newSheep, newPosition));     //wykonuj dopoki przypisanie pozycji nie jest mozliwe do nowej owcy (jest to pozycja zajeta)
		
		Starter.getObjectsToAdd().add(newSheep); 
		this.multiplicationPoints=0;	                      //reset punktow rozmnazania	
		return true;
		}
		
		return false;
	}
	
	
	/** 
	 * 	Glowna metoda obiektu.
	 * 	Sprawdza wszystkie sytuacje mozliwe do zdarzenia, zarzadza zachowaniem Owcy:
	 * 	Sprawdza czy Owca spelnia warunki aby sie rozmnozyc i jezeli tak, to robi to i konczy ruch.
	 * 	Jezeli nie, sprawdza czy gdzies w poblizu znajduje sie Trawa i jezeli jest w zasiegu to zjada ja wywolujac metode eatGrass(),
	 * 	albo zbliza sie do niej.
	 * 	W przeciwnym przypadku wykonuje losowy ruch za pomoca metody makeMove().
	 */
	@Override
	public void makeTurn() {
		if(this.isActive) {
			if(multiplicationTry());
			else if (this.isAnyGrassInRange()) {
				Grass grass = this.getTheNearestGrassInRange();
				Position grassPosition = grass.getPosition();
				int squaredDistance = map.squaredDistanceBetweenPositions(this.getPosition(), grassPosition);
				if(squaredDistance == 1) {
					this.stepOnTheGrass(grassPosition);
				}
				else {                                     //gdy odlegosc do kwadratu jest wieksza od 1
					this.moveCloseToGoal(grassPosition);
				}
			}
			else {
				this.makeMove();
			}
		}
	}
		

	
	/**	Metoda wykonuje sie tylko wtedy gdy Trawa jest w zasiegu ruchu Owcy.
	 * Wywoluje metode eatGrass() oraz przenosi Owce na pozycje na ktorej Trawa sie znajdowala.
	 * @param grassPosition
	 */
	private void stepOnTheGrass(Position grassPosition){
		this.eatGrass(grassPosition);
		map.changePosition(this, grassPosition);
	}
	
	
	/** Metoda sprawdza czy w zasiegu wzroku Owcy jest obiekt klasy Grass.
	 * @return true - jezeli w zasiegu wzroku jest obiekt klasy Trawa, w przeciwnym wypadku - false.
	 */
	protected boolean isAnyGrassInRange() {
		List<IObjectsOnBoard> objectsInRangeList = map.objectsInRangeList(this.getPosition(), this.sightRange);  //przypisanie listy obiektow w zasiegu
		if(objectsInRangeList.size() != 0) {                                                                     //jesli lista nie jest pusta
			for(IObjectsOnBoard o : objectsInRangeList) {                                                        //poszukiwanie trawy w liscie
				if(o instanceof Grass) return true;
			}
		}    	
		return false;
	}
	
	
	/** Metoda sposrod listy obiektow klasy Trawa bedacych w zasiegu wzroku Owcy, wybiera najblizej lezacy na mapie.
	 * @return najblizszy obiekt klasy Grass.
	 */
	protected Grass getTheNearestGrassInRange() {
		List<IObjectsOnBoard> objectsInRangeList = map.objectsInRangeList(this.getPosition(), this.sightRange);     //analogicznie jak getTheNearestSheepInRange();
		if(objectsInRangeList.size() == 0) return null;
		TreeMap<Integer, Grass> grassInRangeMap = new TreeMap<>();                                       
		for(IObjectsOnBoard obj : objectsInRangeList) {
			if( obj instanceof Grass) {
				int squaredDistance = map.squaredDistanceBetweenPositions(this.getPosition(), obj.getPosition());
				grassInRangeMap.put(squaredDistance, (Grass)obj );
			}
		}
		if(grassInRangeMap.size() == 0) return null;
		else {                                                                                            
			return grassInRangeMap.firstEntry().getValue();
		}
	}
	
	@Override
	/** Metoda sluzaca do wyswietlania graficznie mapy.
	 */
	public String toString() {
		return "S";
	}
}
