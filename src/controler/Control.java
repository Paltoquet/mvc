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
	/*
	affiche le formulaire
	 */
	public void ajouterfilm() {
		vue.visible();
	}

	/*
	crée le film en fonction des informations et informe le modèle
	 */
	public boolean retourfilm(String ti, String genr, String resum, String dure,String acteurs,String realisateur ) {
		int a=2;
		if(dure.equals("")){
			vue.visible();
			ajoutpopup("Veuillez rentrer une durée !");
			return false;
		}
		try{
			a=Integer.parseInt(dure);
		}
		catch(NumberFormatException e){
			vue.visible();
			ajoutpopup("Mauvais format de durée, chiffre uniquement!");
			return false;
		}
        if(ti.equals("")){
            vue.visible();
            ajoutpopup("Veuillez rentrer un titre !");
            return false;
        }
        if(genr.equals("")){
            vue.visible();
            ajoutpopup("Veuillez rentrer un genre !");
            return false;
        }
        if(resum.equals("")){
            vue.visible();
            ajoutpopup("Veuillez rentrer une résumé !");
            return false;
        }
        if(acteurs.equals("")){
            vue.visible();
            ajoutpopup("Veuillez rentrer un acteur !");
            return false;
        }
        if(realisateur.equals("")){
            vue.visible();
            ajoutpopup("Veuillez rentrer un réalisateur !");
            return false;
        }
		Director rea=new  Director(null,realisateur,null,null,null,null,null);
		ArrayList<Actor>acteur=new ArrayList<Actor>();
		Actor brad=new Actor(null,acteurs,null,null,null,null,null);
		acteur.add(brad);
		ArrayList<Genre>tipe=new ArrayList<Genre>();
        tipe.add(Genre.action);
		Film nouv = new Film("id",ti,rea,acteur,tipe,a,"resources/posters/unknownPoster.jpg",resum);
		manager.ajfilm(nouv);
		return true;
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
