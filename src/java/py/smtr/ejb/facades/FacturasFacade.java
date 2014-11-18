/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.facades;

import py.smtr.ejb.eao.FacturasEAO;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import py.smtr.ejb.eao.UsuarioEAO;
import py.smtr.ejb.entities.Facturas;
import py.smtr.ejb.entities.HistorialPagos;
import py.smtr.ejb.entities.Pagos;
import py.smtr.ejb.entities.Ventas;
import py.smtr.ejb.shared.ResponseEntidad;
import py.smtr.ejb.utilities.Utilidades;

/**
 *
 * @author Strogg
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FacturasFacade{

    private Logger logger = Logger.getLogger("log");
    @EJB
    private UsuarioEAO usuarioEAO;
    @EJB
    private FacturasEAO facturasEAO;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ResponseEntidad listarFaturasPedientes(String sesion, Integer offset, Integer limit) throws Exception {
        logger.info("IN listarFaturasPedientes :"+sesion+";"+offset+";"+limit);
        
        //usuarioEAO.getUsuarioBySesion(sesion);
        
        ResponseEntidad<Facturas> resp = new ResponseEntidad<Facturas>();
        Integer cantidad = 0;
        List<Facturas> facturas = facturasEAO.getFacturasPendientes(offset, limit);
        resp.setCantidadTotal(facturas.size());
        resp.setListaEntidad(facturas);
        logger.info("OUT:"+cantidad+";"+facturas);
        return resp;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ResponseEntidad listarFaturasPagadas(String sesion, Integer offset, Integer limit) throws Exception {
        logger.info("IN listarFaturasPagadas :"+sesion+";"+offset+";"+limit);
        
        //usuarioEAO.getUsuarioBySesion(sesion);
        
        ResponseEntidad<HistorialPagos> resp = new ResponseEntidad<HistorialPagos>();
        Integer cantidad = 0;
        List<HistorialPagos> facturas = facturasEAO.getFacturasPagadas(offset, limit);
        resp.setCantidadTotal(facturas.size());
        resp.setListaEntidad(facturas);
      
        logger.info("OUT CANTIDAD DE FACTURAS:"+cantidad+";"+facturas);
        return resp;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String generarFactura(String sesion, Ventas venta, String numero, double saldo) throws Exception {
        logger.info("IN:"+sesion+";"+venta+";"+numero+";"+saldo);
        
        try {
            usuarioEAO.getUsuarioBySesion(sesion);
        } catch (Exception ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        String numeroFactura = Utilidades.leftPad(numero, "0", 7);
        
        Facturas factura = new Facturas();
        factura.setCreated(new Date(System.currentTimeMillis()));
        factura.setEstado(new Integer(1));
        factura.setIdVenta(venta);
        factura.setNumero(numeroFactura);
        factura.setSaldo(saldo);
        facturasEAO.guardarFactura(factura);
        
        logger.info("OUT:"+numeroFactura);
        return numeroFactura;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void actualizarSaldo(String sesion, Integer idFactura, double saldo) throws Exception {
        logger.info("IN actualizarSaldo Facade:"+sesion+";"+idFactura+";"+saldo);
        Facturas factura = null;
        try {
            usuarioEAO.getUsuarioBySesion(sesion);
            factura = facturasEAO.getFacturaById(idFactura);
        } catch (Exception ex) {
            logger.info("OUT:" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        
        Double saldoActual = factura.getSaldo();
        Double nuevoSaldo = saldoActual - saldo;
        if(nuevoSaldo < 0) {
            throw new Exception("El monto insertado es mayor al saldo");
        }
        if(nuevoSaldo == 0) {
            factura.setEstado(3);
        } else {
            factura.setEstado(2);
        }
        factura.setSaldo(nuevoSaldo);
        facturasEAO.guardarFactura(factura);
        
        logger.info("OUT:OK");
    }
}
