package simulation;


public interface IMap {

	
	boolean setPosition(IObjectsOnBoard object, Position position);
	
	boolean changePosition(IObjectsOnBoard object, Position position);
	
	void delateObject(IObjectsOnBoard object);
	
	IObjectsOnBoard getObject(Position position);
	
	Position getObjectPosition(IObjectsOnBoard object);
	
	boolean isTheMoveProperly(Position position, int move);
	
	int getSize();

}
