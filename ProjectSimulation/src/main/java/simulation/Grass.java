package simulation;

public class Grass implements IObjectsOnBoard {
	
	public Grass(IMap map) {
		this.map = map;
		this.isActive=true;
	}
	
	IMap map; //musi posiadac obiekt spelniajacy interface mapy, zeby wykonywac ponizsze metody. Moglibysmy utworzyc sobie jakis nowy w konstruktorze
	          //ale w tym przypadku musimy dostac konkretny i dlatego jest podany jako argument konstruktora.
	boolean isActive;
	

	@Override
	public void setMap(IMap map) {
		this.map = map;
	}

	@Override
	public IMap getMap() {
		return this.map;
	}

	@Override
	public void disappear() {
		isActive=false;
		map.deleteObject(this);
	}

	@Override
	public Position getPosition() {
		return map.getObjectPosition(this);
	}


	@Override
	public void makeTurn() {				// na poczatku kazdej rundy, trawy ktore zostaly zjedzone, dostaja nowe pozycje
		if(!isActive)
		{
			while(!map.setPosition(this, RandomGenerator.giveRandomPosition( map.getSize() )));
		}
		isActive=true;
		
	}
	


	
	@Override
	public String toString() {
		return "G";
	}

	@Override
	public boolean getState() {
		return isActive;
	}
	
	
}

