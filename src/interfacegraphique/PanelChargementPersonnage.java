package interfacegraphique;

import execution.Main;
import personnage.Personnage;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Affiche un JFileChooser pour selectionner le personnage sauvegardée
 *
 * @author Nicolas  et Jeremy
 */
public class PanelChargementPersonnage extends JPanel
{

    private final Fenetre fenetre;

    /**
     * Efface et affiche le JFileChooser
     */
    public PanelChargementPersonnage()
    {
        super(new BorderLayout());
        this.fenetre = Fenetre.getInstance();

        //On efface ce qu'il y avait avant
        fenetre.getContentPane().removeAll();
        //on revalide tout
        fenetre.getContentPane().add(this);
        fenetre.getContentPane().revalidate();
        this.selectionPersonnage();
    }


    /**
     * Affiche la liste des personnages à charger
     */
    public Personnage selectionPersonnage()
    {
        JFileChooser fc = new JFileChooser("./src/jars/sauvegarde");
        int returnVal = fc.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            if(file.getName().contains(".data"))
            {
                System.out.println(file.getName() + "\n" + file.getPath());
                ClassLoader cl = Main.class.getClassLoader();
                String nom = "/src/jars/classespersonnages/" + file.getClass().getSimpleName() + ".jar";
                System.out.println("test = " + nom);
                try
                {
                    cl.loadClass(nom);
                }
                catch(ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
                Personnage p = Personnage.loadPerso(file.getName());
                System.out.println(p.afficheStatPersonnage());
                return p;
            }else
            {
                System.out.println("Fichier incorrecte");
            }
        }
        return null;

    }


}
