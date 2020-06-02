package simulation;

public abstract class Enemies extends ObjectsOnBoard {

	/**Konstruktor Enemies
	 * przekazuje map, sightRange, movementSpeed do konstruktora klasy, po korej dziedziczy oraz zmienia wartosc dziedziczonego pola isActive na false. Przypisuje wartosc do visibilityRange
	 * is Active jest zmieniane z domyślnego true na false, ponieważ wrogowie nie maja sie pojawic od razu na starcie symulacji na mapie tylko w losowej iteracji
	 */
	public Enemies(IMap map, int sightRange, int movementSpeed, float visibilityRange) {
		super(map, sightRange, movementSpeed);
		this.visibilityRange = visibilityRange;
		super.isActive = false;                   
	}
	/**
	 * @param visibilityRange (zakres widocznosc) - parametr wplywajacy na pole widzenia psa (gdy =1 to pole widzenia psa sie nie zmieni, gdy >1 pole sie zwiekszy, gdy <1 zmniejszy)
	 */
	private float visibilityRange;
	/**
	 * @param myTime zawiera numer iteracji, w której dany wrog ma sie pojawic
	 */
	protected int myTime;
	
	/**Funkcja zwracajaca zakres widocznosc wroga
	 * @return zakres widocznosci 
	 */
	public float getVisibilityRange(){
		return this.visibilityRange;
	}
	
	
	
	



}
