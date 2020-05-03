package simulation;

public class Wolf extends Enemies {

	public Wolf(IMap map, int mapSize, int sightRange, int movementSpeed, int visibilityRange) {
		super(map, mapSize, sightRange, movementSpeed, visibilityRange);
	}
	public Wolf(IMap map, int mapSize) { //dodatkowy konstruktor, ktory sam przypisze domyslne wartosci do sightRange, movementSpeed i visibilityRange
		this(map, mapSize, 5, 4, 1);
	}

	@Override
	public void attack(IObjectsOnBoard object) {
		// TODO Auto-generated method stub
		
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
