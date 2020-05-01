package simulation;

import java.util.LinkedList;
import java.util.List;

public class ObjectsOnBoardCreator implements IObjectsOnBoardCreator{
	public ObjectsOnBoardCreator(IMap map) {
		this.map = map;	
	}
	
	IMap map;

	@Override
	public List<IObjectsOnBoard> create(int numS, int numD, int numG, int numT, int numW) { 
		
		List<IObjectsOnBoard> list = new LinkedList<>();
		
		for(int i=0; i<numS; i++) {
			list.add(new Sheep(map));
		}
		
		for(int i=0; i<numD; i++) {
			list.add(new Dog(map));
		}
		
		for(int i=0; i<numG; i++) {
			list.add(new Grass(map));
		}
		
		for(int i=0; i<numT; i++) {
			list.add(new Thief(map));
		}
		
		for(int i=0; i<numW; i++) {
			list.add(new Wolf(map));
		}
		
		return list;
	}

	@Override
	public void addNewObject(IObjectsOnBoard object) {
		// TODO Auto-generated method stub
		
	}

}
