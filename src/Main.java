
import fr.unice.polytech.mediamanager.control.Control;
import fr.unice.polytech.mediamanager.model.Manager;
import fr.unice.polytech.mediamanager.view.Ajoutvue;
import fr.unice.polytech.mediamanager.view.Window;


/**
 * Created by user on 29/03/2015.
 */
public class Main {

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
}
