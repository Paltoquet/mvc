package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controler.Control;
import fr.unice.polytech.mediamanager.model.Film;
import fr.unice.polytech.mediamanager.model.Genre;

public class Ajoutvue extends JFrame {

	Control c;

	JLabel titre;
	JLabel rea;
	JLabel genre;
	JLabel resum;
	JLabel acteur;
	JLabel duree;

	JTextField textTitre;
	JTextField textRea;
	JTextField textGenre;
	JTextField textResum;
	JTextField textActeur;
	JTextField textDuree;
	JFrame frame;
	JButton addButton;

	public Ajoutvue() {
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

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});

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
    
	public class AjoutListenner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//System.out.println(textTitre.getText()+textResum.getText()+textGenre.getText());
			frame.setVisible(false);
			int a;
            Film testIfNull;
//			if(textDuree.getText()==""){
//				a=130;
//			}
            testIfNull = c.retourfilm(textTitre.getText(), textGenre.getText() ,textResum.getText(),textDuree.getText(),textActeur.getText(),textRea.getText());
            //ne reset pas les champs si null, car poput erreur
            if (testIfNull != null) {
                this.reset();
            }
		}

        public void reset() {
            textTitre.setText("");
            textResum.setText("");
            textActeur.setText("");
            textRea.setText("");
            textDuree.setText("");
            textGenre.setText("");

        }
	}
}
