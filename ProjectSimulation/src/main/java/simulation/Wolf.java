package simulation;

public class Wolf extends Enemies {

	public Wolf(IMap map, int sightRange, int movementSpeed, int visibilityRange) {
		super(map, sightRange, movementSpeed, visibilityRange);
	}
	public Wolf(IMap map) { //dodatkowy konstruktor, ktory sam przypisze domyslne wartosci do sightRange, movementSpeed i visibilityRange
		this(map, 5, 4, 1);
	}
	
	int myTime;

	@Override
	public void attack(IObjectsOnBoard object) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void makeTurn() {
		if(Starter.getActualIteration()==0) {   //jesli poczatek iteracji
			myTime = RandomGenerator.giveRandomNumber( Starter.getNumberOfIter() );                //dla numerOfIteration=5 wylosuje liczbe od 0 do 4 
			}
		
		if(Starter.getActualIteration()==myTime) {  //jesli aktualna iteracja pokryje sie z jego wylosowana iteracja ma sie pojawic na mapie
			//tutaj trzeba dodac metode sprawdzajaca, czy wg jest jakies wolne miejsce na brzegu
			while(!map.setPosition(this, RandomGenerator.giveRandomPositionEnemy( map.getSize() )));
			this.isActive = true;
		}
		else if(this.isActive) {
			for(int i=0; i<4; i++) {
				this.makeMove();
			}		
		}
		
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
