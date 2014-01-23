package personnage;

import classepersonnage.IClassePersonnage;
import equipement.AEquipement;
import equipement.IArme;
import equipement.IArmure;
import persistance.Persistance;
import strategie.Attaque;
import strategie.Comportement;
import strategie.Pacifiste;
import strategie.Soin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe abstraite représentant un Personnage et ses méthodes.
 *
 * @author Damien et marc
 */
public abstract class APersonnage implements IPersonnage, Serializable
{
    static final long serialVersionUID = 42L;
    /**
     * The Classe personnage.
     */
    protected IClassePersonnage classePersonnage;
    /**
     * The Map stat personnage.
     */
    protected Map<NomStatsPerso, Integer> mapStatPersonnage;
    /**
     * The Liste armes.
     */
    protected ArrayList<IArme> listeArmes;    // listeArmes.get(0) sera par défaut l'arme sélectionnée
    /**
     * The Liste armures.
     */
    protected ArrayList<IArmure> listeArmures;
    /**
     * The Comportement.
     */
    protected Comportement comportement;
    /**
     * The Liste plugin.
     */
    protected ArrayList<String> listePlugin;
    /**
     * The Nom personnage.
     */
    protected String nomPersonnage;
    /**
     * The Adversaire.
     */
    protected APersonnage adversaire;
    protected int PVMax;


    /**
     * Instantiates a new A personnage.
     */
    public APersonnage()
    {
        this.mapStatPersonnage = new HashMap<NomStatsPerso, Integer>();
        this.listeArmes = new ArrayList<>();
        this.listeArmures = new ArrayList<>();
        this.listePlugin = new ArrayList<>();
        this.comportement = new Pacifiste();
        this.adversaire = null;
        this.PVMax = 100;
    }

    /**
     * Gets liste armes.
     *
     * @return the listeArmes
     */
    public ArrayList<IArme> getListeArmes()
    {
        return listeArmes;
    }

    /**
     * Sets liste armes.
     *
     * @param listeArmes the listeArmes to set
     */
    public void setListeArmes(ArrayList<IArme> listeArmes)
    {
        this.listeArmes = listeArmes;
    }

    /**
     * Gets liste armures.
     *
     * @return the listeArmures
     */
    public ArrayList<IArmure> getListeArmures()
    {
        return listeArmures;
    }

    /**
     * Sets liste armures.
     *
     * @param listeArmures the listeArmures to set
     */
    public void setListeArmures(ArrayList<IArmure> listeArmures)
    {
        this.listeArmures = listeArmures;
    }

    /**
     * Méthode pour attaquer un personnage adverse
     *
     * @param cible Le personnage a attaquer
     */
    @Override
    public void attaquer(APersonnage cible)
    {
        listeArmes.get(0).attaquer(cible);  //To change body of implemented methods use File | Settings | File
        // Templates.
    }

    @Override
    public int getStatPersonnage(NomStatsPerso nomStatPerso)
    {
        return mapStatPersonnage.get(nomStatPerso);
    }

    @Override
    public void setStatPersonnage(NomStatsPerso nomStat, int valeurStat)
    {
        this.mapStatPersonnage.put(nomStat, valeurStat);
    }

    @Override
    public Map<NomStatsPerso, Integer> getAllStatPersonnage()
    {
        return this.mapStatPersonnage;
    }

    @Override
    public void addArme(IArme arme)
    {
        this.listeArmes.add(arme);
        this.updateStatPerso((AEquipement) arme);
        this.addPlugin(arme.getClass().getSimpleName());  // ajout de l'arme dans la liste des plugins
    }

    /**
     * Met à jour les statsistiques du personnages, qui sont modifiées par l'équipement equipement.
     * Pour celà, APersonnage.mapStatPersonnage s'aditionne avec AEquipement.mapStatPersonnage
     *
     * @param equipement the equipement
     */
    public void updateStatPerso(AEquipement equipement)
    {
        Map<NomStatsPerso, Integer> mapStatPerso = equipement.getAllStatPersonnage();

        // Somme des deux map : celle du personnage avec celle de l'équipement
        for(NomStatsPerso stat : equipement.getAllStatPersonnage().keySet())
        {
            if(this.mapStatPersonnage.keySet().contains(stat))
            {
                this.mapStatPersonnage.put(stat, (this.mapStatPersonnage.get(stat) + mapStatPerso.get(stat)));
            }
        }
    }


    @Override
    public void addArmures(ArrayList<IArmure> armures)
    {
        for(IArmure a : armures)
        {
            this.updateStatPerso((AEquipement) a);
            this.addPlugin(armures.getClass().getSimpleName());
        }
    }

    @Override
    public abstract String afficheStatPersonnage();

    /**
     * Save perso.
     *
     * @param nomFichier the nom fichier
     *//*public PersonnageToCareTaker savePersonnage(String nomFichier)
    {
        return new PersonnageMemento(nomFichier);

    }*/
    public void savePerso(String nomFichier)
    {
        Persistance p = new Persistance(nomFichier);
        p.storePersonnage(this, nomFichier);
    }

    /**
     * Load perso.
     *
     * @param nomFichier the nom fichier
     * @return the personnage
     */
    public static Personnage loadPerso(String nomFichier)
    {
        Persistance p = new Persistance(nomFichier);
        return (Personnage) p.loadPersonnage(nomFichier);
    }

    /**
     * Une liste de String avec le nom des plugins associe au personnage
     *
     * @return liste des noms des plugins
     */
    @Override
    public ArrayList<String> getPluginsName()
    {
        return this.listePlugin;
    }

    /**
     * Ajoute un plugin dans la liste de plugin
     *
     * @param name le nom du plugin
     */
    public void addPlugin(String name)
    {
        this.listePlugin.add(name);
    }


    /*public static Personnage loadPersonnage(String path)
    {

        System.out.println("coucou je suis la");
        PersonnageToOriginator memento = new PersonnageMemento(path);

        return memento.getPersonnage(path);
    }*/

    /**
     * Gets nom personnage.
     *
     * @return the nom personnage
     */
    public String getNomPersonnage()
    {
        return nomPersonnage;
    }

    /**
     * Gets comportement.
     *
     * @return the comportement
     */
    public Comportement getComportement()
    {
        return comportement;
    }

    /**
     * Sets comportement.
     *
     * @param comportement the comportement to set
     */
    public void setComportement(Comportement comportement)
    {
        this.comportement = comportement;
    }

    /**
     * Méthode appelée pendant un combat à chaque tour, permettant au personnage d'effectuer son action,
     * en fonction de son comportement.
     */
    public void actionAuCombat()
    {
        this.comportement.actionAuCombat(this, this.adversaire);
    }


    /**
     * @return the adversaire
     */
    public APersonnage getAdversaire()
    {
        return adversaire;
    }

    /**
     * Sets adversaire.
     *
     * @param adversaire the adversaire to set
     */
    public void setAdversaire(APersonnage adversaire)
    {
        this.adversaire = adversaire;
    }

    /**
     * @return the pVMax
     */
    public int getPVMax()
    {
        return PVMax;
    }

    /**
     * @param pVMax the pVMax to set
     */
    public void setPVMax(int pVMax)
    {
        PVMax = pVMax;
    }

    /**
     * Méthode mettant à jour la stratégie du personnage à chaque tour, selon la situation.
     */
    public void majComportement()
    {
        int pvPerso = this.getStatPersonnage(NomStatsPerso.PV);
        int pvAdversaire = this.adversaire.getStatPersonnage(NomStatsPerso.PV);

        if(this.getStatPersonnage(NomStatsPerso.PV) > 0)
        {
            if(pvPerso < this.PVMax * 0.15)
            {
                setComportement(new Soin());
            }else
            {
                setComportement(new Attaque());
            }
        }
    }


}
