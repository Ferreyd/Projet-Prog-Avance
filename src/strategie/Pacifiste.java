package strategie;

import personnage.APersonnage;

/**
 * Comportement par défaut du personnage, qu'il adopte lorsqu'il ne combat pas
 *
 * @author Damien Savelli
 */
@SuppressWarnings("serial")
public class Pacifiste implements Comportement
{

    /**
     * Le personnage ne combat pas
     */
    @Override
    public void actionAuCombat(APersonnage persoPrincipal, APersonnage adversaire)
    {
        System.out.println("Je ne fais rien");
    }

}
