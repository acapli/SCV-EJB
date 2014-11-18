/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.eao;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;

import py.smtr.ejb.entities.Productos;
import py.smtr.ejb.entities.ProductosProveedores;
import py.smtr.ejb.entities.Proveedores;
import py.smtr.ejb.exceptions.EJBWithOutRollBackException;
import py.smtr.ejb.exceptions.EJBWithRollBackException;
import py.smtr.ejb.utilities.ConstantesEJB;


@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductosProveedoresEAO{

    @PersistenceContext(unitName = "TPWeb2011-ejbPU")
    private EntityManager em;
    private Logger logger = Logger.getLogger("log");

    protected EntityManager getEntityManager() {
        return em;
    }
    
    //@Override
    public List<ProductosProveedores> getProductosProveedoresByProducto(Productos producto) throws EJBWithOutRollBackException {
        logger.info("Entro en ProductosProveedoresEAO:" + producto);
        
        List<ProductosProveedores> listProductos = null;
        try {
            listProductos = (List<ProductosProveedores>) em.createNamedQuery("ProductosProveedores.findByIdProducto").setParameter("id", producto).getResultList();
        } catch (QueryTimeoutException ex) {
            logger.info("OUT:" + "Se ha perdido la conexión a la base de datos.");
            throw new EJBWithOutRollBackException("Se ha perdido la conexión a la base de datos.");
        } catch (TransactionRequiredException ex) {
            logger.info("OUT:" + "Se requiere de una transacción para esta operación.");
            throw new EJBWithOutRollBackException("Se requiere de una transacción para esta operación.");
        } catch (Exception ex) {
            logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithOutRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        logger.info("OUT:" + listProductos);
        return listProductos;
    }
    
    //@Override
    public ProductosProveedores getProductosProveedoresByProductoProveedor(Productos producto, Proveedores proveedor) throws EJBWithOutRollBackException {
        logger.info("IN:" + producto+";"+proveedor);
        ProductosProveedores productoProveedor = null;
        try {
            productoProveedor = (ProductosProveedores) em.createNamedQuery("ProductosProveedores.findByIdProductoIdProveedor").setParameter("idProducto", producto).setParameter("idProveedor", proveedor).getSingleResult();
        } catch (QueryTimeoutException ex) {
            logger.info("OUT:" + "Se ha perdido la conexión a la base de datos.");
            throw new EJBWithOutRollBackException("Se ha perdido la conexión a la base de datos.");
        } catch (TransactionRequiredException ex) {
            logger.info("OUT:" + "Se requiere de una transacción para esta operación.");
            throw new EJBWithOutRollBackException("Se requiere de una transacción para esta operación.");
        } catch (Exception ex) {
            logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithOutRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        logger.info("OUT:" + productoProveedor);
        return productoProveedor;
    }
    
   // @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarProductosProveedores(ProductosProveedores productosProveedores) throws EJBWithRollBackException {
        logger.info("IN:" + productosProveedores);
        try {
            em.persist(productosProveedores);
        } catch (Exception ex) {
            logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        logger.info("OUT:OK");
    }
    
    //@Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void borrarProductosProveedores(ProductosProveedores productosProveedores) throws EJBWithRollBackException {
        logger.info("IN:" + productosProveedores);
        try {
            em.remove(productosProveedores);
        } catch (Exception ex) {
            logger.info("OUT:" + "No se pudo eliminar la relacion entre " + productosProveedores.getIdProducto().getNombre() + " y " + productosProveedores.getIdProveedor().getNombre());
            throw new EJBWithRollBackException("No se pudo eliminar la relacion entre " + productosProveedores.getIdProducto().getNombre() + " y " + productosProveedores.getIdProveedor().getNombre());
        }
        logger.info("OUT:OK");
    }
    
    //@Override
    public List<ProductosProveedores> getProductosProveedoresByProveedor(Proveedores proveedor) throws EJBWithOutRollBackException {
        logger.info("IN getProductosProveedoresByProveedor en el EAO:" + proveedor);
        
        List<ProductosProveedores> listProductos = null;
        try {
            listProductos = (List<ProductosProveedores>) em.createNamedQuery("ProductosProveedores.findByIdProveedor").setParameter("idProveedor", proveedor).getResultList();
        } catch (QueryTimeoutException ex) {
            logger.info("OUT:" + "Se ha perdido la conexión a la base de datos.");
            throw new EJBWithOutRollBackException("Se ha perdido la conexión a la base de datos.");
        } catch (TransactionRequiredException ex) {
            logger.info("OUT:" + "Se requiere de una transacción para esta operación.");
            throw new EJBWithOutRollBackException("Se requiere de una transacción para esta operación.");
        } catch (Exception ex) {
            logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithOutRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        logger.info("OUT:" + listProductos);
        return listProductos;
    }
    
}
