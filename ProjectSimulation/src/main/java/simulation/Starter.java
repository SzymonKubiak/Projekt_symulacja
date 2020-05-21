package simulation;

import java.util.LinkedList;
import java.util.List;

public class Starter {
	
public Starter(int numberOfIter, IMap map, IObjectsOnBoardCreator objectsCreator) {
		
		Starter.numberOfIter = numberOfIter;
		this.map = map;
		objectList = objectsCreator.create(map);                //utorzenie listy obiektow
		objectsToAdd = new LinkedList<IObjectsOnBoard>();    //utworzenie dodatkowej listy na obiekty utworzone w trakcie iteracji
		objectsToRemove = new LinkedList<IObjectsOnBoard>();
		
		for(IObjectsOnBoard o : objectList) { //musimy dodac wszystkie utworzone obiekty do kolekcji typu map i przypisac im randomowa pozycje
			if(o instanceof Enemies);   //jesli to wilk/zlodziej nie dodawaj go
			else {
				while(!map.setPosition(o, RandomGenerator.giveRandomPosition( map.getSize() ) ) );
			}
			
			
		}
			
	}


    private static List<IObjectsOnBoard> objectList;
    private static List<IObjectsOnBoard> objectsToAdd;
    private static List<IObjectsOnBoard> objectsToRemove;
    private static int actualIteration;                             //bedzie ja potrzebowal wilk i zlodziej
    private static int numberOfIter;
    IMap map; 
    


	void runSimulation()
	{
		System.out.println("Starting map");
		map.printTableMap();                                    //wyswietlenie tablicy dwuwymiarowej
		System.out.println();
		
		for(int i=0; i<numberOfIter; i++) {
			actualIteration = i;
			
			for(IObjectsOnBoard iObjectsOnBoard : objectList) {
				iObjectsOnBoard.makeTurn();
			}
			objectList.addAll(objectsToAdd);					// po kazdej iteracji do glownej listy dodawane sa nowo powstale obiekty
			objectsToAdd.clear();                               // po dodaniu nalezy usunac wszystkie obiekty z dodatkowej listy
			objectList.removeAll(objectsToRemove);              // po kazdej iteracji z glownej listy usuwane sa obiekty, ktore trafily do listy objectsToRemove
			objectsToRemove.clear();                            // czyszczenie listy
			
			System.out.println("Iteration: " + i);
			map.printTableMap();                                //wyswietlenie tablicy dwuwymiarowej
			System.out.println();
			
		}

	}
	
	public static List<IObjectsOnBoard> getObjectList(){   // funkcja ta jest potrzebna do dodawania obiektow do listy, po ktorej iterujemy
		return objectList;
	}
	
	public static List<IObjectsOnBoard> getObjectsToAdd(){   // funkcja ta jest potrzebna do dodawania obiektow do listy, po ktorej iterujemy
		return objectsToAdd;
	}
	public static List<IObjectsOnBoard> getObjectsToRemove(){   // funkcja ta jest potrzebna do dodawania obiektow do listy, po ktorej iterujemy
		return objectsToRemove;
	}
	
	public static int getActualIteration() {
		return actualIteration;
	}
	
	public static int getNumberOfIter() {
		return numberOfIter;
	}
	
	public static void main(String[] args) {
		
		IMap map = new MapSimple(10);
		IObjectsOnBoardCreator objectsCreator = new ObjectsOnBoardCreator(10,0,10,5,1);
		Starter starter = new Starter(30, map, objectsCreator); 
		
		starter.runSimulation();
		
	}

}
