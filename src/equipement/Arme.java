package equipement;

import personnage.APersonnage;
import personnage.NomStatsPerso;

/**
 * Le type de toute Arme du jeu
 *
 * @author Marc et Damien
 */
public abstract class Arme extends AEquipement implements IArme
{

    /**
     * Prend toute les méthode de AEquipement.
     * Une annotation AnnotEquipement permet de le considérer comme une Arme
     */
    @AnnotEquipement(type = "arme")
    public Arme()
    {
        super();
    }

    /**
     * @see equipement.IArme#attaquer(personnage.APersonnage)
     */
    @Override
    public void attaquer(APersonnage perso)
    {
        if(this.mapStatEquipement.containsKey(NomStatsEquipement.DEGATS))
        {
            int degats = this.getStatEquipement(NomStatsEquipement.DEGATS);
            int pvAdversaire = perso.getStatPersonnage(NomStatsPerso.PV);
            System.out.println(degats + " dégâts contre " + perso.getNomPersonnage());
            if(pvAdversaire > degats)
                perso.setStatPersonnage(NomStatsPerso.PV, perso.getStatPersonnage(NomStatsPerso.PV) - this
                        .getStatEquipement(NomStatsEquipement.DEGATS));
            else perso.setStatPersonnage(NomStatsPerso.PV, 0);
            System.out.println("Il reste " + perso.getStatPersonnage(NomStatsPerso.PV) + " à " + perso
                    .getNomPersonnage());
        }else System.out.println("Cette arme n'inflige aucun dégât");
    }

    /**
     * @see equipement.AEquipement#initStat()
     */
    @Override
    public void initStat()
    {
        this.setStatEquipement(NomStatsEquipement.DEGATS, 0);
        this.setStatEquipement(NomStatsEquipement.PV, 5);
        this.setStatEquipement(NomStatsEquipement.RESISTANCE, 3);
        this.setStatEquipement(NomStatsEquipement.DEXTERITE, 0);
    }


}
