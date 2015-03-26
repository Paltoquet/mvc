package view;

import Ressource.Info;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controler.Control;

public class Affichage extends JFrame implements Observer {
    
    Control control;
    Info film;
    
    public Affichage(Info film) {
        JFrame frame = new JFrame("Film");

        // Creation de la fenetre
        frame.pack();
        frame.setSize(600, 510);
        //frame.setResizable(false);

        // Contenair
        Container contentPane = frame.getContentPane();

        // Sous fenetre
        JPanel pane = new JPanel();
        JPanel pane2 = new JPanel();

        //Permet le retour à la ligne des elements suivant leur taille
        pane.setLayout(new FlowLayout());
        pane.setPreferredSize(new Dimension(150, 0));
        GridLayout grid = new GridLayout(6,1);
        grid.setVgap(60);
        pane.setLayout(grid);
        
        JLabel label1 = new JLabel();
        pane.add(label1);
        label1.setLayout(new GridLayout(2,1));
        
        JLabel label2 = new JLabel();
        pane.add(label2);
        label2.setLayout(new GridLayout(2,1));
        
        JLabel label3 = new JLabel();
        pane.add(label3);
        label3.setLayout(new GridLayout(2,1));
        
        JLabel label4 = new JLabel();
        pane.add(label4);
        label4.setLayout(new GridLayout(2,1));
        
        JLabel label5 = new JLabel();
        pane.add(label5);
        label5.setLayout(new GridLayout(2,1));
        
        JLabel label6 = new JLabel();
        pane.add(label6);
        label6.setLayout(new GridLayout(2,1));
        
        //Creer les casses dans le pane2
        pane2.setLayout(new BorderLayout());
        
        //text
        JLabel titre = new JLabel("Titre :");
        JLabel realisateurs = new JLabel("Réalisateurs :");
        JLabel acteurs = new JLabel("Acteurs :");
        JLabel genre = new JLabel("Genre :");
        JLabel resume = new JLabel("Résumé :");
        JLabel duree = new JLabel("Durée :");
        
        JLabel titrer = new JLabel("Heroes of the storm");
        JLabel realisateursr = new JLabel("Jackson");
        JLabel acteursr = new JLabel("Brad");
        JLabel genrer = new JLabel("drame");
        JLabel resumer = new JLabel("coucou");
        JLabel dureer = new JLabel("2h30");
        
        //image
        ImageIcon image = new ImageIcon("mugen.jpg");
        JLabel imageLabel = new JLabel(image);
        
        //Boutton
        JButton button = new JButton("Film suivant");
        
        //Placement dans la fenetre de la sous zone
        contentPane.add(pane);
        contentPane.add(pane2, BorderLayout.WEST);
        
        //Placement texte
        label1.add(titre);
        label1.add(titrer);
        label2.add(realisateurs);
        label2.add(realisateursr);
        label3.add(acteurs);
        label3.add(acteursr);
        label4.add(genre);
        label4.add(genrer);
        label5.add(resume);
        label5.add(resumer);
        label6.add(duree);
        label6.add(dureer);
        
        //ajout image
        pane2.add(button, BorderLayout.SOUTH);
        pane2.add(imageLabel, BorderLayout.CENTER);
      
        frame.setVisible(true);
        }
    
    public void setControl(Control c) {
        this.control = c;
    }
    
    public void setInfo(Info film) {
        this.film = film;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        
        
    }

}

