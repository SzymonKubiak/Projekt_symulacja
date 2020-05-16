package simulation;

public class Sheep extends FarmAnimals {
	
	public Sheep(IMap map, int sightRange, int movementSpeed) {
		super(map, sightRange, movementSpeed);
		this.multiplicationPoints=0;
	}
	
	public Sheep(IMap map) { //ustawienie dodatkowego konstruktora, ktory sam przypisze domyslne wartosci do sightRange i movementSpeed
		this(map, 1, 2);  
	}
	
	
	public int multiplicationPoints;
	
	
	private void eatGrass(Position grassPosition)	//argumentem jest pozycja Trawy
	{
		this.multiplicationPoints++;	// zjedzenie trawy dodaje jeden punkt do rozmnazania
		map.getObject(grassPosition).disappear();	// wywo³uje metodê disappear na obiekcie Trawy
	}
	   	
	private boolean multiplicate()
	{
		if(this.multiplicationPoints>=10 &&map.isAnyEmptyFieldAround(this.getPosition()) )  // czy jest przynajmniej 10 punktow i jakies wolne miejsce obok
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
			
		newPosition= this.getPosition().positionAfterMove(newDirection); //przypisanie pozycji, kotra nie wyjdzie poza mape
		
		} while(!map.setPosition(newSheep, newPosition));     //wykonuj dopoki przypisanie pozycji nie jest mozliwe do nowej owcy (jest to pozycja zajeta)
		
		Starter.getAddedObjectsList().add(newSheep); 
		this.multiplicationPoints=0;	                      //reset punktow rozmnazania	
		System.out.println("Multiplication 101!");
		return true;
		}
		
		return false;
	}
	
	

	@Override
	public void makeTurn() {
		
		if(multiplicate()); 
		else {                     //jesli multiplikacja sie nie odbyla
			
			if(goForGrass()==false) {     //wykonaj zwykly ruch gdy nie ma trawy w poblizu, gdy zwroci true zje trawe i przejdzie na jej pozycje
				this.makeMove();
			}	
		}
	}		
		
	
	
	private boolean goForGrass()
	{
		int grassDirection = map.lookAroundForGrass(this.getPosition());
		
		if(grassDirection==0) return false;													  // jezeli brak trawy w zasiegu zwraca false
		Position grassPosition = this.getPosition().positionAfterMove(grassDirection);        // obliczenie pozycji trawy
		this.eatGrass(grassPosition);						                                  // zanim wejdzie na trawe, zjada ja
		map.changePosition(this, grassPosition);	                                          // przenosi Owce tam gdzie byla trawa
		return true;
		
	}
	
	@Override
	public String toString() {
		return "S";
	}

	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
