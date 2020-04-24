package simulation;

import java.util.LinkedList;
import java.util.List;

public class Starter {
	
	List<IObjectsOnBoard> objectList;
	IMap map;
	int numberofIter;
	int mapSize;
	
	public Starter(int numberofIter, int mapSize) {
		
		this.numberofIter = numberofIter;
		map = new MapSimple(mapSize);
		this.mapSize = mapSize;
		objectList = new LinkedList<>();
		
	}
	
	public int getSize()
	{
		return mapSize;
	}
	
	
	void runSimulation()
	{
		
	}
	
	
	
	public static void main(String[] args) {
		
		Starter starter = new Starter(10,10);
		
		starter.runSimulation();
		

	}

}
