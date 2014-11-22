/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.smtr.ejb.eao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import py.smtr.ejb.entities.Roles;
import py.smtr.ejb.exceptions.EJBWithOutRollBackException;
import py.smtr.ejb.utilities.ConstantesEJB;


/**
 *
 * @author 
 */
@Stateless
public class RolEAO extends AbstractEAO<Roles> {
    @PersistenceContext(unitName = "TPWeb2011-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolEAO() {
        super(Roles.class);
    }
    
    
     //  private Logger logger = Logger.getLogger(this.getClass());

  
    
    //@Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Roles getRolesById(Integer id) throws EJBWithOutRollBackException {
        //logger.info("IN:" + id);
        Roles rol = null;
        try {
            rol = (Roles) em.createNamedQuery("Roles.findById").setParameter("id", id).getSingleResult();
        } catch (NoResultException ex) {
            //logger.info("OUT:" + "No existe el rol con id: " + id);
            throw new EJBWithOutRollBackException("No existe el rol con id: " + id);
        } catch (QueryTimeoutException ex) {
            //logger.info("OUT:" + "Se ha perdido la conexión a la base de datos.");
            throw new EJBWithOutRollBackException("Se ha perdido la conexión a la base de datos.");
        } catch (TransactionRequiredException ex) {
            //logger.info("OUT:" + "Se requiere de una transacción para esta operación.");
            throw new EJBWithOutRollBackException("Se requiere de una transacción para esta operación.");
        } catch (Exception ex) {
            //logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithOutRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        //logger.info("OUT:" + rol);
        return rol;
    }
    
    //@Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int getCantidadRoles() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Roles> rt = cq.from(Roles.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    //@Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Roles> getAllRoles(Integer offset, Integer limit) throws EJBWithOutRollBackException {
        List<Roles> clientes = null;
        try {
            Query query = em.createNamedQuery("Roles.findAll");
            if (offset != null) {
                query.setFirstResult(offset);
            }
            if (limit != null) {
                query.setMaxResults(limit);
            }
            clientes = (List<Roles>) query.getResultList();
        } catch (QueryTimeoutException ex) {
           // logger.info("OUT:" + "Se ha perdido la conexión a la base de datos.");
            throw new EJBWithOutRollBackException("Se ha perdido la conexión a la base de datos.");
        } catch (TransactionRequiredException ex) {
            //logger.info("OUT:" + "Se requiere de una transacción para esta operación.");
            throw new EJBWithOutRollBackException("Se requiere de una transacción para esta operación.");
        } catch (Exception ex) {
            //logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithOutRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        return clientes;
    }
    
    
    
}
