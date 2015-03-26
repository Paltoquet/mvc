package interfac;

import java.util.ArrayList;

import Ressource.Info;

public interface Observable {
	public void addObserver(Observer obs);

	public void removeObserver();

	public void notifyObserver();
}
