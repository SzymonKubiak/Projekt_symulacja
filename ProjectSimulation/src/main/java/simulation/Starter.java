package simulation;

import java.util.List;

public class Starter {
	
public Starter(int numberofIter, int mapSize, int numS, int numD, int numG, int numT, int numW) {
		
		this.numberofIter = numberofIter;
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
	
	List<IObjectsOnBoard> objectList;
	IObjectsOnBoardCreator creator;
	IMap map;
	int numberofIter;
	int mapSize;
	int numS;
	int numD;
	int numG;
	int numT;
	int numW;
	
	
	

	
	public int getSize()
	{
		return mapSize;
	}
	
	
	void runSimulation()
	{
		
	}
	
	
	
	public static void main(String[] args) {
		
		//nie mozemy tutaj utworzyc ObjectsOnBoardCreator bo trzeba mu podac w konstruktorze konkretna mape, a ona jest tworzona dopiero w konstruktorze Starter
		Starter starter = new Starter(10,100,20,2,50,2,3);    
		
		starter.runSimulation();
		
	}

}
