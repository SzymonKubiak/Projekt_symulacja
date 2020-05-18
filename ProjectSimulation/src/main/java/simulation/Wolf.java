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
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void makeTurn() {
		
		if(this.isActive) {
			int sheepDirection;
			
			for(int i=0; i<movementSpeed; i++) {                                                                 //wykonuje 4 ruchy w jednej iteracji
				sheepDirection = map.wolfLookAroundForSheep(this.getPosition());
				if( sheepDirection!=0 ) {
					
					Position sheepPosition = this.getPosition().positionAfterMove(sheepDirection);
					Sheep sheepToRemove = (Sheep)map.getObject(sheepPosition);
					sheepToRemove.disappear();         //disapper dla owcy-usuniecie z hashmapy i tablicy, isActive = false oraz dodanie do listy objectsToRemove                                                    	
					map.changePosition(this, sheepPosition);                             //zmiana pozycji wilka na pozycje owcy
					break;                                                               //przerwanie petli, aby w jednym ruchu nie zjadl kilku owiec
				}
				else if(sheepDirection == 0){
					this.makeMove();
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
		return "W";
	}
	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		return false;
	}


}
