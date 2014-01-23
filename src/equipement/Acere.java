package equipement;


/**
 * Permet de decorer une arme comme Acere, c'est à dire qu'elle permet de donner plus de dégats
 *
 * @author Marc
 */
public class Acere extends DecorateurArme
{


    /**
     * Instancie une nouvelle armee acere
     *
     * @param arme the arme
     */
    public Acere(Arme arme)
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

        return arme.getStatEquipement(NomStatsEquipement.DEGATS) * 2;
    }


    @Override
    public void setStatEquipement()
    {

        arme.setStatEquipement();

    }


}
