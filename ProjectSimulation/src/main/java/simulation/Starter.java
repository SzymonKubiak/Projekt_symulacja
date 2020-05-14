package simulation;

import java.util.List;

public class Starter {
	
public Starter(int numberOfIter, IMap map, IObjectsOnBoardCreator objectsCreator) {
		
		this.numberOfIter = numberOfIter;
		this.map = map;
		objectList = objectsCreator.create(map); //utorzenie listy obiektow
		
		for(IObjectsOnBoard o : objectList) { //musimy dodac wszystkie utworzone obiekty do kolekcji typu map i przypisac im randomowa pozycje
			
			while(!map.setPosition(o, RandomGenerator.giveRandomPosition( map.getSize() ) ) );
			
		}
			
	}


    private static List<IObjectsOnBoard> objectList;	
    IMap map;
    int numberOfIter;


	void runSimulation()
	{
		for(int i =0; i<numberOfIter; i++) {
			
			for(IObjectsOnBoard iObjectsOnBoard : objectList) {
				iObjectsOnBoard.makeTurn();
			}
			map.printTableMap();               //wyswietlenie tablicy dwuwymiarowej
			System.out.println();
		}

	}
	
	public static List<IObjectsOnBoard> getObjectList(){   // funkcja ta jest potrzebna do dodawania obiektow do listy, po ktorej iterujemy
		return objectList;
	}
	
	
	
	public static void main(String[] args) {
		
		IMap map = new MapSimple(12);
		IObjectsOnBoardCreator objectsCreator = new ObjectsOnBoardCreator(5,1,15,2,1);
		Starter starter = new Starter(1, map, objectsCreator); 
		
		starter.runSimulation();
		
	}

}
