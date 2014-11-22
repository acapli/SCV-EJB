package py.smtr.ejb.entities;

import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.smtr.ejb.entities.RolesUsuarios;
import py.smtr.ejb.entities.TiposEventos;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-16T12:57:34")
@StaticMetamodel(Eventos.class)
public class Eventos_ { 

    public static volatile SingularAttribute<Eventos, Long> id;
    public static volatile SingularAttribute<Eventos, Date> created;
    public static volatile SingularAttribute<Eventos, RolesUsuarios> idUsuarioRol;
    public static volatile SingularAttribute<Eventos, BigInteger> evento;
    public static volatile SingularAttribute<Eventos, TiposEventos> idTipoEvento;

}