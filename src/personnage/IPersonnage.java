package personnage;

import equipement.IArme;
import equipement.IArmure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * Interface que doit implémenter APersonnage
 *
 * @author Damien Savelli
 */
public interface IPersonnage extends Serializable
{
    static final long serialVersionUID = 42L;

    /**
     * Méthode pour attaquer un personnage adverse
     *
     * @param cible Le personnage a attaquer
     */
    public void attaquer(APersonnage cible);

    public int getStatPersonnage(NomStatsPerso nomStat);

    /**
     * Permet de récupérer la map des statistiques du personnage
     *
     * @return la Map des statistiques du personnage
     */
    public Map<NomStatsPerso, Integer> getAllStatPersonnage();

    public void setStatPersonnage(NomStatsPerso nomStat, int valeurStat);

    /**
     * Ajoute une arme à la liste d'armes du personnage
     *
     * @param arme
     */
    public void addArme(IArme arme);

    /**
     * Ajoute une liste d'armure à la liste d'armures du personnage
     *
     * @param armures
     */
    public void addArmures(ArrayList<IArmure> armures);

    /**
     * Affiche les statistiques du personnage
     *
     * @return La totalité des statistiques dans une chaîne de caractères
     */
    public String afficheStatPersonnage();

    //Persistance
    //public PersonnageToCareTaker savePersonnage(String nomFichier);

    /**
     * Une liste de String avec le nom des plugins associe au personnage
     *
     * @return liste des noms des plugins
     */
    public ArrayList<String> getPluginsName();

}
