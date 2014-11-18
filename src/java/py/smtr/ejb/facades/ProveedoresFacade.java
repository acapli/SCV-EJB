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

import py.smtr.ejb.entities.Proveedores;
import py.smtr.ejb.exceptions.EJBWithOutRollBackException;
import py.smtr.ejb.exceptions.EJBWithRollBackException;
import py.smtr.ejb.eao.ProveedoresEAO;
import py.smtr.ejb.eao.UsuarioEAO;
import py.smtr.ejb.shared.ResponseEntidad;
import py.smtr.ejb.utilities.ConstantesEJB;



@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProveedoresFacade {

    private Logger logger = Logger.getLogger("log");
    @EJB
    private ProveedoresFacade proveedorFacade;
    @EJB
    private UsuarioEAO usuarioEAO;
    @EJB
    private ProveedoresEAO proveedorEAO;

    //@Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ResponseEntidad listarProveedores(String sesion, Integer offset, Integer limit) throws EJBWithOutRollBackException {
        logger.info("IN:" + sesion + ";" + offset + ";" + limit);

        //usuarioEAO.getUsuarioBySesion(sesion);

        ResponseEntidad<Proveedores> resp = new ResponseEntidad<Proveedores>();
        Integer cantidad = 0;
        List<Proveedores> proveedores = null;

        cantidad = proveedorEAO.getCantidadProveedores();
        proveedores = proveedorEAO.getAllProveedores(offset, limit);

        resp.setCantidadTotal(cantidad);
        resp.setListaEntidad(proveedores);
        logger.info("OUT:" + cantidad + ";" + proveedores.toString());
        return resp;
    }

    //@Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarProveedor(String sesion, String nombre, String ruc, String direccion, String telefono, String email, Boolean activo) throws EJBWithRollBackException {
        logger.info("IN:" + sesion + ";" + nombre + ";" + ruc + ";" + direccion + ";" + telefono + ";" + email + ";" + activo);
        
//        try {
//            usuarioEAO.getUsuarioBySesion(sesion);
//        } catch (EJBWithOutRollBackException ex) {
//            logger.info("OUT:" + ex.getMessage());
//            throw new EJBWithRollBackException(ex.getMessage());
//        }
        Proveedores aux = null;
        try {
            aux = proveedorEAO.getProveedoresByRUC(ruc);
        } catch (EJBWithOutRollBackException ex) {}
        if (aux != null) {
            logger.info("OUT:" + ConstantesEJB.ENTITY_EXIST);
            throw new EJBWithRollBackException(ConstantesEJB.ENTITY_EXIST);
        }
        Proveedores nuevoProveedor = new Proveedores();
        nuevoProveedor.setNombre(nombre);
        nuevoProveedor.setRuc(ruc);
        nuevoProveedor.setDireccion(direccion);
        nuevoProveedor.setTelefono(telefono);
        nuevoProveedor.setEmail(email);
        nuevoProveedor.setActivo(activo);
        nuevoProveedor.setCreated(new Date(System.currentTimeMillis()));
        proveedorEAO.guardarProveedor(nuevoProveedor);

        logger.info("OUT:OK");
    }

    //@Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void borrarProveedor(Integer id) throws Exception {
        logger.info("IN entro en borrarProveedor:" + id);
        
        Proveedores proveedor = null;
        try {
            proveedor = proveedorEAO.getProveedorById(id);
        } catch (Exception ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        proveedorEAO.borrarProveedor(proveedor);
        
        logger.info("OUT:OK");
    }

    //@Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void borrarListProveedores(String sesion, List<Integer> listaIds) throws  Exception {
        logger.info("IN Entro en el Facade:" + sesion + ";" + listaIds.toString());
        
//        try {
//            usuarioEAO.getUsuarioBySesion(sesion);
//        } catch (EJBWithOutRollBackException ex) {
//            logger.info("OUT:" + ex.getMessage());
//            throw new EJBWithRollBackException(ex.getMessage());
//        }

        for (Integer id : listaIds) {
            proveedorFacade.borrarProveedor(id);
        }
        logger.info("OUT:OK");
    }

    //@Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void actualizarProveedor(String sesion, Integer id, String nombre, String ruc, String direccion, String telefono, String email, Boolean activo) throws EJBWithRollBackException {
        logger.info("IN:" + sesion + ";" + id + ";" + nombre + ";" + ruc + ";" + direccion + ";" + telefono + ";" + email + ";" + activo);
        
        try {
            usuarioEAO.getUsuarioBySesion(sesion);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
        
        Proveedores aux = null;
        try {
            aux = proveedorEAO.getProveedoresByRUC(ruc);
        } catch (EJBWithOutRollBackException ex) {}
        if (aux != null && aux.getId() != id) {
            logger.info("OUT:" + ConstantesEJB.PROVEEDOR_MISMO_RUC);
            throw new EJBWithRollBackException(ConstantesEJB.PROVEEDOR_MISMO_RUC);
        }
        Proveedores proveedor = null;
        try {
            proveedor = proveedorEAO.getProveedorById(id);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
        proveedor.setNombre(nombre);
        proveedor.setRuc(ruc);
        proveedor.setDireccion(direccion);
        proveedor.setTelefono(telefono);
        proveedor.setEmail(email);
        proveedor.setActivo(activo);
        proveedor.setChanged(new Date(System.currentTimeMillis()));
        proveedorEAO.guardarProveedor(proveedor);
        
        logger.info("OUT:OK");
    }

    //@Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Proveedores obtenerProveedorByRuc(String sesion, String ruc) throws EJBWithOutRollBackException {
        logger.info("IN:" + sesion + ";" + ruc);
        
        usuarioEAO.getUsuarioBySesion(sesion);
        
        Proveedores proveedor = proveedorEAO.getProveedoresByRUC(ruc);
        logger.info("OUT:" + proveedor);
        
        return proveedor;
    }
}
