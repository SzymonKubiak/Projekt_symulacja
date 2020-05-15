package simulation;

public class Thief extends Enemies {

	public Thief(IMap map, int sightRange, int movementSpeed, int visibilityRange) {
		super(map, sightRange, movementSpeed, visibilityRange);
	}
	public Thief(IMap map) { //dodatkowy konstruktor, ktory sam przypisze domyslne wartosci do sightRange, movementSpeed i visibilityRange
		this(map, 2, 2, 2);
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
		return "T";
	}
	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
