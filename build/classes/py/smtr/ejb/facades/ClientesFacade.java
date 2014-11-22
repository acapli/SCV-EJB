/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.facades;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import py.smtr.ejb.eao.ClientesEAO;
import py.smtr.ejb.eao.UsuarioEAO;
import py.smtr.ejb.entities.Clientes;
import py.smtr.ejb.exceptions.EJBWithOutRollBackException;
import py.smtr.ejb.exceptions.EJBWithRollBackException;
import py.smtr.ejb.eao.ClientesEAO;
import py.smtr.ejb.shared.ResponseEntidad;
import py.smtr.ejb.utilities.ConstantesEJB;



/**
 *
 * @author Strogg
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ClientesFacade{

    private Logger logger = Logger.getLogger("log");
    @EJB
    private UsuarioEAO usuarioEAO;
    @EJB
    private ClientesEAO clientesEAO;
    @EJB
    private ClientesFacade clienteFacade;

   
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarCliente(String sesion, String nombre, String ruc, String direccion, String telefono, String email, Boolean activo) throws EJBWithRollBackException {
        logger.info("IN:" + sesion + ";" + nombre + ";" + ruc + ";" + direccion + ";" + telefono + ";" + email + ";" + activo);
        
//        try {
//            usuarioEAO.getUsuarioBySesion(sesion);
//        } catch (EJBWithOutRollBackException ex) {
//            logger.info("OUT:" + ex.getMessage());
//            throw new EJBWithRollBackException(ex.getMessage());
//        }
        
        Clientes aux = null;
        try {
            aux = clientesEAO.getClientesByRUC(ruc);
        } catch (EJBWithOutRollBackException ex) { }
        if (aux != null) {
            logger.info("OUT:" + ConstantesEJB.ENTITY_EXIST);
            throw new EJBWithRollBackException(ConstantesEJB.ENTITY_EXIST);
        }
        Clientes nuevoCliente = new Clientes();
        nuevoCliente.setNombre(nombre);
        nuevoCliente.setRuc(ruc);
        nuevoCliente.setDireccion(direccion);
        nuevoCliente.setTelefono(telefono);
        nuevoCliente.setMail(email);
        nuevoCliente.setActivo(activo);
        nuevoCliente.setCreated(new Date(System.currentTimeMillis()));
        clientesEAO.guardarCliente(nuevoCliente);
        
        logger.info("OUT:OK");
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void borrarListClientes(String sesion, List<Integer> listaIds) throws EJBWithRollBackException {
        logger.info("IN:" + sesion + ";" + listaIds.toString());
        
//        try {
//            usuarioEAO.getUsuarioBySesion(sesion);
//        } catch (EJBWithOutRollBackException ex) {
//            logger.info("OUT:" + ex.getMessage());
//            throw new EJBWithRollBackException(ex.getMessage());
//        }
        for (Integer id : listaIds) {
            clienteFacade.borrarCliente(id);
        }
        logger.info("OUT:OK");
    }
    
     
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void borrarCliente(Integer id) throws EJBWithRollBackException {
        logger.info("IN:" + id);
        
        Clientes cliente = null;
        try {
            cliente = clientesEAO.getClienteById(id);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
       
        clientesEAO.borrarCliente(cliente);
        
        logger.info("OUT:OK");
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ResponseEntidad listarClientes(String sesion, Integer offset, Integer limit) throws EJBWithOutRollBackException {
        logger.info("IN:" + sesion + ";" + offset + ";" + limit);

        //usuarioEAO.getUsuarioBySesion(sesion);

        ResponseEntidad<Clientes> resp = new ResponseEntidad<Clientes>();
        Integer cantidad = 0;
        List<Clientes> clientes = null;

        cantidad = clientesEAO.getCantidadClientes();
        clientes = clientesEAO.obtenerTodosClientes(offset, limit);

        resp.setCantidadTotal(cantidad);
        resp.setListaEntidad(clientes);
        
        logger.info("OUT:" + cantidad + ";" + clientes.toString());
        return resp;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void actualizarCliente(String sesion, Integer id, String nombre, String ruc, String direccion, String telefono, String email, Boolean activo) throws EJBWithRollBackException {
        logger.info("IN:" + sesion + ";" + id + ";" + nombre + ";" + ruc + ";" + direccion + ";" + telefono + ";" + email + ";" + activo);
        
        try {
            usuarioEAO.getUsuarioBySesion(sesion);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
        
        Clientes aux = null;
        try {
            aux = clientesEAO.getClientesByRUC(ruc);
        } catch (EJBWithOutRollBackException ex) {}
        if (aux != null && aux.getId() != id) {
            logger.info("OUT:" + ConstantesEJB.CLIENT_MISMO_NOMBRE);
            throw new EJBWithRollBackException(ConstantesEJB.CLIENT_MISMO_NOMBRE);
        }
        
        Clientes cliente = null;
        try {
            cliente = clientesEAO.getClienteById(id);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
        
        cliente.setNombre(nombre);
        cliente.setRuc(ruc);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setMail(email);
        cliente.setActivo(activo);
        cliente.setChanged(new Date(System.currentTimeMillis()));
        clientesEAO.guardarCliente(cliente);
        
        logger.info("OUT:OK");
    }
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Clientes obtenerClienteByRUC(String sesion, String ruc) throws EJBWithOutRollBackException {
        logger.info("IN:" + sesion + ";" + ruc);
        usuarioEAO.getUsuarioBySesion(sesion);
        return clientesEAO.getClientesByRUC(ruc);
    }
  

}
