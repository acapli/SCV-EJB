/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.eao;

//import static com.sun.xml.ws.security.impl.policy.Constants.logger;
import java.util.ArrayList;
import java.util.List;
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
import py.smtr.ejb.entities.Roles;
import py.smtr.ejb.entities.RolesUsuarios;
import py.smtr.ejb.entities.Usuarios;
import py.smtr.ejb.exceptions.EJBWithOutRollBackException;
import py.smtr.ejb.exceptions.EJBWithRollBackException;
import py.smtr.ejb.utilities.ConstantesEJB;


/**
 *
 * @author 
 */
@Stateless
public class RolesUsuariosEAO extends AbstractEAO<RolesUsuarios> {
    @PersistenceContext(unitName = "TPWeb2011-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolesUsuariosEAO() {
        super(RolesUsuarios.class);
    }

    //@Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<RolesUsuarios> obtenerRolesUsuarioByUsuario(Usuarios usuario) throws EJBWithOutRollBackException {
        //logger.info("IN:"+usuario);
        List<RolesUsuarios> listaRolesUsuario = new ArrayList<RolesUsuarios>();
        try {
            listaRolesUsuario = (List<RolesUsuarios>) em.createNamedQuery("RolesUsuarios.findByIdUsuario").setParameter("idUsuario", usuario).getResultList();
        } catch (NoResultException ex) {
        //    logger.info("OUT:" + "El usuario "+usuario.getLogin()+" no posee roles asociados.");
            throw new EJBWithOutRollBackException("El usuario "+usuario.getLogin()+" no posee roles asociados.");
        } catch (QueryTimeoutException ex) {
        //    logger.info("OUT:" + "Se ha perdido la conexión a la base de datos.");
            throw new EJBWithOutRollBackException("Se ha perdido la conexión a la base de datos.");
        } catch (TransactionRequiredException ex) {
        //    logger.info("OUT:" + "Se requiere de una transacción para esta operación.");
            throw new EJBWithOutRollBackException("Se requiere de una transacción para esta operación.");
        } catch (Exception ex) {
        //    logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithOutRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        //logger.info("OUT:"+listaRolesUsuario);
        return listaRolesUsuario;
    }
    
//    @Override
    public RolesUsuarios getRolesUsuariosByUsuarioRol(Usuarios usuario, Roles rol) throws EJBWithOutRollBackException {
        //logger.info("IN:" + usuario+";"+rol);
        RolesUsuarios rolesUsuarios = null;
        try {
            rolesUsuarios = (RolesUsuarios) em.createNamedQuery("RolesUsuarios.findByIdUsuarioIdRol").setParameter("idUsuario", usuario).setParameter("idRol", rol).getSingleResult();
        } catch (QueryTimeoutException ex) {
        //    logger.info("OUT:" + "Se ha perdido la conexión a la base de datos.");
            throw new EJBWithOutRollBackException("Se ha perdido la conexión a la base de datos.");
        } catch (TransactionRequiredException ex) {
        //    logger.info("OUT:" + "Se requiere de una transacción para esta operación.");
            throw new EJBWithOutRollBackException("Se requiere de una transacción para esta operación.");
        } catch (Exception ex) {
        //    logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithOutRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        //logger.info("OUT:" + rolesUsuarios);
        return rolesUsuarios;
    }
    
     // @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void borrarRolesUsuarios(RolesUsuarios rolesUsuarios) throws EJBWithRollBackException {
        //logger.info("IN:" + rolesUsuarios);
        try {
            em.remove(rolesUsuarios);
        } catch (Exception ex) {
        //    logger.info("OUT:" + "No se pudo eliminar la relacion entre " + rolesUsuarios.getIdUsuario().getNombre() + " y " + rolesUsuarios.getIdRol().getNombre());
            throw new EJBWithRollBackException("No se pudo eliminar la relacion entre " + rolesUsuarios.getIdUsuario().getNombre() + " y " + rolesUsuarios.getIdRol().getNombre());
        }
        //logger.info("OUT:OK");
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarRolesUsuarios(RolesUsuarios rolesUsuarios) throws EJBWithRollBackException {
  //      logger.info("IN - guardarRolesUsuarios:" + rolesUsuarios);
        System.out.println("-------------Accedio a guardarRolesUsuarios-------------");
        try {
            em.persist(rolesUsuarios);
        } catch (Exception ex) {
        //    logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        //logger.info("OUT:OK");
    }
    
}