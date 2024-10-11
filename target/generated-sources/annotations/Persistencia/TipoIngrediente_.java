package Persistencia;

import Persistencia.Ingrediente;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-07T17:22:20", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(TipoIngrediente.class)
public class TipoIngrediente_ { 

    public static volatile SingularAttribute<TipoIngrediente, String> descripcion;
    public static volatile ListAttribute<TipoIngrediente, Ingrediente> ingredientes;
    public static volatile SingularAttribute<TipoIngrediente, Long> id;

}