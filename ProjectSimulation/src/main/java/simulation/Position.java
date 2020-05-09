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
	
	void positionAfterMove(int move) // u¿ywane do obliczenia nowej pozycji obiektu
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
	}
	
	boolean equals(IObjectsOnBoard o1, IObjectsOnBoard o2) {
		if( (o1.getPosition().getX()==o2.getPosition().getX() ) && (o1.getPosition().getY()==o2.getPosition().getY()) ) return true;
		return false;
		
	}


}
