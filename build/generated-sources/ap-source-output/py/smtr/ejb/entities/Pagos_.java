package py.smtr.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.smtr.ejb.entities.Cajas;
import py.smtr.ejb.entities.Facturas;
import py.smtr.ejb.entities.HistorialPagos;
import py.smtr.ejb.entities.Usuarios;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-16T12:57:34")
@StaticMetamodel(Pagos.class)
public class Pagos_ { 

    public static volatile SingularAttribute<Pagos, Integer> id;
    public static volatile SingularAttribute<Pagos, Boolean> cerrado;
    public static volatile SingularAttribute<Pagos, Usuarios> idUsuario;
    public static volatile SingularAttribute<Pagos, Date> created;
    public static volatile ListAttribute<Pagos, HistorialPagos> historialPagosList;
    public static volatile SingularAttribute<Pagos, Facturas> idFactura;
    public static volatile SingularAttribute<Pagos, Cajas> idCaja;
    public static volatile SingularAttribute<Pagos, Double> monto;
    public static volatile SingularAttribute<Pagos, Date> changed;

}