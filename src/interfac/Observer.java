package interfac;

import java.util.ArrayList;

import fr.unice.polytech.mediamanager.model.Film;

public interface Observer {

	  public void update(ArrayList<Film> list);
	public void update(Film film);
	public void update(Film film,boolean b);

}