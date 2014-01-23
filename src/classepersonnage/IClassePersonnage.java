package classepersonnage;

import personnage.Personnage;

import java.io.Serializable;

/**
 * Interface permettant de définir une classe de personnage
 * Chaque classe définit ses caractéristiques à un Personnage
 *
 * @author Damien
 */
public interface IClassePersonnage extends Serializable
{
    static final long serialVersionUID = 42L;

    /**
     * Initialise les statistiques du personnage lors de sa cr�ation
     */
    public void setStatPersonnage(Personnage personnage);


}
