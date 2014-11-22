package py.smtr.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.smtr.ejb.entities.Eventos;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-16T12:57:34")
@StaticMetamodel(TiposEventos.class)
public class TiposEventos_ { 

    public static volatile SingularAttribute<TiposEventos, Integer> id;
    public static volatile SingularAttribute<TiposEventos, String> nombre;
    public static volatile ListAttribute<TiposEventos, Eventos> eventosList;
    public static volatile SingularAttribute<TiposEventos, Date> created;
    public static volatile SingularAttribute<TiposEventos, Boolean> activo;
    public static volatile SingularAttribute<TiposEventos, Date> changed;

}