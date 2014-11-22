package py.smtr.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.smtr.ejb.entities.ComprasDetalle;
import py.smtr.ejb.entities.Proveedores;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-16T12:57:34")
@StaticMetamodel(Compras.class)
public class Compras_ { 

    public static volatile SingularAttribute<Compras, Integer> id;
    public static volatile SingularAttribute<Compras, Double> total;
    public static volatile SingularAttribute<Compras, Date> created;
    public static volatile SingularAttribute<Compras, Proveedores> idProveedor;
    public static volatile SingularAttribute<Compras, Date> fechaCompra;
    public static volatile ListAttribute<Compras, ComprasDetalle> comprasDetalleList;
    public static volatile SingularAttribute<Compras, Date> changed;

}