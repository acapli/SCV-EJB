/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.facades;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import py.smtr.ejb.entities.Productos;
import py.smtr.ejb.entities.ProductosProveedores;
import py.smtr.ejb.entities.Proveedores;
import py.smtr.ejb.exceptions.EJBWithOutRollBackException;
import py.smtr.ejb.exceptions.EJBWithRollBackException;

import py.smtr.ejb.eao.ProductosEAO;
import py.smtr.ejb.eao.ProductosProveedoresEAO;
import py.smtr.ejb.eao.ProveedoresEAO;

import py.smtr.ejb.eao.UsuarioEAO;
import py.smtr.ejb.shared.ResponseEntidad;
import py.smtr.ejb.utilities.ConstantesEJB;
import java.util.logging.Logger;
/**
 *
 * @author Strogg
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductosFacade{

    private Logger logger = Logger.getLogger("log");
    @EJB
    private ProductosFacade productoFacade;
    @EJB
    private UsuarioEAO usuarioEAO;
    @EJB
    private ProductosEAO productosEAO;
    @EJB
    private ProveedoresEAO proveedoresEAO;
    @EJB
    private ProductosProveedoresEAO productosProveedoresEAO;

    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ResponseEntidad listarProductos(String sesion, Integer offset, Integer limit) throws EJBWithOutRollBackException {
       
        logger.info("IN - Entro en listarProductos Facade:" + sesion + ";" + offset + ";" + limit);

       //usuarioEAO.getUsuarioBySesion(sesion);

        ResponseEntidad<Productos> resp = new ResponseEntidad<Productos>();
        Integer cantidad = 0;
        List<Productos> productos = null;

        cantidad = productosEAO.getCantidadProductos();
        productos = productosEAO.getAllProductos(offset, limit);

        resp.setCantidadTotal(cantidad);
        resp.setListaEntidad(productos);
        logger.info("OUT Cantidad de Productos:" + cantidad + ";" + productos.toString());
        return resp;
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarProducto(String sesion, String nombre, String codigo, double porcentaje_ganancia, double costo, double cantidad_existencia, Boolean activo) throws EJBWithRollBackException {
        logger.info("IN:" + sesion + ";" + nombre + ";" + codigo + ";" + porcentaje_ganancia + ";" + costo + ";" + cantidad_existencia + ";" + activo);

//        try {
//            usuarioEAO.getUsuarioBySesion(sesion);
//        } catch (EJBWithOutRollBackException ex) {
//            logger.info("OUT:" + ex.getMessage());
//            throw new EJBWithRollBackException(ex.getMessage());
//        }
        Productos aux = null;
        try {
            aux = productosEAO.getProductoByCodigo(codigo);
        } catch (EJBWithOutRollBackException ex) {
             logger.info("ERROR  EN GUARDAR PRODUCTO!!");
            
        }
        if (aux != null) {
            logger.info("OUT:" + ConstantesEJB.PROD_MISMO_COD);
            throw new EJBWithRollBackException(ConstantesEJB.PROD_MISMO_COD);
        }
        Productos nuevoProducto = new Productos();
      
        nuevoProducto.setNombre(nombre);
        nuevoProducto.setCodigo(codigo);
        nuevoProducto.setPorcentajeGanancia(porcentaje_ganancia);
        nuevoProducto.setCosto(costo);
        nuevoProducto.setCantidadExistencia(cantidad_existencia);
        nuevoProducto.setActivo(activo);
        nuevoProducto.setCreated(new Date(System.currentTimeMillis()));

        productosEAO.guardarProducto(nuevoProducto);

        logger.info("Producto Guardado!!");
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void borrarListProductos(String sesion, List<Integer> listaIds) throws EJBWithRollBackException {
        logger.info("IN:" + sesion + ";" + listaIds.toString());

//        try {
//            usuarioEAO.getUsuarioBySesion(sesion);
//        } catch (EJBWithOutRollBackException ex) {
//            logger.info("OUT:" + ex.getMessage());
//            throw new EJBWithRollBackException(ex.getMessage());
//        }

        for (Integer id : listaIds) {
            productoFacade.borrarProducto(id);
        }
        logger.info("OUT:OK");
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void borrarProducto(Integer id) throws EJBWithRollBackException {
        logger.info("IN:" + id);

        Productos producto = null;
        try {
            producto = productosEAO.getProductoById(id);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
        productosEAO.borrarProducto(producto);

        logger.info("OUT:OK");
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void actualizarProducto(String sesion, Integer id, String nombre, String codigo, double porcentajeGanancia, double costo, double cantidadExistencia, Boolean activo) throws EJBWithRollBackException {
        logger.info("IN:" + sesion + ";" + id + ";" + nombre + ";" + codigo + ";" + porcentajeGanancia + ";" + costo + ";" + cantidadExistencia + ";" + activo);

//        try {
//            usuarioEAO.getUsuarioBySesion(sesion);
//        } catch (EJBWithOutRollBackException ex) {
//            logger.info("OUT:" + ex.getMessage());
//            throw new EJBWithRollBackException(ex.getMessage());
//        }

        Productos aux = null;
        try {
            aux = productosEAO.getProductoByCodigo(codigo);
        } catch (EJBWithOutRollBackException ex) {
        }
        if (aux != null && aux.getId() != id) {
            logger.info("OUT:" + ConstantesEJB.PROD_MISMO_COD);
            throw new EJBWithRollBackException(ConstantesEJB.PROD_MISMO_COD);
        }

        Productos producto = null;
        try {
            producto = productosEAO.getProductoById(id);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
        producto.setNombre(nombre);
        producto.setCodigo(codigo);
        producto.setPorcentajeGanancia(porcentajeGanancia);
        producto.setCosto(costo);
        producto.setCantidadExistencia(cantidadExistencia);
        producto.setActivo(activo);
        producto.setChanged(new Date(System.currentTimeMillis()));
        productosEAO.guardarProducto(producto);

        logger.info("OUT:OK");
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void actualizarStock(String sesion, Integer id, double cantidadCV) throws EJBWithRollBackException {
        logger.info("IN:" + sesion + ";" + id + ";" + cantidadCV);

        try {
            usuarioEAO.getUsuarioBySesion(sesion);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }

        Productos producto = null;
        try {
            producto = productosEAO.getProductoById(id);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
        double cantidadActual = producto.getCantidadExistencia();
        double cantidadNueva = cantidadActual + cantidadCV;
        if (cantidadNueva < 0) {
            logger.info("OUT:" + "Se ha superado la cantidad de la existencia del producto.");
            throw new EJBWithRollBackException("Se ha superado la cantidad de la existencia del producto " + producto.getNombre() + ".");
        }
        producto.setCantidadExistencia(cantidadNueva);
        productosEAO.guardarProducto(producto);

        logger.info("OUT:OK");
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void actualizarCosto(String sesion, Integer id, double cantidadCompra, double costoCompra) throws EJBWithRollBackException {
        logger.info("IN:" + sesion + ";" + id + ";" + cantidadCompra + ";" + costoCompra);

        try {
            usuarioEAO.getUsuarioBySesion(sesion);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }

        Productos producto = null;
        try {
            producto = productosEAO.getProductoById(id);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
        double costoActual = producto.getCosto();
        double cantidadActual = producto.getCantidadExistencia();
        double costoNuevo = (costoActual * cantidadActual + costoCompra * cantidadCompra) / (cantidadActual + cantidadCompra);
        DecimalFormat df = new DecimalFormat("#.##");
        producto.setCosto(Double.valueOf(df.format(costoNuevo).replaceAll(",", ".")));
        productosEAO.guardarProducto(producto);

        logger.info("OUT:OK");
    }

    /**
     * getProveedores(String sesion);
     * getListaProoveedoresAsignados(String sesion, Integer idProducto)
     * actualizarProveedoresProducto(String sesion, Integer idProducto, List<Proveedores> proveedores) 
     * asignarProveedorProducto(String sesion, Integer idProducto, Integer idProveedor)
     * eliminarProveedorProducto(String sesion, Integer idProducto, Integer idProveedor)
     *  
     */

       // @Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Proveedores> getListaProveedoresAsignados(String sesion, Integer idProducto) throws EJBWithOutRollBackException {
        logger.info("IN:" + sesion);

       // usuarioEAO.getUsuarioBySesion(sesion);

        Productos producto = productosEAO.getProductoById(idProducto);

        /*        //controlar que exista el producto
        Productos producto = productoFacade.find(idProducto);
        if (producto == null) {
        throw new EJBException(ConstantesEJB.PROD_NO_EXIST);
        }       */
        try {
            List<ProductosProveedores> productoProveedores = productosProveedoresEAO.getProductosProveedoresByProducto(producto);
            List<Proveedores> myProveedores = new ArrayList<Proveedores>();

            if (!productoProveedores.isEmpty()) {
                for (ProductosProveedores productoProveedor : productoProveedores) {
                    myProveedores.add(productoProveedor.getIdProveedor());
                }
                logger.info("Retorna Lista!!");
                return myProveedores;
            } else {
                logger.info("Retorna Lista Vacia!!");
                return new ArrayList<Proveedores>();        //se retorna una lista vacia...
            }
        } catch (Exception ex) {
            logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithOutRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void asignarProveedorProducto(String sesion, Integer idProducto, Integer idProveedor) throws EJBWithRollBackException {
        logger.info("IN:" + sesion);
        Productos producto = null;
        Proveedores proveedor = null;
        try {
            usuarioEAO.getUsuarioBySesion(sesion);
            producto = productosEAO.getProductoById(idProducto);
            proveedor = proveedoresEAO.getProveedorById(idProveedor);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }

        ProductosProveedores productoProveedor = null;
        try {
            productoProveedor = productosProveedoresEAO.getProductosProveedoresByProductoProveedor(producto, proveedor);
        } catch (Exception e) {
            //ya tiene asignado ese proveedor            
        }

        if (productoProveedor == null) {
            //no tiene asignado a ese proveedor --> entonces asignar            
            productoProveedor = new ProductosProveedores();
            productoProveedor.setIdProducto(producto);
            productoProveedor.setIdProveedor(proveedor);
            productoProveedor.setCreated(new Date(System.currentTimeMillis()));
            productoProveedor.setActivo(true);

            productosProveedoresEAO.guardarProductosProveedores(productoProveedor);
        }
        logger.info("OUT:OK");
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void eliminarProveedorProducto(String sesion, Integer idProducto, Integer idProveedor) throws EJBWithRollBackException {
        logger.info("IN:" + sesion);
        Productos producto = null;
        Proveedores proveedor = null;
        try {
            usuarioEAO.getUsuarioBySesion(sesion);
            producto = productosEAO.getProductoById(idProducto);
            proveedor = proveedoresEAO.getProveedorById(idProveedor);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }

        ProductosProveedores productoProveedor = null;
        try {
          //  productoProveedor = productosProveedoresEAO.getProductosProveedoresByProductoProveedor(producto, proveedor);
        } catch (Exception e) {
            //no tiene asignado al proveedor --> no se puede eliminar                                    
        }
        if (productoProveedor != null) {
            //ya tiene asignado este proveedor -->eliminarlo!                
           // productosProveedoresEAO.borrarProductosProveedores(productoProveedor);
        }
        logger.info("OUT:OK");
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void actualizarProveedoresProducto(String sesion, Integer idProducto, List<Proveedores> proveedores) throws EJBWithRollBackException {
        logger.info("IN:" + sesion);
        logger.info("------------Accedio a actualizarProveedoresProducto de ProductosFacade-----------");
        try {
            usuarioEAO.getUsuarioBySesion(sesion);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
        try {
            for (Proveedores proveedor : proveedores) {
                if (proveedor.getActivo()) {
                    productoFacade.asignarProveedorProducto(sesion, idProducto, proveedor.getId());
                } else {
                    productoFacade.eliminarProveedorProducto(sesion, idProducto, proveedor.getId());
                }
            }
        } catch (Exception ex) {
            logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            //throw new EJBWithRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        logger.info("OUT:OK");
    }


   public List<ProductosProveedores> getProductosProveedoresByProveedor(String sesion, Integer idProveedor) throws EJBWithOutRollBackException {
       logger.info("IN:" + sesion);
      // usuarioEAO.getUsuarioBySesion(sesion);
       Proveedores proveedor = proveedoresEAO.getProveedorById(idProveedor);
       List<ProductosProveedores> productosProveedoresByProveedor = productosProveedoresEAO.getProductosProveedoresByProveedor(proveedor);
        logger.info("OUT:" + productosProveedoresByProveedor);
        return productosProveedoresByProveedor;
   }

}
