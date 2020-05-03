package simulation;

import java.util.List;

public class Starter {
	
	List<IObjectsOnBoard> objectList;
	IObjectsOnBoardCreator creator;
	IMap map;
	int numberOfIter;
	int mapSize;
	int numS;
	int numD;
	int numG;
	int numT;
	int numW;
	
	
public Starter(int numberOfIter, int mapSize, int numS, int numD, int numG, int numT, int numW) {
		
		this.numberOfIter = numberOfIter;
		map = new MapSimple(mapSize);
		this.mapSize = mapSize;
		creator = new ObjectsOnBoardCreator(map, mapSize);
		objectList = creator.create(numS, numD, numG, numT, numW);
		
		this.numS = numS;
		this.numD = numD;
		this.numG = numG; 
		this.numT = numT; 
		this.numW = numW;
		
	}
	

	
	

	
	public int getSize()
	{
		return mapSize;
	}
	
	
	void runSimulation()
	{
		for(int i =0; i<numberOfIter; i++) {
			
			for(IObjectsOnBoard iObjectsOnBoard : objectList) 
				iObjectsOnBoard.makeTurn();
		}

	}
	
	
	
	public static void main(String[] args) {
		
		//nie mozemy tutaj utworzyc ObjectsOnBoardCreator bo trzeba mu podac w konstruktorze konkretna mape, a ona jest tworzona dopiero w konstruktorze Starter
		Starter starter = new Starter(10,100,20,2,50,2,3);    
		
		starter.runSimulation();
		
	}

}
