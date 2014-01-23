package interfacegraphique;

import javax.swing.*;

/**
 * Une JMenuBar qui permet de naviguer dans les menus du jeu
 *
 * @author Nicolas et Jeremy
 */
public class MenuBarre extends JMenuBar
{


    public MenuBarre()
    {
        init();
    }

    /**
     * Retourne la JMenuBar
     *
     * @return La Barre de Menu
     */
    @SuppressWarnings("JavaDoc")
    void init()
    {
        JMenu menuPersonnage = new JMenu("Personnage"), menuCombat = new JMenu("Combat");
        JMenuItem itemNouveauPerso = new JMenuItem("Nouveau personnage");
        JMenuItem itemCombat = new JMenuItem("Lancer Combat");

        //Ajout Action listener aux JMenuItem
        itemNouveauPerso.addActionListener(new PanelPrincipal());
        itemCombat.addActionListener(new PanelPrincipal());


        //Ajout JMenuItem à la JMenuBar
        menuPersonnage.add(itemNouveauPerso);
        menuCombat.add(itemCombat);


        this.add(menuPersonnage);
        this.add(menuCombat);

    }


}
