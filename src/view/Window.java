package view;

import fr.unice.polytech.mediamanager.model.Film;
import fr.unice.polytech.mediamanager.model.Manager;
import interfac.Observer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import Ressource.Info;
import controler.Control;


public class Window extends JFrame implements Observer {
	JTextField text;
	ArrayList<Film> liste;
	JLabel lb;
	JList list;
	DefaultListModel model;
	Control control;
	private boolean haschanged;//utilisé lors de l'ajout dans la liste pour ne pas entrer en confrontation avec
	//valuechanged de listSelectionLister
    /**
     * @Author Lucas Sauvage
     * Permet de gérer les différents films
     */
	public Window() {
		model = new DefaultListModel();
		list = new JList(model);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		JScrollPane listscroll = new JScrollPane(list);
		liste = new ArrayList<Film>();
		haschanged=false;
		this.setLayout(new FlowLayout());
		JButton button = new JButton("Search");
		JButton button2 = new JButton("Suppr.");
		JButton button3 = new JButton("Ajouter");
		JButton button4 = new JButton("Modify");
		this.setSize(425, 400);
		this.setLayout(new GridLayout(1, 2));
		text = new JTextField();
		text.setPreferredSize(new Dimension(150, 100));
		button.setPreferredSize(new Dimension(100, 40));
		button2.setPreferredSize(new Dimension(100, 40));
		button3.setPreferredSize(new Dimension(100, 40));
		button4.setPreferredSize(new Dimension(100, 40));

		button.addActionListener(new SearchListener());
		button3.addActionListener(new ListListener());
		button2.addActionListener(new SupListListener());
		button4.addActionListener(new ModifListener());
		JPanel grup = new JPanel();
		this.setLayout(new GridLayout(1, 2));

		JPanel grup2 = new JPanel();
		this.setLayout(new GridLayout(1, 2));
		
		JPanel grup3 = new JPanel();
        this.setLayout(new GridLayout(1, 2));

		grup2.add(button);
		grup2.add(button2);
		grup3.add(button3);
		grup3.add(button4);
		grup.add(text);
		grup.add(grup2);
		grup.add(grup3);

		this.add(listscroll);
		this.add(grup);
		list.addListSelectionListener(new ListSelectionListener(){
			//on affiche dans la text area notre selection
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()&&!haschanged) {
					JList source = (JList)e.getSource();
					String selected = source.getSelectedValue().toString();
					text.setText(selected);
				}
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public  class SearchListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {

            //test si le film existe
            int i = 0;
            boolean Isin = false;
			//cherche le film
            while(i < liste.size() ){
                if(text.getText().equals(liste.get(i).getTitle())) {
                    Isin = true;
                }
                i++;
            }
            if(Isin == false) {
                ajoutpopup("Ce film n'existe pas !");
            }
            else {
                control.rechercher(text.getText());
            }
			// a faire si film non présent
		}
	}

    public void ajoutpopup(String error) {
        JOptionPane.showMessageDialog(this, error, "Erreur !", JOptionPane.ERROR_MESSAGE);
    }

	public class ModifListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			control.modifFilm(text.getText());

		}

	}
	public class ListListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
				//appelle le controleur
				text.setText("");
				control.ajouterfilm();

		}

	}

	public class SupListListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
            //test si le film a supprimer existe
            int i = 0;
            boolean Isin = false;
            while (i < liste.size()) {
                if (text.getText().equals(liste.get(i).getTitle())) {
                    Isin = true;
                }
                i++;
            }
            if (Isin == false) {
                ajoutpopup("Ce film n'existe pas !");
            } else if (!text.getText().equals("") && !model.isEmpty()) {
                control.supprimerfilm(text.getText());
            }
//				String nouv = text.getText();
//				while (model.contains(nouv)) {
//
//					model.remove(model.indexOf(nouv));
		}
	}

	public void initfilm(){
		control.getallfilm();
	}

	public void setControl(Control c) {
		this.control = c;
	}

	public static void main(String[] args) {
		Window fen = new Window();
		Ajoutvue vue=new Ajoutvue();
		Manager manag=new Manager();
		Control cont = new Control();
		manag.addObserver(fen);
		cont.setManager(manag);
		fen.setControl(cont);
		cont.setView(vue);
		fen.initfilm();
		fen.repaint();
		vue.setController(cont);
	}
	@Override
	public void update(ArrayList<Film> lis) {
		int i;
		haschanged=true;
		liste.clear();
		model.clear();
		for (i = 0; i < lis.size(); i++) {
			liste.add(lis.get(i));
			model.addElement(lis.get(i).getTitle());
		}
		list = new JList(model);
		haschanged=false;
	}
	/*
	une fois que le modèle a fait son choix la vue crée uen nouvelle fenêtre d'affichage
	 */
	@Override
	public void update(Film film) {
		Affichage affiche=new Affichage(film);
	}
	/*
	Renvoie le film et si il existe ou pas
	 */
	@Override
	public void update(Film film,boolean b){
		if(!b){
			ajoutpopup("Ce film n'existe pas !");
		}
		else{
			control.lanceModif(film);
		}
	}
}
