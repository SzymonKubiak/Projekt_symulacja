package simulation;

public class Shepherd implements IShepherd{
	
	@Override
	public void removeEnemies() {	// dla kazdego AKTYWNEGO wilka lub zlodzieja sprawia ze znikaja
		
		for(IObjectsOnBoard obj :Starter.getObjectList())	
		{
			if((obj instanceof Wolf || obj instanceof Thief) && obj.getState())
			{
				obj.setState(false);
				obj.disappear();
			}
		}
		
	}

	

}
