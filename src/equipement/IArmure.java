package equipement;

import java.io.Serializable;

/**
 * @author Damien
 *         Interface désignant le type Armure
 *         Permet de définir les stats aux personnages
 */
public interface IArmure extends Serializable
{

    static final long serialVersionUID = 42L;

    /**
     * Modifie les stats d'un personnage
     */
    public void setStatPersonnage();
}
