package simulation;

public interface IObjectsOnBoard {

	void setMap(IMap map);
	IMap getMap();
	void disappear();
	Position getPosition();
	void makeTurn();
	
}
