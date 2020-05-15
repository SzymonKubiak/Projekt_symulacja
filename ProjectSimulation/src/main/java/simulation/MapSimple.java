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
		tableMap[position.getX()][position.getY()]=object;                              //dodanie do tablicy
		objectsPositions.put(object, position);                                         //dodanie do hashmapy
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
	public void deleteObject(IObjectsOnBoard object) {                                  //czyszczenie tablicy i hashmapy przy calkowitym usuwaniu obiektu
		tableMap[object.getPosition().getX()][object.getPosition().getY()]=null;        //usuniecie z tablicy

		objectsPositions.remove(object);                                                //usuniecie z hashmapy
		
	}

	
	@Override
	public IObjectsOnBoard getObject(Position position) {
		int x= position.getX();
		int y= position.getY();
		
		return tableMap[x][y];
	}

	
	@Override
	public Position getObjectPosition(IObjectsOnBoard object) { // Odczyt pozycji podanego obiektu
		return objectsPositions.get(object);   					//get(Object key) - zwraca wartosc przypisaną do klucza 'key' lub null jesli do takiego klucza nie jest przypisana zadna wartosc
	}
	
	@Override
	public void printTableMap() {
		for(int i=0; i<size; i++) {
			for(int j=0 ; j<size; j++) {
				if(tableMap[j][i]==null)System.out.print(". ");
				else System.out.print( tableMap[j][i].toString() + " ");
			}
			System.out.println();
		}
	}

	
	@Override
	public boolean isTheMoveProperly(Position position, int move) { //Metoda sprawdza, czy nie wyjezdzamy za granice planszy
		int x = position.getX();
		int y = position.getY();
		
		if(move==1)                     //jesli (wylosujemy) ruch w gore
		{
			if(y==0) return false;
		}
		if(move==2)                     //jesli (wylosujemy) ruch w prawo
		{
			if(x==size-1) return false;  
		}
		if(move==3)                     //jesli (wylosujemy) ruch w dol
		{
			if(y==size-1) return false;
		}
		if(move==4)                     //jesli (wylosujemy) ruch w lewo
		{
			if(x==0) return false;
		}
		return true;
	}

	
	@Override
	public int getSize() {
		return this.size;
	}
	
	public int lookAroundForGrass(Position position) { 					//jako parametr przyjmuje klase której obiektow szuka
		
		int posX = position.getX();
		int posY = position.getY();
		for(IObjectsOnBoard obj : Starter.getObjectList()) {
			if(obj instanceof Grass && obj.getState())									//przeszukujemy tylko obiekty typu Grass
			{
			
				int grassPosX=obj.getPosition().getX();					//przypisania w celu skrocenia zapisu
				int grassPosY=obj.getPosition().getY();
				if(posX == grassPosX && posY-1==grassPosY) return 1;	//zwraca kierunek 1 (jest do góry)
				if(posX+1 == grassPosX && posY==grassPosY) return 2;	//zwraca kierunek 2 (jest na prawo)
				if(posX == grassPosX && posY+1==grassPosY) return 3;	//zwraca kierunek 3 (jest na dół)
				if(posX-1 == grassPosX && posY==grassPosY) return 4;	//zwraca kierunek 4 (jest na lewo)
				
			}
		}
		return 0;
		
	}
	
public int wolfLookAroundForSheep(Position position) { 
		
		int posX = position.getX();
		int posY = position.getY();
		for(IObjectsOnBoard obj : Starter.getObjectList()) {
			if(obj instanceof Sheep)									//przeszukujemy tylko obiekty typu Sheep
			{
				int sheepPosX=obj.getPosition().getX();					//przypisania w celu skrocenia zapisu
				int sheepPosY=obj.getPosition().getY();
				if(posX == sheepPosX && posY+1==sheepPosY) return 1;	//zwraca kierunek 1 (jest do góry)
				if(posX+1 == sheepPosX && posY==sheepPosY) return 2;	//zwraca kierunek 2 (jest na prawo)
				if(posX == sheepPosX && posY-1==sheepPosY) return 3;	//zwraca kierunek 3 (jest na dół)
				if(posX-1 == sheepPosX && posY==sheepPosY) return 4;	//zwraca kierunek 4 (jest na lewo)
				return 0;
			}
		}
		return 0;
		
	}

public boolean dogLookAroundForEnemies(Position position, int sightRange) { 
	
	int posX = position.getX();
	int posY = position.getY();
	for(IObjectsOnBoard obj : Starter.getObjectList()) {
		if(obj instanceof Wolf || obj instanceof Thief)									//przeszukujemy tylko obiekty typu Sheep
		{
			int dogPosX=obj.getPosition().getX();										//przypisania w celu skrocenia zapisu
			int dogPosY=obj.getPosition().getY();
			
			if(dogPosX-posX<sightRange && dogPosX-posX>-sightRange)
			{
				if(dogPosY-posY<sightRange && dogPosY-posY>-sightRange) return true;
			}
		}
	}
	return false;
	
	
}

public int thiefLookAroundForSheeps(Position position, int sightRange) {						 //zwraca ilosc owiec w zasiegu ataku
	
	int sheepCounter = 0;
	int posX = position.getX();
	int posY = position.getY();
	for(IObjectsOnBoard obj : Starter.getObjectList()) {
		if(obj instanceof Sheep)																//przeszukujemy tylko obiekty typu Sheep
		{
			int thiefPosX=obj.getPosition().getX();												//przypisania w celu skrocenia zapisu
			int thiefPosY=obj.getPosition().getY();
			
			if(thiefPosX-posX<sightRange && thiefPosX-posX>-sightRange)							// jezeli znajduje sie w zasiegu na osi x
			{
				if(thiefPosY-posY<sightRange && thiefPosY-posY>-sightRange) sheepCounter++;  	// jezeli znajduje sie w zasiegu na osi y
			}																					// gdy owca spelni oba warunki jest zwiekszana liczba owiec w zasiegu
		}
	}
	
	return sheepCounter;
	
}

public boolean isAnyEmptyFieldAround(Position position)
{
	int posX=position.getX();
	int posY=position.getY();
	if(posX+1<=size-1)							//warunki sa po to, aby uniknac proby dostania się do pol poza zakresem
	{
		if(tableMap[posX+1][posY]==null) return true;
	}
	if(posX-1>=0)
	{
		if(tableMap[posX-1][posY]==null) return true;
	}
	if(posY+1<=size-1)
	{
		if(tableMap[posX][posY+1]==null) return true;
	}
	if(posY-1>=0)
	{
		if(tableMap[posX][posY-1]==null) return true;
	}
	return false;
}

	
}

