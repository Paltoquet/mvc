package Ressource;

import java.awt.Component;

public class Info {
	String titre;
	String genre;
	String résumé;

	public Info(String ti, String genr, String résum) {
		titre = ti;
		genre = genr;
		résumé = résum;
	}
	public String getname(){
		return titre;
	}
}