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
	
	
	private void multiplicate()
	{
		Position newPosition;
		int newDirection;
		
		if(this.multiplicationPoints>=10 && map.isAnyEmptyFieldAround(this.getPosition()))  // czy jest 10 punktow rozmnazania i jest jakies wolne miejsce obok
		{	
			IObjectsOnBoard newSheep = new Sheep(map);
		do 
		{
			do 
			{	
			newDirection = RandomGenerator.giveRandomMove();	
			} while(!map.isTheMoveProperly(map.getObjectPosition(this), newDirection));
			
		newPosition= new Position(this.getPosition().positionAfterMove(newDirection)); 
		
		} while(!map.setPosition(newSheep, newPosition));
		
		Starter.getAddedObjectsList().add(newSheep);
		this.multiplicationPoints=0;	//reset punktow rozmnazania	
		System.out.println("Multiplication 101!");
		}
	}
	
	

	@Override
	public void makeTurn() {
		
		multiplicate();		// sprobuj sie rozmnozyc
		if(goForGrass()==false)
		{
			this.makeMove();  // jezeli nie bylo trawy w poblizu to wykonaj dowolny ruch
			
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
