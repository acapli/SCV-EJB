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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import py.smtr.ejb.entities.Cajas;
import py.smtr.ejb.entities.CajasUsuarios;
import py.smtr.ejb.entities.Usuarios;
import py.smtr.ejb.exceptions.EJBWithOutRollBackException;
import py.smtr.ejb.exceptions.EJBWithRollBackException;
import py.smtr.ejb.utilities.ConstantesEJB;
//import org.apache.log4j.Logger;
//import py.fpuna.web.tp2011.ejb.entity.Cajas;
//import py.fpuna.web.tp2011.ejb.entity.CajasUsuarios;
//import py.fpuna.web.tp2011.ejb.entity.Usuarios;
//import py.fpuna.web.tp2011.ejb.exceptions.EJBWithOutRollBackException;
//import py.fpuna.web.tp2011.ejb.exceptions.EJBWithRollBackException;
//import py.fpuna.web.tp2011.ejb.utilities.ConstantesEJB;


@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CajasUsuariosEAO {

    @PersistenceContext(unitName = "TPWeb2011-ejbPU")
    private EntityManager em;
    private Logger logger = Logger.getLogger("log");

    protected EntityManager getEntityManager() {
        return em;
    }

        public CajasUsuarios obtenerCajasUsuarioByUsuario(Usuarios usuario) throws EJBWithOutRollBackException {
        logger.info("IN obtenerCajasUsuarioByUsuario EAO:"+";"+usuario);
        CajasUsuarios cajausuario = null;
        try {
            cajausuario = (CajasUsuarios) em.createNamedQuery("CajasUsuarios.findByIdUsuario").setParameter("idUsuario", usuario).getSingleResult();
        } catch (NoResultException ex) {
            logger.info("OUT:" + "El usuario "+usuario.getNombre()+" no cuenta con cajas asignadas.");
            throw new EJBWithOutRollBackException("El usuario "+usuario.getNombre()+" no cuenta con cajas asignadas.");
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
        logger.info("OUT:"+cajausuario);
        return cajausuario;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarCajasUsuario(CajasUsuarios cajaUsuario) throws EJBWithRollBackException {
        logger.info("IN:" + cajaUsuario);
        try {
            em.persist(cajaUsuario);
        } catch (Exception ex) {
            logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        logger.info("OUT:OK");
    }
    
        public CajasUsuarios obtenerCajasUsuarioByCaja(Cajas caja) throws EJBWithOutRollBackException {
        logger.info("IN:"+";"+caja);
        CajasUsuarios cajausuario = null;
        try {
            cajausuario = (CajasUsuarios) em.createNamedQuery("CajasUsuarios.findByIdCaja").setParameter("idCaja", caja).getSingleResult();
        } catch (NoResultException ex) {
            logger.info("OUT:" + "La caja "+caja.getNombre()+" no cuenta con usuarios asignados.");
            throw new EJBWithOutRollBackException("La caja "+caja.getNombre()+" no cuenta con usuarios asignados.");
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
        logger.info("OUT:"+cajausuario);
        return cajausuario;
    }
}
