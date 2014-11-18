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
import py.smtr.ejb.entities.Facturas;
import py.smtr.ejb.entities.HistorialPagos;
import py.smtr.ejb.entities.Pagos;
import py.smtr.ejb.exceptions.EJBWithOutRollBackException;
import py.smtr.ejb.exceptions.EJBWithRollBackException;
import py.smtr.ejb.utilities.ConstantesEJB;


@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FacturasEAO {

    @PersistenceContext(unitName = "TPWeb2011-ejbPU")
    private EntityManager em;
    private Logger logger = Logger.getLogger("log");

    protected EntityManager getEntityManager() {
        return em;
    }

        public List<Facturas> getFacturasPendientes(Integer offset, Integer limit) throws EJBWithOutRollBackException {
        logger.info("IN getFacturasPendientes EAO:" + offset + ";" + limit);
        List<Facturas> facturas = null;
        try {
            Query query = em.createNamedQuery("Facturas.findByPendientes").setParameter("estado1", new Integer(1)).setParameter("estado2", new Integer(2));
            if (offset != null) {
                query.setFirstResult(offset);
            }
            if (limit != null) {
                query.setMaxResults(limit);
            }
            facturas = (List<Facturas>) query.getResultList();
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
        logger.info("OUT:" + facturas);
        return facturas;
    }
        
        public List<HistorialPagos> getFacturasPagadas(Integer offset, Integer limit) throws EJBWithOutRollBackException {
        logger.info("IN getFacturasPagadas EAO:" + offset + ";" + limit);
        List<HistorialPagos> facturas = null;
        try {
            Query query = em.createNamedQuery("HistorialPagos.findByCerrado").setParameter("cerrado", false);
           // Query query = em.createNamedQuery("HistorialPagos.findBySaldoParcial").setParameter("cerrado", false);
            
            if (offset != null) {
                query.setFirstResult(offset);
            }
            if (limit != null) {
                query.setMaxResults(limit);
            }
            facturas = (List<HistorialPagos>) query.getResultList();
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
        logger.info("OUT FACTURAS OBTENIDAS:" + facturas);
        return facturas;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarFactura(Facturas factura) throws EJBWithRollBackException {
        logger.info("IN:" + factura);
        try {
            em.persist(factura);
        } catch (Exception ex) {
            logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        logger.info("OUT:OK");
    }

        public Facturas getFacturaById(Integer id) throws EJBWithOutRollBackException {
        logger.info("IN:" + id);
        Facturas factura = null;
        try {
            factura = (Facturas) em.createNamedQuery("Facturas.findById").setParameter("id", id).getSingleResult();
        } catch (NoResultException ex) {
            logger.info("OUT:" + "No existe la factura con id: " + id);
            throw new EJBWithOutRollBackException("No existe la factura con id: " + id);
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
        logger.info("OUT:" + factura);
        return factura;
    }

        public Facturas getFacturaByNumero(String numero) throws EJBWithOutRollBackException {
        logger.info("IN:" + numero);
        Facturas factura = null;
        try {
            factura = (Facturas) em.createNamedQuery("Facturas.findByNumero").setParameter("numero", numero).getSingleResult();
        } catch (NoResultException ex) {
            logger.info("OUT:" + "No existe la factura con número: " + numero);
            throw new EJBWithOutRollBackException("No existe la factura con número: " + numero);
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
        logger.info("OUT:OK");
        return factura;
    }
}
