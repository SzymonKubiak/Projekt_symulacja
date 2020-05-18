package simulation;

public class Thief extends Enemies {

	public Thief(IMap map, int sightRange, int movementSpeed, int visibilityRange) {
		super(map, sightRange, movementSpeed, visibilityRange);
	}
	public Thief(IMap map) { //dodatkowy konstruktor, ktory sam przypisze domyslne wartosci do sightRange, movementSpeed i visibilityRange
		this(map, 2, 2, 2);
	}
	
	int myTime;
	
	@Override
	public void attack() {
		System.out.println("attac");
		for(IObjectsOnBoard sheep : map.thiefLookAroundForSheeps(this.getPosition(), this.sightRange))
		{
			sheep.disappear();
		}
		
	}

	
	
	@Override
	public void makeTurn() {
		
		if(this.isActive) {
			for(int i = 0 ; i<movementSpeed; i++)
				{
					if(map.thiefLookAroundForSheeps(this.getPosition(), this.sightRange).size() < 2) this.makeMove(); // jezeli ilosc elementow w liscie, ktora zawiera wszystkie owce w zasiegu
																									 		//jest mniejsza niz 2 to wykonuje ruch
					else 
						{
						attack();	// gdy w zasiegu sa ponad dwie owce, wykonuje atak, ktory polega na zabraniu wszystkich owiec z pola zasiegu
						break;      // w jednej kolejce moze tylko raz ukrasc owce
						}
				}
			}
		
		if(Starter.getActualIteration()==0) {                                            //jesli poczatek iteracji
			myTime = RandomGenerator.giveRandomNumber( Starter.getNumberOfIter() );      //dla numerOfIteration=5 wylosuje liczbe od 0 do 4 
			}
		
		if(Starter.getActualIteration()==myTime) {                       //jesli aktualna iteracja pokryje sie z jego wylosowana iteracja ma sie pojawic na mapie
			if(map.isFreePlaceOnEdge()) {                                //sprawdzanie, czy ma gdzie sie pojawic
				while(!map.setPosition(this, RandomGenerator.giveRandomPositionEnemy( map.getSize() )));
				this.isActive = true;
			}
			else {
				myTime++;
			}		
		}
		
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
