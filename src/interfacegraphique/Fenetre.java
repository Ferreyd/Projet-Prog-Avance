package interfacegraphique;

import classepersonnage.IClassePersonnage;
import equipement.AEquipement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * La JFrame du jeu
 * C'est un Singleton, elle instancie la fenetre de depart avec la JMenuBar MenuBarre afin de naviguer dans les menus.
 * Posséde aussi une liste de plugin chargé par le joueur afin de garder une trace des plugins selectionnée par
 * l'utilisateur*
 *
 * @author Nicolas et Jeremy
 *         Date : 22/11/13 16:05
 */
public class Fenetre extends JFrame
{
    /**
     * The constant INSTANCE.
     */
    final public static Fenetre INSTANCE = new Fenetre();

    private ArrayList<IClassePersonnage> pluginClasse;
    private ArrayList<AEquipement> pluginEquipement;
    private ArrayList<AEquipement> pluginArme;
    private ArrayList<AEquipement> pluginEquipementMagique;

    /**
     * Instanciation de la fenetre lors de la construction de l'objet ainsi que des ArrayyList de plugin
     */
    private Fenetre()
    {
        instancieFenetre();
        pluginClasse = new ArrayList<>();
        pluginEquipement = new ArrayList<>();
        pluginArme = new ArrayList<>();
        pluginEquipementMagique = new ArrayList<>();
    }

    /**
     * Instancie la Fenetre principal avec la JMenuBar.
     */
    private void instancieFenetre()
    {
        //fenetre = new JFrame();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setTitle("L'arene");
        this.setSize(new Dimension(1024, 768));


        //Ajout du JMenu a la fenetre
        MenuBarre menuBarre = new MenuBarre();
        this.setJMenuBar(menuBarre);

        //Ajout du JPanel PanelPrincipal , placement au CENTRE
        PanelPrincipal panelPrincipal = new PanelPrincipal();
        this.setLayout(new BorderLayout());
        this.add(panelPrincipal, BorderLayout.CENTER);

        this.setVisible(true);

    }

    /**
     * Recupère l'unique instance du jeu
     *
     * @return l 'instance de la fenetre
     */
    static public Fenetre getInstance()
    {
        return INSTANCE;
    }

    /**
     * Gets plugin classePersonnage.
     *
     * @return the plugin classe
     */
    public ArrayList<IClassePersonnage> getPluginClasse()
    {
        return pluginClasse;
    }

    /**
     * Gets plugin equipement.
     *
     * @return the plugin equipement
     */
    public ArrayList<AEquipement> getPluginEquipement()
    {
        return pluginEquipement;
    }

    /**
     * Gets plugin arme.
     *
     * @return the plugin arme
     */
    public ArrayList<AEquipement> getPluginArme()
    {
        return pluginArme;
    }

    /**
     * Gets plugin equipement magique.
     *
     * @return the plugin equipement magique
     */
    public ArrayList<AEquipement> getPluginEquipementMagique()
    {
        return pluginEquipementMagique;
    }
}
