package simulation;

import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


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

public int squaredDistanceBetweenPositions(Position position1, Position position2){     //zwraca dystans pomiedzy pozycjami podniesiony do kwadratu
	int pos1X = position1.getX();
	int pos1Y = position1.getY();
	
	int pos2X = position2.getX();
	int pos2Y = position2.getY();
	
	int distanceX = pos1X-pos2X;
	int distanceY = pos1Y-pos2Y;
	
	return (distanceX*distanceX) + (distanceY*distanceY);
}
public List<IObjectsOnBoard> objectsInRangeList(Position position, float range){
	List<IObjectsOnBoard> objectsInRangeList = new ArrayList<>();
	for(IObjectsOnBoard obj : Starter.getObjectList()) {
		if(obj.getState()) {                                                                         //jesli jest aktywny
			int squaredDistance = this.squaredDistanceBetweenPositions(position, obj.getPosition()); //oblicz odlelosc do kwadratu
			if(squaredDistance <= range*range) {                                                     //jesli odleglosc do kwadratu <= zasieg do kwadratu
				objectsInRangeList.add(obj);                                                         //dodaj do listy
			}
		}
	}
	return objectsInRangeList;
}

public Sheep getTheNearestSheepInRange(Position position, float range) {
	List<IObjectsOnBoard> objectsInRangeList = this.objectsInRangeList(position, range);    //pobranie listy obiektow w zasiegu
	if(objectsInRangeList.size() == 0) return null;                                         //jesli pobrana lista jest pusta, zwroc null
	TreeMap<Integer, Sheep> sheepsInRangeMap = new TreeMap<>();                             //utworzenie TreeMap, ktora sortuje klucze (w tym przypadku kluczami sa liczby calkowite)
	for(IObjectsOnBoard obj : objectsInRangeList) {                                         //sprawdzamy, czy w liscie sa owce
		if( obj instanceof Sheep ) {                                                        //jesli tak dodajemy do mapy (odleglosc^2, owca)
			int squaredDistance = this.squaredDistanceBetweenPositions(position, obj.getPosition());
			sheepsInRangeMap.put(squaredDistance, (Sheep)obj );
		}
	}
	if(sheepsInRangeMap.size() == 0) return null;                                            //jesli nie bylo owiec, zwracamy null
	else {                                                     
		return sheepsInRangeMap.firstEntry().getValue();                                     //zwracamy najblizsza owce z mapy (czyli pierwsza w mapie)
	}
}


public Grass getTheNearestGrassInRange(Position position, float range) {
	List<IObjectsOnBoard> objectsInRangeList = this.objectsInRangeList(position, range);     //analogicznie jak getTheNearestSheepInRange();
	if(objectsInRangeList.size() == 0) return null;
	TreeMap<Integer, Grass> grassInRangeMap = new TreeMap<>();                                       
	for(IObjectsOnBoard obj : objectsInRangeList) {
		if( obj instanceof Grass) {
			int squaredDistance = this.squaredDistanceBetweenPositions(position, obj.getPosition());
			grassInRangeMap.put(squaredDistance, (Grass)obj );
		}
	}
	if(grassInRangeMap.size() == 0) return null;
	else {                                                                                            
		return grassInRangeMap.firstEntry().getValue();
	}
}


public boolean isAnyEmptyFieldAround(Position position){
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

@Override
public boolean isFreePlaceOnEdge() {
	for(int i=1 ; i<(size-1); i++) {                      //i=1 bo nie chcemy sprawdzac dwa razy rogow
		if(tableMap[i][0]==null)return true;              //sprawdzanie gory
		if(tableMap[i][size-1]==null)return true;         //sprawdzanie dolu
	}
	for(int i=0 ; i<size; i++) {
		if(tableMap[0][i]==null) return true;             //sprawdzanie lewej strony
		if(tableMap[size-1][i]==null) return true;        //sprawdzanie prawej strony
	}
	return false;
}


public List<Position> getListOfFreePlaces()		//Metoda analizuje tableMap i zwraca obiekty Position zawierające puste miejsca
{
	ArrayList<Position> freePlaces = new ArrayList<>();
	
	for(int i=0; i<size; i++)
	{
		for(int j = 0; j<size; j++)
		{
			if(tableMap[i][j]==null)	freePlaces.add(new Position(i,j));	// jezeli miejsce jest puste, to jego pozycje dodaje do listy
		}
	}
	
	return freePlaces;
}

}

