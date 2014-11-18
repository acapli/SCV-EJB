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
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import py.smtr.ejb.entities.Roles;
import py.smtr.ejb.entities.Usuarios;
import py.smtr.ejb.exceptions.EJBWithOutRollBackException;
import py.smtr.ejb.exceptions.EJBWithRollBackException;
import py.smtr.ejb.utilities.ConstantesEJB;

@Stateless
//@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioEAO extends AbstractEAO<Usuarios> {

    @PersistenceContext(unitName = "TPWeb2011-ejbPU")
    private EntityManager em;
    private Logger logger = Logger.getLogger("log");

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioEAO() {
        super(Usuarios.class);
    }

    public Usuarios getUsuarioByUsername(String username)throws Exception {
        //logger.info("IN:" + username);
        Usuarios usuario = null;
        try {
            usuario = (Usuarios) em.createNamedQuery("Usuario.findByUsuario").setParameter("usuario", username).getSingleResult();
        } catch (Exception ex) {
            
        }
        return usuario;
    }

    public boolean usuarioTieneSesion(String sesion)throws Exception{ 
        //logger.info("IN:" + username);
        Usuarios usuario = null;
        try {
            usuario = (Usuarios) em.createNamedQuery("Usuario.findBySesion").setParameter("sesion", sesion).getSingleResult();
            return usuario != null;
        } catch (Exception ex) {
        }
        return false;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarUsuario(Usuarios usuario) throws Exception{
            em.persist(usuario);
    }
   
    
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    
    public Usuarios getUsuarioBySesion(String sesion) throws EJBWithOutRollBackException {
        Usuarios usuario = null;
        try {
            usuario = (Usuarios) em.createNamedQuery("Usuarios.findBySesion").setParameter("sesion", sesion).getSingleResult();
        } catch (NoResultException ex) {
            throw new EJBWithOutRollBackException("Error");
        } 
        return usuario;
    }
    
    
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Usuarios getUsuarioById(Integer idUsuario) throws EJBWithOutRollBackException {
        Usuarios usuario = null;
        try {
            usuario = (Usuarios) em.createNamedQuery("Usuarios.findById").setParameter("id", idUsuario).getSingleResult();
        } catch (Exception ex) {
            throw new EJBWithOutRollBackException("Error");
        }
        return usuario;
    }
    
    //  @Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int getCantidadUsuarios() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Usuarios> rt = cq.from(Usuarios.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
     //@Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Usuarios> obtenerTodosUsuarios(Integer offset, Integer limit) throws EJBWithOutRollBackException {
        
        List<Usuarios> usuarios = null;
        try {
            Query query = em.createNamedQuery("Usuarios.findAll");
            if (offset != null) {
                query.setFirstResult(offset);
            }
            if (limit != null) {
                query.setMaxResults(limit);
            }
            usuarios = (List<Usuarios>) query.getResultList();
        } catch (QueryTimeoutException ex) {
            throw new EJBWithOutRollBackException("Se ha perdido la conexión a la base de datos.");
        } catch (TransactionRequiredException ex) {
            throw new EJBWithOutRollBackException("Se requiere de una transacción para esta operación.");
        } catch (Exception ex) {
            throw new EJBWithOutRollBackException("ERROR_INESPERADO");
        }    
        return usuarios;
    }

    
    
    
    public Boolean existeUsuario(String usuario) {
        try {
            em.createNamedQuery("Usuarios.findByUsuario").setParameter("usuario", usuario).getSingleResult();
        } catch (NoResultException ex) {
            return false;
        } catch (NonUniqueResultException ex) {
            return false;
        } catch (Exception ex) {
            return true;
        }
        return true;
    }
    
    //  @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void borrarUsuario(Usuarios usuario) throws EJBWithRollBackException {
        try {
            em.remove(usuario);
        } catch (Exception ex) {
                throw new EJBWithRollBackException("No se pudo eliminar el usuario");
        }
    }  
 
   // @Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Usuarios getUsuarioByLogin(String login) throws EJBWithOutRollBackException {
        logger.info("IN:" + login + ";****");
        Usuarios usuario = null;
        
         logger.info("\n\n-----------------Accediendo a LoginUsuarioEAO----------------\n\n");
        try {
            usuario = (Usuarios) em.createNamedQuery("Usuarios.findByLogin").setParameter("login", login).getSingleResult();
        } catch (NoResultException ex) {
            logger.info("OUT:" + ConstantesEJB.USER_PASS_INC);
            throw new EJBWithOutRollBackException(ConstantesEJB.USER_PASS_INC);
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
        logger.info("OUT:" + usuario);
        return usuario;
    }
    
    //@Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean existeSesion(String sesion) {
        //logger.info("IN:" + sesion);
        try {
            em.createNamedQuery("Usuarios.findBySesion").setParameter("sesion", sesion).getSingleResult();
        } catch (NoResultException ex) {
            //logger.info("OUT:" + false);
            return false;
        } catch (Exception ex) {
            //logger.info("OUT:" + false);
            return false;
        }
        //logger.info("OUT:" + true);
        return true;
    }
    
}