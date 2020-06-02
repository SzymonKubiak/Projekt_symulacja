package simulation;

public abstract class FarmAnimals extends ObjectsOnBoard {

	
	/**
	 *	Konstruktor wywoluje konstruktor z klasy rodzica.
	 *	Dodatkowo ustawia pole isActive na wartosc true.
	 * 	@param map, 
	 * 	@param sightRange,
	 *  @param movementSpeed.
	 */
	public FarmAnimals(IMap map, int sightRange, int movementSpeed) {
		super(map, sightRange, movementSpeed);
		super.isActive = true;                          //domyslnie true
	}
	
	

}
