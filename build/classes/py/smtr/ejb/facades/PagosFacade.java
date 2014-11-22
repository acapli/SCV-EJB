/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.facades;

import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import py.smtr.ejb.eao.CajasUsuariosEAO;
import py.smtr.ejb.eao.ClientesEAO;
import py.smtr.ejb.eao.FacturasEAO;
import py.smtr.ejb.eao.PagosEAO;
import py.smtr.ejb.eao.UsuarioEAO;
import py.smtr.ejb.entities.Cajas;
import py.smtr.ejb.entities.Clientes;
import py.smtr.ejb.entities.Facturas;
import static py.smtr.ejb.entities.Facturas_.saldo;
import py.smtr.ejb.entities.HistorialPagos;
import py.smtr.ejb.entities.Pagos;
import py.smtr.ejb.entities.Usuarios;
import py.smtr.ejb.exceptions.EJBWithRollBackException;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PagosFacade {

    private Logger logger = Logger.getLogger("log");
    @EJB
    private FacturasFacade facturaFacade;
    @EJB
    private CajasUsuariosEAO cajaUsuarioEAO;
    @EJB
    private PagosEAO pagosEAO;
    @EJB
    private FacturasEAO facturasEAO;
    @EJB
    private UsuarioEAO  usuarioEAO;
    @EJB
    private ClientesEAO  ClienteEAO;
   
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void procesarPago(String sesion, Integer idFactura, double monto) throws Exception {
        logger.info("IN procesarPago FACADE:"+sesion+";"+idFactura+";"+monto);
        Usuarios usu = null;
        Facturas factura = null;
        Cajas caja = null;
        try {
            usu = usuarioEAO.getUsuarioBySesion(sesion);
            logger.info("USUARIO:"+usu);
            factura = facturasEAO.getFacturaById(idFactura);
            logger.info("FACTURA:"+factura);
            caja = cajaUsuarioEAO.obtenerCajasUsuarioByUsuario(usu).getIdCaja();
            logger.info("ID_CAJA:"+caja);
        } catch (Exception ex) {
            logger.info(" ERROR EN procesarPago FACADE:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
        
        Pagos pago = new Pagos();
        pago.setIdFactura(factura);
        pago.setIdCaja(caja);
        pago.setIdUsuario(usu);
        pago.setMonto(monto);
        pago.setCerrado(Boolean.FALSE);
        pago.setCreated(new Date(System.currentTimeMillis()));
        pagosEAO.guardaPago(pago);
        facturaFacade.actualizarSaldo(sesion,idFactura, monto);
       
        logger.info("OUT:OK Pago procesado exitosamente");
       
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void procesarPagoParcial(String sesion, Integer idFactura, double monto) throws Exception {
        logger.info("IN procesarPagoParcial FACADE:"+sesion+";"+idFactura+";"+monto);
        Integer usu = null;
        Facturas factura = null;
        Cajas caja = null;
        Double nuevoSaldo = null;
        Double saldoAnterior = null;
        Clientes cliente = null;
        try {
            usu = usuarioEAO.getUsuarioBySesion(sesion).getId();
            logger.info("USUARIO:"+usu);
            
            factura = facturasEAO.getFacturaById(idFactura);
            //cliente = ClienteEAO.getClienteById(usu)
            cliente = factura.getIdVenta().getIdCliente();
            saldoAnterior = factura.getSaldo();
            logger.info("FACTURA:"+factura);
            
           // caja = cajaUsuarioEAO.obtenerCajasUsuarioByUsuario(usu).getIdCaja();
            logger.info("ID_CAJA:"+caja);
            
            
          
        } catch (Exception ex) {
            logger.info(" ERROR EN procesarPago FACADE:" + ex.getMessage());
            throw new EJBWithRollBackException(ex.getMessage());
        }
        
        nuevoSaldo = saldoAnterior - monto;
         
        HistorialPagos pagoHistorial = new HistorialPagos();
        pagoHistorial.setIdFactura(factura);
        pagoHistorial.setIdCliente(cliente);
        pagoHistorial.setCerrado(Boolean.FALSE);
        pagoHistorial.setSaldoAnterior(saldoAnterior);
        pagoHistorial.setSaldoParcial(monto);
        pagoHistorial.setSaldoActual(nuevoSaldo);
        pagoHistorial.setChanged(new Date(System.currentTimeMillis()));
        
        pagosEAO.guardaHistorialPago(pagoHistorial);
            logger.info("OUT:OK Pago Parcial procesado con éxito");
        
       
        
    }
    
}
