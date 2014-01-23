package strategie;

import personnage.APersonnage;

/**
 * Comportement offensif, le personnage principal attaque son adversaire.
 *
 * @author Damien Savelli
 */
@SuppressWarnings("serial")
public class Attaque implements Comportement
{

    /**
     * Utilise la méthode attaquer(APersonnage cible) de l'Arme du PersonnagePrincipal,
     * qui se situe à la première position de sa liste d'armes
     */
    @Override
    public void actionAuCombat(APersonnage persoPrincipal, APersonnage adversaire)
    {
        persoPrincipal.getListeArmes().get(0).attaquer(adversaire);
    }

}
