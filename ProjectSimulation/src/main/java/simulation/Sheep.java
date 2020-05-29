package simulation;
import java.util.List;
import java.util.TreeMap;

public class Sheep extends FarmAnimals {
	
	public Sheep(IMap map, int sightRange, int movementSpeed) {
		super(map, sightRange, movementSpeed);
		this.multiplicationPoints=0;
	}
	public Sheep(IMap map) { //ustawienie dodatkowego konstruktora, ktory sam przypisze domyslne wartosci do sightRange i movementSpeed
		this(map, 1, 1);  
	}
	
	public int multiplicationPoints;
	
	private void eatGrass(Position grassPosition)	//argumentem jest pozycja Trawy
	{
		this.multiplicationPoints++;	            // zjedzenie trawy dodaje jeden punkt do rozmnazania
		map.getObject(grassPosition).disappear();	// wywoluje metode disappear na obiekcie Trawy
	}
	   	
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
		System.out.println("Multiplication 101!");
		return true;
		}
		
		return false;
	}
	
	

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
		
	 
	public void moveCloseToGoal(Position goalPosition){                                                //ruch zblizony do celu (odleglosc od celu wieksza od 1)
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
	
	private void stepOnTheGrass(Position grassPosition){
		this.eatGrass(grassPosition);
		map.changePosition(this, grassPosition);
		
	}
	
	public boolean isAnyGrassInRange() {
		List<IObjectsOnBoard> objectsInRangeList = map.objectsInRangeList(this.getPosition(), this.sightRange);  //przypisanie listy obiektow w zasiegu
		if(objectsInRangeList.size() != 0) {                                                                     //jesli lista nie jest pusta
			for(IObjectsOnBoard o : objectsInRangeList) {                                                        //poszukiwanie trawy w liscie
				if(o instanceof Grass) return true;
			}
		}    	
		return false;
	}
	
	public Grass getTheNearestGrassInRange() {
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
	public String toString() {
		return "S";
	}
}
