package apt;


import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.util.DeclarationVisitor;
import com.sun.mirror.util.DeclarationVisitors;

public class SimpleAnnotationProcessor implements AnnotationProcessor
{

    /**
     * L'environnement du processeur d'annotation.
     */
    protected final AnnotationProcessorEnvironment env;

    /**
     * Constructeur.
     *
     * @param env L'environnement du processeur d'annotation.
     */
    public SimpleAnnotationProcessor(AnnotationProcessorEnvironment env)
    {
        this.env = env;
    }

    /**
     * Traitement des fichiers sources.
     */
    public void process()
    {
        // Instanciation du Visitor
        NoteVisitor noteVisitor = new NoteVisitor(env);

        // On boucle sur toutes les Annotations :
        for(TypeDeclaration d : env.getTypeDeclarations())
        {

            // On "visite" chacune des déclarations trouvées :
            d.accept(DeclarationVisitors.getSourceOrderDeclarationScanner((DeclarationVisitor) noteVisitor,
                    DeclarationVisitors.NO_OP));
        }
    }
}