package simulation;

import java.util.List;

public interface IObjectsOnBoardCreator {
	
	List<IObjectsOnBoard> create(int numS, int numD, int numG, int numT, int numW);
	void addNewObject (IObjectsOnBoard object);

}
