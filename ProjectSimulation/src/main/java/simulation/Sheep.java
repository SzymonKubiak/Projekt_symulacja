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
		if(this.multiplicationPoints==10)
		{
			this.multiplicationPoints=0;
			
		//	IMap.createNewObject();//// dokonczyc po zrobieniu metody create new object z mapsimple!!
		}
	}
	
	
	protected void makeMove() {
		
		boolean movePossible;
		int moveDirection;
		Position newPosition;
		do {
			
			do {
				moveDirection=RandomGenerator.giveRandomMove(); //losujemy kierunek przemieszczenia
			} while(!map.isTheMoveProperly(this.getPosition(), moveDirection)); // dopoki nie bêdzie poprawny - nie wyjdzie poza mape

		newPosition= new Position(this.getPosition().positionAfterMove(moveDirection)); //obliczenie nowej pozycji
			
		if(map.getObject(newPosition)==null || map.getObject(newPosition) instanceof Grass) movePossible=true;  // sprawdzenie czy nowy ruch nie spowoduje kolizji z innym obiektem
		else movePossible=false;
		
	} while(!movePossible);
	
		map.setPosition(this, newPosition);
	
	}

	@Override
	public void makeTurn() {
		// TODO Auto-generated method stub
		
	}

}
