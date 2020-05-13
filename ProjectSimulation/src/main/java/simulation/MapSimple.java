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
		tableMap[position.getY()][position.getX()]=object;                              //dodanie do tablicy
		return true;
		
	}
	
	@Override
	public boolean changePosition(IObjectsOnBoard object, Position position) {          //dla obiektow majacych juz jakas pozycje
		if(getObject(position)!=null) return false;
		tableMap[ object.getPosition().getY() ][ object.getPosition().getX() ]=null;    //usuniecie z tablicy obiektu o starym polozeniu
		objectsPositions.replace(object, position);                                     //przypisanie nowej wartosci do klucza w hashmapie
		tableMap[position.getY()][position.getX()]=object;                              //ustawienie obiektu o nowym pozlozeniu w tablicy 
		return true;
	}
	
	
	@Override
	public void delateObject(IObjectsOnBoard object) {                                  //czyszczenie tablicy i hashmapy przy calkowitym usuwaniu obiektu
		tableMap[object.getPosition().getY()][object.getPosition().getX()]=null;        //usuniecie z tablicy
		objectsPositions.remove(object);                                                //usuniecie z hashmapy
		
	}

	
	@Override
	public IObjectsOnBoard getObject(Position position) {
		int x= position.getX();
		int y= position.getY();
		
		return tableMap[y][x];
	}

	
	@Override
	public Position getObjectPosition(IObjectsOnBoard object) { //// Odczyt pozycji podanego obiektu
		return objectsPositions.get(object);   //get(Object key) - zwraca wartosc przypisaną do klucza 'key' lub null jesli do takiego klucza nie jest przypisana zadna wartosc
	}

	
	@Override
	public boolean isTheMoveProperly(Position position, int move) { ////Metoda sprawdza, czy nie wyjezdzamy za granice planszy
		int x = position.getX();
		int y = position.getY();
		
		if(move==1) {                                //jesli (wylosujemy) ruch w gore
			if(y==(size-1)) return false;            //np. dla size=100, nie poruszmymy sie w gore, gdy y==99
		}
		
		if(move==2) {                                //jesli (wylosujemy) ruch w prawo
			if(x==(size-1)) return false;            //np. dla size=100, nie poruszmy sie w prawo, gdy x==99
		}
		
		if(move==3) {                                //jesli (wylosujemy) ruch w dol
			if(y==0) return false;                   //np. dla size=100, nie poruszymy sie w dol, gdy y==0
		}
		
		if(move==4) {                                //jesli (wylosujemy) ruch w lewo
			if(x==0) return false;                   //np. dla size=100, nie poruszymy sie w lewo, gdy x==0
		}
		return true;
	}

	
	@Override
	public int getSize() {
		return this.size;
	}

	
}

