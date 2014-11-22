package py.smtr.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.smtr.ejb.entities.CajasUsuarios;
import py.smtr.ejb.entities.HistorialPagos;
import py.smtr.ejb.entities.Pagos;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-16T12:57:34")
@StaticMetamodel(Cajas.class)
public class Cajas_ { 

    public static volatile SingularAttribute<Cajas, Integer> id;
    public static volatile SingularAttribute<Cajas, String> nombre;
    public static volatile ListAttribute<Cajas, CajasUsuarios> cajasUsuariosList;
    public static volatile SingularAttribute<Cajas, Date> created;
    public static volatile ListAttribute<Cajas, HistorialPagos> historialPagosList;
    public static volatile SingularAttribute<Cajas, Boolean> activo;
    public static volatile SingularAttribute<Cajas, Date> changed;
    public static volatile ListAttribute<Cajas, Pagos> pagosList;

}