package simulation;
import java.util.Random;
public class RandomGenerator {
	
	public static int giveRandomMove() //// Generuje liczbê od 1 - 4.  Przyjmujemy kierunki: 1-góra, 2-prawo, 3-dó³, 4-lewo.
	{
		Random generator = new Random();
		int number = generator.nextInt(4)+1;
		return number;
	}
	
	
	public static Position giveRandomPosition(int size) //// Generuje randomowe wspó³rzêdne na mapie i odsy³a obiekt Position
	{
		Random generator = new Random();
		Position position = new Position();
		
		position.setX(generator.nextInt(size));   //dla size=100 losuje liczby z przedzialu od 0 do 99;
		position.setY(generator.nextInt(size));
		
		return position;
	}
	
	public static Position giveRandomPositionEnemy(int size) 
	{
		Random generator = new Random();
		int way = generator.nextInt(4) +1;
		if(way==1) {                       //wystartuje od gory
			return new Position(generator.nextInt(size) ,0);
		}
		if(way==2) {                       //od prawej strony
			return new Position(size-1, generator.nextInt(size));
		}
		if(way==3) {                       //od dolu
			return new Position(generator.nextInt(size) ,size-1);
		}
		if(way==4) {                       //od lewej strony
			return new Position(0, generator.nextInt(size));
		}
		return null;
	}
	
	public static int giveRandomNumber(int range)
	{
		Random generator = new Random();
		int number = generator.nextInt(range);
		return number;
	}
	
}
