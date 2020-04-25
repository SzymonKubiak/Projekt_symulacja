package simulation;

public interface IMap {

	
	void setPosition(IObjectsOnBoard object, Position position);
	
	IObjectsOnBoard getObject(Position position);
	
	Position getObjectPosition(IObjectsOnBoard object);
	
	void createNewObject(IObjectsOnBoard object, Position position);
	
	boolean isTheMoveProperly(Position position, int move);
}
