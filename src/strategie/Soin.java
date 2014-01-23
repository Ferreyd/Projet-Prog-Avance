package strategie;

import competence.AnnotCompetences;
import equipement.IArme;
import personnage.APersonnage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

/**
 * Comportement défensif, qui permet au personnage de par exemple se soigner
 *
 * @author Damien Savelli
 */
public class Soin implements Comportement
{

    /**
     * Invocation de la méthode ayant pour AnnotCompetences "soin", si le personnage persoPrincipal en a bien une.
     * S'il n'en a pas, son comportement devient Attaque.
     */
    @Override
    public void actionAuCombat(APersonnage persoPrincipal, APersonnage adversaire)
    {
        // Afin d'éviter des combats infinis si le personnage se soigne tout le temps,
        // fait en sorte que la probabilité que
        // personnage se soigne est de 2/3, et 1/3 il attaquera à la place
        Random r = new Random();
        int hasard = r.nextInt(3);
        if(hasard > 0)
        {
            IArme arme = persoPrincipal.getListeArmes().get(0);    // Sélection de l'arme pricnipale
            Method[] methodesPerso = arme.getClass().getMethods();
            for(Method m : methodesPerso)
            {
                AnnotCompetences annot = m.getAnnotation(AnnotCompetences.class);
                if(annot != null && annot.type().equals("soin"))
                {
                    try
                    {
                        m.invoke(arme, persoPrincipal);
                        return;
                    }
                    catch(IllegalAccessException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    catch(IllegalArgumentException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    catch(InvocationTargetException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }

        // S'il n'y a pas de méthode ayant pour AnnotCompetence "soin",
        // ou une fois sur 3,
        // le comportement du personnage devient "Attaque" et il attaque
        persoPrincipal.setComportement(new Attaque());
        persoPrincipal.actionAuCombat();
    }

}
