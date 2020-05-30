package simulation;

public interface IObjectsOnBoard {

	void disappear();
	Position getPosition();
	void makeTurn();
	boolean getState();
	void setState(boolean state);
	
}
