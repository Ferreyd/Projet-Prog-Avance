package chargement;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Classe permettant de charger dynamiquement un fichier .jar dans le logiciel
 *
 * @author Jeremy
 */
public class JarClassLoader extends MyClassLoader
{

    /**
     * Constructeur JarClassLoader
     *
     * @param path : chemin absolu du fichier jar
     */
    public JarClassLoader(String path) throws IOException, ClassNotFoundException, InstantiationException,
            IllegalAccessException
    {

        // R�cup du path
        this.setF(new File(path));

        // Cr�ation d'un URLClassLoader
        this.setCl(new URLClassLoader(new URL[]{getF().toURI().toURL()}));
        JarFile jar = new JarFile(getF());
        this.setClasseChargee(getCl().loadClass(getClassName(jar)));
        this.setClasseInstanciee(getClasseChargee().newInstance());

        this.listeAllMethodes();
    }

    /**
     * Methode qui r�cup�re les .class dans le fichier jar
     *
     * @param jar : le jar parcouru
     */
    private String getClassName(JarFile jar)
    {
        Enumeration<JarEntry> enume = jar.entries();
        JarEntry je;
        while(enume.hasMoreElements())
        {
            je = enume.nextElement();
            String className = je.toString();
            if(className.contains(".class") && !className.contains(".classpath"))
            {
                return className.replace("/", ".").substring(0, className.length() - 6);
            }
        }
        return null;
    }
}
