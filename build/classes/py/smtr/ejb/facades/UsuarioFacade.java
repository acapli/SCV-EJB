package py.smtr.ejb.facades;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import py.smtr.ejb.eao.RolEAO;
import py.smtr.ejb.eao.RolesUsuariosEAO;
import py.smtr.ejb.exceptions.EJBWithOutRollBackException;
import py.smtr.ejb.eao.UsuarioEAO;
import py.smtr.ejb.entities.Roles;
import py.smtr.ejb.entities.RolesUsuarios;
import py.smtr.ejb.entities.Usuarios;
import py.smtr.ejb.exceptions.EJBWithRollBackException;
import py.smtr.ejb.exceptions.SMTRExceptionEJB;
import py.smtr.ejb.shared.ResponseEntidad;
import py.smtr.ejb.shared.ResponseLogin;
import py.smtr.ejb.utilities.ConstantesEJB;
import py.smtr.ejb.utilities.SHA512B64;
import py.smtr.ejb.utilities.SessionGenerator;

//@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
//@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioFacade {

    @EJB
    private UsuarioEAO usuarioEAO;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private RolesUsuariosEAO rolesUsuarioEAO;
    @EJB
    private RolEAO rolesEAO;
    
    //private Logger logger = Logger.getLogger("");
    
    private Logger logger = Logger.getLogger("log");


   //@Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ResponseLogin login(String user, String pass) throws EJBWithRollBackException, Exception {

       logger.info("IN:" + user + ";****");
       Usuarios usu = null;     
       logger.info("\n\n-----------------Accediendo a LoginUsuarioFACADE----------------\n\n");
        try {
            usu = usuarioEAO.getUsuarioByLogin(user);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
      
        if (usu != null) {
            if (usu.getPassword().equals(SHA512B64.encriptar(pass))) {
                boolean exito = true;
                String sesion = null;
                if (!usu.getActivo()) {
                    logger.info("OUT:" + ConstantesEJB.USER_INACTIVO);
                    throw new EJBWithRollBackException(ConstantesEJB.USER_INACTIVO);
                }
                while (exito) {
                    sesion = SessionGenerator.generarSesion(50);
                    exito = usuarioEAO.existeSesion(sesion);
                }
                usu.setSesion(sesion);
                List<RolesUsuarios> listaRolesUsuario = null;              //los roles del usuario...
                try {
                    listaRolesUsuario = rolesUsuarioEAO.obtenerRolesUsuarioByUsuario(usu);
                    logger.info("Obtuvo lista de Roles!!");
                } catch (EJBWithOutRollBackException ex) {           
                    logger.info("OUT:" + ex.getMessage());
                    throw new EJBWithRollBackException(ex.getMessage());
                }
                 Integer roles[] = {0, 0, 0, 0, 0};
                for (RolesUsuarios rolUsuario : listaRolesUsuario) {
                    roles[rolUsuario.getIdRol().getId()] = rolUsuario.getIdRol().getId();
                    logger.info(" Se cargan los roles!!!" + roles[1]);
                }
                usuarioEAO.guardarUsuario(usu);
                return new ResponseLogin(usu.getNombre(), sesion, roles);              
            } else {
                logger.info("OUT:" + ConstantesEJB.USER_PASS_INC);
                throw new EJBWithRollBackException(ConstantesEJB.USER_PASS_INC);
            }
        } else {
            logger.info("OUT:" + ConstantesEJB.ERROR_INESPERADO);
            throw new EJBWithRollBackException(ConstantesEJB.ERROR_INESPERADO);
        }
        
        
    }

    /**
     * 
     * @param sesion SIEMPRE recibir este valor
     * @param param1
     * @return
     * @throws SMTRExceptionEJB 
     */
    public String cambiarMetodo(String sesion, String param1) throws SMTRExceptionEJB, Exception {
        /* COPIAR EN TODOS LOS METODOS */
        if(!usuarioEAO.usuarioTieneSesion(sesion)){
            throw new SMTRExceptionEJB("Su sesion ha expirado");
        }
        /*******************************/
                //hacer la logica del negocio
        return "hacer la logica del negocio";
    }
    
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarUsuario(String sesion, String username, String nombres, long documento, String email, String password, Boolean activo)throws Exception, EJBWithOutRollBackException{
         try {
        
        logger.info("\n\n-----------------Accedió a UsuarioFacade----------------\n\n");    
        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setNombre(nombres);
        nuevoUsuario.setLogin(username);
        nuevoUsuario.setCi(documento);
        nuevoUsuario.setEmail(email);
        
        String passEncry = null;
        try {
            passEncry = SHA512B64.encriptar(password);
        } catch (Exception ex) {
            logger.info("OUT:" + ConstantesEJB.EROR_ENCRYP);
            throw new EJBWithRollBackException(ConstantesEJB.EROR_ENCRYP);
        }
        nuevoUsuario.setPassword(passEncry);
        nuevoUsuario.setActivo(activo);
        nuevoUsuario.setCreated(new Date(System.currentTimeMillis()));
        usuarioEAO.guardarUsuario(nuevoUsuario);
        logger.info("OUT:OK");
        usuarioEAO.guardarUsuario(nuevoUsuario);       
        } catch (Exception ex) {
            logger.info("\n\n----------------Pasó llamada a UsuarioEAO desde UsuarioFacade----------------\n\n");       
        }
    }
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void logout(String sesion) {
        try {
            Usuarios user = usuarioEAO.getUsuarioBySesion(sesion);
            user.setSesion(null);
            usuarioEAO.guardarUsuario(user);
        } catch (Exception ex) {
        }
        
    }
    
   // @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Integer> getListaRoles(String sesion, Integer idUsuario) throws EJBWithOutRollBackException {
        

        usuarioEAO.getUsuarioBySesion(sesion);
        //controlar que exista el usuario
        Usuarios usuario = usuarioEAO.getUsuarioById(idUsuario);
        List<Integer> myRol = new ArrayList<Integer>();
        
       /* if(usuario.getRol().getNombre().equals("Administrador")){
            myRol.add(1);
        }else if(usuario.getRol().getNombre().equals("Operador")){
            myRol.add(2);
        }else{
            myRol.add(0);
        }
        
        */
            return myRol;
        
        
    }
    
    //    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ResponseEntidad listarUsuarios(String sesion, Integer offset, Integer limit) throws EJBWithOutRollBackException {
        logger.info("IN:" + sesion + ";" + offset + ";" + limit);

        usuarioEAO.getUsuarioBySesion(sesion);
        ResponseEntidad<Usuarios> resp = new ResponseEntidad<Usuarios>();
        Integer cantidad = 0;
        List<Usuarios> usuarios = null;
        cantidad = usuarioEAO.getCantidadUsuarios();
        usuarios = usuarioEAO.obtenerTodosUsuarios(offset, limit);
        resp.setCantidadTotal(cantidad);
        resp.setListaEntidad(usuarios);
        logger.info("OUT:" + cantidad + ";" + usuarios.toString());
        return resp;
    }
    
    
    
    //@Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void actualizarUsuario(String sesion, Integer id, String nombre, long documento, String email, Boolean activo) throws Exception {       
        logger.info("IN:" + sesion + ";" + id + ";" + nombre + ";" + documento + ";" + email + ";" + activo);
        Usuarios usu = null;
        try {
            usuarioEAO.getUsuarioBySesion(sesion);
            usu = usuarioEAO.getUsuarioById(id);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
        usu.setNombre(nombre);
        usu.setCi(documento);
        usu.setEmail(email);
        usu.setActivo(activo);
        usu.setChanged(new Date(System.currentTimeMillis()));
        usuarioEAO.guardarUsuario(usu);
        logger.info("OUT:OK");
    }
    
    
    
    //@Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void actualizarRolesUsuario(String sesion, Integer idUsuario, Boolean roles[]) throws EJBWithRollBackException {
        
        logger.info("----Accedió actualizarRolesUsuario UsuarioFacade----");
        
        try {
            usuarioEAO.getUsuarioBySesion(sesion);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }

        //bue aqui viene el alambre...
        Integer valoresRoles[] = {1, 2 , 3, 4};
        for (int i = 0; i < roles.length; i++) {
            //nombreRoles[] = {"Administrador", "Comprador", "Vendedor", "Cajero"};
            try {
                if (roles[i]) {
                    //asignar
                    logger.info("----Accedió if (roles[i]) de UsuarioFacade----");
                    usuarioFacade.asignarRolUsuario(sesion, idUsuario, valoresRoles[i]);
                } else {
                    //desasignar
                    logger.info("----Accedió else de UsuarioFacade----");
                    usuarioFacade.eliminarRolUsuario(sesion, idUsuario, valoresRoles[i]);
                }
            } catch (Exception ex) {
                throw new EJBWithRollBackException(ConstantesEJB.ERROR_INESPERADO);
            }
        }
                
    }
    
    //@Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void eliminarRolUsuario(String sesion, Integer idUsuario, Integer idRol) throws EJBWithRollBackException {
        logger.info("IN:" + sesion);
        Usuarios usuario = null;
        Roles rol = null;
        try {
            usuarioEAO.getUsuarioBySesion(sesion);
            usuario = usuarioEAO.getUsuarioById(idUsuario);
            rol = rolesEAO.getRolesById(idRol);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }

        //existe el usuario y existe el rol....buscamos si ya tiene asignado ese rol, si ya tiene...lanzamos una excepcion!        
        RolesUsuarios rolUsuario = null;
        try {
            rolUsuario = rolesUsuarioEAO.getRolesUsuariosByUsuarioRol(usuario, rol);
        } catch (Exception e) {
        }
        if (rolUsuario != null) {

            rolesUsuarioEAO.borrarRolesUsuarios(rolUsuario);
        }
        logger.info("OUT:OK");
    }

    
     // @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void asignarRolUsuario(String sesion, Integer idUsuario, Integer idRol) throws EJBWithRollBackException {
        logger.info("IN:" + sesion);
        logger.info("---Accesdio a asignarRolUsuario de UsuarioFacade ");
        
        
        Usuarios usuario = null;
        Roles rol = null;
        try {
            usuarioEAO.getUsuarioBySesion(sesion);
            usuario = usuarioEAO.getUsuarioById(idUsuario);
            rol = rolesEAO.getRolesById(idRol);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }

        //existe el usuario y existe el rol....buscamos si ya tiene asignado ese rol, si ya tiene...lanzamos una excepcion!        
        RolesUsuarios rolUsuario = null;
        try {
            rolUsuario = rolesUsuarioEAO.getRolesUsuariosByUsuarioRol(usuario, rol);
        } catch (Exception e) {
            //tiene asignado el rol           
        }

        if (rolUsuario == null) {
            
            logger.info("---Accedio a if (rolUsuario == null) de UsuarioFacade---------");
            //no tiene asignado ese rol
            //entonces asignar
            rolUsuario = new RolesUsuarios();
            
             logger.info("---Paso new RolesUsuarios();---------");
            rolUsuario.setIdRol(rol);
             logger.info("---Paso setIdRol(rol)---------");
            rolUsuario.setIdUsuario(usuario);
             logger.info("---Paso setIdUsuario(usuario)---------");
            rolUsuario.setActivo(true);

           
            
            
            logger.info("---" +rolUsuario.getIdRol().getNombre() +"---------"+rolUsuario.getIdUsuario().getNombre() +"---------");
            
            rolesUsuarioEAO.guardarRolesUsuarios(rolUsuario);
        }
        logger.info("OUT:OK");
    }

    
    
   //  @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void borrarListUsuarios(String sesion, List<Integer> listaIds) throws EJBWithRollBackException {
        logger.info("Usuario Facade: " + sesion + ";" + listaIds.toString());
       /*
        try {
            usuarioEAO.getUsuarioBySesion(sesion);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
      */
        for (Integer id : listaIds) {
            usuarioFacade.borrarUsuario(id);
        }
        logger.info("OUT:OK");
    }
    
    
    
     // @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void borrarUsuario(Integer id) throws EJBWithRollBackException {
        logger.info("IN:" + id);
        Usuarios usu = null;
        try {
            usu = usuarioEAO.getUsuarioById(id);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
        usuarioEAO.borrarUsuario(usu);
        logger.info("OUT:OK");
    }      
}