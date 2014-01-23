package interfacegraphique;

import combat.Combat;
import personnage.Personnage;

import javax.swing.*;
import java.io.File;

/**
 * Permet de réprésenter un JPanel avec un JTextArea affichant els donénes du combat
 *
 * @author Nicolas et Jeremy
 */
public class PanelCombat extends JPanel
{
    private Fenetre fenetre;
    private JLabel label;


    /**
     * Instantiates a new Panel combat.
     */
    public PanelCombat()
    {
        fenetre = Fenetre.getInstance();
        this.afficheCombat();
    }


    /**
     * Affiche le combat
     */
    public void afficheCombat()
    {
        //On efface ce qu'il y avait avant
        fenetre.getContentPane().removeAll();

        //On affiche le choix classes de persos
        Personnage personnage1 = selectionPerso();
        Personnage personnage2 = selectionPerso();
        Combat combat = new Combat(personnage1, personnage2);
        this.add(combat);
        try
        {
            combat.lancerCombat();
        }
        catch(InterruptedException e1)
        {
            e1.printStackTrace();
        }


        //on revalide tout
        fenetre.getContentPane().add(this);
        fenetre.getContentPane().revalidate();
    }


    /**
     * Cree un JFileChooser et selectionne un Personnage
     *
     * @return Le Personnage
     */
    public Personnage selectionPerso()
    {
        JFileChooser fc = new JFileChooser("./jars/sauvegarde");
        int returnVal = fc.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            if(file.getName().contains(".data"))
            {
                System.out.println("nom = " + file.getName());
                Personnage p = Personnage.loadPerso(file.getName());
                System.out.println(p.afficheStatPersonnage());
                return p;
            }

        }else
        {
            System.out.println("Fichier incorrecte");
        }
        return null;
    }


}
