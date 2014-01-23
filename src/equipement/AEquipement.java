package equipement;

import personnage.NomStatsPerso;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe permettant de représenter un équipement avec ces caractéristique et ses méthodes
 *
 * @author Marc Dugué et Damien Savelli
 */
public abstract class AEquipement implements Serializable
{
    static final long serialVersionUID = 42L;
    /**
     * The Map stat personnage.
     */
    public Map<NomStatsPerso, Integer> mapStatPersonnage;    // les statistiques du personnage que va modifier cet
    /**
     * The Map stat equipement.
     */ // équipement
    public Map<NomStatsEquipement, Integer> mapStatEquipement;    // les statistiques propres à cet équipement

    /**
     * Initialise deux <code>HashMap</code>, une pour les stats du personnage et une autre pour les stats de
     * l'équipement
     */
    public AEquipement()
    {
        this.mapStatPersonnage = new HashMap<NomStatsPerso, Integer>();
        this.mapStatEquipement = new HashMap<NomStatsEquipement, Integer>();

        this.initStat();
        this.setStatEquipement();
        this.setStatPersonnage();
    }

    /**
     * Ajoute une statistique NomStatsEquipement avec pour valeur valeurStat à l'équipement,
     * ou la modifie si elle existe déjà
     *
     * @param nomStat    the nom stat
     * @param valeurStat the valeur stat
     */
    public void setStatEquipement(NomStatsEquipement nomStat, int valeurStat)
    {
        this.mapStatEquipement.put(nomStat, valeurStat);
    }


    /**
     * Gets stat equipement.
     *
     * @param nomStat the nom stat
     * @return the stat equipement
     */
    public int getStatEquipement(NomStatsEquipement nomStat)
    {
        return this.mapStatEquipement.get(nomStat);
    }

    /**
     * Gets all stat equipement.
     *
     * @return the all stat equipement
     */
    public Map<NomStatsEquipement, Integer> getAllStatEquipement()
    {
        return this.mapStatEquipement;
    }

    /**
     * Gets stat personnage.
     *
     * @param nomStat the nom stat
     * @return the stat personnage
     */
    public int getStatPersonnage(NomStatsEquipement nomStat)
    {
        return this.mapStatPersonnage.get(nomStat);
    }

    /**
     * Gets all stat personnage.
     *
     * @return the all stat personnage
     */
    public Map<NomStatsPerso, Integer> getAllStatPersonnage()
    {
        return this.mapStatPersonnage;
    }


    /**
     * Initialise les statistiques de l'�quipement
     */
    public abstract void initStat();


    /**
     * Sets stat personnage.
     */
    public abstract void setStatPersonnage();


    /**
     * modifie les statistiques "degats", "pv", "resistance" de l'équipement
     */
    public abstract void setStatEquipement();

    /**
     * Affiche les stats de l equipement
     *
     * @return string string
     */
    public String afficheStatEquipement()
    {
        String res = "";
        int i = 0;
        for(NomStatsEquipement stat : NomStatsEquipement.values())
        {
            res += String.valueOf(stat) + " : " + this.getStatEquipement(stat) + "\n   ";

        }
        return res;
    }
}
