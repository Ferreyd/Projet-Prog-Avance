package execution;

import interfacegraphique.Fenetre;

/**
 * @author Nicolas
 *         Date : 22/11/13 14:39
 *         La classe qui permet de lancer le jeu
 */

public class Main
{
    /**
     * La méthode main qui éxecute le jeu
     * utilise le singleton de la classe Fenetre
     *
     * @param args
     */
    public static void main(String args[])
    {
        Fenetre.getInstance();
    }
}
