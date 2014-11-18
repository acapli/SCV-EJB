/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.eao;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import py.smtr.ejb.entities.Productos;
import py.smtr.ejb.exceptions.EJBWithOutRollBackException;
import py.smtr.ejb.exceptions.EJBWithRollBackException;
import py.smtr.ejb.utilities.ConstantesEJB;
import java.util.logging.Logger;
/**
 *
 * @author Strogg
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductosEAO {

    @PersistenceContext(unitName = "TPWeb2011-ejbPU")
    private EntityManager em;
    private Logger logger = Logger.getLogger("log");

    protected EntityManager getEntityManager() {
        return em;
    }

    //@Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarProducto(Productos producto) throws EJBWithRollBackException {
        logger.info("IN:" + producto);
        try {
            em.persist(producto);
        } catch (Exception ex) {
            logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        logger.info("OUT:OK");
    }
    
   // @Override
   // @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int getCantidadProductos() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Productos> rt = cq.from(Productos.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    //@Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Productos> getAllProductos(Integer offset, Integer limit) throws EJBWithOutRollBackException {
        logger.info("IN:" + offset + ";" + limit);
        List<Productos> productos = null;
        try {
            Query query = em.createNamedQuery("Productos.findAll");
            if (offset != null) {
                query.setFirstResult(offset);
            }
            if (limit != null) {
                query.setMaxResults(limit);
            }
            productos = (List<Productos>) query.getResultList();
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
        logger.info("OUT:"+productos);
        return productos;
    }
    
   // @Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Productos getProductoByCodigo(String codigo) throws EJBWithOutRollBackException {
        logger.info("IN:" + codigo);
        Productos producto = null;
        try {
            producto = (Productos) em.createNamedQuery("Productos.findByCodigo").setParameter("codigo", codigo).getSingleResult();
        } catch (NoResultException ex) {
            logger.info("OUT:" + "No se encuentra el producto con código: "+codigo);
            throw new EJBWithOutRollBackException("No se encuentra el producto con código: "+codigo);
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
        logger.info("OUT:" + producto);
        return producto;
    }
    
    //@Override
   // @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Productos getProductoById(Integer id) throws EJBWithOutRollBackException {
        logger.info("IN:" + id);
        Productos producto = null;
        try {
            producto = (Productos) em.createNamedQuery("Productos.findById").setParameter("id", id).getSingleResult();
        } catch (NoResultException ex) {
            logger.info("OUT:" + "No se encuentra el producto con id: "+id);
            throw new EJBWithOutRollBackException("No se encuentra el producto con id: "+id);
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
        logger.info("OUT:" + producto);
        return producto;
    }
    
    //@Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void borrarProducto(Productos producto) throws EJBWithRollBackException {
        logger.info("IN:" + producto);
        try {
            em.remove(producto);
        } catch (Exception ex) {
            logger.info("OUT:" + "No se pudo eliminar el producto: " + producto.getNombre());
            throw new EJBWithRollBackException("No se pudo eliminar el producto: " + producto.getNombre());
        }
        logger.info("OUT:OK");
    }
}
