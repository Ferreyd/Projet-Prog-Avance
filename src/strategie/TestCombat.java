package strategie;

import classepersonnage.Guerrier;
import classepersonnage.Sorcier;
import combat.Combat;
import equipement.*;
import personnage.APersonnage;
import personnage.Personnage;

import java.util.ArrayList;

public class TestCombat
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("~~~~~~~~~~~ Création du premier personnage ~~~~~~~~~~~\n");
        APersonnage perso1 = new Personnage(new Guerrier());
        perso1.addArme(new Epee());
        ArrayList<IArmure> armuresPerso1 = new ArrayList<IArmure>();
        armuresPerso1.add(new Bouclier());
        perso1.addArmures(armuresPerso1);
        System.out.println(perso1.afficheStatPersonnage());

        System.out.println("~~~~~~~~~~~ Création du deuxième personnage ~~~~~~~~~~~\n");
        APersonnage perso2 = new Personnage(new Sorcier());
        perso2.addArme(new Sceptre());
        ArrayList<IArmure> armuresPerso2 = new ArrayList<IArmure>();
        armuresPerso2.add(new BagueDuDestin());
        perso2.addArmures(armuresPerso1);
        System.out.println(perso2.afficheStatPersonnage());

        perso1.setAdversaire(perso2);
        perso2.setAdversaire(perso1);
        System.out.println("\n~~~~~~~~~~~ Fin créations ~~~~~~~~~~~\n");

        Combat combat = new Combat(perso1, perso2);
        try
        {
            combat.lancerCombat();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}
