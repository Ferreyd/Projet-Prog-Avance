package equipement;

import competence.AnnotCompetences;
import personnage.NomStatsPerso;
import personnage.Personnage;

public class Sceptre extends Arme
{

    @AnnotEquipement(type = "arme")
    public Sceptre()
    {
        super();
    }


    @AnnotCompetences(type = "soin")
    public void utiliser(Personnage p)
    {
        System.out.println("Je me soigne de 10 point de vie !");
        if(p.getStatPersonnage(NomStatsPerso.PV) + 10 <= p.getPVMax())
            p.setStatPersonnage(NomStatsPerso.PV, p.getStatPersonnage(NomStatsPerso.PV) + 10);
        else p.setStatPersonnage(NomStatsPerso.PV, p.getPVMax());
    }

    @Override
    public void setStatPersonnage()
    {
        this.mapStatPersonnage.put(NomStatsPerso.INTELLIGENCE, 7);
    }

    @Override
    public void setStatEquipement()
    {
        this.setStatEquipement(NomStatsEquipement.DEGATS, this.getStatEquipement(NomStatsEquipement.DEGATS) + 5);
        this.setStatEquipement(NomStatsEquipement.RESISTANCE, this.getStatEquipement(NomStatsEquipement.RESISTANCE) +
                                                              5);
    }


}
