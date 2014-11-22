package py.smtr.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.smtr.ejb.entities.RolesUsuarios;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-16T12:57:34")
@StaticMetamodel(Roles.class)
public class Roles_ { 

    public static volatile SingularAttribute<Roles, Integer> id;
    public static volatile SingularAttribute<Roles, String> nombre;
    public static volatile SingularAttribute<Roles, Date> created;
    public static volatile ListAttribute<Roles, RolesUsuarios> rolesUsuariosList;
    public static volatile SingularAttribute<Roles, Boolean> activo;
    public static volatile SingularAttribute<Roles, Date> changed;

}