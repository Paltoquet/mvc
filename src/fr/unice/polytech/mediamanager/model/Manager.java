package fr.unice.polytech.mediamanager.model;

import interfac.Observable;
import interfac.Observer;

import java.util.ArrayList;

import fr.inria.acacia.corese.api.*;
import fr.inria.acacia.corese.exceptions.EngineException;

/**
 * Classe qui permet la gestion des medias.
 *
 * @author Brel Christian <brel@polytech.unice.fr>
 * @version 05/06/2009
 */
public class Manager implements IManager,Observable {

    public ArrayList<Film> list;//notre liste de film
    private ArrayList<Observer> listObserver;
    public EngineFactory ef;
    public IEngine engine;

    /**
     * Constructeur.
     */
    public Manager() {
        list = new ArrayList<Film>();
        ef = new EngineFactory();
        engine = ef.newInstance();
        listObserver = new ArrayList<Observer>();

        try {
            // load a single file (with a suffix .rdf .rdfs .owl .rul .xhtml, .nt or .nts)
            engine.load("resources/schemas");
            engine.load("resources/annotations");
            // it is also possible to load a whole directory
//		  	engine.load("path/to/the/directory/to/load");
            // run rules
            engine.runRuleEngine();
        } catch (EngineException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de recharger le moteur Corese.
     */
    private void reloadEngine() {

        System.out.println("RELOAD CORESE ENGINE");
        engine = ef.newInstance();

        try {
            // load a single file (with a suffix .rdf .rdfs .owl .rul .xhtml, .nt or .nts)
            engine.load("resources/schemas");
            engine.load("resources/annotations");
            // it is also possible to load a whole directory
//		  	engine.load("path/to/the/directory/to/load");
            // run rules
            engine.runRuleEngine();
        } catch (EngineException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de recuperer tous les films de la base de donnees.
     *
     * @return les films contenus dans la base de donnees.
     */
    public ArrayList<Film> getAllFilms() {
        return Film.getAll(this.engine);
    }

    /**
     * Permet de recuperer tous les genres de film de la base de donnees.
     *
     * @return les genres de films contenus dans la base de donnees.
     */
    public ArrayList<Genre> getAllGenres() {
        Genre[] genresArray = Genre.values();
        ArrayList<Genre> genres = new ArrayList<Genre>();
        for (Genre g : genresArray) {
            genres.add(g);
        }
        return genres;
    }

    /**
     * Permet de recuperer la liste des acteurs de la base de donnees.
     *
     * @return les acteurs contenus dans la base de donnees.
     */
    public ArrayList<Actor> getAllActors() {
        return Actor.getAll(this.engine);
    }

    /**
     * Permet de recuperer la liste des realisateurs de la base de donnees.
     *
     * @return les realisateurs contenus dans la base de donnees.
     */
    public ArrayList<Director> getAllDirectors() {
        return Director.getAll(this.engine);
    }

    /**
     * Permet de recuperer la liste des nationalites de la base de donnees.
     * (La liste n'est pas exhaustive)
     *
     * @return les nationalites contenus dans la base de donnees.
     */
    public ArrayList<Nationality> getAllNationalities() {
        Nationality[] nationalitiesArray = Nationality.values();
        ArrayList<Nationality> nationalities = new ArrayList<Nationality>();
        for (Nationality n : nationalitiesArray) {
            nationalities.add(n);
        }
        return nationalities;
    }

    /**
     * Permet de rechercher des films par leur titre.
     * (L'espace est le separateur pris par defaut. Cette methode renvoie
     * tous les films dont le titre contient au moins un mot du titre passe en parametre)
     *
     * @param title le titre du film recherche
     * @return une liste de films correspondant a la recherche.
     */
    public ArrayList<Film> searchByTitle(String title) {
        return Film.searchByTitle(engine, title);
    }

    /**
     * Permet de rechercher les films par genre.
     *
     * @param genre genre des films recherches
     * @return une liste de films correspondant a la recherche.
     */
    public ArrayList<Film> searchByGenre(Genre genre) {
        return Film.searchByGenre(engine, genre);
    }

    /**
     * Permet de rechercher les films par acteur.
     *
     * @param actor acteur des films recherches
     * @return une liste de films correspondant a la recherche
     */
    public ArrayList<Film> searchByActor(Actor actor) {
        return Film.searchByActor(engine, actor);
    }

    /**
     * Permet de rechercher les films par realisateur.
     *
     * @param director realisateur des films recherches
     * @return une liste de films correspondant a la recherche.
     */
    public ArrayList<Film> searchByDirector(Director director) {
        return Film.searchByDirector(engine, director);
    }

    /**
     * Permet de rajouter un film a la base de donnees.
     *
     * @param film film a ajouter
     * @return true si le film a bien ete rajoute, false sinon
     */
    public boolean addFilm(Film film) {
        if (Film.add(engine, film, false)) {
            this.reloadEngine();
            return true;
        }
        return false;
    }

    /**
     * Permet de supprimer un film de la base de donnees.
     *
     * @param film film a supprimer
     * @return true si le film a bien ete supprime, false sinon.
     */
    public boolean deleteFilm(Film film) {
        if (Film.delete(engine, film)) {
            this.reloadEngine();
            return true;
        }
        return false;
    }

    /**
     * Permet de mettre a jour un film.
     *
     * @param film le film a mettre a jour
     * @return true si le film a bien ete mis a jour, false sinon.
     */
    public boolean updateFilm(Film film) {
        if (Film.update(engine, film)) {
            this.reloadEngine();
            return true;
        }
        return false;
    }

    public void getallfilm() {
        list.addAll(this.getAllFilms());
        notifyObserver();
    }

    @Override
    public void notifyObserver() {
        for (Observer obs : listObserver)
            obs.update(list);
    }

    public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }

    @Override
    public void removeObserver() {
        // TODO Auto-generated method stub

    }

    /*
    informe la vue du film à traiter
     */
    public void suppfilm(String str) {
        int i;
        boolean isTrue = false;
        for (i = 0; i < list.size(); i++) {
            if (list.get(i).getTitle().equals(str)) {
                list.remove(list.get(i));
                isTrue = true;
            }
            notifyObserver();

        }
        //Cas ou le film n'existe pas
        if(isTrue == false) {
            for (Observer obs : listObserver)
                obs.update(null, false);
        }
    }

    /*
    ajoute le film à la liste
     */
    public void ajfilm(Film nouv) {
        list.add(nouv);
        notifyObserver();
    }

    /*
    informe la vue du film à traiter
     */
    public void modifFilm(String str) {
        for (Film i : list) {
            if (i.getTitle().equals(str)) {
                for (Observer obs : listObserver) {
                    obs.update(i, true);
                    return;
                }
            }
        }
        //Cas ou le film n'existe pas
        for (Observer obs : listObserver)
            obs.update(null, false);
    }

    /*
    Informe la vue du film à traiter
     */
    public void searchFilm(String film) {
        int listSize = list.size();
        int i = 0;

        for (i = 0; i < listSize; i++) {
            if (list.get(i).getTitle().equals(film)) {
                {
                    for (Observer obs : listObserver) {
                        obs.update(list.get(i));
                        return;
                    }
                }
            }

        }
        //Cas ou le film n'existe pas
        for (Observer obs : listObserver)
            obs.update(null, false);
    }

    public void traiteFilm(String previous, Film film) {
        int i;
        for (i = 0; i < list.size(); i++) {
            if (list.get(i).getTitle().equals(previous)) {
                list.get(i).setFilm(film);
            }
        }
        notifyObserver();
    }
}