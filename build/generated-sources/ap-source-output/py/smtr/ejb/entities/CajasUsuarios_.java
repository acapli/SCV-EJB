package py.smtr.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.smtr.ejb.entities.Cajas;
import py.smtr.ejb.entities.Usuarios;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-16T12:57:34")
@StaticMetamodel(CajasUsuarios.class)
public class CajasUsuarios_ { 

    public static volatile SingularAttribute<CajasUsuarios, Integer> id;
    public static volatile SingularAttribute<CajasUsuarios, Usuarios> idUsuario;
    public static volatile SingularAttribute<CajasUsuarios, Date> created;
    public static volatile SingularAttribute<CajasUsuarios, Boolean> activo;
    public static volatile SingularAttribute<CajasUsuarios, Cajas> idCaja;
    public static volatile SingularAttribute<CajasUsuarios, Date> changed;

}