package simulation;

import java.util.LinkedList;
import java.util.List;

public class Starter {
	
public Starter(int numberOfIter, IMap map, IObjectsOnBoardCreator objectsCreator) {
		
		Starter.numberOfIter = numberOfIter;
		this.map = map;
		objectList = objectsCreator.create(map);                //utorzenie listy obiektow
		addedObjectsList= new LinkedList<IObjectsOnBoard>();    //utworzenie dodatkowej listy na obiekty utworzone w trakcie iteracji
		
		for(IObjectsOnBoard o : objectList) { //musimy dodac wszystkie utworzone obiekty do kolekcji typu map i przypisac im randomowa pozycje
			if(o instanceof Enemies);   //jesli to wilk/zlodziej nie dodawaj go
			else {
				while(!map.setPosition(o, RandomGenerator.giveRandomPosition( map.getSize() ) ) );
			}
			
			
		}
			
	}


    private static List<IObjectsOnBoard> objectList;
    private static List<IObjectsOnBoard> addedObjectsList;
    private static int actualIteration;                             //bedzie ja potrzebowal wilk i zlodziej
    private static int numberOfIter;
    IMap map; 
    


	void runSimulation()
	{
		for(int i=0; i<numberOfIter; i++) {
			actualIteration = i;
			
			System.out.println("Iteration: " + i);
			map.printTableMap();                                    //wyswietlenie tablicy dwuwymiarowej
			System.out.println();
			
			for(IObjectsOnBoard iObjectsOnBoard : objectList) {
				iObjectsOnBoard.makeTurn();
			}
			objectList.addAll(addedObjectsList);					// po kazdej iteracji do glownej listy dodawane sa nowo powstale obiekty
			addedObjectsList.clear();                               // po dodaniu nalezy usunac wszystkie obiekty z dodatkowej listy
			
		}

	}
	
	public static List<IObjectsOnBoard> getObjectList(){   // funkcja ta jest potrzebna do dodawania obiektow do listy, po ktorej iterujemy
		return objectList;
	}
	
	public static List<IObjectsOnBoard> getAddedObjectsList(){   // funkcja ta jest potrzebna do dodawania obiektow do listy, po ktorej iterujemy
		return addedObjectsList;
	}
	
	public static int getActualIteration() {
		return actualIteration;
	}
	
	public static int getNumberOfIter() {
		return numberOfIter;
	}
	
	public static void main(String[] args) {
		
		IMap map = new MapSimple(10);
		IObjectsOnBoardCreator objectsCreator = new ObjectsOnBoardCreator(0,0,0,0,1);
		Starter starter = new Starter(10, map, objectsCreator); 
		
		starter.runSimulation();
		
	}

}
