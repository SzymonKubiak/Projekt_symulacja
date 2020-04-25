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
			boolean moveProperly;
			boolean movePossible;
			int moveDirection;
			Position newPosition;
		do {
			moveDirection=RandomGenerator.giveRandomMove(); //losujemy kierunek przemieszczenia
			moveProperly = map.isTheMoveProperly(this.position, moveDirection); //sprawdzenie czy ruch nie wyjedzie poza mape
			newPosition= new Position(this.position); //stworzenie nowego obiektu na now¹ pozycjê
			newPosition.positionAfterMove(moveDirection); //obliczenie pozycji po ruchu
			if(map.getObject(newPosition)==null || map.getObject(newPosition) instanceof Grass) movePossible=true;  // sprawdzenie czy nowy ruch nie spowoduje kolizji z innym obiektem
			else movePossible=false;
			
		} while(!moveProperly || !movePossible);
		 position=null;  // zwolnienie pamiêci 
		map.setPosition(this, newPosition);
		
		
	}

}
