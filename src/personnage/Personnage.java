package personnage;

import classepersonnage.IClassePersonnage;


/**
 * Personnage concret avec sa classe
 *
 * @author Marc Dugué et Damien Savelli
 */

public class Personnage extends APersonnage
{
    public Personnage(IClassePersonnage classePerso)
    {
        super();
        this.classePersonnage = classePerso;
        this.nomPersonnage = classePerso.getClass().getSimpleName();
        // Initialisation des statistiques du personnage par défaut
        this.initStatistiques();

        // Modifie les statistiques du personnage en fonction de sa classe
        System.out.println(classePerso.getClass().getSimpleName());
        System.out.println(afficheStatPersonnage());
        classePersonnage.setStatPersonnage(this);
        System.out.println(afficheStatPersonnage());
    }

    /**
     * Initialise les statistiques par défaut du personnage
     */
    private void initStatistiques()
    {
        this.setStatPersonnage(NomStatsPerso.PV, 50);
        this.setStatPersonnage(NomStatsPerso.FORCE, 0);
        this.setStatPersonnage(NomStatsPerso.RESISTANCE, 10);
        this.setStatPersonnage(NomStatsPerso.DEXTERITE, 0);
        this.setStatPersonnage(NomStatsPerso.DISTANCEATTAQUE, 0);
        this.setStatPersonnage(NomStatsPerso.INTELLIGENCE, 0);
    }


    /**
     * Retourne un tableau de String avec les stats des personnages
     *
     * @return tableau de String
     */
    @Override
    public String afficheStatPersonnage()
    {
        String res = "";
        for(NomStatsPerso stat : NomStatsPerso.values())
        {
            res += String.valueOf(stat) + " : " + this.getStatPersonnage(stat) + "\n   ";

        }
        return res;
    }

    /*@Override
    public PersonnageToCareTaker savePersonnage(String nomFichier) {
		// TODO Auto-generated method stub
		return null;
	}*/


}
