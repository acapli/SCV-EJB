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

import py.smtr.ejb.entities.Proveedores;
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
public class ProveedoresEAO{
    
    @PersistenceContext(unitName = "TPWeb2011-ejbPU")
    private EntityManager em;
      
    
    private Logger logger = Logger.getLogger("log");

    protected EntityManager getEntityManager() {
        return em;
    }
    
    //@Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int getCantidadProveedores() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Proveedores> rt = cq.from(Proveedores.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    //@Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Proveedores> getAllProveedores(Integer offset, Integer limit) throws EJBWithOutRollBackException {
        List<Proveedores> Proveedores = null;
        try {
            Query query = em.createNamedQuery("Proveedores.findAll");
            if (offset != null) {
                query.setFirstResult(offset);
            }
            if (limit != null) {
                query.setMaxResults(limit);
            }
            Proveedores = (List<Proveedores>) query.getResultList();
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
        return Proveedores;
    }
    
    //@Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Proveedores getProveedoresByRUC(String ruc) throws EJBWithOutRollBackException {
        logger.info("IN:" + ruc);
        Proveedores proveedor = null;
        try {
            proveedor = (Proveedores) em.createNamedQuery("Proveedores.findByRuc").setParameter("ruc", ruc).getSingleResult();
        } catch (NoResultException ex) {
            logger.info("OUT:" + "No se encuentra el proveedor con R.U.C.: "+ruc);
            throw new EJBWithOutRollBackException("No se encuentra el proveedor con R.U.C.: "+ruc);
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
        logger.info("OUT:" + proveedor);
        return proveedor;
    }
    
    //@Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarProveedor(Proveedores proveedor) throws EJBWithRollBackException {
        logger.info("IN:" + proveedor);
        try {
            em.persist(proveedor);
        } catch (Exception ex) {
            logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        logger.info("OUT:OK");
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void borrarProveedor(Proveedores proveedor) throws Exception {
        logger.info("IN entro en el EAO:" + proveedor);
        try {
          
           
       
            em.remove(em.merge(proveedor));
        } catch (Exception ex) {
            logger.info("OUT:" + "No se pudo eliminar el proveedor: " + proveedor.getNombre()+" - "+ex.getMessage());
            throw new Exception("No se pudo eliminar el proveedor: " + proveedor.getNombre());
        }
        logger.info("OUT:OK");
    }
    
    //@Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Proveedores getProveedorById(Integer id) throws EJBWithOutRollBackException {
        logger.info("IN:" + id);
        Proveedores proveedor = null;
        try {
            proveedor = (Proveedores) em.createNamedQuery("Proveedores.findById").setParameter("id", id).getSingleResult();
        } catch (NoResultException ex) {
            logger.info("OUT:" + "No existe el proveedor con id: " + id);
            throw new EJBWithOutRollBackException("No existe el proveedor con id: " + id);
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
        logger.info("OUT:" + proveedor);
        return proveedor;
    }
}
