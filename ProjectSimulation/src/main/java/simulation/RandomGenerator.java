package simulation;
import java.util.Random;
public class RandomGenerator {
	
	static int giveRandomMove() //// Generuje liczb� od 1 - 4.  Przyjmujemy kierunki: 1-g�ra, 2-prawo, 3-d�, 4-lewo.
	{
		Random generator = new Random();
		int number = generator.nextInt(4)+1;
		return number;
	}
	
	
	static Position giveRandomPosition(int size) //// Generuje randomowe wsp�rz�dne na mapie i odsy�a obiekt Position
	{
		Random generator = new Random();
		Position position = new Position();
		
		position.setX(generator.nextInt(size));   //dla size=100 losuje liczby z przedzialu od 0 do 99;
		position.setY(generator.nextInt(size));
		
		return position;
	}
	
	static int giveRandomNumber(int range)
	{
		Random generator = new Random();
		int number = generator.nextInt(range);
		return number;
	}
	
}
