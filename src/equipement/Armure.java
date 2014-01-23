package equipement;

/**
 * Représente une armure
 *
 * @author Damien
 */
public abstract class Armure extends AEquipement implements IArmure
{


    /**
     * Instantiates a new Armure.
     */
    public Armure()
    {
        super();
    }

    /**
     * @see equipement.AEquipement#initStat()
     */
    @Override
    public void initStat()
    {
        this.setStatEquipement(NomStatsEquipement.DEGATS, 0);
        this.setStatEquipement(NomStatsEquipement.PV, 5);
        this.setStatEquipement(NomStatsEquipement.RESISTANCE, 5);
        this.setStatEquipement(NomStatsEquipement.DEXTERITE, 0);
    }
}
