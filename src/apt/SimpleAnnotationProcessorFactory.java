package apt;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.apt.AnnotationProcessors;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class SimpleAnnotationProcessorFactory implements AnnotationProcessorFactory
{

    /**
     * Collection contenant le nom des Annotations supportées.
     */
    protected Collection<String> supportedAnnotationTypes = Arrays.asList(Note.class.getName());

    /**
     * Collection des options supportées.
     */
    protected Collection<String> supportedOptions = Collections.emptyList();

    /**
     * Retourne la liste des annotations supportées par cette Factory.
     */
    public Collection<String> supportedAnnotationTypes()
    {
        return supportedAnnotationTypes;
    }

    /**
     * Retourne la liste des options supportées par cette Factory.
     */
    public Collection<String> supportedOptions()
    {
        return supportedOptions;
    }

    /**
     * Retourne l'AnnotationProcessor associé avec cette Factory...
     */
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds, AnnotationProcessorEnvironment env)
    {
        // Si aucune annotation n'est présente on retourne un processeur "vide"
        if(atds.isEmpty()) return AnnotationProcessors.NO_OP;
        return new SimpleAnnotationProcessor(env);
    }


}