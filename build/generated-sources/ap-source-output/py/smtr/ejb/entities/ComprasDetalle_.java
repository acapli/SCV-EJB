package py.smtr.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.smtr.ejb.entities.Compras;
import py.smtr.ejb.entities.Productos;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-16T12:57:34")
@StaticMetamodel(ComprasDetalle.class)
public class ComprasDetalle_ { 

    public static volatile SingularAttribute<ComprasDetalle, Integer> id;
    public static volatile SingularAttribute<ComprasDetalle, Date> created;
    public static volatile SingularAttribute<ComprasDetalle, Double> cantidad;
    public static volatile SingularAttribute<ComprasDetalle, Productos> idProducto;
    public static volatile SingularAttribute<ComprasDetalle, Double> costo;
    public static volatile SingularAttribute<ComprasDetalle, Date> changed;
    public static volatile SingularAttribute<ComprasDetalle, Compras> idCompra;

}