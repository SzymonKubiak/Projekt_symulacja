package simulation;

import java.util.List;

public class Dog extends FarmAnimals {
	public Dog(IMap map, int sightRange, int movementSpeed) {
		super(map, sightRange, movementSpeed);
	}
	/**Domyslny konstruktor klasy Dog.
	 * Wartosc sightRange ustalana jest na 4, movementSpeed na 3.
	 */
	public Dog(IMap map) {  
		this(map, 4, 3);        
	}

	
	/**Metoda tworzaca pasterza.
	 * Metoda ta odpowiedzialna jest za utworzenie nowego obiektu implementujacego interfejs IShepherd, wywolanie na tym obiekcie metody removeEnemies() 
	 * i nastepnie jego usuniecie(tj. usuwa referencje do obiektu, dajac sygnal do wyczyszczenia pamieci).
	 */
	private void bark()	{
		IShepherd shepherd = new Shepherd();	
		shepherd.removeEnemies();						
		shepherd=null;							
	}
	

	/**Glowna metoda Psa decydujaca o jego zachowaniu w zaleznosci od warunkow.
	 *W zaleznosci od ilosci ruchow, ktore moze wykonac w jednej iteracji, wywoluje na danym obiekcie metode sprawdzajaca, 
	 *czy w zasiegu nie ma jakiegos wroga. Jesli bedzie to zacznie szczekac, jesli nie to wykona zwykly ruch.
	 */
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
	
	/**Metoda sprawdzajaca, czy w zasiegu znajduje sie jakis wrog.
	 * @return true-gdy sie znajduje, false-gdy sie nie znajduje.
	 */
	protected boolean isAnyEnemyInRange() {
		List<IObjectsOnBoard> objectsInRangeList = map.objectsInRangeList(this.getPosition(), this.sightRange);  //pobranie listy aktywnych obiektow w zasiegu
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


