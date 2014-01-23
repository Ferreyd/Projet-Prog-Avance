package classepersonnage;

import personnage.APersonnage;
import personnage.NomStatsPerso;
import personnage.Personnage;

/**
 * @author Nicolas
 *         Date : 13/12/13 11:57
 */
public class Clerc extends APersonnage implements IClassePersonnage
{
    /**
     * Initialise les statistiques du personnage lors de sa cr�ation
     *
     * @param personnage
     */
    @Override
    public void setStatPersonnage(Personnage personnage)
    {
        personnage.setStatPersonnage(NomStatsPerso.PV, personnage.getStatPersonnage(NomStatsPerso.PV) + 10);
        personnage.setStatPersonnage(NomStatsPerso.FORCE, personnage.getStatPersonnage(NomStatsPerso.FORCE) + 10);
        personnage.setStatPersonnage(NomStatsPerso.RESISTANCE, personnage.getStatPersonnage(NomStatsPerso.RESISTANCE)
                                                               + 50);
        personnage.setStatPersonnage(NomStatsPerso.DEXTERITE, personnage.getStatPersonnage(NomStatsPerso.DEXTERITE) +
                                                              20);
        personnage.setStatPersonnage(NomStatsPerso.DISTANCEATTAQUE, personnage.getStatPersonnage(NomStatsPerso
                .DISTANCEATTAQUE) + 15);
        personnage.setStatPersonnage(NomStatsPerso.INTELLIGENCE, personnage.getStatPersonnage(NomStatsPerso
                .INTELLIGENCE) + 25);
    }

    @Override
    public String afficheStatPersonnage()
    {
        return null;
    }

}
