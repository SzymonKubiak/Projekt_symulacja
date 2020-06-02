package simulation;

public class Shepherd implements IShepherd{
	
	/**Metoda odpowiedzialna za usuniecie wrogow z mapy.
	 *Jesli wilk lub zlodziej jest aktywny, zmienia jego status na nieaktywny i wywoluje na nim metode disappear().
	 */
	@Override
	public void removeEnemies() {	
		
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
