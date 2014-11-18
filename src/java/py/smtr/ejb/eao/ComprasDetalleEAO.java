/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.eao;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.smtr.ejb.entities.ComprasDetalle;
import py.smtr.ejb.exceptions.EJBWithRollBackException;
import py.smtr.ejb.utilities.ConstantesEJB;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ComprasDetalleEAO {
    @PersistenceContext(unitName = "TPWeb2011-ejbPU")
    private EntityManager em;
    private Logger logger = Logger.getLogger("log");

    protected EntityManager getEntityManager() {
        return em;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarComprasDetalle(ComprasDetalle detalleCompra) throws EJBWithRollBackException {
        logger.info("IN:" + detalleCompra);
        try {
            em.persist(detalleCompra);
        } catch (Exception ex) {
            logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        logger.info("OUT:OK");
    }
}
