package simulation;

import java.util.List;

public interface IMap {

	
	boolean setPosition(IObjectsOnBoard object, Position position);
	
	boolean changePosition(IObjectsOnBoard object, Position position);
	
	void deleteObject(IObjectsOnBoard object);
	
	IObjectsOnBoard getObject(Position position);
	
	Position getObjectPosition(IObjectsOnBoard object);
	
	void printTableMap();
	
	boolean isTheMoveProperly(Position position, int move);
	
	boolean isFreePlaceOnEdge();
	
	//boolean isFreePlaceOnMap();
	
	int getSize();
	
	boolean isAnyEmptyFieldAround(Position position);
	
	Sheep getTheNearestSheepInRange(Position position, float range);
	
	Grass getTheNearestGrassInRange(Position position, float range);
	
	int squaredDistanceBetweenPositions(Position position1, Position position2);
	
	List<IObjectsOnBoard> objectsInRangeList(Position position, float range);
	
	List<Position> getListOfFreePlaces();

}
