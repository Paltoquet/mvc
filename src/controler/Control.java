package controler;

import fr.unice.polytech.mediamanager.model.*;
import Ressource.Info;
import view.Ajoutvue;

import javax.swing.*;
import java.util.ArrayList;

public class Control {
	public Manager manager;
	public Ajoutvue vue;

	public void Control() {

	}

	public void ajouterfilm() {
		vue.visible();
	}


	public Film retourfilm(String ti, Genre genr, String resum, String dure,String acteurs,String realisateur ) {
		if(dure.equals("")){
			vue.visible();
			ajoutpopup("Veuillez rentrer une durée !");
			return null;//new Film(null,null,null,null,null,0,null,null);
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
		int a = Integer.parseInt(dure);
		Director rea=new  Director(null,realisateur,null,null,null,null,null);
		ArrayList<Actor>acteur=new ArrayList<Actor>();
		Actor brad=new Actor(null,acteurs,null,null,null,null,null);
		acteur.add(brad);
		ArrayList<Genre>tipe=new ArrayList<Genre>();
		tipe.add(genr);
		Film nouv = new Film("id",ti,rea,acteur,tipe,a,"resources/posters/unknownPoster.jpg",resum);
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
        JOptionPane.showMessageDialog(vue,error,"Erreur !", JOptionPane.ERROR_MESSAGE);
    }

}
