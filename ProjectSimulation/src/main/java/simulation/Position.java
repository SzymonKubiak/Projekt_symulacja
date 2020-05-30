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
	
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}
	
	public Position positionAfterMove(int move, int mapSize)          //zwraca pozycje obiektu, ktora by zajal, gdyby obiekt wykonal ruch w zadanym kierunku, jesli wyjdzie po za mape zwraca null
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
	
	
	
	public String toString() {
		// TODO Auto-generated method stub
		return this.x+","+this.y;
	}
			
}



