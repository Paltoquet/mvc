package controler;

import fr.unice.polytech.mediamanager.model.Actor;
import fr.unice.polytech.mediamanager.model.Director;
import fr.unice.polytech.mediamanager.model.Film;
import fr.unice.polytech.mediamanager.model.Manager;
import Ressource.Info;
import view.Ajoutvue;

import java.util.ArrayList;

public class Control {
	public Manager manager;
	public Ajoutvue vue;

	public void Control() {

	}

	public void ajouterfilm() {
		vue.visible();
	}

	public Film retourfilm(String ti, String genr, String resum, String dure,String acteurs,String realisateur ) {
		Director rea=new  Director(null,realisateur,null,null,null,null,null);
		ArrayList<Actor>acteur=new ArrayList<Actor>();
		Actor brad=new Actor(null,acteurs,null,null,null,null,null);
		acteur.add(brad);
		Film nouv = new Film("id",ti,rea,null,null,2,null,null);
		System.out.println(ti);
		manager.ajfilm(nouv);
		return nouv;
	}

	public void supprimerfilm(String str) {
		manager.suppfilm(str);
	}

	public void setManager(Manager m) {
		manager = m;
	}

	public void setView(Ajoutvue vu) {
		vue = vu;

	}

	public void getallfilm() {
		manager.getallfilm();
	}
}
