package simulation;

public abstract class Enemies extends ObjectsOnBoard {

	public Enemies(IMap map, int sightRange, int movementSpeed, int visibilityRange) {
		super(map, sightRange, movementSpeed);
		this.visibilityRange = visibilityRange;
	}
	int visibilityRange;
	boolean isActive = false;  //domyslnie beda ustawiane na nieaktywne (zarowno wilk jak i zlodziei)
	public abstract void attack(IObjectsOnBoard object);
	
	
	
	protected void makeMove() {	// Funkcja bêdzie u¿yta w metodzie makeTurn()
		boolean moveProperly;
		boolean movePossible;
		int moveDirection;
		Position newPosition;
	do {
		moveDirection=RandomGenerator.giveRandomMove(); //losujemy kierunek przemieszczenia
		
		moveProperly = map.isTheMoveProperly(this.position, moveDirection); //sprawdzenie czy ruch nie wyjedzie poza mape
		
		newPosition= new Position(this.position); //stworzenie nowego obiektu na now¹ pozycjê
		
		newPosition.positionAfterMove(moveDirection); //obliczenie pozycji po ruchu
		
		if(map.getObject(newPosition)==null || map.getObject(newPosition) instanceof Grass || map.getObject(newPosition) instanceof Sheep) movePossible=true;  // sprawdzenie czy nowy ruch nie spowoduje kolizji z innym obiektem
		
		else movePossible=false;
		
	} while(!moveProperly || !movePossible);
	 
	 position=null;  // zwolnienie pamiêci
	 map.setPosition(this, newPosition);
	
	
}
}
