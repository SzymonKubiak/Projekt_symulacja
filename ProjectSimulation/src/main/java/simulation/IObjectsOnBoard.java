package simulation;

public interface IObjectsOnBoard {

	void makeMove();
	void setMap(IMap map);
	IMap getMap();
	void disappear();
	Position getPosition();
}
