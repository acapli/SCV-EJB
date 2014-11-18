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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import py.smtr.ejb.entities.Clientes;
import py.smtr.ejb.exceptions.EJBWithOutRollBackException;
import py.smtr.ejb.exceptions.EJBWithRollBackException;
import py.smtr.ejb.utilities.ConstantesEJB;


/**
 *
 * @author Strogg
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ClientesEAO {

    @PersistenceContext(unitName = "TPWeb2011-ejbPU")
    private EntityManager em;
    
     private Logger logger = Logger.getLogger("log");

    protected EntityManager getEntityManager() {
        return em;
    }
    
    //@Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int getCantidadClientes() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Clientes> rt = cq.from(Clientes.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    

    public List<Clientes> obtenerTodosClientes(Integer offset, Integer limit) throws EJBWithOutRollBackException {
        List<Clientes> clientes = null;
        try {
            Query query = em.createNamedQuery("Clientes.findAll");
            if (offset != null) {
                query.setFirstResult(offset);
            }
            if (limit != null) {
                query.setMaxResults(limit);
            }
            clientes = (List<Clientes>) query.getResultList();
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
        return clientes;
    }
    

    public Clientes getClientesByRUC(String ruc) throws EJBWithOutRollBackException {
        logger.info("IN:" + ruc);
        Clientes cliente = null;
        try {
            cliente = (Clientes) em.createNamedQuery("Clientes.findByRuc").setParameter("ruc", ruc).getSingleResult();
        } catch (NoResultException ex) {
            logger.info("OUT:" + "No se encuentra el cliente con R.U.C.: "+ruc);
            throw new EJBWithOutRollBackException("No se encuentra el cliente con R.U.C.: "+ruc);
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
        logger.info("OUT:" + cliente);
        return cliente;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarCliente(Clientes cliente) throws EJBWithRollBackException {
        logger.info("IN:" + cliente);
        try {
            em.persist(cliente);
        } catch (Exception ex) {
            logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        logger.info("OUT:OK");
    }
    

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void borrarCliente(Clientes cliente) throws EJBWithRollBackException {
        logger.info("IN:" + cliente);
        try {
            em.remove(cliente);
        } catch (Exception ex) {
            logger.info("OUT:" + "No se pudo eliminar el cliente: " + cliente.getNombre());
            throw new EJBWithRollBackException("No se pudo eliminar el cliente: " + cliente.getNombre());
        }
        logger.info("OUT:OK");
    }
    

    public Clientes getClienteById(Integer id) throws EJBWithOutRollBackException {
        logger.info("IN:" + id);
        Clientes cliente = null;
        try {
            cliente = (Clientes) em.createNamedQuery("Clientes.findById").setParameter("id", id).getSingleResult();
        } catch (NoResultException ex) {
            logger.info("OUT:" + "No existe el cliente con id: " + id);
            throw new EJBWithOutRollBackException("No existe el cliente con id: " + id);
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
        logger.info("OUT:" + cliente);
        return cliente;
    }
}
