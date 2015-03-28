package controler;

import fr.unice.polytech.mediamanager.model.*;
import Ressource.Info;
import view.Ajoutvue;

import javax.swing.*;
import java.util.ArrayList;

public class Control {
	public Manager manager;
	public Ajoutvue vue;
    public Genre genre;

	public void Control() {

	}

	public void ajouterfilm() {
		vue.visible();
	}


	public Film retourfilm(String ti, String genr, String resum, String dure,String acteurs,String realisateur ) {
		if(dure.equals("")){
			vue.visible();
			ajoutpopup("Veuillez rentrer une durée !");
			return null;//new Film(null,null,null,null,null,0,null,null);
		}
		int a;
		try{
			a = Integer.parseInt(dure);
		}
		catch(Exception e){

		}
        if(ti.equals("")){
            vue.visible();
            ajoutpopup("Veuillez rentrer un titre !");
            return null;
        }
        if(genr.equals("")){
            vue.visible();
            ajoutpopup("Veuillez rentrer un genre !");
            return null;
        }
        if(resum.equals("")){
            vue.visible();
            ajoutpopup("Veuillez rentrer une résumé !");
            return null;
        }
        if(acteurs.equals("")){
            vue.visible();
            ajoutpopup("Veuillez rentrer un acteur !");
            return null;
        }
        if(realisateur.equals("")){
            vue.visible();
            ajoutpopup("Veuillez rentrer un réalisateur !");
            return null;
        }
		Director rea=new  Director(null,realisateur,null,null,null,null,null);
		ArrayList<Actor>acteur=new ArrayList<Actor>();
		Actor brad=new Actor(null,acteurs,null,null,null,null,null);
		acteur.add(brad);
		ArrayList<Genre>tipe=new ArrayList<Genre>();
        tipe.add(Genre.action);
		Film nouv = new Film("id",ti,rea,acteur,tipe,8,"resources/posters/unknownPoster.jpg",resum);
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

    public void rechercher(String film) {
        manager.searchFilm(film);
    }

    public void ajoutpopup(String error) {
        JOptionPane.showMessageDialog(vue, error, "Erreur !", JOptionPane.ERROR_MESSAGE);
    }

}
