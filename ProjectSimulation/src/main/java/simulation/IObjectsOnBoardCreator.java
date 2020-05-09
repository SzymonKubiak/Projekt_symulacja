package simulation;

import java.util.List;

public interface IObjectsOnBoardCreator {
	
	List<IObjectsOnBoard> create(IMap map);
	void addNewObject (IObjectsOnBoard object);

}
