package simulation;

import java.util.LinkedList;
import java.util.List;

public class Starter {
	
/**
 * Metoda inicjalizuje potrzebne komponenty, takie jak glowna lista - po ktorej iterujemy cala symulacje,
 * Lista obiektow do dodania, ktore zostaly dodane na konkretnym etapie symulacji,
 * Lista obiektow do usuniecia, ktore zostaly do niej dodane w ciagu symuacji.
 * @param numberOfIter - ilosc epok symulacji
 * @param map - referencja do obiektu mapa
 * @param objectsCreator - referencja do kreatora obiektow
 */
public Starter(int numberOfIter, IMap map, IObjectsOnBoardCreator objectsCreator) {
		
		Starter.numberOfIter = numberOfIter;
		this.map = map;
		objectList = objectsCreator.create(map);                //utorzenie listy obiektow
		objectsToAdd = new LinkedList<IObjectsOnBoard>();    //utworzenie dodatkowej listy na obiekty utworzone w trakcie iteracji
		objectsToRemove = new LinkedList<IObjectsOnBoard>();
		this.setObjectsOnMap(objectList);
			
	}


    private static List<IObjectsOnBoard> objectList;
    private static List<IObjectsOnBoard> objectsToAdd;
    private static List<IObjectsOnBoard> objectsToRemove;
    private static int actualIteration;                             //bedzie ja potrzebowal wilk i zlodziej
    private static int numberOfIter;
    IMap map; 
    


    /**
     * Metoda sprawdza, czy podane startowe ilosci obiektow, sa w stanie zmiescic sie na mapie.
     * @param objectsList - lista glowna obiektow.
     * @return True jezeli ilosc obiektow jest mniejsza niz ilosc pol mapy, w przeciwnym przypadku false.
     */
    public boolean parametersCorrectness(List<IObjectsOnBoard> objectsList) {
    	int quantity = 0;
    	for(IObjectsOnBoard o : objectsList) {
    		if(o instanceof Enemies);
    		else quantity++;
    	}
    	if(quantity > (map.getSize()*map.getSize())) return false;
    	else return true;
    }
    
    /**
     * Metoda nadaje pozycje kazdemu obiektowi - uzywana tylko raz, na poczatku symulacji.
     * @param objectList - lista glowna obiektow.
     */
    public void setObjectsOnMap(List<IObjectsOnBoard> objectList) {
   	    if(this.parametersCorrectness(objectList) ) {         //jesli obiektow jest mniej lub tyle samo co miejsc na mapie to przypisanie sie wykona
    		for(IObjectsOnBoard o : objectList) {                     //musimy dodac obiekty do kolekcji typu map i przypisac im randomowa pozycje
    			if(o instanceof Enemies);                             //jesli to wilk/zlodziej nie dodawaj go (oni maja byc pozniej dodani)
    			else {
    				while(!map.setPosition(o, RandomGenerator.giveRandomPosition( map.getSize() ) ) );
    			}
        	}
    	}		
    }
    
    /**
     * Metoda aktualizuje glowna liste po ktorej iterujemy.
     * Dodaje obiekty, ktore powstaly i usuwa, ktore powinny zostac usuniete.
     */
    private void actualiseList()
    {
    	objectList.addAll(objectsToAdd);					// po kazdej iteracji do glownej listy dodawane sa nowo powstale obiekty
		objectsToAdd.clear();                               // po dodaniu nalezy usunac wszystkie obiekty z dodatkowej listy
		objectList.removeAll(objectsToRemove);              // po kazdej iteracji z glownej listy usuwane sa obiekty, ktore trafily do listy objectsToRemove
		objectsToRemove.clear();                            // czyszczenie listy
		
    }
    
	/**
	 * Czesc glowna symulacji - obsluga wszystkich zdarzen, drukowanie mapy w konsoli,
	 * Iteracja po kazdym obiekcie z listy objectList i wywolywanie metody makeTurn(), aktualizacja listy obiektow.
	 */
	public void runSimulation()
	{
		if(parametersCorrectness(objectList) == false) {
			System.out.println("The objects won't fit on the map!");
		}
		else {
			System.out.println("Starting map");
			map.printTableMap();                                    //wyswietlenie tablicy dwuwymiarowej
			
			for(int i=0; i<numberOfIter; i++) {
				actualIteration = i;
				
				for(IObjectsOnBoard iObjectsOnBoard : objectList) {
					iObjectsOnBoard.makeTurn();
				}
				
				actualiseList();
				
				System.out.println("Iteration: " + i);
				map.printTableMap();                                //wyswietlenie tablicy dwuwymiarowej			
			}
		}

	}
	
	/**
	 * @return Glowna lista obiektow.
	 */
	public static List<IObjectsOnBoard> getObjectList(){   // funkcja ta jest potrzebna do dodawania obiektow do listy, po ktorej iterujemy
		return objectList;
	}
	
	/**
	 * @return Lista obiektow do dodania przed kolejna iteracja.
	 */
	public static List<IObjectsOnBoard> getObjectsToAdd(){   // funkcja ta jest potrzebna do dodawania obiektow do listy, po ktorej iterujemy
		return objectsToAdd;
	}
	/**
	 * @return  Lista obiektow do usuniecia przed kolejna iteracja.
	 */
	public static List<IObjectsOnBoard> getObjectsToRemove(){   // funkcja ta jest potrzebna do dodawania obiektow do listy, po ktorej iterujemy
		return objectsToRemove;
	}
	
	/**
	 * @return Numer indeksu iteracji
	 */
	public static int getActualIteration() {
		return actualIteration;
	}
	
	/**
	 * @return Calkowita ilosc iteracji
	 */
	public static int getNumberOfIter() {
		return numberOfIter;
	}

	
	public static void main(String[] args) {
		
		IMap map = new MapSimple(12);
																                            // glowne parametry symulacji. Ilosci (po kolei):
		IObjectsOnBoardCreator objectsCreator = new ObjectsOnBoardCreator(15,1,10,5,5);		// Owca, Pies, Trawa, Zlodziej, Wilk

		Starter starter = new Starter(300, map, objectsCreator); 
		
		starter.runSimulation();
	
	}

}
