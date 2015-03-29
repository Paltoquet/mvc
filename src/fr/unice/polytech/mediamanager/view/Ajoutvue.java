package fr.unice.polytech.mediamanager.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.unice.polytech.mediamanager.control.Control;
import fr.unice.polytech.mediamanager.model.Film;


/**
 * @Author Lucas Sauvage et Thibault Ober
 * Permet l'affichage de la fenetre d'ajout d'un film et modification d'un film
 */
public class Ajoutvue extends JFrame {

	Control c;
	Film before;
	JLabel titre;
	JLabel rea;
	JLabel genre;
	JLabel resum;
	JLabel acteur;
	JLabel duree;
	Boolean modifmode;

	JTextField textTitre;
	JTextField textRea;
	JTextField textGenre;
	JTextField textResum;
	JTextField textActeur;
	JTextField textDuree;
	JFrame frame;
	JButton addButton;

    /*
    Création de la fenetre
     */
	public Ajoutvue() {
		modifmode=false;
		frame = new JFrame("Film");

		titre = new JLabel("Titre");
		rea = new JLabel("Réalisateurs");
		genre = new JLabel("Genre");

		resum = new JLabel("Résumé");
		acteur = new JLabel("Acteurs");
		duree = new JLabel("Durée");

		JLabel vide = new JLabel("");
		JLabel vide2 = new JLabel("");

		JLabel carac = new JLabel("Rentez les caractèristiques du films :");

		textTitre = new JTextField();
		textRea = new JTextField();
		textGenre = new JTextField();
		textResum = new JTextField();
		textActeur = new JTextField();
		textDuree = new JTextField();

		addButton = new JButton("Add");
		addButton.addActionListener(new AjoutListenner());

		// Creation de la fenetre
		frame.pack();
		frame.setSize(600, 510);

		// Contenair
		Container contentPane = frame.getContentPane();

		// Sous fenetre
		JPanel pane = new JPanel();
		JPanel pane2 = new JPanel();
		JPanel pane3 = new JPanel();
		JPanel pane4 = new JPanel();

		pane.setPreferredSize(new Dimension(300, 70));

		// Placement dans la fenetre de la sous zone
		contentPane.add(pane, BorderLayout.WEST);
		contentPane.add(pane2);
		contentPane.add(pane3, BorderLayout.SOUTH);
		contentPane.add(pane4, BorderLayout.NORTH);

		pane.setLayout(new GridLayout(3, 1));
		pane2.setLayout(new GridLayout(3, 1));
		pane3.setLayout(new GridLayout(1, 3));

		// Sous grid
		JPanel paneb = new JPanel();
		pane.add(paneb);
		paneb.setLayout(new GridLayout(3, 1));

		// Sous grid
		JPanel paneb2 = new JPanel();
		pane.add(paneb2);
		paneb2.setLayout(new GridLayout(3, 1));

		// Sous grid
		JPanel paneb3 = new JPanel();
		pane.add(paneb3);
		paneb3.setLayout(new GridLayout(3, 1));

		// Placement du text
		paneb.add(titre);
		paneb.add(textTitre);

		paneb2.add(genre);
		paneb2.add(textGenre);

		paneb3.add(rea);
		paneb3.add(textRea);

		// Sous grid
		JPanel paneb4 = new JPanel();
		pane2.add(paneb4);
		paneb4.setLayout(new GridLayout(3, 1));

		// Sous grid
		JPanel paneb5 = new JPanel();
		pane2.add(paneb5);
		paneb5.setLayout(new GridLayout(3, 1));

		// Sous grid
		JPanel paneb6 = new JPanel();
		pane2.add(paneb6);
		paneb6.setLayout(new GridLayout(3, 1));

		paneb4.add(resum);
		paneb4.add(textResum);

		paneb5.add(acteur);
		paneb5.add(textActeur);

		paneb6.add(duree);
		paneb6.add(textDuree);

		pane3.add(vide);
		pane3.add(addButton);
		pane3.add(vide2);

		pane4.add(carac);
		
		frame.setVisible(false);
	}

	public void setController(Control cont) {
		c = cont;
	}
	public void visible(){
        frame.setVisible(true);
    }

    /*
    Envois les informations lors du clique sur le bouton add
     */
	public class AjoutListenner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Boolean isTrue;
			if (modifmode == true) {
				frame.setVisible(false);
				isTrue=c.traiteModif(textTitre.getText(), textGenre.getText(), textResum.getText(), textDuree.getText(), textActeur.getText(), textRea.getText(), before);
				modifmode = false;
				if (isTrue == true) {
					reset();
				}
				return;
			}
			frame.setVisible(false);
			isTrue = c.retourfilm(textTitre.getText(), textGenre.getText(), textResum.getText(), textDuree.getText(), textActeur.getText(), textRea.getText());
			//si l'ajout à marcher on réinitialise les fields
			if (isTrue == true) {
				reset();
			}
		}
	}
        /*
        remet les champs vide après un ajout réussi
         */
        public void reset() {
            textTitre.setText("");
            textResum.setText("");
            textActeur.setText("");
            textRea.setText("");
            textDuree.setText("");
            textGenre.setText("");

        }
		//openfilm ouvre le film tel qu il était, la vue est en mode modification et non en ajout
		public void openFilm(Film i){
			before=i;
			textTitre.setText(i.getTitle());
			textResum.setText(i.getSynopsis());
			textActeur.setText(i.getActors().get(0).getFirstname());
			textRea.setText(i.getDirector().getFirstname());
			textDuree.setText(String.valueOf(i.getRuntime()));
			textGenre.setText(i.getGenres().get(0).getLabelFr());
			modifmode=true;

		}

}
