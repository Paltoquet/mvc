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


	public void retourfilm(String ti, String genr, String resum, String dure,String acteurs,String realisateur ) {
		int a=2;
		if(dure.equals("")){
			vue.visible();
			ajoutpopup("Veuillez rentrer une durée !");
			return;
		}
		try{
			a=Integer.parseInt(dure);
		}
		catch(NumberFormatException e){
			vue.visible();
			ajoutpopup("Mauvais format de durée, chiffre uniquement!");
			return;
		}
        if(ti.equals("")){
            vue.visible();
            ajoutpopup("Veuillez rentrer un titre !");
            return;
        }
        if(genr.equals("")){
            vue.visible();
            ajoutpopup("Veuillez rentrer un genre !");
            return;
        }
        if(resum.equals("")){
            vue.visible();
            ajoutpopup("Veuillez rentrer une résumé !");
            return;
        }
        if(acteurs.equals("")){
            vue.visible();
            ajoutpopup("Veuillez rentrer un acteur !");
            return;
        }
        if(realisateur.equals("")){
            vue.visible();
            ajoutpopup("Veuillez rentrer un réalisateur !");
            return;
        }
		Director rea=new  Director(null,realisateur,null,null,null,null,null);
		ArrayList<Actor>acteur=new ArrayList<Actor>();
		Actor brad=new Actor(null,acteurs,null,null,null,null,null);
		acteur.add(brad);
		ArrayList<Genre>tipe=new ArrayList<Genre>();
        tipe.add(Genre.action);
		Film nouv = new Film("id",ti,rea,acteur,tipe,a,"resources/posters/unknownPoster.jpg",resum);
		System.out.println(ti);
		manager.ajfilm(nouv);
		return;
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
