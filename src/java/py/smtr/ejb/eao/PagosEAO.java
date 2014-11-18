/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.eao;

import java.util.Calendar;
import java.util.Date;
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
import py.smtr.ejb.entities.Cajas;
import py.smtr.ejb.entities.HistorialPagos;
import py.smtr.ejb.entities.Pagos;
import py.smtr.ejb.exceptions.EJBWithOutRollBackException;
import py.smtr.ejb.exceptions.EJBWithRollBackException;
import py.smtr.ejb.utilities.ConstantesEJB;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PagosEAO  {
    @PersistenceContext(unitName = "TPWeb2011-ejbPU")
    private EntityManager em;
    private Logger logger = Logger.getLogger("log");

    protected EntityManager getEntityManager() {
        return em;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardaPago(Pagos pago) throws EJBWithRollBackException {
        logger.info("IN guardaPago EAO:" + pago);
        try {
            em.persist(pago);
        } catch (Exception ex) {
            logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        logger.info("OUT:OK Pago procesado con éxito");
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardaHistorialPago(HistorialPagos pagos) throws EJBWithRollBackException {
        logger.info("IN guardaHistorialPago EAO:" + pagos);
        try {
            em.persist(pagos);
        } catch (Exception ex) {
            logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        logger.info("OUT:OK Pago Parcial registrado exitosamente");
    }
    
        public List<Pagos> getPagosByCaja(Cajas caja) throws EJBWithOutRollBackException {
        logger.info("IN:" + caja);
        Calendar ahoraCal = Calendar.getInstance();
        Date timeActual = ahoraCal.getTime();
        Calendar inicioCal = Calendar.getInstance();
        inicioCal.set(ahoraCal.get(Calendar.YEAR), ahoraCal.get(Calendar.MONTH), ahoraCal.get(Calendar.DATE), 0, 0, 0);
        Date time0HS = inicioCal.getTime();
        List<Pagos> pagos = null;
        try {
            pagos = (List<Pagos>) em.createNamedQuery("Pagos.findByIdCaja").setParameter("idCaja", caja).setParameter("startDate", time0HS).setParameter("endDate", timeActual).setParameter("cerrado", false).getResultList();
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
        
        if(pagos.isEmpty()) {
            logger.info("OUT:" + "La caja no posee pagos pendientes de cierre");
            throw new EJBWithOutRollBackException("La caja no posee pagos pendientes de cierre");
        }
        
        logger.info("OUT:" + pagos);
        return pagos;
    }
}
