package apt;

import com.sun.javadoc.SourcePosition;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.Declaration;
import com.sun.mirror.declaration.FieldDeclaration;
import com.sun.mirror.util.SimpleDeclarationVisitor;
import com.sun.tools.javadoc.Messager;

import java.util.Map;

public class NoteVisitor extends SimpleDeclarationVisitor
{

    protected final AnnotationProcessorEnvironment env;

    /**
     * Indique si l'option -Arelease fait partie de la ligne de commande.
     */

    public NoteVisitor(AnnotationProcessorEnvironment env)
    {
        this.env = env;
    }

    /**
     * Pour tout type de déclaration, on affiche un message si
     * l'Annotation @Note est présente...
     */
    public void visitDeclaration(FieldDeclaration decl)
    {

        // On regarde si la déclaration possède une annotation Note
        Note note = decl.getAnnotation(Note.class);
        // Et on l'affiche eventuellement :
        if(note != null)

            printMessage(decl, note);

        if(decl instanceof Map<?, ?>)
        {


            if(((Map<?, ?>) decl).containsKey("FORCE"))

                printMessage(decl, note);
        }


    }

    /**
     * Affiche dans la console l'annotation Note.
     *
     * @param decl
     * @param todo
     */
    public void printMessage(Declaration decl, Note note)
    {
        printDebugingInfo(decl, note);
    }


    /**
     * Affiche le message pendant la phase de developpement.
     * Les messages IMPORTANT sont affichées comme des 'warnings'.
     * Les messages NORMAL sont affichées comme des 'notes'.
     * Les messages MINEUR sont affichées comme des 'notes' simples (sans position dans le code).
     *
     * @param decl La déclaration qui contient l'annotation.
     * @param note L'Annotation affichée.
     */
    public void printDebugingInfo(Declaration decl, Note note)
    {
        Messager m = (Messager) env.getMessager();

        System.out.println("aaaa" + note.value());

        switch(note.level())
        {
            case IMPORTANT:
                m.printWarning((SourcePosition) decl.getPosition(), decl.getSimpleName() + " ,km: " + note.value());
                break;
            case NORMAL:
                m.printNotice((SourcePosition) decl.getPosition(), decl.getSimpleName() + "hiu : " + note.value());
                break;
            case MINEUR:
                m.printNotice(decl.getSimpleName() + "n,g : " + note.value());
                break;
        }
    }

}

