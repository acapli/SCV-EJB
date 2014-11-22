/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.smtr.ejb.shared;

import java.util.List;


public class ResponseEntidad<T> {

    private Integer cantidadTotal;
    private List<T> listaEntidad;

    public List<T> getListaEntidad() {
        return listaEntidad;
    }

    public void setListaEntidad(List<T> listaEntidad) {
        this.listaEntidad = listaEntidad;
    }

    public Integer getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

}