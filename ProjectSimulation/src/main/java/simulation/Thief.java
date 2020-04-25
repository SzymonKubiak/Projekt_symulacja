package simulation;

public class Thief extends Enemies {

	public Thief(IMap map, Position position, int sightRange, int movementSpeed, int visibilityRange) {
		super(map, position, sightRange, movementSpeed, visibilityRange);
	}
	public Thief(IMap map, Position position) { //dodatkowy konstruktor, ktory sam przypisze domyslne wartosci do sightRange, movementSpeed i visibilityRange
		super(map, position, 2, 2, 2);
	}

	@Override
	public void attack(IObjectsOnBoard object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeMove() {
		// TODO Auto-generated method stub
		
	}

	
}
