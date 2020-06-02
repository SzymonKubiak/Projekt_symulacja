package simulation;

import static org.junit.Assert.*;

import org.junit.Test;

public class SheepTest {
	IMap map = new MapSimple(10);
	Sheep sheep = new Sheep(map);

	@Test
	public void testMoveCloseToGoal() {
		Position goalPosition = new Position(5,5);
		Position sheepPosition = new Position(5,3);
		Position nextPosition = new Position(5,4);
		map.setPosition(sheep, sheepPosition);
		sheep.moveCloseToGoal(goalPosition);
		assertTrue(sheep.getPosition().equals(nextPosition));
		
	}

	@Test
	public void testIsAnyGrassInRange() {
		sheep.sightRange = 3;                              //na potrzeby testu ustawiamy sightRange = 3, aby testy byly poprawne po zmianie sightRange (domyslnie 1)
		Position sheepPosition = new Position(5,5);
		map.setPosition(sheep, sheepPosition);
		Grass grass1 = new Grass(map);
		Grass grass2 = new Grass(map);
		Position pos1 = new Position(2,5);
		Position pos2 = new Position(1,1);
		map.setPosition(grass1, pos1);
		map.setPosition(grass2, pos2);
		assertTrue(sheep.isAnyGrassInRange() == true);
		Position pos3 = new Position(2,4);
		map.changePosition(grass1, pos3);
		assertTrue(sheep.isAnyGrassInRange() == false);
	}

	@Test
	public void testGetTheNearestGrassInRange() {
		sheep.sightRange = 3;
		Position sheepPosition = new Position(5,5);
		map.setPosition(sheep, sheepPosition);
		Position pos1 = new Position(3,5);
		Position pos2 = new Position(5,2);
		Grass grass1 = new Grass(map);
		Grass grass2 = new Grass(map);
		map.setPosition(grass1, pos1);
		map.setPosition(grass2, pos2);
		assertTrue(sheep.getTheNearestGrassInRange() == grass1);
	}

}
