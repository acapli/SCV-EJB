package py.smtr.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.smtr.ejb.entities.Productos;
import py.smtr.ejb.entities.Proveedores;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-16T12:57:34")
@StaticMetamodel(ProductosProveedores.class)
public class ProductosProveedores_ { 

    public static volatile SingularAttribute<ProductosProveedores, Integer> id;
    public static volatile SingularAttribute<ProductosProveedores, Date> created;
    public static volatile SingularAttribute<ProductosProveedores, Proveedores> idProveedor;
    public static volatile SingularAttribute<ProductosProveedores, Productos> idProducto;
    public static volatile SingularAttribute<ProductosProveedores, Boolean> activo;
    public static volatile SingularAttribute<ProductosProveedores, Date> changed;

}