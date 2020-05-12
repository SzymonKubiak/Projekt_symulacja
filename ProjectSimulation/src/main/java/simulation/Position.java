package simulation;

public class Position {
	private int x;
	private int y;
	
	public Position(Position position) { ////Konstruktor pozycji s³u¿¹cy przy kopiowaniu
		
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
	
	Position positionAfterMove(int move) // u¿ywane do obliczenia nowej pozycji obiektu
	{
		if(move==1) {
			this.x=this.x-1;
		}
		if(move==2) {
			this.y=this.y+1;
		}
		if(move==3) {
			this.x=this.x+1;
		}
		if(move==4) {
			this.y=this.y-1;
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



