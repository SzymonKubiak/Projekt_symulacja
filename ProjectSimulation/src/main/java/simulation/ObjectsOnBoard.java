package simulation;

public abstract class ObjectsOnBoard implements IObjectsOnBoard {
	
	Position position;
	int sightRange;
	int movementSpeed;
	



	@Override
	public void setMap(IMap map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IMap getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void disappear() {
		// TODO Auto-generated method stub
		
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	

}
