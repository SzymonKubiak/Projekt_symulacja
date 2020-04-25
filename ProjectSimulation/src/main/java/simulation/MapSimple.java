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
	int size;
	
	@Override
	public void setPosition(IObjectsOnBoard object, Position position) {
		
		
		//ustawienie obiektu tylko w tablicy
		int targetX = position.getX();
		int targetY = position.getY();
		
		Position actualPosition = object.getPosition();
		int actualX = actualPosition.getX();
		int actualY = actualPosition.getY();
		
		tableMap[actualX][actualY]=null;
		tableMap[targetX][targetY]=object;
		
		//ustawienie w mapie (zakladajac, ze obiekt juz tam jest i ma przypisana wartosc)
		objectsPositions.remove(object);  //usuwanie wartosci przypisanej do klucza object, 
		objectsPositions.put(object, position); // nie wiem czy jest to poprawne przypisanie
		
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
}


