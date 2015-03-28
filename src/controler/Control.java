package controler;

import fr.unice.polytech.mediamanager.model.*;
import view.Ajoutvue;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @Author Lucas Sauvage et Thibault Ober
 * Gère les controles ( modèle MVC)
 */
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
	Modifie un film et envoie le titre avantg modification et le nouveau film
	 */
	public boolean traiteModif(String ti, String genr, String resum, String dure,String acteurs,String realisateur,Film last){
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
		//on reprend le poster,l'id et le genre car trop compliqué ou non traité, la ficche est crée on l'envoie au manager
		Film nouv = new Film(last.getId(),ti,rea,acteur,last.getGenres(),a,last.getPoster(),resum);
		vue.reset();
		manager.traiteFilm(last.getTitle(), nouv);
		return true;
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

	//va voir si le film existe
	public void modifFilm(String str){
		manager.modifFilm(str);

	}
	//dit a la vue d'ouvir la fiche du film a modifier
	public void lanceModif(Film i){
		vue.visible();
		vue.openFilm(i);
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