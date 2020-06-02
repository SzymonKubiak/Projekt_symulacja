package simulation;

import static org.junit.Assert.*;

import org.junit.Test;

public class WolfTest {
	IMap map = new MapSimple(10);
	Wolf wolf = new Wolf(map);


	@Test
	public void testIsAnySheepInRange() {
		wolf.sightRange = 3;                               //na potrzeby testu ustawiamy sightRange = 3, aby testy byly poprawne po zmianie sightRange (domyslnie 4)
		Position wolfPosition = new Position(5,5);
		map.setPosition(wolf, wolfPosition);
		Sheep sheep1 = new Sheep(map);
		Sheep sheep2 = new Sheep(map);
		Position pos1 = new Position(2,5);
		Position pos2 = new Position(1,1);
		map.setPosition(sheep1, pos1);
		map.setPosition(sheep2, pos2);
		assertTrue(wolf.isAnySheepInRange() == true);
		Position pos3 = new Position(2,4);
		map.changePosition(sheep1, pos3);
		assertTrue(wolf.isAnySheepInRange() == false);
	}

	@Test
	public void testGetTheNearestSheepInRange() {
		wolf.sightRange = 3;
		Position wolfPosition = new Position(5,5);
		map.setPosition(wolf, wolfPosition);
		Position pos1 = new Position(3,5);
		Position pos2 = new Position(5,2);
		Sheep sheep1 = new Sheep(map);
		Sheep sheep2 = new Sheep(map);
		map.setPosition(sheep1, pos1);
		map.setPosition(sheep2, pos2);
		assertTrue(wolf.getTheNearestSheepInRange() == sheep1);
	}

}
