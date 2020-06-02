package simulation;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class MapSimple implements IMap {
		
	/**
	 * Metoda tworzy tabele dwuwymiarowa, na ktorej reprezentowane beda obiekty.
	 * Tworzy takze HashMape ktora ulatwi dostanie sie do poszczegolnego obiektu.
	 * @param size - rozmiar mapy
	 */
	public MapSimple(int size) {
		tableMap = new IObjectsOnBoard[size][size];
		objectsPositions= new HashMap<>();
		this.size=size;
	}
	
	private IObjectsOnBoard[][] tableMap;
	private Map<IObjectsOnBoard, Position> objectsPositions;
	private int size;
	
	
	/**
	 *	Metoda sluzaca do ustawiania pozycji obiektu, ktory nie zostal jeszcze ulokowany na mapie
	 * 	@param  object - Obiekt ktory umieszczamy @param position - pozycja ktora bedzie mial.
	 */
	@Override
	public boolean setPosition(IObjectsOnBoard object, Position position) {             //dla obiektow nie majacych pozycji (dla nowo utworzonych)
		if(getObject(position)!=null) return false;
		tableMap[position.getX()][position.getY()]=object;                              //dodanie do tablicy
		objectsPositions.put(object, position);                                         //dodanie do hashmapy
		return true;
		
	}
	
	/**
	 *	Metoda sluzaca do zmiany pozycji obiektu, ktory byl juz wczesniej na mapie.
	 * 	@param  object - Obiekt ktory umieszczamy @param position - pozycja ktora bedzie mial.
	 */
	@Override
	public boolean changePosition(IObjectsOnBoard object, Position position) {          //dla obiektow majacych juz jakas pozycje
		if(getObject(position)!=null) return false;
		tableMap[ object.getPosition().getX() ][ object.getPosition().getY() ]=null;    //usuniecie z tablicy obiektu o starym polozeniu
		objectsPositions.replace(object, position);                                     //przypisanie nowej wartosci do klucza w hashmapie
		tableMap[position.getX()][position.getY()]=object;                              //ustawienie obiektu o nowym pozlozeniu w tablicy 
		return true;
	}
	
	
	/**
	 * Metoda sluzy do usuniecia danego obiektu z mapy.
	 * Usuwa obiekt z tablicy i HashMapy.
	 * @param object - obiekt, ktory chcemy usunac.
	 */
	@Override
	public void deleteObject(IObjectsOnBoard object) {                                  //czyszczenie tablicy i hashmapy przy calkowitym usuwaniu obiektu
		tableMap[object.getPosition().getX()][object.getPosition().getY()]=null;        //usuniecie z tablicy

		objectsPositions.remove(object);                                                //usuniecie z hashmapy
		
	}

	
	/**
	 *	Metoda zwraca obiekt znajdujacy sie na podanej pozycji.
	 * @param position - Pozycja, ktora sprawdzamy.
	 */
	@Override
	public IObjectsOnBoard getObject(Position position) {
		int x= position.getX();
		int y= position.getY();
		
		return tableMap[x][y];
	}

	
	/**
	 * Metoda zwraca pozycje danego obiektu.
	 * @param object - Obiekt ktorego pozycji szukamy.
	 */
	@Override
	public Position getObjectPosition(IObjectsOnBoard object) { // Odczyt pozycji podanego obiektu
		return objectsPositions.get(object);   					//get(Object key) - zwraca wartosc przypisaną do klucza 'key' lub null jesli do takiego klucza nie jest przypisana zadna wartosc
	}
	
	/**
	 *	Metoda pozwalajaca graficznie reprezentowac mape.
	 */
	@Override
	public void printTableMap() {
		for(int i=0; i<size; i++) {
			for(int j=0 ; j<size; j++) {
				if(tableMap[j][i]==null)System.out.print(". ");
				else System.out.print( tableMap[j][i].toString() + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	
	/**
	 *	Metoda sprawdza czy analizowany ruch nie wyjdzie poza mape.
	 *	@param position - Pozycja z ktorej sie ruszamy, 
	 *	@param move - kierunek ruchu.
	 */
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

	
	/**
	 * Metoda zwraca rozmiar mapy.
	 */
	@Override
	public int getSize() {
		return this.size;
	}

/**
 * Metoda zwraca odleglosc w linii prostej miedzy pozycjami podniesiona do kwadratu. Odleglosc ta nie zostaje spierwiastkowana,
 * po to aby zaoszczedzic mocy obliczeniowej i pamieci. Dzialanie metody i metod ktore z niej korzystaja byloby i tak identyczne.
 */
public int squaredDistanceBetweenPositions(Position position1, Position position2){     //zwraca dystans pomiedzy pozycjami podniesiony do kwadratu
	int pos1X = position1.getX();
	int pos1Y = position1.getY();
	
	int pos2X = position2.getX();
	int pos2Y = position2.getY();
	
	int distanceX = pos1X-pos2X;
	int distanceY = pos1Y-pos2Y;
	
	return (distanceX*distanceX) + (distanceY*distanceY);
}

/**
 * Metoda zwraca liste obiektow znajdujacych sie w zasiegu wzroku "sightRange" obiektu.
 */
public List<IObjectsOnBoard> objectsInRangeList(Position position, float range){
	List<IObjectsOnBoard> objectsInRangeList = new ArrayList<>();
	for(int i = 0 ; i<size ; i++) {
		for(int j = 0 ; j <size ; j++) {
			if(tableMap[i][j] != null) {
				Position pos = new Position(i,j);
				if(this.getObject(pos).getState()) {                                                                               //jesli jest aktywny
					int squaredDistance = this.squaredDistanceBetweenPositions(position, this.getObject(pos).getPosition());       //oblicz odlelosc do kwadratu
					if(squaredDistance <= range*range) {                                                                           //jesli odleglosc do kwadratu <= zasieg do kwadratu
						objectsInRangeList.add(this.getObject(pos));                                                               //dodaj do listy
					}
				}
			}
		}
	}
	return objectsInRangeList;
}


/**
 * Metoda zwraca wartosc logiczna, odpowiadajaca temu, czy wokol pozycji podanej jako argument jest jakies wolne pole.
 * Metoda uzywana po to, aby podczas ruchu, lub rozmnazania nie probowac tego dokonac kiedy wokolo sa wszystkie miejsca zajete.
 */
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

/**
 * Metoda sprawdza czy na krawedziach planszy sa wolne miejsca.
 * Uzywana przy generowaniu Wilka i Zlodzieja, ktorzy to pojawiaja sie wlasnie na krawedziach planszy.
 */
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


/**
 *	Metoda zwracajaca liste pustych miejsc, w ktorych mozliwe jest losowe generowanie obiektow, czy to na poczatku symulacji,
 *	czy odrastanie Trawy w dowolnym miejscu w trakcie trwania symulacji.
 */
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

