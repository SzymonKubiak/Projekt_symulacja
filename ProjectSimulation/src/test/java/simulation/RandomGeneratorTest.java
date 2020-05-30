package simulation;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomGeneratorTest {
	
	/*
	 * Testy wybranych metod z klasy RandomGenerator
	 * 
	 * Testowane metody: giveRandomNumber(), giveRandomPosition(int size)
	 */

	@Test
	public void testGiveRandomPosition() {
		Position pos1 = RandomGenerator.giveRandomPosition(10);
		assertTrue(pos1.getX()<10);
		assertTrue(pos1.getY()<10);
		
		pos1 = RandomGenerator.giveRandomPosition(100);
		assertTrue(pos1.getX()<100);
		assertTrue(pos1.getY()<100);
	}

	@Test
	public void testGiveRandomNumber() {
		assertTrue(RandomGenerator.giveRandomNumber(4)<4);
		assertTrue(RandomGenerator.giveRandomNumber(1)<1);
		assertTrue(RandomGenerator.giveRandomNumber(999)<999);
	}

}
