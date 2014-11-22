package py.smtr.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.smtr.ejb.entities.Productos;
import py.smtr.ejb.entities.Ventas;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-16T12:57:34")
@StaticMetamodel(VentasDetalle.class)
public class VentasDetalle_ { 

    public static volatile SingularAttribute<VentasDetalle, Integer> id;
    public static volatile SingularAttribute<VentasDetalle, Ventas> idVenta;
    public static volatile SingularAttribute<VentasDetalle, Double> precio;
    public static volatile SingularAttribute<VentasDetalle, Date> created;
    public static volatile SingularAttribute<VentasDetalle, Double> cantidad;
    public static volatile SingularAttribute<VentasDetalle, Productos> idProducto;
    public static volatile SingularAttribute<VentasDetalle, Date> changed;

}