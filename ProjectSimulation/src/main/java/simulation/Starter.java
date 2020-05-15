package simulation;

import java.util.LinkedList;
import java.util.List;

public class Starter {
	
public Starter(int numberOfIter, IMap map, IObjectsOnBoardCreator objectsCreator) {
		
		this.numberOfIter = numberOfIter;
		this.map = map;
		objectList = objectsCreator.create(map);                //utorzenie listy obiektow
		addedObjectsList= new LinkedList<IObjectsOnBoard>();    //utworzenie dodatkowej listy na obiekty utworzone w trakcie iteracji
		
		for(IObjectsOnBoard o : objectList) { //musimy dodac wszystkie utworzone obiekty do kolekcji typu map i przypisac im randomowa pozycje
			
			while(!map.setPosition(o, RandomGenerator.giveRandomPosition( map.getSize() ) ) );
			
		}
			
	}


    private static List<IObjectsOnBoard> objectList;
    private static List<IObjectsOnBoard> addedObjectsList;
    IMap map;
    int numberOfIter;


	void runSimulation()
	{
		for(int i =0; i<numberOfIter; i++) {
			System.out.println("Iteration: " + i);
			map.printTableMap();               //wyswietlenie tablicy dwuwymiarowej
			System.out.println();
			objectList.addAll(addedObjectsList);					// przed kazda iteracja do listy dodawane s¹ nowo powstale obiekty
			for(IObjectsOnBoard iObjectsOnBoard : objectList) {
				iObjectsOnBoard.makeTurn();
			}
			
		}

	}
	
	public static List<IObjectsOnBoard> getObjectList(){   // funkcja ta jest potrzebna do dodawania obiektow do listy, po ktorej iterujemy
		return objectList;
	}
	
	public static List<IObjectsOnBoard> getAddedObjectsList(){   // funkcja ta jest potrzebna do dodawania obiektow do listy, po ktorej iterujemy
		return addedObjectsList;
	}
	
	
	
	public static void main(String[] args) {
		
		IMap map = new MapSimple(10);
		IObjectsOnBoardCreator objectsCreator = new ObjectsOnBoardCreator(1,0,5,0,0);
		Starter starter = new Starter(30, map, objectsCreator); 
		
		starter.runSimulation();
		
	}

}
