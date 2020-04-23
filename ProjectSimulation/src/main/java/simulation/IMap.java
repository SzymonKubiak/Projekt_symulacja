package simulation;

public interface IMap {

	
	void setPosition(IObjectsOnBoard object, Position position);
	
	IObjectsOnBoard getObject(Position position);
	
	Position getObjectPosition(IObjectsOnBoard object);
	
	void createMap(int size);
	
	void createNewObject(IObjectsOnBoard object, Position position);
}
