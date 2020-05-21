package simulation;

public class Dog extends FarmAnimals {
	public Dog(IMap map, int sightRange, int movementSpeed) {
		super(map, sightRange, movementSpeed);
	}
	public Dog(IMap map) {  //ustawienie dodatkowego konstruktora, ktory sam przypisze domyslne wartosci do sightRange i movementSpeed
		this(map, 4, 3);        
	}

	void bark()
	{
		IShepherd shepherd = new Shepherd();			//stwarza obiekt klasy Pasterz
		shepherd.removeEnemies();						// wywoluje metode removeEnemies();
		shepherd=null;									// usuwa referencje do obiektu, dajac sygnal do wyczyszczenia pamieci
	}

	

	@Override
	public void makeTurn() {
		
		for(int i=0; i<movementSpeed; i++)
		{
			if(map.dogLookAroundForEnemies(this.getPosition(), sightRange)) 
				{
					bark();
					break;
				}
			this.makeMove();
		}
			
	}
	@Override
	public String toString() {
		
		return "D";
	}
	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setState(boolean state) {
		// TODO Auto-generated method stub
		
	}
	
}


