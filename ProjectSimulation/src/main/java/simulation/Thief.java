package simulation;

import java.util.ArrayList;
import java.util.List;

public class Thief extends Enemies {

	public Thief(IMap map, int sightRange, int movementSpeed, float visibilityRange) {
		super(map, sightRange, movementSpeed, visibilityRange);
	}
	public Thief(IMap map) { //dodatkowy konstruktor, ktory sam przypisze domyslne wartosci do sightRange, movementSpeed i visibilityRange
		this(map, 2, 2, 1);
	}
	
	
	/** Metoda zabija wszystkie Owce bedace w zasiegu ataku Zlodzieja.
	 */
	private void attackSheeps() {
		for(Sheep sheep : this.sheepsInRangeList()) {
			sheep.disappear();
		}	
	}

	@Override
	/** Glowna metoda Zlodzieja, generujaca go w losowym czasie na mapie,
	 * 	a gdy juz Zlodziej istnieje na mapie odpowiada za jego atak gdy conajmniej dwie Owce sa w poblizu.
	 * 	Gdy atak nie zachodzi, Zlodziej porusza sie losowo.
	 */
	public void makeTurn() {
		
		if(this.isActive){
			for(int i = 0 ; i<movementSpeed; i++) {
				if( this.sheepsInRangeList().size() <2 ) {
					this.makeMove();
				}
				else {
					this.attackSheeps();
					break;
				}
			}	
		} 
	
		if(Starter.getActualIteration()==0) {                                                 //jesli poczatek iteracji
			this.myTime = RandomGenerator.giveRandomNumber( Starter.getNumberOfIter() );      //dla numerOfIteration=5 wylosuje liczbe od 0 do 4 
		}
		
		if(Starter.getActualIteration()==this.myTime) {                       //jesli aktualna iteracja pokryje sie z jego wylosowana iteracja ma sie pojawic na mapie
			if(map.isFreePlaceOnEdge()) {                                     //sprawdzanie, czy ma gdzie sie pojawic
				while(!map.setPosition(this, RandomGenerator.giveRandomPositionEnemy( map.getSize() )));
				this.isActive = true;
			}
			else {
				this.myTime++;
			}		
		}
		
	}
	
	/** Metoda wskazuje na Owce bedace w zasiegu ataku Zlodzieja
	 * @return lista Owiec w zasiegu ataku
	 */
	private List<Sheep> sheepsInRangeList(){
		List<IObjectsOnBoard> objectsInRangeList = map.objectsInRangeList(this.getPosition(), this.sightRange);
		List<Sheep> sheepsInRangeList = new ArrayList<>();
		if(objectsInRangeList.size() != 0) {
			for(IObjectsOnBoard o : objectsInRangeList) {
				if(o instanceof Sheep) {
					sheepsInRangeList.add((Sheep)o);
				}
			}
		}
		return sheepsInRangeList;
	}
	
	@Override
	public String toString() {
		return "T";
	}
}
