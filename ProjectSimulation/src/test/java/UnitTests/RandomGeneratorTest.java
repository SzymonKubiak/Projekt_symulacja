package UnitTests;

import static org.junit.Assert.*;
import simulation.RandomGenerator;
import simulation.Position;

import org.junit.Test;

public class RandomGeneratorTest {

	@Test
	public void test() {
		
		/*
		 * Testy wybranych metod z klasy RandomGenerator
		 * 
		 * Testowane metody: giveRandomNumber(), giveRandomPosition(int size)
		 */
		
		//giveRandomNumber
		assertTrue(RandomGenerator.giveRandomNumber(4)<4);
		assertTrue(RandomGenerator.giveRandomNumber(1)<1);
		assertTrue(RandomGenerator.giveRandomNumber(999)<999);
		
		
		//giveRandomPosition
		Position pos1 = RandomGenerator.giveRandomPosition(10);
		assertTrue(pos1.getX()<10);
		assertTrue(pos1.getY()<10);
		
		pos1 = RandomGenerator.giveRandomPosition(100);
		assertTrue(pos1.getX()<100);
		assertTrue(pos1.getY()<100);
		
		//
	}

}
