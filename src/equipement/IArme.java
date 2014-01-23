package equipement;

import personnage.APersonnage;

import java.io.Serializable;

/**
 * @author Damien
 *         Interface désignant le type Arme
 *         Permet de définir l'attaque et les stats de personnages
 */
public interface IArme extends Serializable
{

    static final long serialVersionUID = 42L;

    /**
     * Permet d'attaquer un personnage passé en parametre
     *
     * @param cible Personnable cible
     */
    public void attaquer(APersonnage cible);

    /**
     * Modifie les statistiques du personnage selon l'équipement
     */
    public void setStatPersonnage();
}
