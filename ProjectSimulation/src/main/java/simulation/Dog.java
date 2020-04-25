package simulation;

public class Dog extends FarmAnimals {
	public Dog(IMap map, Position position, int sightRange, int movementSpeed) {
		super(map, position, sightRange, movementSpeed);
	}
	public Dog(IMap map, Position position) {  //ustawienie dodatkowego konstruktora, ktory sam przypisze domyslne wartosci do sightRange i movementSpeed
		super(map, position, 4, 3);        
	}

	void bark()
	{
		
	}

	@Override
	public void makeMove() {
		// TODO Auto-generated method stub
		
	}
}
