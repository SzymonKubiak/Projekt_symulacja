package simulation;

public class Position {
	private int x;
	private int y;
	
	public Position(Position position) { ////Konstruktor pozycji s�u��cy przy kopiowaniu
		
		this.x=position.getX();
		this.y=position.getY();
	}
	
	public Position()
	{
		
	}
	
	int getX()
	{
		return x;
	}
	
	int getY()
	{
		return y;
	}
	
	void setX(int x)
	{
		this.x=x;
	}
	
	void setY(int y)
	{
		this.y=y;
	}
	
	Position positionAfterMove(int move) // uzywane do obliczenia nowej pozycji obiektu
	{
		if(move==1) {              //jesli gora
			this.y=this.y+1;
		}
		if(move==2) {              //jesli prawo
			this.x=this.x+1;
		}
		if(move==3) {              //jesli dol
			this.y=this.y-1;
		}
		if(move==4) {              //jesli lewo
			this.x=this.x-1;
		}
		return this;
	}
	
	@Override
	public boolean equals(Object o) {   //equals, ktore porownuje pozycje
		if(o == this) return true;   
		if(!(o instanceof Position)) return false;  //jezeli obiekt, ktory porownujemy nie jest obiektem kalsy Position - false
		Position position = (Position)o;
		
		return(( this.getX() == position.getX() )&& ( this.getY() == position.getY() ));
	}
			
}



