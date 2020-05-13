package simulation;


public interface IMap {

	
	boolean setPosition(IObjectsOnBoard object, Position position);
	
	boolean changePosition(IObjectsOnBoard object, Position position);
	
	void deleteObject(IObjectsOnBoard object);
	
	IObjectsOnBoard getObject(Position position);
	
	Position getObjectPosition(IObjectsOnBoard object);
	
	boolean isTheMoveProperly(Position position, int move);
	
	int getSize();
	
	int wolfLookAroundForSheep(Position position);
	
	int lookAroundForGrass(Position position);
	
	boolean dogLookAroundForEnemies(Position position, int sightRange);
	
	int thiefLookAroundForSheeps(Position position, int sightRange);

}
