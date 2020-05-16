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
	
	private boolean isFull(){                          //sprawdzanie, czy jest najedzona
		if(this.multiplicationPoints==10) return true;
		return false;
	}    
	
	private void multiplicate()
	{
		Position newPosition;
		int newDirection;
		
		if(map.isAnyEmptyFieldAround(this.getPosition()) )  // czy jest jakies wolne miejsce obok
		{	
			IObjectsOnBoard newSheep = new Sheep(map);
		do 
		{
			do  
			{	
			newDirection = RandomGenerator.giveRandomMove();	
			} while(!map.isTheMoveProperly(map.getObjectPosition(this), newDirection));  //losuje taki ruch, ktory nie wyjdzie poza mape wzgledem starej owcy
			
		newPosition= new Position(this.getPosition().positionAfterMove(newDirection)); //przypisanie pozycji, kotra nie wyjdzie poza mape
		
		} while(!map.setPosition(newSheep, newPosition));     //wykonuj dopoki przypisanie pozycji nie jest mozliwe do nowej owcy (jest to pozycja zajeta)
		
		Starter.getAddedObjectsList().add(newSheep); 
		this.multiplicationPoints=0;	                      //reset punktow rozmnazania	
		System.out.println("Multiplication 101!");
		}
	}
	
	

	@Override
	public void makeTurn() {
		if(this.isFull()) {               //jesli jest najedzona
			multiplicate();               //proba multiplikacji (wykona sie, gdy bedzie wolne miejsce obok owcy)
		}
		else {                            //jesli nie jest najedzona
			if(goForGrass()==false) {     //wykonaj zwykly ruch gdy nie ma trawy w poblizu, gdy zwroci true zje trawe i przejdzie na jej pozycje
				this.makeMove();
			}
		}
		
	}
	
	private boolean goForGrass()
	{
		int grassDirection = map.lookAroundForGrass(this.getPosition());
		
		if(grassDirection==0) return false;													// jezeli brak trawy w zasiegu zwraca false
		this.eatGrass(this.getPosition().positionAfterMove(grassDirection));						// zanim wejdzie na trawe, zjada ja
		map.changePosition(this, this.getPosition().positionAfterMove(grassDirection));	// przenosi Owce tam gdzie byla trawa
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
