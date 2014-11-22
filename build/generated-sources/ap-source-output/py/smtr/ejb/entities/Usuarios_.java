package py.smtr.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.smtr.ejb.entities.CajasUsuarios;
import py.smtr.ejb.entities.Pagos;
import py.smtr.ejb.entities.RolesUsuarios;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-16T12:57:34")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile SingularAttribute<Usuarios, String> nombre;
    public static volatile SingularAttribute<Usuarios, Integer> id;
    public static volatile SingularAttribute<Usuarios, CajasUsuarios> cajasUsuarios;
    public static volatile SingularAttribute<Usuarios, Date> created;
    public static volatile SingularAttribute<Usuarios, String> email;
    public static volatile SingularAttribute<Usuarios, Long> ci;
    public static volatile ListAttribute<Usuarios, RolesUsuarios> rolesUsuariosList;
    public static volatile SingularAttribute<Usuarios, String> sesion;
    public static volatile SingularAttribute<Usuarios, String> login;
    public static volatile SingularAttribute<Usuarios, String> password;
    public static volatile SingularAttribute<Usuarios, Boolean> activo;
    public static volatile SingularAttribute<Usuarios, Date> changed;
    public static volatile ListAttribute<Usuarios, Pagos> pagosList;

}