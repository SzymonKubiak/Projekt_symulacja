package simulation;

public class Position {
	public Position(){	
	}
	
	public Position(int x, int y){
		this.x=x;
		this.y=y;
	}
    
    /**
	 * @param x wspolrzedna x na mapie
	 */
	private int x;
	/**
	 * @param y wspolrzedna y na mapie 
	 */
	private int y;
	
	
	/**
	 * @return wspolrzedna x
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * @return wspolrzedna y
	 */
	public int getY(){
		return y;
	}
	
	/**Metoda pozwala na ustawienie wspolrzednej x 
	 */
	public void setX(int x){
		this.x=x;
	}
	
	/**Metoda pozwala na ustawienie wspolrzednej y
	 */
	public void setY(int y){
		this.y=y;
	}
	
	/**Metoda zwracajaca pozycje obiektu, ktora by zajal, gdyby obiekt wykonal ruch w zadanym kierunku
	 * Jesli wyszedłby poza mape zwraca null.
	 * @param move - dany kierunek ruchu
	 * @param mapSize - rozmiar mapy
	 * @return pozycje po ruchu w zadanym kierunku
	 */
	public Position positionAfterMove(int move, int mapSize){
		
		if(move==1) {                                                 //jesli gora
			if(this.y == 0) {
				return null;
			}
			else {
				return new Position(this.x,this.y-1);
			}	
		}
		if(move==2) {                                                 //jesli prawo
			if(this.x == mapSize-1) {
				return null;
			}
			else {
				return new Position(this.x+1,this.y);
			}			
		}
		if(move==3) {                                                 //jesli dol
			if(this.y == mapSize-1) {
				return null;
			}
			else {
				return new Position(this.x,this.y+1);
			}	
		}
		if(move==4) {                                                 //jesli lewo
			if(this.x == 0) {
				return null;
			}
			else {
				return new Position(this.x-1,this.y);
			}		
		}
		return null;
	}
	
	/**Nadpisanie metody equals
	 *Aby obiekty tej klasy byly porownywane na podstawie wspolrzednych.
	 */
	@Override	
	public boolean equals(Object o) {   			                                      
		if(o == this) return true;   	
		if(!(o instanceof Position)) return false;                                 //jezeli obiekt, ktory porownujemy nie jest obiektem klasy Position - false	
		Position position = (Position)o;	

		return(( this.getX() == position.getX() )&& ( this.getY() == position.getY() ));	
	}
			
}



