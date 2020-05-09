package simulation;

import java.util.Map;
import java.util.HashMap;



public class MapSimple implements IMap {
	
	
		
	public MapSimple(int size) {
		tableMap = new IObjectsOnBoard[size][size];
		objectsPositions= new HashMap<>();
		this.size=size;
	}
	
	private IObjectsOnBoard[][] tableMap;
	private Map<IObjectsOnBoard, Position> objectsPositions;
	private int size;
	
	@Override
	public boolean setPosition(IObjectsOnBoard object, Position position) {
		
		if(getObject(position)!=null) return false;
		objectsPositions.put(object, position);
		tableMap[position.getX()][position.getY()]=object;
		return true;
		
	}
	
	public void freePosition(IObjectsOnBoard object, Position position) {
		
		objectsPositions.remove(object);
		tableMap[position.getX()][position.getY()]=null;
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
	public boolean createNewObject(IObjectsOnBoard object, Position position) {
		
		if(getObject(position)==null) // je¿eli pozycja jest wolna osadz tam obiekt, zwroc true
		{
			tableMap[position.getX()][position.getY()]= object;
			objectsPositions.put(object, position);
			return true;
		}
		return false;
	}
	
	

	public boolean isTheMoveProperly(Position position, int move) { ////Metoda sprawdza, czy nie wyjezdzamy za granice planszy
		int x = position.getX();
		int y = position.getY();
		
		if(move==1) 
		{
			if(x==0) return false;
		}
		if(move==2)
		{
			if(y==size) return false;
		}
		if(move==3)
		{
			if(x==size) return false;
		}
		if(move==4)
		{
			if(y==0) return false;
		}
		return true;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	
}

