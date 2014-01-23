package equipement;

/**
 * Décore une Arme pour la rendre acéré ou rouillé
 */
public abstract class DecorateurArme extends Arme
{

    /**
     * L'arme a décoré
     */
    protected Arme arme;

    public abstract int getStatEquipement(NomStatsEquipement nomStat);

}
