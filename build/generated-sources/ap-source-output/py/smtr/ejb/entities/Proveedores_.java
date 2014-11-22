package py.smtr.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.smtr.ejb.entities.Compras;
import py.smtr.ejb.entities.ProductosProveedores;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-16T12:57:34")
@StaticMetamodel(Proveedores.class)
public class Proveedores_ { 

    public static volatile SingularAttribute<Proveedores, Integer> id;
    public static volatile SingularAttribute<Proveedores, String> nombre;
    public static volatile SingularAttribute<Proveedores, String> direccion;
    public static volatile SingularAttribute<Proveedores, String> email;
    public static volatile SingularAttribute<Proveedores, Date> created;
    public static volatile ListAttribute<Proveedores, ProductosProveedores> productosProveedoresList;
    public static volatile SingularAttribute<Proveedores, String> ruc;
    public static volatile SingularAttribute<Proveedores, String> telefono;
    public static volatile ListAttribute<Proveedores, Compras> comprasList;
    public static volatile SingularAttribute<Proveedores, Boolean> activo;
    public static volatile SingularAttribute<Proveedores, Date> changed;

}