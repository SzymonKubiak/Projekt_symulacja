package simulation;
import java.util.Random;
public class RandomGenerator {
	
	static int giveRandomMove() //// Generuje liczbê od 1 - 4.  Przyjmujemy kierunki: 1-góra, 2-prawo, 3-dó³, 4-lewo.
	{
		Random generator = new Random();
		int number = generator.nextInt(4)+1;
		return number;
	}
	
	
	static Position giveRandomPosition(int size) //// Generuje randomowe wspó³rzêdne na mapie i odsy³a obiekt Position
	{
		Random generator = new Random();
		Position position = new Position();
		
		position.setX(generator.nextInt(size));
		position.setY(generator.nextInt(size));
		
		return position;
	}
	
}
