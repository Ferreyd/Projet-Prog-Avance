package apt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE , ElementType.METHOD , ElementType.CONSTRUCTOR , ElementType.PACKAGE , ElementType.FIELD})
public @interface Note
{
    String value();

    /**
     * Niveau de criticité de la tâche (défaut : NORMAL).
     */
    Level level() default Level.NORMAL;

    /**
     * Enumération des différents niveaux de criticités.
     */
    public static enum Level
    {
        MINEUR, NORMAL, IMPORTANT
    }

    ;

}