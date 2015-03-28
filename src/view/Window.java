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


import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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

	public Window() {
		model = new DefaultListModel();
		list = new JList(model);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		JScrollPane listscroll = new JScrollPane(list);
		liste = new ArrayList<Film>();
		this.setLayout(new FlowLayout());
		JButton button = new JButton("Search");
		JButton button2 = new JButton("Suppr.");
		JButton button3 = new JButton("Ajouter");
		this.setSize(425, 400);
		// lb=new JLabel();
		// lb.setPreferredSize(new Dimension(180,60));
		this.setLayout(new GridLayout(1, 2));
		text = new JTextField();
		text.setPreferredSize(new Dimension(150, 100));
		button.setPreferredSize(new Dimension(100, 40));
		button2.setPreferredSize(new Dimension(100, 40));
		button3.setPreferredSize(new Dimension(100, 40));

		button.addActionListener(new SearchListener());
		button3.addActionListener(new ListListener());
		button2.addActionListener(new SupListListener());

		JPanel grup = new JPanel();
		this.setLayout(new GridLayout(1, 2));

		JPanel grup2 = new JPanel();
		this.setLayout(new GridLayout(1, 2));
		
		JPanel grup3 = new JPanel();
        this.setLayout(new GridLayout(1, 2));

		grup2.add(button);
		grup2.add(button2);
		grup3.add(button3);
		grup.add(text);
		grup.add(grup2);
		grup.add(grup3);

		this.add(listscroll);
		this.add(grup);
		list.addListSelectionListener(listSelectionListener);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public  class SearchListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			control.rechercher(text.getText());
		}
	}
	public class ListListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//if (!text.getText().equals("")) {
				String nouv = text.getText();
				text.setText("");
				control.ajouterfilm();
			//}
			//String mot = build(liste);
		}

	}

	public class SupListListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (!text.getText().equals("") && !model.isEmpty()) {
				control.supprimerfilm(text.getText());
//				String nouv = text.getText();
//				while (model.contains(nouv)) {
//					System.out.println("coucou");
//					model.remove(model.indexOf(nouv));
//				}
			}
		}
	}

	ListSelectionListener listSelectionListener = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent listSelectionEvent) {
			Affichage affiche = new Affichage(null);
		}
	};

	public String build(ArrayList<String> list) {
		int k;
		String mot = "";
		for (k = 0; k < list.size(); k++) {
			mot = mot + list.get(k);
			mot = mot + ",";
		}
		return mot;
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
		model.clear();
		for (i = 0; i < lis.size(); i++) {
			liste.add(lis.get(i));
			System.out.println(lis.get(0).getTitle());
			model.addElement(lis.get(i).getTitle());
		}
		list = new JList(model);
	}
	public void update(Film film) {
		Affichage affiche=new Affichage(film);
	}
}