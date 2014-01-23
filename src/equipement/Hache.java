package equipement;

import personnage.NomStatsPerso;

public class Hache extends Arme
{
    @AnnotEquipement(type = "arme")
    public Hache()
    {
        super();

    }

    @Override
    public void setStatPersonnage()
    {
        this.mapStatPersonnage.put(NomStatsPerso.FORCE, 7);
    }

    @Override
    public void setStatEquipement()
    {
        this.setStatEquipement(NomStatsEquipement.DEGATS, this.getStatEquipement(NomStatsEquipement.DEGATS) + 10);
        this.setStatEquipement(NomStatsEquipement.PV, this.getStatEquipement(NomStatsEquipement.PV) + 5);
        this.setStatEquipement(NomStatsEquipement.RESISTANCE, this.getStatEquipement(NomStatsEquipement.RESISTANCE) +
                                                              5);
        this.setStatEquipement(NomStatsEquipement.DEXTERITE, this.getStatEquipement(NomStatsEquipement.DEXTERITE) + 5);
    }

}
