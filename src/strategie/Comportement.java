package strategie;

import personnage.APersonnage;

import java.io.Serializable;

/**
 * Pattern Strategy.
 * Interface correspondant à la stratégie que le personnage adopte pendant le combat
 *
 * @author Damien Savelli
 */
public interface Comportement extends Serializable
{
    static final long serialVersionUID = 42L;

    /**
     * Correspond à l'action que réalisera le personnage principal lors du combat, selon son comportement.
     *
     * @param persoPrincipal le personnage qui adopte la stratégie
     * @param adversaire     l'adversaire du personnage principal
     */
    public void actionAuCombat(APersonnage persoPrincipal, APersonnage adversaire);
}
