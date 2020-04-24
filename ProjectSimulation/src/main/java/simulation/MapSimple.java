package simulation;

import java.util.Map;
import java.util.HashMap;



public class MapSimple implements IMap {
	
	public MapSimple(int size) {
		tableMap = new IObjectsOnBoard[size][size];
		objectsPositions= new HashMap<>();
	}
	
	private IObjectsOnBoard[][] tableMap;
	private Map<IObjectsOnBoard, Position> objectsPositions;
	
	@Override
	public void setPosition(IObjectsOnBoard object, Position position) {
		
		int targetX = position.getX();
		int targetY = position.getY();
		
		Position actualPosition = object.getPosition();
		int actualX = actualPosition.getX();
		int actualY = actualPosition.getY();
		
		tableMap[actualX][actualY]=null;
		tableMap[targetX][targetY]=object;
		
	}

	@Override
	public IObjectsOnBoard getObject(Position position) {
		int x= position.getX();
		int y= position.getY();
		
		return tableMap[x][y];
	}

	@Override
	public Position getObjectPosition(IObjectsOnBoard object) { //// Odczyt pozycji podanego obiektu
		Position position= objectsPositions.get(object);
		if(position!=null)return position;
		return null;
	}

	@Override
	public void createNewObject(IObjectsOnBoard object, Position position) {
		// TODO Auto-generated method stub
		
	}
}


