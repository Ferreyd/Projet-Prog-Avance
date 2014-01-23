package equipement;

import personnage.NomStatsPerso;

public class Bouclier extends Armure
{

    @AnnotEquipement(type = "equipement")
    public Bouclier()
    {
        super();
    }

    @Override
    public void setStatPersonnage()
    {
        this.mapStatPersonnage.put(NomStatsPerso.PV, 10);
        this.mapStatPersonnage.put(NomStatsPerso.RESISTANCE, 10);
    }

    @Override
    public void setStatEquipement()
    {
        this.setStatEquipement(NomStatsEquipement.DEGATS, this.getStatEquipement(NomStatsEquipement.DEGATS) + 2);
        this.setStatEquipement(NomStatsEquipement.PV, this.getStatEquipement(NomStatsEquipement.PV) + 10);
        this.setStatEquipement(NomStatsEquipement.RESISTANCE, this.getStatEquipement(NomStatsEquipement.RESISTANCE) +
                                                              10);
        this.setStatEquipement(NomStatsEquipement.DEXTERITE, this.getStatEquipement(NomStatsEquipement.DEXTERITE) + 4);
    }

}
