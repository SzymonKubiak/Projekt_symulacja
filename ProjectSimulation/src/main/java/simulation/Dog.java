package simulation;

public class Dog extends FarmAnimals {
	public Dog(IMap map, int sightRange, int movementSpeed) {
		super(map, sightRange, movementSpeed);
	}
	public Dog(IMap map) {  //ustawienie dodatkowego konstruktora, ktory sam przypisze domyslne wartosci do sightRange i movementSpeed
		this(map, 4, 3);        
	}

	void bark()
	{
		
	}

	@Override
	public void makeMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeTurn() {
		// TODO Auto-generated method stub
	}
}


