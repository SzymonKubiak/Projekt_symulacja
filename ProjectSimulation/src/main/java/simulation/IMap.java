package simulation;


public interface IMap {

	
	boolean setPosition(IObjectsOnBoard object, Position position);
	
	void freePosition(IObjectsOnBoard object, Position position);
	
	IObjectsOnBoard getObject(Position position);
	
	Position getObjectPosition(IObjectsOnBoard object);
	
	boolean createNewObject(IObjectsOnBoard object, Position position);
	
	boolean isTheMoveProperly(Position position, int move);
	
	int getSize();
}
