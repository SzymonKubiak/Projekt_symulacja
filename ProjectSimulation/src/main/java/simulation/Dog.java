package simulation;

import java.util.List;

public class Dog extends FarmAnimals {
	public Dog(IMap map, int sightRange, int movementSpeed) {
		super(map, sightRange, movementSpeed);
	}
	public Dog(IMap map) {  //ustawienie dodatkowego konstruktora, ktory sam przypisze domyslne wartosci do sightRange i movementSpeed
		this(map, 4, 3);        
	}

	private void bark()
	{
		IShepherd shepherd = new Shepherd();			//stwarza obiekt klasy Pasterz
		shepherd.removeEnemies();						// wywoluje metode removeEnemies();
		shepherd=null;									// usuwa referencje do obiektu, dajac sygnal do wyczyszczenia pamieci
	}
	

	@Override
	public void makeTurn() {
		
		for(int i=0; i<movementSpeed; i++)
		{
			if(this.isAnyEnemyInRange()){
				bark();
				break;
			}
			this.makeMove();
		}
			
	}
	
	protected boolean isAnyEnemyInRange() {
		List<IObjectsOnBoard> objectsInRangeList = map.objectsInRangeList(this.getPosition(), this.sightRange);  //dostajemy liste aktywnych obiektow w zasiegu
		if(objectsInRangeList == null) return false;
		for(IObjectsOnBoard o : objectsInRangeList) {
			if(o instanceof Enemies) {
				int squaredDistance = map.squaredDistanceBetweenPositions(this.getPosition(), o.getPosition());
				float enemyVisibilityRange = ((Enemies)o).getVisibilityRange();       //pobranie visibilityRange od Wilka/Zlodzieja
				float sightRangeWhenEnemy = sightRange*(enemyVisibilityRange);        //zasieg psa, dla danego obcego (np. pies(sightRange=4), wilk(visibilityRange = 0.8), zlodziej(visibilityRange = 1)), zlodziej bedzie dostrzegany przez psa normalnie poniewaz 4*1=4, a wilk bedzie dostrzegany przez psa 4*0.8=3.2 )
				if(squaredDistance <= (sightRangeWhenEnemy*sightRangeWhenEnemy)) return true;				
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		
		return "D";
	}
}


