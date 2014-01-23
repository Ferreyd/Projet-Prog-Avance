package competence;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Permet d'annoter un equipement en fonction de son type
 * Restreint aux méthodes
 *
 * @author Nicolas
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AnnotCompetences
{
    String type();

}
