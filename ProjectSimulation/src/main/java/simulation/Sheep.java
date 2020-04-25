package simulation;

public class Sheep extends FarmAnimals {
	
	public Sheep(IMap map, Position position, int sightRange, int movementSpeed) {
		super(map, position, sightRange, movementSpeed);
	}
	
	public Sheep(IMap map, Position position) { //ustawienie dodatkowego konstruktora, ktory sam przypisze domyslne wartosci do sightRange i movementSpeed
		super(map , position, 1, 2);  
	}
	
	
	public int multiplicationPoints;
	
	void eatGrass()
	{
		
		
	}
	
	void multiplicate()
	{
		if(this.multiplicationPoints==10)
		{
			this.multiplicationPoints=0;
			
		//	IMap.createNewObject();//// dokonczyc po zrobieniu metody create new object z mapsimple!!
		}
	}
	
	@Override
	public void makeMove() {
		
		do {
			
			int moveDirection=RandomGenerator.giveRandomMove(); // gdzieœ wy¿ej trzeba wrzucic metode getMap() bo nie mamy innego sposobu na dostanie sie do mapy...
			
			
		} while(false);
		
	}

}
