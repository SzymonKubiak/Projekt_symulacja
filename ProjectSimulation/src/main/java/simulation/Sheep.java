package simulation;

public class Sheep extends FarmAnimals {
	
	public int multiplicationPoints;
	
	void eatGrass()
	{
		
		
	}
	void multiplicate()
	{
		if(this.multiplicationPoints==10)
		{
			this.multiplicationPoints=0;
			
		//	IMap.createNewObject();//// dokonczyc po zrobieniu metody create new object z mapsimple!!
		}
	}
	@Override
	public void makeMove() {
		
		do {
			
			int moveDirection=RandomGenerator.giveRandomMove(); // gdzieœ wy¿ej trzeba wrzucic metode getMap() bo nie mamy innego sposobu na dostanie sie do mapy...
			
			
		} while(false);
		
	}
}
