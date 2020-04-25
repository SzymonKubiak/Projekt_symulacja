package simulation;

public class Grass implements IObjectsOnBoard {
	public Grass(IMap map, Position position) {
		this.map = map;
		this.position = position;
	}
	
	IMap map; //musi posiadac obiekt spelniajacy interface mapy, zeby wykonywac ponizsze metody. Moglibysmy utworzyc sobie jakis nowy w konstruktorze
	          //ale w tym przypadku musimy dostac konkretny i dlatego jest podany jako argument konstruktora.
	Position position;

	@Override
	public void makeMove() {
		// TODO Auto-generated method stub
		
	}

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Position getPosition() {
		return this.position;
	}

}
