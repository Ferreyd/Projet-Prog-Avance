package persistance;

import personnage.APersonnage;

import java.io.*;

/**
 * La classe Persistance.java
 * Possede les m�thodes n�c�ssaires � la serialisation et d�serialisation
 * d'un objet.
 *
 * @author Jeremy
 */
public class Persistance
{
    private static String nomFichier;

    //protected IClassePersonnage classePersonnage;

    public Persistance(String nomFichier)
    {
        this.nomFichier = nomFichier;

    }

    /**
     * Sauve dans un fichier .data un objet de type APersonnage
     * dont le nom est pass� en parametre
     *
     * @param p          Personnage � sauvegarder
     * @param nomFichier Nom du fichier � sauvegarder
     */
    public void storePersonnage(APersonnage p, String nomFichier)
    {
        store(p, nomFichier);
    }

    /**
     * Charge un fichier .data dont le nom est pass� en parametre
     *
     * @param name Nom du fichier � charger
     * @return personnage
     */
    public APersonnage loadPersonnage(String name)
    {
        APersonnage personnage = (APersonnage) load(name);
        return personnage;
    }

    /**
     * Methode statique permettant l'enregistrement d'unl'objet dans un fichier logique (.data)
     * <p/>
     *
     * @param data Objet à sauvegarder
     * @param name nom de l'objet à sauvegarder
     * @return
     */
    private static boolean store(Object data, String name)
    {

        String nomFichier;
        FileOutputStream f = null;
        ObjectOutputStream out = null;
        if(data == null) return false;
        File file = new File("");
        String path = "";

        try
        {
            path = file.getCanonicalPath().toString();
            path += "\\jars\\sauvegarde\\";

        }
        catch(IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        nomFichier = path;
        nomFichier += name + ".data";
        try
        {
            f = new FileOutputStream(nomFichier);
        }
        catch(FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            return false;
        }
        try
        {
            out = new ObjectOutputStream(f);
        }
        catch(IOException e)
        {
            // TODO Auto-generated catch block
            return false;
        }
        try
        {
            out.writeObject(data);

        }
        catch(IOException e)
        {
            // TODO Auto-generated catch block
            return false;
        }
        System.out.println(nomFichier + " ok");
        return true;

    }

    /**
     * M�thode statique permettant le chargement d'un fichier logique (.data) en fournissant son nom en parametre
     *
     * @param name
     * @return
     */
    private static Object load(String name)
    {
        //String nomFichier;
        FileInputStream f = null;
        ObjectInputStream in = null;

        Object resultat;

        try
        {
            f = new FileInputStream(name);
            System.out.println("test1");
        }
        catch(FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            return null;
        }

        try
        {
            in = new ObjectInputStream(f);
        }
        catch(IOException e)
        {
            // TODO Auto-generated catch block
            return null;
        }

        try
        {
            resultat = in.readObject();
        }
        catch(Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        System.out.println("Chargement ok : " + nomFichier);
        return resultat;
    }

}
