package py.smtr.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.smtr.ejb.entities.HistorialPagos;
import py.smtr.ejb.entities.Ventas;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-16T12:57:34")
@StaticMetamodel(Clientes.class)
public class Clientes_ { 

    public static volatile SingularAttribute<Clientes, Integer> id;
    public static volatile SingularAttribute<Clientes, String> nombre;
    public static volatile SingularAttribute<Clientes, String> direccion;
    public static volatile SingularAttribute<Clientes, String> mail;
    public static volatile SingularAttribute<Clientes, Date> created;
    public static volatile ListAttribute<Clientes, HistorialPagos> historialPagosList;
    public static volatile SingularAttribute<Clientes, String> ruc;
    public static volatile SingularAttribute<Clientes, String> telefono;
    public static volatile ListAttribute<Clientes, Ventas> ventasList;
    public static volatile SingularAttribute<Clientes, Boolean> activo;
    public static volatile SingularAttribute<Clientes, Date> changed;

}