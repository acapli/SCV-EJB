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
import py.smtr.ejb.eao.VentasEAO;
import py.smtr.ejb.entities.Clientes;
import py.smtr.ejb.entities.Ventas;
import py.smtr.ejb.shared.DetalleCV;


/**
 *
 * @author Strogg
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class VentasFacade {

    private Logger logger = Logger.getLogger("log");
    @EJB
    private ClientesEAO clienteEAO;
    @EJB
    private VentasDetalleFacade ventasDetalleFacade;
    @EJB
    private VentasEAO ventasEAO;
    @EJB
    private FacturasFacade facturaFacade;
    @EJB
    private UsuarioEAO usuarioEAO;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String guardarVenta(String sesion, Integer idCliente, Date fecha, double total, List<DetalleCV> detalles) throws Exception {
        logger.info("IN:"+sesion+";"+idCliente+";"+fecha.toString()+";"+total+";"+detalles.toString());
        Clientes cliente = null;
        try {
            usuarioEAO.getUsuarioBySesion(sesion);
            cliente = clienteEAO.getClienteById(idCliente);
        } catch (Exception ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        Ventas venta = new Ventas();
        venta.setIdCliente(cliente);
        venta.setFechaVenta(fecha);
        venta.setTotal(total);
        venta.setCreated(new Date(System.currentTimeMillis()));
        ventasEAO.guardarVentas(venta);
        
        ventasDetalleFacade.guardarDetalles(sesion, venta, detalles);
        
        String numero = String.valueOf(ventasEAO.getCantidadVentas());
        String numeroFactura = facturaFacade.generarFactura(sesion, venta, numero, total);
        
        return numeroFactura;
    }
    
}
