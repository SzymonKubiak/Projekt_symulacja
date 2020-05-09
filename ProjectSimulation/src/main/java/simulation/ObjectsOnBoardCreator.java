package simulation;

import java.util.LinkedList;
import java.util.List;

public class ObjectsOnBoardCreator implements IObjectsOnBoardCreator{
	public ObjectsOnBoardCreator(int numS, int numD, int numG, int numT, int numW) {
		
		this.numS = numS;
		this.numD = numD;
		this.numG = numG; 
		this.numT = numT; 
		this.numW = numW;
	}
	public ObjectsOnBoardCreator() { //konstruktor domyslny, gdy nic nie podamy 
		
		this(20,2,25,2,3);
	}
	
	
	int numS;
	int numD;
	int numG;
	int numT;
	int numW;

	@Override
	public List<IObjectsOnBoard> create(IMap map) { 
		
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
