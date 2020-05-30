package simulation;

import static org.junit.Assert.*;

import org.junit.Test;

public class DogTest {
	
	@Test
	public void testIsAnyEnemyInRange() {
		MapSimple map = new MapSimple(10);
		Dog dog = new Dog(map);
		Wolf wolf = new Wolf(map);
		Thief thief = new Thief(map);
		
		Position dogPosition = new Position(5,5);
		Position Pos1 = new Position(1,5);
		Position Pos2 = new Position(9,5);
		Position Pos3 = new Position(2,5);
		Position Pos4 = new Position(5,2);
		Position Pos5 = new Position(0,5);
		Position Pos6 = new Position(5,8);
		
		
		map.setPosition(dog, dogPosition);
		wolf.isActive = true;
		
		map.setPosition(wolf, Pos1);	
		assertTrue(dog.isAnyEnemyInRange() == false);
		
		map.changePosition(wolf, Pos2);	
		assertTrue(dog.isAnyEnemyInRange() == false);
		
		map.changePosition(wolf, Pos3);	
		assertTrue(dog.isAnyEnemyInRange() == true);
		
		map.changePosition(wolf, Pos4);
		assertTrue(dog.isAnyEnemyInRange() == true);
		
		map.changePosition(wolf, Pos5);	
		assertTrue(dog.isAnyEnemyInRange() == false);
		
		map.changePosition(wolf, Pos6);		
		assertTrue(dog.isAnyEnemyInRange() == true);
				
		wolf.isActive = false;
		assertTrue(dog.isAnyEnemyInRange() == false);
		
		map.deleteObject(wolf);
		assertTrue(dog.isAnyEnemyInRange() == false);
		
		
		
		thief.isActive = true;
		map.setPosition(thief, Pos1);	
		assertTrue(dog.isAnyEnemyInRange() == true);
		
		map.changePosition(thief, Pos2);	
		assertTrue(dog.isAnyEnemyInRange() == true);
		
		map.changePosition(thief, Pos3);	
		assertTrue(dog.isAnyEnemyInRange() == true);
		
		map.changePosition(thief, Pos4);
		assertTrue(dog.isAnyEnemyInRange() == true);
		
		map.changePosition(thief, Pos5);	
		assertTrue(dog.isAnyEnemyInRange() == false);
		
		map.changePosition(thief, Pos6);		
		assertTrue(dog.isAnyEnemyInRange() == true);
		
		thief.isActive = false;
		assertTrue(dog.isAnyEnemyInRange() == false);
		
		map.deleteObject(thief);
		assertTrue(dog.isAnyEnemyInRange() == false);
		
	}

}
