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
import py.smtr.ejb.eao.ComprasEAO;
import py.smtr.ejb.eao.ProveedoresEAO;
import py.smtr.ejb.eao.UsuarioEAO;
import py.smtr.ejb.entities.Compras;
import py.smtr.ejb.entities.Proveedores;
import py.smtr.ejb.exceptions.EJBWithOutRollBackException;
import py.smtr.ejb.exceptions.EJBWithRollBackException;
import py.smtr.ejb.shared.DetalleCV;
//import org.apache.log4j.Logger;
//import py.fpuna.web.tp2011.ejb.entity.Compras;
//import py.fpuna.web.tp2011.ejb.entity.Proveedores;
//import py.fpuna.web.tp2011.ejb.exceptions.EJBWithOutRollBackException;
//import py.fpuna.web.tp2011.ejb.exceptions.EJBWithRollBackException;
//import py.fpuna.web.tp2011.ejb.facades.eao.ComprasEAOLocal;
//import py.fpuna.web.tp2011.ejb.facades.eao.ProveedoresEAOLocal;
//import py.fpuna.web.tp2011.ejb.facades.eao.UsuarioEAOLocal;
//import py.fpuna.web.tp2011.ejb.shared.DetalleCV;


@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ComprasFacade{

    private Logger logger = Logger.getLogger("log");
    @EJB
    private ProveedoresEAO proveedorEAO;
    @EJB
    private ComprasDetalleFacade comprasDetalleFacade;
    @EJB
    private UsuarioEAO usuarioEAO;
    @EJB
    private ComprasEAO comprasEAO;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarCompra(String sesion, Integer idProveedor, Date fecha, double total, List<DetalleCV> detalles) throws EJBWithRollBackException {
        logger.info("IN:"+sesion+";"+idProveedor+";"+fecha.toString()+";"+total+";"+detalles.toString());
        Proveedores proveedor = null;
        try {
            usuarioEAO.getUsuarioBySesion(sesion);
            proveedor = proveedorEAO.getProveedorById(idProveedor);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
        
        Compras compra = new Compras();
        compra.setIdProveedor(proveedor);
        compra.setFechaCompra(fecha);
        compra.setTotal(total);
        compra.setCreated(new Date(System.currentTimeMillis()));
        comprasEAO.guardarCompras(compra);
        
        comprasDetalleFacade.guardarDetalles(sesion, compra, detalles);

        logger.info("OUT:OK");
    }
    
}
