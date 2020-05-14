package simulation;

public class Grass implements IObjectsOnBoard {
	
	public Grass(IMap map) {
		this.map = map;
	}
	
	IMap map; //musi posiadac obiekt spelniajacy interface mapy, zeby wykonywac ponizsze metody. Moglibysmy utworzyc sobie jakis nowy w konstruktorze
	          //ale w tym przypadku musimy dostac konkretny i dlatego jest podany jako argument konstruktora.

	

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
		map.deleteObject(this);                                							//usuniecie z hashmapy i tablicy
		Starter.getObjectList().remove(this);											 //usuniecie z listy
		while(!map.changePosition(this, RandomGenerator.giveRandomPosition( map.getSize() )));
	}

	@Override
	public Position getPosition() {
		return map.getObjectPosition(this);
	}


	@Override
	public void makeTurn() {
		// TODO Auto-generated method stub
		
	}
	


	
	@Override
	public String toString() {
		return "G";
	}
	
	
}

