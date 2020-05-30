package simulation;

import static org.junit.Assert.*;

import org.junit.Test;

public class PositionTest {

	@Test
	public void testPositionAfterMove() {
		Position position = new Position(0,0);
		Position rightPosition = new Position(1,0);
		Position downPosition = new Position(0,1);
		assertNull(position.positionAfterMove(1, 4));  //gdy ruch poza mape zwraca null
		assertNull(position.positionAfterMove(4, 4));
		assertTrue(position.positionAfterMove(2, 4).equals(rightPosition));
		assertTrue(position.positionAfterMove(3, 4).equals(downPosition));
		
	}

}
