package simulation;

public class Sheep extends FarmAnimals {
	
	public Sheep(IMap map, int sightRange, int movementSpeed) {
		super(map, sightRange, movementSpeed);
	}
	
	public Sheep(IMap map) { //ustawienie dodatkowego konstruktora, ktory sam przypisze domyslne wartosci do sightRange i movementSpeed
		this(map, 1, 2);  
	}
	
	
	
	public int multiplicationPoints=0;
	
	void eatGrass()
	{
		
	}
	
	void multiplicate()
	{
		Position newPosition;
		int newDirection;
		
		if(this.multiplicationPoints==10 && map.isAnyEmptyFieldAround(this.getPosition()))  // czy jest 10 punktow rozmnazania i jest jakies wolne miejsce obok
		{
			
		do {
		
			do {
				
				newDirection = RandomGenerator.giveRandomMove();
				
			} while(!map.isTheMoveProperly(map.getObjectPosition(this), newDirection));
			
			newPosition= new Position(this.getPosition().positionAfterMove(newDirection)); 
		
		
		}while(map.setPosition(new Sheep(map), newPosition));
		
		this.multiplicationPoints=0;	//reset punktów rozmna¿ania	
			
			
			
					
			
			
			
		}
	}
	
	

	@Override
	public void makeTurn() {
		
		
	}
	
	public void lookForGrass() {
		
	}
	

}
