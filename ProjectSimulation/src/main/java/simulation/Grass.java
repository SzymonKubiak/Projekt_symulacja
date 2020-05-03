package simulation;

public class Grass implements IObjectsOnBoard {
	public Grass(IMap map) {
		this.map = map;
		this.position = RandomGenerator.giveRandomPosition( map.getSize() );
	}
	
	IMap map; //musi posiadac obiekt spelniajacy interface mapy, zeby wykonywac ponizsze metody. Moglibysmy utworzyc sobie jakis nowy w konstruktorze
	          //ale w tym przypadku musimy dostac konkretny i dlatego jest podany jako argument konstruktora.
	Position position;

	

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


	@Override
	public void makeTurn() {
		// TODO Auto-generated method stub
		
	}
	
	public void changePosition()
	{
		Position newPosition;
		boolean isMovePossible;
		
		do {
			newPosition=new Position(RandomGenerator.giveRandomPosition(map.getSize())); // wylosowanie pozycji na mapie
			if(map.getObject(newPosition)==null) isMovePossible=true;
			
			else isMovePossible=false;      //// sprawdzenie czy wylosowane miejsce jest wolne
		
		}while(!isMovePossible);   // dopoki nie znajdzie miejsca ktore jest wolne
		
		position=null;  // zwolnienie pamiêci
		map.setPosition(this, newPosition);
		
		
		
	}
	
	
}

