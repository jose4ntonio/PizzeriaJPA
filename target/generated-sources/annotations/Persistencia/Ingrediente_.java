package Persistencia;

import Persistencia.TipoIngrediente;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-07T17:22:20", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Ingrediente.class)
public class Ingrediente_ { 

    public static volatile SingularAttribute<Ingrediente, TipoIngrediente> tipo;
    public static volatile SingularAttribute<Ingrediente, Long> id;
    public static volatile SingularAttribute<Ingrediente, Integer> cantidad;
    public static volatile SingularAttribute<Ingrediente, String> nombre;

}