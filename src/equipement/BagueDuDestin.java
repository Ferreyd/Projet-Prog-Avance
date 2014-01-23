package equipement;

import personnage.NomStatsPerso;

public class BagueDuDestin extends Armure
{

    @AnnotEquipement(type = "equipementMagique")
    public BagueDuDestin()
    {
        super();
    }

    @Override
    public void setStatPersonnage()
    {
        this.mapStatPersonnage.put(NomStatsPerso.PV, 15);
        this.mapStatPersonnage.put(NomStatsPerso.RESISTANCE, 3);
        this.mapStatPersonnage.put(NomStatsPerso.DEXTERITE, 4);
    }

    @Override
    public void setStatEquipement()
    {
        this.setStatEquipement(NomStatsEquipement.DEGATS, this.getStatEquipement(NomStatsEquipement.DEGATS) + 2);
        this.setStatEquipement(NomStatsEquipement.PV, this.getStatEquipement(NomStatsEquipement.PV) + 8);
        this.setStatEquipement(NomStatsEquipement.RESISTANCE, this.getStatEquipement(NomStatsEquipement.RESISTANCE) +
                                                              8);
        this.setStatEquipement(NomStatsEquipement.DEXTERITE, this.getStatEquipement(NomStatsEquipement.DEXTERITE) + 5);
    }

}
