package simulation;

public class Position {
	public Position(){	
	}
	public Position(int x, int y){
		this.x=x;
		this.y=y;
	}
    public Position(Position position) { ////Konstruktor pozycji sluzacy przy kopiowaniu
		
		this.x=position.getX();
		this.y=position.getY();
	}
    
	private int x;
	private int y;
	
	
	int getX(){
		return x;
	}
	
	int getY(){
		return y;
	}
	
	void setX(int x){
		this.x=x;
	}
	
	void setY(int y){
		this.y=y;
	}
	
	Position positionAfterMove(int move, int mapSize)          //zwraca pozycje obiektu, ktora by zajal, gdyby obiekt wykonal ruch w zadanym kierunku, jesli wyjdzie po za mape zwraca null
	{
		
		if(move==1) {                                 //jesli gora
			if(this.y == 0) {
				return null;
			}
			else {
				return new Position(this.x,this.y-1);
			}	
		}
		if(move==2) {                                 //jesli prawo
			if(this.x == mapSize-1) {
				return null;
			}
			else {
				return new Position(this.x+1,this.y);
			}			
		}
		if(move==3) {                                 //jesli dol
			if(this.y == mapSize-1) {
				return null;
			}
			else {
				return new Position(this.x,this.y+1);
			}	
		}
		if(move==4) {                                 //jesli lewo
			if(this.x == 0) {
				return null;
			}
			else {
				return new Position(this.x-1,this.y);
			}		
		}
		return null;
	}
	
	@Override
	public boolean equals(Object o) {   //equals, ktore porownuje pozycje
		if(o == this) return true;   
		if(!(o instanceof Position)) return false;  //jezeli obiekt, ktory porownujemy nie jest obiektem kalsy Position - false
		Position position = (Position)o;
		
		return(( this.getX() == position.getX() )&& ( this.getY() == position.getY() ));
	}
	
	
	public String toString() {
		// TODO Auto-generated method stub
		return this.x+","+this.y;
	}
			
}



