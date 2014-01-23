package interfacegraphique;

import chargement.JarClassLoader;
import classepersonnage.IClassePersonnage;
import equipement.AEquipement;
import equipement.AnnotEquipement;
import equipement.IArme;
import equipement.IArmure;
import personnage.Personnage;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 * JPanel qui permet de créer son personnage
 * Utilisation de JList pour presenter les plugins disponibles aux joueurs.
 * Si pas de classes, le joueur peut choisir d'en charger un. Il est alors guider vers le dossier de stockage des
 * plugins de classes de personnages
 * Une fois validée, on efface tout le panel et on recrée des JList avec les équipements,
 * une liste par type d'équipements.
 * Encore une fois le joueur peut choisir d'importer des plugins.
 * Le logiciel detecte le type d'équipement et l'affiche dans la JList correspondante.
 * Il peut choisir qu'une arme, et autant d'équipement qu'il veut
 * Une fois selectionnée, le personnage est crée et sauvegardé.
 * Posséde aussi ses propres Listener qui sont ListSelectionListener, ActionListener et DocumentListener
 *
 * @author Nicolas
 */
public class NouveauPersonnage extends JPanel implements ListSelectionListener, ActionListener, DocumentListener
{
    private static final String classeString = "Selectionner Classe";
    private static final String equipementString = "Valider le personnage";
    private static final String ajoutClasseString = "Ajouter une Classe";
    private static final String ajoutEquipementString = "Ajouter un Equipement";
    private Fenetre fenetre;
    private JPanel textPane;
    private DefaultListModel modelClasse = new DefaultListModel();
    private DefaultListModel modelArme = new DefaultListModel();
    private DefaultListModel modelEquipements = new DefaultListModel();
    private DefaultListModel modelEquipementMagique = new DefaultListModel();
    private JList listeClassePersonnage = new JList(modelClasse);
    private JList listeEquipements = new JList(modelEquipements);
    private JList listeArme = new JList(modelArme);
    private JList listeEquipementMagique = new JList(modelEquipementMagique);
    private ArrayList<IClassePersonnage> ArraylisteClasse = new ArrayList<IClassePersonnage>();
    private ArrayList<AEquipement> ArrayListeEquipements = new ArrayList<AEquipement>();
    private ArrayList<AEquipement> ArrayListArme = new ArrayList<AEquipement>();
    private ArrayList<AEquipement> ArrayListeEquipementsMagique = new ArrayList<>();
    private JTextArea jLabelPersonnage;
    private JTextArea jLabelEquipement;

    private Personnage personnage;

    /**
     * Le constructeur crée un grid layout et appel la méthode permettant d'afficher le code permettant de choisir sa
     * classe de personnage
     */
    public NouveauPersonnage()
    {
        super(new GridLayout(3, 3));
        this.fenetre = Fenetre.getInstance();

        //On efface ce qu'il y avait avant
        fenetre.getContentPane().removeAll();

        //On affiche le choix classes de persos
        afficheChoixClassePersonnage();

        //on revalide tout
        fenetre.getContentPane().add(this);
        fenetre.getContentPane().revalidate();


    }

    /**
     * Méthode permettant d'afficher les classes de personnages
     */
    private void afficheChoixClassePersonnage()
    {

        /**
         * On affiche d'abord la liste des classes de personnages
         */
        //S'il y a des plugins déjà chargées, on les met dans la liste
        if(modelClasse.isEmpty() && fenetre.getPluginClasse().size() != 0)
        {
            ArraylisteClasse = fenetre.getPluginClasse();
            for(int i = 0 ; i < fenetre.getPluginClasse().size() ; i++)
            {
                modelClasse.add(i, fenetre.getPluginClasse().get(i).getClass().getSimpleName());
            }
        }


        //Creation de la JList en fonction de la DefaultModelList
        listeClassePersonnage.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Un seul element selectionnable
        listeClassePersonnage.setSelectedIndex(0);
        listeClassePersonnage.addListSelectionListener(this);
        JScrollPane listPaneClassePersonnage = new JScrollPane(listeClassePersonnage);


        //Initialisation du bouton de slection de personnage
        JButton selectClasse = new JButton(classeString);
        selectClasse.setActionCommand(classeString);
        selectClasse.addActionListener(this);

        //Initialisation du bouton d ajout d une classe
        JButton ajoutClasse = new JButton(ajoutClasseString);
        ajoutClasse.setActionCommand(ajoutClasseString);
        ajoutClasse.addActionListener(this);

        //JTextField affichant les classes disponibles
        JTextField classePersonnages = new JTextField();
        classePersonnages.addActionListener(this);
        classePersonnages.getDocument().addDocumentListener(this);
        if(listeClassePersonnage.getLastVisibleIndex() > 0)
        {
            String name = modelClasse.getElementAt(listeClassePersonnage.getSelectedIndex()).toString();
        }


        //JPanel avec les stats de l'équipement
        textPane = new JPanel();
        jLabelPersonnage = new JTextArea();
        textPane.add(jLabelPersonnage);

        //Creation du Panel pour le boutton
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(selectClasse);
        buttonPane.add(ajoutClasse);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(listPaneClassePersonnage, BorderLayout.CENTER);
        add(textPane);
        add(buttonPane, BorderLayout.PAGE_END);

    }

    /**
     * Affiche choix equipements.
     */
    private void afficheChoixEquipements()
    {
        /**
         * On affiche d'abord la liste des équipements
         */

        //S'il y a des plugins déjà chargée, on les met dans la liste
        if(modelArme.isEmpty() && fenetre.getPluginArme().size() != 0)
        {
            ArrayListArme = fenetre.getPluginArme();
            for(int i = 0 ; i < fenetre.getPluginArme().size() ; i++)
            {
                modelArme.add(i, fenetre.getPluginArme().get(i).getClass().getSimpleName());
            }

        }
        //S'il y a des plugins déjà chargées, on les met dans la liste
        if(modelEquipements.isEmpty() && fenetre.getPluginEquipement().size() != 0)
        {
            ArrayListeEquipements = fenetre.getPluginEquipement();
            for(int i = 0 ; i < fenetre.getPluginEquipement().size() ; i++)
            {
                modelEquipements.add(i, fenetre.getPluginEquipement().get(i).getClass().getSimpleName());
            }

        }
        //S'il y a des plugins déjà chargées, on les met dans la liste
        if(modelEquipementMagique.isEmpty() && fenetre.getPluginEquipementMagique().size() != 0)
        {
            ArrayListeEquipementsMagique = fenetre.getPluginEquipementMagique();
            for(int i = 0 ; i < fenetre.getPluginEquipementMagique().size() ; i++)
            {
                modelEquipementMagique.add(i, fenetre.getPluginEquipementMagique().get(i).getClass().getSimpleName());
            }
        }

        //Creation des JList en fonction de leurs DefaultModelList
        JPanel panelEquipement = new JPanel();
        listeEquipements = new JList(modelEquipements);
        listeEquipements.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); //plusieurs éléments
        // selectionnable
        listeEquipements.setSelectedIndex(0);
        listeEquipements.addListSelectionListener(this);
        JScrollPane listePaneEquipements = new JScrollPane(listeEquipements);
        panelEquipement.add(listePaneEquipements);

        JPanel panelArme = new JPanel();
        listeArme = new JList(modelArme);
        listeArme.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Un seul element
        listeArme.setSelectedIndex(0);
        listeArme.addListSelectionListener(this);
        JScrollPane listePaneArme = new JScrollPane(listeArme);
        panelArme.add(listePaneArme);

        JPanel panelEquipementMagique = new JPanel();
        listeEquipementMagique = new JList(modelEquipementMagique);
        listeEquipementMagique.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Un seul element
        listeEquipementMagique.setSelectedIndex(0);
        listeEquipementMagique.addListSelectionListener(this);
        JScrollPane listePaneEquipementsMagique = new JScrollPane(listeEquipementMagique);
        panelEquipementMagique.add(listePaneEquipementsMagique);

        //Initilisation du bouton d ajout d equipement
        JButton ajoutEquipement = new JButton(ajoutEquipementString);
        ajoutEquipement.setActionCommand(ajoutEquipementString);
        ajoutEquipement.addActionListener(this);

        //Initialisation du bouton de slection des equipements qui permet de creer le personnage
        JButton selectEquipements = new JButton(equipementString);
        selectEquipements.setActionCommand(equipementString);
        selectEquipements.addActionListener(this);

        //JTextField affichant les stats des équipements
        JTextField equipements = new JTextField();
        equipements.addActionListener(this);
        equipements.getDocument().addDocumentListener(this);
        if(listeEquipements.getLastVisibleIndex() > 0)
        {
            String nameEquipement = modelEquipements.getElementAt(listeEquipements.getSelectedIndex()).toString();
        }
        if(listeArme.getLastVisibleIndex() > 0)
        {
            String nameArme = modelArme.getElementAt(listeArme.getSelectedIndex()).toString();
        }
        if(listeEquipementMagique.getLastVisibleIndex() > 0)
        {
            String nameEquipementMagique = modelEquipementMagique.getElementAt(listeEquipementMagique
                    .getSelectedIndex()).toString();
        }


        //JPanel avec les stats de l'équipement
        textPane = new JPanel();
        jLabelEquipement = new JTextArea();
        textPane.add(jLabelEquipement);


        //Creation du Panel pour le boutton
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(selectEquipements);
        buttonPane.add(ajoutEquipement);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(listePaneEquipements);
        add(listePaneArme);
        add(listePaneEquipementsMagique);
        add(textPane);
        add(buttonPane);


    }


    /**
     * Charge et retourne une classe de personnage grace au chemin vers un .jar passé en parametre
     *
     * @param path Le chemin vers le jar
     * @return La classe chargée
     * @throws ClassNotFoundException the class not found exception
     * @throws ClassNotFoundException the class not found exception
     * @throws ClassNotFoundException the class not found exception
     * @throws ClassNotFoundException the class not found exception
     */
    private IClassePersonnage chargeClasse(String path) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, IOException
    {
        JarClassLoader jar = new JarClassLoader(path);
        System.out.println("classe chargé = " + jar.getClasseChargee().getName());
        IClassePersonnage classePersonnage = (IClassePersonnage) jar.getClasseChargee().newInstance();
        System.out.println(classePersonnage.getClass().getSimpleName());


        return classePersonnage;


    }

    /**
     * Charge et retourne un equipement grace au chemin vers un .jar passé en parametre
     *
     * @param path Le chemin vers le jar
     * @return L equipement chargée
     * @throws ClassNotFoundException the class not found exception
     * @throws ClassNotFoundException the class not found exception
     * @throws ClassNotFoundException the class not found exception
     * @throws ClassNotFoundException the class not found exception
     */
    private AEquipement chargeEquipement(String path) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, IOException
    {
        JarClassLoader jar = new JarClassLoader(path);
        System.out.println("classe chargé = " + jar.getClasseChargee().getName() + " path = " + path);
        AEquipement equipement = (AEquipement) jar.getClasseChargee().newInstance();
        System.out.println(equipement.getClass().getSimpleName());

        return equipement;

    }


    /**
     * Cherche si une classe de personnage est déjà chargé
     *
     * @param classe le nom de la classe de personnage
     * @return VRAI si la classe est déjà dans la liste, FAUX sinon
     */
    private boolean chercheClasse(String classe)
    {
        for(IClassePersonnage i : ArraylisteClasse)
        {
            if(i.getClass().getSimpleName().equals(classe)) return true;
        }
        return false;

    }

    /**
     * Cherche si l'équipement est deja charge ou non
     *
     * @param equipement le nom de l equipement
     * @param annotation l annotation de lequipement
     * @return VRAI si l equipement est déjà dans la liste, FAUX sinon
     */
    private boolean chercheEquipement(String equipement, String annotation)
    {
        if(annotation.equals("arme"))
        {
            for(AEquipement i : ArrayListArme)
            {
                if(i.getClass().getSimpleName().equals(equipement)) return true;
            }
            return false;
        }
        if(annotation.equals("equipementMagique"))
        {
            for(AEquipement i : ArrayListeEquipementsMagique)
            {
                if(i.getClass().getSimpleName().equals(equipement)) return true;
            }
            return false;

        }
        if(annotation.equals("equipement"))
        {
            for(AEquipement i : ArrayListeEquipements)
            {
                if(i.getClass().getSimpleName().equals(equipement)) return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Retourne l'annotation de l'équipement passe en parametre
     *
     * @param equipement
     * @return l'annotation de l'équipement
     */
    private String getAnnotationEquipement(AEquipement equipement)
    {

        String annotation = "";
        System.out.println("Equipement selectionne = " + equipement.getClass().getName());
        Constructor[] c = equipement.getClass().getConstructors();
        for(Constructor cons : c)
        {
            AnnotEquipement ae = (AnnotEquipement) cons.getAnnotation(AnnotEquipement.class);
            if(ae != null)
            {

                annotation = ae.type();
            }
        }
        return annotation;


    }


    /**
     * Affiche les stat d'une arme dans un JLabel
     */
    public void afficheStatArme()
    {
        int index = listeArme.getSelectedIndex();
        AEquipement aEquipement = ArrayListArme.get(index);
        jLabelEquipement.setText(aEquipement.getClass().getSimpleName() + "\n" + aEquipement.getAllStatPersonnage()
                .toString() + "\n" + aEquipement.getAllStatEquipement().toString());
    }

    /**
     * Affiche les stat d'un équipement dans un JLabel
     */
    public void afficheStatEquipement()
    {
        int index = listeEquipements.getSelectedIndex();
        AEquipement aEquipement = ArrayListeEquipements.get(index);
        jLabelEquipement.setText(aEquipement.getClass().getSimpleName() + "\n" + aEquipement.getAllStatPersonnage()
                .toString() + "\n" + aEquipement.getAllStatEquipement().toString());
    }

    public void afficheStatEquipementMagique()
    {
        int index = listeEquipementMagique.getSelectedIndex();
        AEquipement aEquipement = ArrayListeEquipementsMagique.get(index);
        jLabelEquipement.setText(aEquipement.getClass().getSimpleName() + "\n" + aEquipement.getAllStatPersonnage()
                .toString() + "\n" + aEquipement.getAllStatEquipement().toString());
    }

    public void afficheStatClasse()
    {
        int index = listeClassePersonnage.getSelectedIndex();
        IClassePersonnage iClassePersonnage = ArraylisteClasse.get(index);
        Personnage temp = new Personnage(iClassePersonnage);
        jLabelPersonnage.setText(temp.afficheStatPersonnage());
    }

    /**
     * Selectionne une classe dans la liste
     */
    public void selectionClasse()
    {
        int index = listeClassePersonnage.getSelectedIndex(); // on prend le choix de l'utilisateur
        if(index >= 0)
        {
            personnage = new Personnage(ArraylisteClasse.get(index)); // on prend l'objet de la liste et je le
            // met dans le persos

            //On efface ce qu'il y avait avant
            fenetre.getContentPane().removeAll();
            this.removeAll();
            //On affiche le choix classes de persos
            afficheChoixEquipements();

            //on revalide tout
            fenetre.getContentPane().add(this);
            fenetre.getContentPane().revalidate();
        }

    }

    public void selectionEquipement()
    {

        int indexArme = listeArme.getSelectedIndex();
        int indexEquipement[] = listeEquipements.getSelectedIndices();
        int indexEquipementMagique[] = listeEquipementMagique.getSelectedIndices();

        if(indexArme >= 0 && indexEquipement[0] >= 0 && indexEquipementMagique[0] >= 0)
        {
            AEquipement arme = ArrayListArme.get(indexArme);
            ArrayList<IArmure> armures = new ArrayList<>();

            for(int i = 0 ; i < indexEquipement.length ; i++)
            {
                armures.add((IArmure) ArrayListeEquipements.get(i));


            }

            for(int i = 0 ; i < indexEquipementMagique.length ; i++)
            {
                armures.add((IArmure) ArrayListeEquipementsMagique.get(i));
            }


            personnage.addArme((IArme) arme);    // on ajoute l'arme au personnage
            personnage.addArmures(armures);      // on ajoute les armures au personnage


            //On efface ce qu'il y avait ava513nt
            fenetre.getContentPane().removeAll();
            this.removeAll();

            //On affiche le choix classes de persos
            //afficheChoixClassePersonnage();
            personnage.savePerso(personnage.getNomPersonnage());

            //on revalide tout
            fenetre.getContentPane().add(this);
            fenetre.getContentPane().revalidate();
        }


    }

    /**
     * Ajoute une classe dans le JPanel
     */
    public void ajoutClasse()
    {
        JFileChooser fc = new JFileChooser("./jars/classespersonnages");
        int returnVal = fc.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            try
            {
                IClassePersonnage classePersonnage = chargeClasse(file.getAbsolutePath());
                //Si la classe de personnage chargée n'est pas déjà dans la liste
                if(this.chercheClasse(classePersonnage.getClass().getSimpleName()) == false)
                {
                    modelClasse.addElement(classePersonnage.getClass().getSimpleName());  // On ajoute dans la
                    // liste la classe chargé dynamiquement
                    ArraylisteClasse.add(modelClasse.indexOf(classePersonnage.getClass().getSimpleName()),
                            classePersonnage); //On ajoute l'objet chargé dans une liste de IClasePersonnage à la
                    // position il se trouve dans la liste de modelclasse
                    fenetre.getPluginClasse().add(classePersonnage);
                }

            }
            catch(ClassNotFoundException e1)
            {
                e1.printStackTrace();
            }
            catch(InstantiationException e1)
            {
                e1.printStackTrace();
            }
            catch(IllegalAccessException e1)
            {
                e1.printStackTrace();
            }
            catch(IOException e1)
            {
                e1.printStackTrace();
            }
        }else
        {
            System.out.println("Pas de fichier charge");
        }
    }

    /**
     * Ajoute un Equipement dnas le JPanel
     */
    public void ajoutEquipement()
    {
        JFileChooser fc = new JFileChooser("./jars/equipements");
        int returnVal = fc.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            try
            {
                AEquipement equipement = chargeEquipement(file.getAbsolutePath());
                String annotationEquipement = getAnnotationEquipement(equipement);
                //Si la classe de personnage chargée n'est pas déjà dans la liste
                if(this.chercheEquipement(equipement.getClass().getSimpleName(), annotationEquipement) == false)
                {
                    if(annotationEquipement.equals("arme"))
                    {
                        modelArme.addElement(equipement.getClass().getSimpleName());  // On ajoute dans la
                        // liste la classe
                        // chargé dynamiquement
                        ArrayListArme.add(modelArme.indexOf(equipement.getClass().getSimpleName()),
                                equipement); //On ajoute l'objet chargé dans une liste de IClasePersonnage à la
                        // position il se trouve dans la liste de modelclasse
                        fenetre.getPluginArme().add(equipement);

                    }
                    if(annotationEquipement.equals("equipementMagique"))
                    {
                        modelEquipementMagique.addElement(equipement.getClass().getSimpleName());  // On ajoute
                        // dans la
                        // liste la classe
                        // chargé dynamiquement
                        ArrayListeEquipementsMagique.add(modelEquipementMagique.indexOf(equipement.getClass()
                                .getSimpleName()), equipement); //On ajoute l'objet chargé dans une liste de
                        // IClasePersonnage à la
                        // position il se trouve dans la liste de modelclasse
                        fenetre.getPluginEquipementMagique().add(equipement);

                    }
                    if(annotationEquipement.equals("equipement"))
                    {
                        modelEquipements.addElement(equipement.getClass().getSimpleName());  // On ajoute dans la
                        // liste la classe
                        // chargé dynamiquement
                        ArrayListeEquipements.add(modelEquipements.indexOf(equipement.getClass().getSimpleName()),
                                equipement); //On ajoute l'objet chargé dans une liste de IClasePersonnage à la
                        // position il se trouve dans la liste de modelclasse
                        fenetre.getPluginEquipement().add(equipement);

                    }else
                    {
                    }

                }

            }
            catch(ClassNotFoundException e1)
            {
                e1.printStackTrace();
            }
            catch(InstantiationException e1)
            {
                e1.printStackTrace();
            }
            catch(IllegalAccessException e1)
            {
                e1.printStackTrace();
            }
            catch(IOException e1)
            {
                e1.printStackTrace();
            }
        }else
        {
            System.out.println("Pas de fichier charge");
        }

    }


    /**
     * Invoked when an action occurs.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {

        if(e.getActionCommand().equals(classeString)) // Si on clic sur le boutton "Selectionner classe" on affiche les
        // équipements
        {
            selectionClasse();
        }
        if(e.getActionCommand().equals(equipementString))  // Si on clic sur le boutton "Selectionner Equipement" on
        // affiche
        // les classes
        {
            selectionEquipement();
            this.removeAll();
            afficheChoixClassePersonnage();
        }

        if(e.getActionCommand().equals(ajoutClasseString))  // Si clic sur le boutton d'ajout de classe
        {
            ajoutClasse();
        }
        if(e.getActionCommand().equals(ajoutEquipementString))
        {
            ajoutEquipement();


        }

    }

    /**
     * Gives notification that there was an insert into the document.  The
     * range given by the DocumentEvent bounds the freshly inserted region.
     *
     * @param e the document event
     */
    @Override
    public void insertUpdate(DocumentEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gives notification that a portion of the document has been
     * removed.  The range is given in terms of what the view last
     * saw (that is, before updating sticky positions).
     *
     * @param e the document event
     */
    @Override
    public void removeUpdate(DocumentEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gives notification that an attribute or set of attributes changed.
     *
     * @param e the document event
     */
    @Override
    public void changedUpdate(DocumentEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Called whenever the value of the selection changes.
     *
     * @param e the event that characterizes the change.
     */
    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        if(e.getSource() == listeClassePersonnage)
        {
            afficheStatClasse();
        }
        if(e.getSource() == listeArme)  // Si l'objet est une JList Equipement
        {

            afficheStatArme();


        }
        if(e.getSource() == listeEquipementMagique)
        {
            afficheStatEquipementMagique();
        }
        if(e.getSource() == listeEquipements) //Si l'objet est une JList Classe Personnage
        {

            afficheStatEquipement();

        }

    }

    /**
     * Gets personnage.
     *
     * @return the personnage
     */
    public Personnage getPersonnage()
    {
        return personnage;
    }


}


