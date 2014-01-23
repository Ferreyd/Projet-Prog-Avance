package equipement;

/**
 * Décore une arme comme étant rouillé, c'est à dire qu'elle fait moins de dégats
 */
public class Rouille extends DecorateurArme
{


    /**
     * Instancie une nouvelle arme rouille
     *
     * @param arme the arme
     */
    public Rouille(Arme arme)
    {

        this.arme = arme;
    }


    @Override
    public void setStatPersonnage()
    {

        arme.setStatPersonnage();

    }

    @Override
    public int getStatEquipement(NomStatsEquipement nomStat)
    {

        return arme.getStatEquipement(NomStatsEquipement.DEGATS) / 2;
    }


    @Override
    public void setStatEquipement()
    {

        arme.setStatEquipement();

    }


}
