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
import py.smtr.ejb.eao.ProductosEAO;
import py.smtr.ejb.eao.UsuarioEAO;
import py.smtr.ejb.eao.VentasDetalleEAO;
import py.smtr.ejb.entities.Productos;
import py.smtr.ejb.entities.Ventas;
import py.smtr.ejb.entities.VentasDetalle;
import py.smtr.ejb.exceptions.EJBWithRollBackException;
import py.smtr.ejb.shared.DetalleCV;


/**
 *
 * @author Strogg
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class VentasDetalleFacade {

    private Logger logger = Logger.getLogger("log");
    @EJB
    private UsuarioEAO usuarioEAO;
    @EJB
    private ProductosEAO productosEAO;
    @EJB
    private ProductosFacade productosFacade;
    @EJB
    private VentasDetalleFacade ventasDetalleFacade;
    @EJB
    private VentasDetalleEAO ventasDetalleEAO;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<VentasDetalle> guardarDetalles(String sesion, Ventas venta, List<DetalleCV> detalles) throws Exception {
        logger.info("IN:"+sesion+";"+venta);
        
//        try {
//            usuarioEAO.getUsuarioBySesion(sesion);
//        } catch (Exception ex) {
//            logger.info("OUT:" + ex.getMessage());
//            throw new EJBWithRollBackException(ex.getMessage());
//        }
        
        List<VentasDetalle> detallesVentas = new ArrayList<VentasDetalle>();
        for (DetalleCV detalleCV : detalles) {
            detallesVentas.add(ventasDetalleFacade.guardarDetalle(sesion, venta, detalleCV));
        }
        logger.info("OUT:OK");
        return detallesVentas;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public VentasDetalle guardarDetalle(String sesion, Ventas venta, DetalleCV detalleCV) throws Exception {
        logger.info("IN:"+sesion+";"+detalleCV.toString());
        Productos producto = null;
        try {
            usuarioEAO.getUsuarioBySesion(sesion);
            producto = productosEAO.getProductoById(detalleCV.getId());
        } catch (Exception ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }

        productosFacade.actualizarStock(sesion, producto.getId(), detalleCV.getCantidad()*-1);
        
        VentasDetalle ventaDetalle = new VentasDetalle();
        ventaDetalle.setIdProducto(producto);
        ventaDetalle.setCantidad(detalleCV.getCantidad());
        ventaDetalle.setCreated(new Date(System.currentTimeMillis()));
        ventaDetalle.setIdVenta(venta);
        ventasDetalleEAO.guardarVentasDetalle(ventaDetalle);
        
        logger.info("OUT:OK");
        return ventaDetalle;
    }
    
}
