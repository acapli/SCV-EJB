package py.smtr.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.smtr.ejb.entities.Eventos;
import py.smtr.ejb.entities.Roles;
import py.smtr.ejb.entities.Usuarios;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-16T12:57:34")
@StaticMetamodel(RolesUsuarios.class)
public class RolesUsuarios_ { 

    public static volatile SingularAttribute<RolesUsuarios, Integer> id;
    public static volatile ListAttribute<RolesUsuarios, Eventos> eventosList;
    public static volatile SingularAttribute<RolesUsuarios, Usuarios> idUsuario;
    public static volatile SingularAttribute<RolesUsuarios, Date> created;
    public static volatile SingularAttribute<RolesUsuarios, Roles> idRol;
    public static volatile SingularAttribute<RolesUsuarios, Boolean> activo;
    public static volatile SingularAttribute<RolesUsuarios, Date> changed;

}