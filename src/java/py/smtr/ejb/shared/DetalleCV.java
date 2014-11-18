/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.shared;

import java.io.Serializable;

/**
 *
 * @author Strogg
 */
public class DetalleCV implements Serializable {

    private Integer id;
    private Double cantidad;
    private Double costo;
    private String nombre;
    private Double subTotal;

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DetalleCV() {
    }

    public DetalleCV(Integer id, Double cantidad, String nombre, Double costo, Double subTotal) {
        this.id = id;
        this.cantidad = cantidad;
        this.costo = costo;
        this.nombre = nombre;
        this.subTotal = subTotal;
    }
    
    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{"+ id + "," + cantidad + "," + nombre+ "," +costo + "," +subTotal+ "}";
    }
    
}
