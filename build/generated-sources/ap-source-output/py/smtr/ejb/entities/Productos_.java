package py.smtr.ejb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.smtr.ejb.entities.ComprasDetalle;
import py.smtr.ejb.entities.ProductosProveedores;
import py.smtr.ejb.entities.VentasDetalle;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-16T12:57:34")
@StaticMetamodel(Productos.class)
public class Productos_ { 

    public static volatile SingularAttribute<Productos, Integer> id;
    public static volatile SingularAttribute<Productos, String> nombre;
    public static volatile SingularAttribute<Productos, String> codigo;
    public static volatile ListAttribute<Productos, VentasDetalle> ventasDetalleList;
    public static volatile SingularAttribute<Productos, Double> porcentajeGanancia;
    public static volatile SingularAttribute<Productos, Date> created;
    public static volatile ListAttribute<Productos, ProductosProveedores> productosProveedoresList;
    public static volatile SingularAttribute<Productos, Double> costo;
    public static volatile SingularAttribute<Productos, Double> cantidadExistencia;
    public static volatile SingularAttribute<Productos, Boolean> activo;
    public static volatile ListAttribute<Productos, ComprasDetalle> comprasDetalleList;
    public static volatile SingularAttribute<Productos, Date> changed;

}