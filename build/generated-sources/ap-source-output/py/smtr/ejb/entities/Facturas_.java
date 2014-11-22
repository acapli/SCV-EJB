package py.smtr.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.smtr.ejb.entities.HistorialPagos;
import py.smtr.ejb.entities.Pagos;
import py.smtr.ejb.entities.Ventas;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-16T12:57:34")
@StaticMetamodel(Facturas.class)
public class Facturas_ { 

    public static volatile SingularAttribute<Facturas, Integer> id;
    public static volatile SingularAttribute<Facturas, Ventas> idVenta;
    public static volatile SingularAttribute<Facturas, Integer> estado;
    public static volatile SingularAttribute<Facturas, Date> created;
    public static volatile ListAttribute<Facturas, HistorialPagos> historialPagosList;
    public static volatile SingularAttribute<Facturas, Double> saldo;
    public static volatile SingularAttribute<Facturas, String> numero;
    public static volatile SingularAttribute<Facturas, Date> changed;
    public static volatile ListAttribute<Facturas, Pagos> pagosList;

}