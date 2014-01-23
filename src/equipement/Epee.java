package equipement;

import personnage.NomStatsPerso;

public class Epee extends Arme
{

    @AnnotEquipement(type = "arme")
    public Epee()
    {
        super();
    }

    @Override
    public void setStatPersonnage()
    {
        this.mapStatPersonnage.put(NomStatsPerso.FORCE, 4);
    }

    @Override
    public void setStatEquipement()
    {
        this.setStatEquipement(NomStatsEquipement.DEGATS, this.getStatEquipement(NomStatsEquipement.DEGATS) + 10);
        this.setStatEquipement(NomStatsEquipement.PV, this.getStatEquipement(NomStatsEquipement.PV) + 7);
        this.setStatEquipement(NomStatsEquipement.RESISTANCE, this.getStatEquipement(NomStatsEquipement.RESISTANCE) +
                                                              4);
        this.setStatEquipement(NomStatsEquipement.DEXTERITE, this.getStatEquipement(NomStatsEquipement.DEXTERITE) + 4);
    }

}
