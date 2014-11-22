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
import py.smtr.ejb.entities.Ventas;
import py.smtr.ejb.utilities.ConstantesEJB;


/**
 *
 * @author Strogg
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class VentasEAO{
    @PersistenceContext(unitName = "TPWeb2011-ejbPU")
    private EntityManager em;
     private Logger logger = Logger.getLogger("log");

    protected EntityManager getEntityManager() {
        return em;
    }
    
        public int getCantidadVentas() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Ventas> rt = cq.from(Ventas.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarVentas(Ventas venta) throws Exception {
        logger.info("IN:" + venta);
        try {
            em.persist(venta);
        } catch (Exception ex) {
            logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new Exception(ConstantesEJB.ERROR_INESPERADO);
        }
        logger.info("OUT:OK");
    }
}
