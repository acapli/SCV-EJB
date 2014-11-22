package py.smtr.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.smtr.ejb.entities.Clientes;
import py.smtr.ejb.entities.Facturas;
import py.smtr.ejb.entities.VentasDetalle;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-16T12:57:34")
@StaticMetamodel(Ventas.class)
public class Ventas_ { 

    public static volatile SingularAttribute<Ventas, Integer> id;
    public static volatile SingularAttribute<Ventas, Double> total;
    public static volatile ListAttribute<Ventas, VentasDetalle> ventasDetalleList;
    public static volatile SingularAttribute<Ventas, Date> created;
    public static volatile SingularAttribute<Ventas, Clientes> idCliente;
    public static volatile ListAttribute<Ventas, Facturas> facturasList;
    public static volatile SingularAttribute<Ventas, Date> fechaVenta;
    public static volatile SingularAttribute<Ventas, Date> changed;

}