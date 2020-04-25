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
	
	public List<IObjectsOnBoard> createObjects(int numOfSheeps, int numOfGrass, int numOfWolves, int numOfThieves) //rozwa¿my czy nie zrobic z tego osobnej klasy tak jak Trajdos zrobil?
	{
		objectList = new LinkedList<>();
		
		for(int i=0; i<numOfSheeps; i++)
			objectList.add(new Sheep(map)); //// Czy czasem przekazanie jako parametru mapy nie wystarczy? Bo pozycja i tak bedzie randomowa chyba?
		
		for(int i=0; i<numOfGrass; i++)
			objectList.add(new Grass(map));
		
		for(int i=0; i<numOfWolves; i++)
			objectList.add(new Wolf(map));
		
		for(int i=0; i<numOfThieves; i++)
			objectList.add(new Thief(map));
		
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
		
		
		
		 /*ISpecimensCreator specCreat = new SpecimensCreator1(5,5);
		 Simulation sim = new Simulation(mapCreat, specCreat,1, 20);
		 sim.runSimulation();
		 System.out.println("END");
*/

	}

}
