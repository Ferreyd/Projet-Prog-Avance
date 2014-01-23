package chargement;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe permettant de traiter
 *
 * @author Jeremy
 */
public class MyClassLoader extends SecureClassLoader
{

    /**
     * Liste de m�thodes
     */

    private List<Method> listeMethodes = new ArrayList<Method>();

    /**
     * Fichier
     */

    private File f;

    /**
     *
     */
    private URLClassLoader cl;

    /**
     * Type de classe charg�e
     */

    private Class<?> classeChargee;

    /**
     * Nom de la classe
     */
    private String nomClasse;

    /**
     * Instance de la classe
     */
    private Object classeInstanciee;


    /**
     * Instantiates a new My class loader.
     * <p/>
     * public MyClassLoader()
     * {
     * }
     * <p/>
     * /**
     * Permet de r�cup�rer la m�thode dont le nom est "name"
     *
     * @param name the name
     * @return the methode by name
     */
    public Method getMethodeByName(String name)
    {
        int i = 0;
        while(i < listeMethodes.size())
        {
            if(listeMethodes.get(i).getName().equalsIgnoreCase(name))
            {
                return listeMethodes.get(i);
            }
            i++;
        }
        return null;
    }

    /**
     * Permet de remplir une liste de toutes les m�thodes de la classe charg�e
     */
    public void listeAllMethodes()
    {
        for(int i = 0 ; i < classeChargee.getDeclaredMethods().length ; i++)
        {
            listeMethodes.add(classeChargee.getDeclaredMethods()[i]);
        }
    }


    /**
     * Gets liste methodes.
     *
     * @return the liste methodes
     */
    public List<Method> getListeMethodes()
    {
        return listeMethodes;
    }

    /**
     * Sets liste methodes.
     *
     * @param listeMethodes the liste methodes
     */
    public void setListeMethodes(List<Method> listeMethodes)
    {
        this.listeMethodes = listeMethodes;
    }

    /**
     * Gets f.
     *
     * @return the f
     */
    public File getF()
    {
        return f;
    }

    /**
     * Sets f.
     *
     * @param f the f
     */
    public void setF(File f)
    {
        this.f = f;
    }

    /**
     * Gets cl.
     *
     * @return the cl
     */
    public URLClassLoader getCl()
    {
        return cl;
    }

    /**
     * Sets cl.
     *
     * @param cl the cl
     */
    public void setCl(URLClassLoader cl)
    {
        this.cl = cl;
    }

    /**
     * Gets classe chargee.
     *
     * @return the classe chargee
     */
    public Class<?> getClasseChargee()
    {
        return classeChargee;
    }

    /**
     * Sets classe chargee.
     *
     * @param classeChargee the classe chargee
     */
    public void setClasseChargee(Class<?> classeChargee)
    {
        this.classeChargee = classeChargee;
    }

    /**
     * Gets nom classe.
     *
     * @return the nom classe
     */
    public String getNomClasse()
    {
        return nomClasse;
    }

    /**
     * Sets nom classe.
     *
     * @param nomClasse the nom classe
     */
    public void setNomClasse(String nomClasse)
    {
        this.nomClasse = nomClasse;
    }

    /**
     * Gets classe instanciee.
     *
     * @return the classe instanciee
     */
    public Object getClasseInstanciee()
    {
        return classeInstanciee;
    }

    /**
     * Sets classe instanciee.
     *
     * @param classeInstanciee the classe instanciee
     */
    public void setClasseInstanciee(Object classeInstanciee)
    {
        this.classeInstanciee = classeInstanciee;
    }


}
