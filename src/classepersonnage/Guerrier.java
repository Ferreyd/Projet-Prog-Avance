package classepersonnage;

import personnage.APersonnage;
import personnage.NomStatsPerso;
import personnage.Personnage;

public class Guerrier extends APersonnage implements IClassePersonnage
{

    @Override
    public void setStatPersonnage(Personnage personnage)
    {
        personnage.setStatPersonnage(NomStatsPerso.PV, personnage.getStatPersonnage(NomStatsPerso.PV) + 50);
        personnage.setStatPersonnage(NomStatsPerso.FORCE, personnage.getStatPersonnage(NomStatsPerso.FORCE) + 50);
        personnage.setStatPersonnage(NomStatsPerso.RESISTANCE, personnage.getStatPersonnage(NomStatsPerso.RESISTANCE)
                                                               + 50);
        personnage.setStatPersonnage(NomStatsPerso.DEXTERITE, personnage.getStatPersonnage(NomStatsPerso.DEXTERITE) +
                                                              15);
        personnage.setStatPersonnage(NomStatsPerso.DISTANCEATTAQUE, personnage.getStatPersonnage(NomStatsPerso
                .DISTANCEATTAQUE) + 15);
        personnage.setStatPersonnage(NomStatsPerso.INTELLIGENCE, personnage.getStatPersonnage(NomStatsPerso
                .INTELLIGENCE) + 15);
    }


    @Override
    public String afficheStatPersonnage()
    {
        return null;
    }
}
