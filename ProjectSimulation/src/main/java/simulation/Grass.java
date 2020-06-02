package simulation;

public class Grass implements IObjectsOnBoard {
	
	/**
	 * Konstruktor zapisuje referencje do obiektu implementujacego interfejs IMap.
	 * Ustawia pole isActive na true.
	 * 
	 * @param map
	 */
	public Grass(IMap map) {
		this.map = map;
		this.isActive=true;
	}
	
	IMap map;
	private boolean isActive;
	

	/**
	 * Metoda wykonywana, gdy Trawa zostaje zjedzona, znika z mapy i jej atrybut isActive staje sie false
	 */
	@Override
	public void disappear() {                                  
		map.deleteObject(this);                                //usuniecie z hashmapy i tablicy
		this.isActive = false;                                 //isActive = false
	}

	/**
	 * Metoda zwraca sie do mapy w celu odczytu pozycji Trawy
	 */
	@Override
	public Position getPosition() {
		return map.getObjectPosition(this);
	}


	/**
	 * Na poczatku kazdej rundy, Trawa ktora zostala zjedzona - czyli maj atrybut isActive ustawiony jako false, dostaje nowa pozycje
	 */
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

	/**
	 * Metoda zwraca stan Trawy.
	 */
	@Override
	public boolean getState() {
		return isActive;
	}

	/**
	 *Metoda ustawia stan Trawy.
	 */
	@Override
	public void setState(boolean state) {
		this.isActive = state;		
	}
	
	
}

