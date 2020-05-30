package simulation;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class MapSimpleTest {

	IMap map = new MapSimple(10);
	IObjectsOnBoard dog = new Dog(map);
	Position dogPosition = new Position(3,3);
	
	@Test
	public void testSetPosition() {
		map.setPosition(dog, dogPosition);
		IObjectsOnBoard sheep = new Sheep(map);
		assertTrue(map.setPosition(sheep, dogPosition) == false);
		map.deleteObject(dog);
		assertTrue(map.setPosition(sheep, dogPosition) == true);
	}

	@Test
	public void testChangePosition() {
		map.setPosition(dog, dogPosition);
		IObjectsOnBoard sheep = new Sheep(map);
		Position sheepPosition = new Position(2,2);
		map.setPosition(sheep, sheepPosition);
		assertTrue(map.changePosition(sheep, dogPosition) == false);
		map.deleteObject(dog);
		assertTrue(map.changePosition(sheep, dogPosition) == true);
	}

	@Test
	public void testDeleteObject() {
		map.setPosition(dog, dogPosition);
		map.deleteObject(dog);
		assertTrue(map.getObject(dogPosition) == null);
	}

	@Test
	public void testGetObject() {
		assertTrue(map.getObject(dogPosition) == null);
		map.setPosition(dog, dogPosition);
		assertTrue(map.getObject(dogPosition) == dog);
	}

	@Test
	public void testGetObjectPosition() {
		assertTrue(map.getObjectPosition(dog) == null);
		map.setPosition(dog, dogPosition);
		assertTrue(map.getObjectPosition(dog) == dogPosition);
	}

	@Test
	public void testIsTheMoveProperly() {
		IMap map = new MapSimple(4);
		Position pos1 = new Position(0,0);
		Position pos2 = new Position(3,0);
		Position pos3 = new Position(0,3);
		Position pos4 = new Position(3,3);
		Position pos5 = new Position(2,2);
		
		assertTrue(map.isTheMoveProperly(pos1, 1) == false);
		assertTrue(map.isTheMoveProperly(pos1, 2) == true);
		assertTrue(map.isTheMoveProperly(pos1, 3) == true);
		assertTrue(map.isTheMoveProperly(pos1, 4) == false);
		
		assertTrue(map.isTheMoveProperly(pos2, 1) == false);
		assertTrue(map.isTheMoveProperly(pos2, 2) == false);
		assertTrue(map.isTheMoveProperly(pos2, 3) == true);
		assertTrue(map.isTheMoveProperly(pos2, 4) == true);
		
		assertTrue(map.isTheMoveProperly(pos3, 1) == true);
		assertTrue(map.isTheMoveProperly(pos3, 2) == true);
		assertTrue(map.isTheMoveProperly(pos3, 3) == false);
		assertTrue(map.isTheMoveProperly(pos3, 4) == false);
		
		assertTrue(map.isTheMoveProperly(pos4, 1) == true);
		assertTrue(map.isTheMoveProperly(pos4, 2) == false);
		assertTrue(map.isTheMoveProperly(pos4, 3) == false);
		assertTrue(map.isTheMoveProperly(pos4, 4) == true);
		
		assertTrue(map.isTheMoveProperly(pos5, 1) == true);
		assertTrue(map.isTheMoveProperly(pos5, 2) == true);
		assertTrue(map.isTheMoveProperly(pos5, 3) == true);
		assertTrue(map.isTheMoveProperly(pos5, 4) == true);
		
		
	}

	@Test
	public void testSquaredDistanceBetweenPositions() {
		Position pos1 = new Position(0,0);
		Position pos2 = new Position(0,5);
		Position pos3 = new Position(5,3);
		assertTrue(map.squaredDistanceBetweenPositions(pos1, pos2) == 25);
		assertTrue(map.squaredDistanceBetweenPositions(pos2, pos3) == 29);
		assertTrue(map.squaredDistanceBetweenPositions(pos1, pos3) == 34);	
	}

	@Test
	public void testObjectsInRangeList() {
		Position position = new Position(5,5);
		List<IObjectsOnBoard> objectsInRangeList = map.objectsInRangeList(position, 3);
		assertTrue(objectsInRangeList.size() == 0);
		
		IObjectsOnBoard grass = new Grass(map);
		IObjectsOnBoard wolf = new Wolf(map);
		IObjectsOnBoard sheep = new Sheep(map);
		
		
		Position grassPosition = new Position(0,0);
		Position wolfPosition = new Position(4,3);
		Position sheepPosition = new Position(5,8);
		
		map.setPosition(grass, grassPosition);
		map.setPosition(wolf, wolfPosition);
		map.setPosition(sheep, sheepPosition);
		
		objectsInRangeList = map.objectsInRangeList(position, 3);
	
		int quantity = 0;	
		for(IObjectsOnBoard o : objectsInRangeList) {  //sprawdzenie czy na tej liscie znajduje sie wczesniej utworzony wilk i owca (trawa nie moze znalezc sie na liscie bo jest poza zasiegiem)
			if((o == wolf)|| (o == sheep)) quantity++;
		}
		assertTrue(objectsInRangeList.size() == quantity);
		
		
	}

	@Test
	public void testIsAnyEmptyFieldAround() {
		assertTrue(map.isAnyEmptyFieldAround(dogPosition) == true);
		
		IObjectsOnBoard sheep1 = new Sheep(map);
		IObjectsOnBoard sheep2 = new Sheep(map);
		IObjectsOnBoard sheep3 = new Sheep(map);
		IObjectsOnBoard sheep4 = new Sheep(map);
		
		Position pos1 = new Position(3,4);
		Position pos2 = new Position(4,3);
		Position pos3 = new Position(3,2);
		Position pos4 = new Position(2,3);
		
		map.setPosition(sheep1, pos1);
		assertTrue(map.isAnyEmptyFieldAround(dogPosition) == true);
		
		map.setPosition(sheep2, pos2);
		assertTrue(map.isAnyEmptyFieldAround(dogPosition) == true);
		
		map.setPosition(sheep3, pos3);
		assertTrue(map.isAnyEmptyFieldAround(dogPosition) == true);
		
		map.setPosition(sheep4, pos4);
		assertTrue(map.isAnyEmptyFieldAround(dogPosition) == false);

	}

	@Test
	public void testIsFreePlaceOnEdge() {
		IMap map = new MapSimple(2);
		assertTrue(map.isFreePlaceOnEdge() == true);
		Position pos1 = new Position(0,0);
		Position pos2 = new Position(0,1);
		Position pos3 = new Position(1,0);
		Position pos4 = new Position(1,1);
		IObjectsOnBoard sheep = new Sheep(map);
		IObjectsOnBoard grass = new Grass(map);
		IObjectsOnBoard wolf = new Wolf(map);
		IObjectsOnBoard dog = new Dog(map);
		map.setPosition(sheep, pos1);
		map.setPosition(grass, pos2);
		map.setPosition(wolf, pos3);
		map.setPosition(dog, pos4);
		assertTrue(map.isFreePlaceOnEdge() == false);
		
	}

	@Test
	public void testGetListOfFreePlaces() {
		IMap map = new MapSimple(1);
		List<Position> list = map.getListOfFreePlaces();
		assertTrue(list.size() == 1);
		Position position = new Position(0,0);
		map.setPosition(dog, position);
		list = map.getListOfFreePlaces();
		assertTrue(list.size() == 0);
	}

}
