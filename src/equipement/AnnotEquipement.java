package equipement;

import java.lang.annotation.*;

/**
 * Permet d'annoter un equipement en fonction de son type
 * Restreint au constructeur
 * arme / equipementMagique / equipement
 *
 * @author Nicolas
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
public @interface AnnotEquipement
{
    String type();
}
