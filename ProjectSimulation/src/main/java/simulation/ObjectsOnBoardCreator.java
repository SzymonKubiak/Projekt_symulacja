package simulation;

import java.util.LinkedList;
import java.util.List;

public class ObjectsOnBoardCreator implements IObjectsOnBoardCreator{
	/**
	 * @param numS - liczba Owiec
	 * @param numD - liczba Psow
	 * @param numG - liczba pol Trawy
	 * @param numT - liczba Zlodziei
	 * @param numW - liczba Wilkow
	 */
	public ObjectsOnBoardCreator(int numS, int numD, int numG, int numT, int numW) {
		
		this.numS = numS;
		this.numD = numD;
		this.numG = numG; 
		this.numT = numT; 
		this.numW = numW;
	}
	/**
	 * Konstruktor domyslny.
	 */
	public ObjectsOnBoardCreator() { //konstruktor domyslny, gdy nic nie podamy 
		
		this(20,2,25,2,3);
	}
	
	
	int numS;
	int numD;
	int numG;
	int numT;
	int numW;

	/**
	 * Metoda inicjalizuje wszystkie obiekty, ktore beda brac udzial w poczatkowej fazie symulacji,
	 * zwraca liste tych obiektow.
	 */
	@Override
	public List<IObjectsOnBoard> create(IMap map) { 
		
		List<IObjectsOnBoard> list = new LinkedList<>();
		
		for(int i=0; i<numG; i++) {							//zmieniono kolejnosc dodania do listy, aby trawa byla na przodzie
			list.add(new Grass(map));
		}
		
		for(int i=0; i<numD; i++) {
			list.add(new Dog(map));
		}
		
		for(int i=0; i<numT; i++) {
			list.add(new Thief(map));
		}
		
		for(int i=0; i<numW; i++) {
			list.add(new Wolf(map));
		}
		
		for(int i=0; i<numS; i++) {
			list.add(new Sheep(map));
		}
		
		return list;
	}
	
}
