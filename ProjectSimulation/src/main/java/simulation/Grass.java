package simulation;

public class Grass implements IObjectsOnBoard {
	
	public Grass(IMap map) {
		this.map = map;
		this.isActive=true;
	}
	
	IMap map; //musi posiadac obiekt spelniajacy interface mapy, zeby wykonywac ponizsze metody. Moglibysmy utworzyc sobie jakis nowy w konstruktorze
	          //ale w tym przypadku musimy dostac konkretny i dlatego jest podany jako argument konstruktora.
	private boolean isActive;
	

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
		map.deleteObject(this);                                //usuniecie z hashmapy i tablicy
		//Starter.getObjectsToRemove().add(this);                //dodanie do listy obiektow, ktore maja zosatc usuniete z glowenej listy po wykonaniu iteracji
		this.isActive = false;                                 //isActive = false
	}

	@Override
	public Position getPosition() {
		return map.getObjectPosition(this);
	}


	@Override
	public void makeTurn() {				// na poczatku kazdej rundy, trawy ktore zostaly zjedzone, dostaja nowe pozycje
		if(!isActive){						// jezeli trawa zostala zjedzona to ma stan ustawiony jako niekatywny
			
			int numberOfFreePlaces= map.getListOfFreePlaces().size();
			
			if (numberOfFreePlaces>0)			//jezeli istnieje wolne miejsce na mapie gdzie trawa moze odrosnac
			{ 
				map.setPosition(this, map.getListOfFreePlaces().get(RandomGenerator.giveRandomNumber(numberOfFreePlaces)));
				isActive=true;	
									//Trawa zostaje umieszczona na wylosowanym z wolnych miejsc, a jej stan jest aktywny
			}	
		}
	}
	


	
	@Override
	public String toString() {
		return "G";
	}

	@Override
	public boolean getState() {
		return isActive;
	}


	@Override
	public void setState(boolean state) {
		this.isActive = state;		
	}
	
	
}

