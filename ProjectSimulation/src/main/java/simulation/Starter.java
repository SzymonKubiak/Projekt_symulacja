package simulation;

import java.util.List;

public class Starter {
	
public Starter(int numberOfIter, IMap map, IObjectsOnBoardCreator objectsCreator) {
		
		this.numberOfIter = numberOfIter;
		this.map = map;
		objectList = objectsCreator.create(map); //utorzenie listy obiektow
		
		for(IObjectsOnBoard o : objectList) { //musimy dodac wszystkie utworzone obiekty do kolekcji typu map
			map.setPosition(o, RandomGenerator.giveRandomPosition( map.getSize() ));
		}
			
	}


    private static List<IObjectsOnBoard> objectList;	
    IMap map;
    int numberOfIter;


	void runSimulation()
	{
		for(int i =0; i<numberOfIter; i++) {
			
			for(IObjectsOnBoard iObjectsOnBoard : objectList) 
				iObjectsOnBoard.makeTurn();
		}

	}
	
	public static List<IObjectsOnBoard> getObjectList(){   // funkcja ta jest potrzebna do dodawania obiektow do listy, po ktorej iterujemy
		return objectList;
	}
	
	
	
	public static void main(String[] args) {
		
		IMap map = new MapSimple(100);
		IObjectsOnBoardCreator objectsCreator = new ObjectsOnBoardCreator(20,2,25,2,3);
		Starter starter = new Starter(10, map, objectsCreator); 
		
		starter.runSimulation();
		
	}

}
