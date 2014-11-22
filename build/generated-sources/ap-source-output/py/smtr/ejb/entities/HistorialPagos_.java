package py.smtr.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.smtr.ejb.entities.Cajas;
import py.smtr.ejb.entities.Clientes;
import py.smtr.ejb.entities.Facturas;
import py.smtr.ejb.entities.Pagos;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-16T12:57:34")
@StaticMetamodel(HistorialPagos.class)
public class HistorialPagos_ { 

    public static volatile SingularAttribute<HistorialPagos, Integer> id;
    public static volatile SingularAttribute<HistorialPagos, Double> saldoActual;
    public static volatile SingularAttribute<HistorialPagos, Boolean> cerrado;
    public static volatile SingularAttribute<HistorialPagos, Double> saldoParcial;
    public static volatile SingularAttribute<HistorialPagos, Cajas> idUsuario;
    public static volatile SingularAttribute<HistorialPagos, Pagos> idPagos;
    public static volatile SingularAttribute<HistorialPagos, Clientes> idCliente;
    public static volatile SingularAttribute<HistorialPagos, Double> saldoAnterior;
    public static volatile SingularAttribute<HistorialPagos, Facturas> idFactura;
    public static volatile SingularAttribute<HistorialPagos, Date> changed;

}