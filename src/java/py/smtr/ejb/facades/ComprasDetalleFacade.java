/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.facades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import py.smtr.ejb.eao.ComprasDetalleEAO;
import py.smtr.ejb.eao.ProductosEAO;
import py.smtr.ejb.eao.UsuarioEAO;
import py.smtr.ejb.entities.Compras;
import py.smtr.ejb.entities.ComprasDetalle;
import py.smtr.ejb.entities.Productos;
import py.smtr.ejb.exceptions.EJBWithOutRollBackException;
import py.smtr.ejb.exceptions.EJBWithRollBackException;
import py.smtr.ejb.shared.DetalleCV;
//import org.apache.log4j.Logger;
//import py.fpuna.web.tp2011.ejb.entity.Compras;
//import py.fpuna.web.tp2011.ejb.entity.ComprasDetalle;
//import py.fpuna.web.tp2011.ejb.entity.Productos;
//import py.fpuna.web.tp2011.ejb.exceptions.EJBWithOutRollBackException;
//import py.fpuna.web.tp2011.ejb.exceptions.EJBWithRollBackException;
//import py.fpuna.web.tp2011.ejb.facades.eao.ComprasDetalleEAOLocal;
//import py.fpuna.web.tp2011.ejb.facades.eao.ProductosEAOLocal;
//import py.fpuna.web.tp2011.ejb.facades.eao.UsuarioEAOLocal;
//import py.fpuna.web.tp2011.ejb.shared.DetalleCV;

/**
 *
 * @author Strogg
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ComprasDetalleFacade {
    private Logger logger = Logger.getLogger("log");
    @EJB
    private ProductosEAO productosEAO;
    @EJB
    private ProductosFacade productosFacade;
    @EJB
    private ComprasDetalleFacade comprasDetalleFacade;
    @EJB
    private ComprasDetalleEAO comprasDetalleEAO;
    @EJB
    private UsuarioEAO usuarioEAO;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<ComprasDetalle> guardarDetalles(String sesion, Compras compra, List<DetalleCV> detalles) throws EJBWithRollBackException {
        logger.info("IN:"+sesion+";"+compra);

        try {
            usuarioEAO.getUsuarioBySesion(sesion);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
        
        List<ComprasDetalle> detallesCompra = new ArrayList<ComprasDetalle>();
        for (DetalleCV detalleCV : detalles) {
            detallesCompra.add(comprasDetalleFacade.guardarDetalle(sesion, compra, detalleCV));
        }
        logger.info("OUT:OK");
        return detallesCompra;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ComprasDetalle guardarDetalle(String sesion, Compras compra, DetalleCV detalleCV) throws EJBWithRollBackException {
        logger.info("IN:"+sesion+";"+detalleCV);
        
        try {
            usuarioEAO.getUsuarioBySesion(sesion);
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
        
        Productos producto = null;
        try {
            producto = productosEAO.getProductoById(detalleCV.getId());
        } catch (EJBWithOutRollBackException ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
        productosFacade.actualizarCosto(sesion, producto.getId(), detalleCV.getCantidad(), detalleCV.getCosto());
        productosFacade.actualizarStock(sesion, producto.getId(), detalleCV.getCantidad());
        
        ComprasDetalle compraDetalle = new ComprasDetalle();
        compraDetalle.setIdProducto(producto);
        compraDetalle.setCosto(detalleCV.getCosto());
        compraDetalle.setCantidad(detalleCV.getCantidad());
        compraDetalle.setCreated(new Date(System.currentTimeMillis()));
        compraDetalle.setIdCompra(compra);
        comprasDetalleEAO.guardarComprasDetalle(compraDetalle);
        
        logger.info("OUT:OK");
        return compraDetalle;
    }
}
