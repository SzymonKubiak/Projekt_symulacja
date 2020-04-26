package simulation;

import java.util.LinkedList;
import java.util.List;

public class ObjectsOnBoardCreator implements IObjectsOnBoardCreator{
	public ObjectsOnBoardCreator(IMap map, int mapSize) {
		this.map = map;
		this.mapSize = mapSize;
		
	}
	
	IMap map;
	int mapSize;

	@Override
	public List<IObjectsOnBoard> create(int numS, int numD, int numG, int numT, int numW) { 
		
		List<IObjectsOnBoard> list = new LinkedList<>();
		
		for(int i=0; i<numS; i++) {
			list.add(new Sheep(map, mapSize));
		}
		
		for(int i=0; i<numD; i++) {
			list.add(new Dog(map, mapSize));
		}
		
		for(int i=0; i<numG; i++) {
			list.add(new Grass(map, mapSize));
		}
		
		for(int i=0; i<numT; i++) {
			list.add(new Thief(map, mapSize));
		}
		
		for(int i=0; i<numW; i++) {
			list.add(new Wolf(map, mapSize));
		}
		
		return list;
	}

}
