package simulation;

public class Wolf extends Enemies {

	public Wolf(IMap map, int sightRange, int movementSpeed, int visibilityRange) {
		super(map, sightRange, movementSpeed, visibilityRange);
	}
	public Wolf(IMap map) { //dodatkowy konstruktor, ktory sam przypisze domyslne wartosci do sightRange, movementSpeed i visibilityRange
		this(map, 5, 4, 1);
	}

	@Override
	public void attack(IObjectsOnBoard object) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void makeTurn() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "W";
	}
	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		return false;
	}


}
