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
	public boolean setPosition(IObjectsOnBoard object, Position position) {             //dla obiektow nie majacych pozycji (dla nowo utworzonych)
		if(getObject(position)!=null) return false;
		objectsPositions.put(object, position);                                         //dodanie do hashmapy
		tableMap[position.getX()][position.getY()]=object;                              //dodanie do tablicy
		return true;
		
	}
	
	@Override
	public boolean changePosition(IObjectsOnBoard object, Position position) {          //dla obiektow majacych juz jakas pozycje
		if(getObject(position)!=null) return false;
		tableMap[ object.getPosition().getX() ][ object.getPosition().getY() ]=null;    //usuniecie z tablicy obiektu o starym polozeniu
		objectsPositions.replace(object, position);                                     //przypisanie nowej wartosci do klucza w hashmapie
		tableMap[position.getX()][position.getY()]=object;                              //ustawienie obiektu o nowym pozlozeniu w tablicy 
		return true;
	}
	
	
	@Override
	public void delateObject(IObjectsOnBoard object) {                                  //czyszczenie tablicy i hashmapy przy calkowitym usuwaniu obiektu
		objectsPositions.remove(object);                                                //usuniecie z hashmapy
		tableMap[object.getPosition().getX()][object.getPosition().getY()]=null;        //usuniecie z tablicy
	}

	
	@Override
	public IObjectsOnBoard getObject(Position position) {
		int x= position.getX();
		int y= position.getY();
		
		return tableMap[x][y];
	}

	
	@Override
	public Position getObjectPosition(IObjectsOnBoard object) { //// Odczyt pozycji podanego obiektu
		return objectsPositions.get(object);   //get(Object key) - zwraca wartosc przypisaną do klucza 'key' lub null jesli do takiego klucza nie jest przypisana zadna wartosc
	}

	
	@Override
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

