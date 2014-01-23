package interfacegraphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Le menu Principale du jeu
 * Possede les action listeners pour la JMenuBar
 *
 * @author Nicolas et Jeremy
 */
class PanelPrincipal extends JPanel implements ActionListener
{

    private final Fenetre fenetre;

    public PanelPrincipal()
    {
        init();
        fenetre = Fenetre.getInstance();
    }


    void init()
    {
        setLayout(new BorderLayout());


    }

    /**
     * Invoked when an action occurs.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {

        //Si on clic sur nouveau personnage
        if(e.getActionCommand().equals("Nouveau personnage"))
        {
            System.out.println(e.getActionCommand());
            new NouveauPersonnage();  // création du nouveau panel


        }
        //Si on clic sur Charger personnage
        if(e.getActionCommand().equals("Charger personnage"))
        {
            //TODO Action Charger personnage
            System.out.println(e.getActionCommand());
            new PanelChargementPersonnage();


        }
        //Si on clic sur Enregistrer Personnage
        if(e.getActionCommand().equals("Enregistrer personnage"))
        {
            //TODO Action enregistrer Personnage
            System.out.println(e.getActionCommand());
            this.setBackground(Color.RED);

            fenetre.getContentPane().removeAll();
            fenetre.getContentPane().add(this);
            fenetre.getContentPane().revalidate();

        }
        if(e.getActionCommand().equals("Lancer Combat"))
        {

            PanelCombat combat = new PanelCombat();

        }

        //Si on clic Charger classe
        if(e.getActionCommand().equals("Charger classe"))
        {
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION)
            {
                File file = fc.getSelectedFile();
                System.out.println("Fichier charg� : " + file.getAbsolutePath());
            }else
            {
                System.out.println("Pas de fichier charg�");
            }

        }

    }


}



