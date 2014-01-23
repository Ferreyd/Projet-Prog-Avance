package combat;

import personnage.APersonnage;
import personnage.NomStatsPerso;
import strategie.Attaque;

import javax.swing.*;

/**
 * The type Combat.
 *
 * @author Damien
 */
public class Combat extends JTextArea
{

    APersonnage personnage1, personnage2;
    APersonnage tourActuel;

    /**
     * Instantiates a new Combat.
     *
     * @param personnage1 the personnage 1
     * @param personnage2 the personnage 2
     */
    public Combat(APersonnage personnage1, APersonnage personnage2)
    {
        this.personnage1 = personnage1;
        this.personnage2 = personnage2;

        personnage1.setAdversaire(personnage2);
        personnage2.setAdversaire(personnage1);

        this.tourActuel = personnage1;
    }

    ;

    /**
     * lance un combat entre deux adverssaire passer en parametre au constructeur.
     *
     * @throws InterruptedException the interrupted exception
     */
    public void lancerCombat() throws InterruptedException
    {
        String str = "";
        this.personnage1.setComportement(new Attaque());
        this.personnage2.setComportement(new Attaque());
        str += "~~~~~~~~~~ Début du combat ~~~~~~~~~~\n";
        while(personnage1.getStatPersonnage(NomStatsPerso.PV) > 0 && personnage2.getStatPersonnage(NomStatsPerso.PV)
                                                                     > 0)
        {
            this.tourActuel.majComportement();
            this.tourActuel.actionAuCombat();
            str += this.personnage1.getNomPersonnage() + " PV = " + this.personnage1.getStatPersonnage(NomStatsPerso
                    .PV) + "\n" + this.personnage2.getNomPersonnage() + " PV = " + this.personnage2.getStatPersonnage
                    (NomStatsPerso.PV) + "\n\n";
            this.tourSuivant();
            System.out.println();
            Thread.sleep(250);
        }
        this.setText(str + "\n" + "Et le vainqueur est : " + this.trouverVainqueur());
    }

    /**
     * Tour suivant.
     */
    public void tourSuivant()
    {
        if(this.tourActuel == personnage1)
        {
            this.tourActuel = personnage2;
        }else this.tourActuel = personnage1;
    }

    /**
     * Trouve le vainqueur de ce combat
     *
     * @return le nom du vainqueur du combat
     */
    private String trouverVainqueur()
    {
        if(this.personnage1.getStatPersonnage(NomStatsPerso.PV) == 0) return this.personnage2.getNomPersonnage();
        else return this.personnage1.getNomPersonnage();
    }

}
