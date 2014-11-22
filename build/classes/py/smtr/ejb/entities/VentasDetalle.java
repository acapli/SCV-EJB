/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.smtr.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Norita
 */
@Entity
@Table(name = "ventas_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentasDetalle.findAll", query = "SELECT v FROM VentasDetalle v"),
    @NamedQuery(name = "VentasDetalle.findById", query = "SELECT v FROM VentasDetalle v WHERE v.id = :id"),
    @NamedQuery(name = "VentasDetalle.findByCantidad", query = "SELECT v FROM VentasDetalle v WHERE v.cantidad = :cantidad"),
    @NamedQuery(name = "VentasDetalle.findByPrecio", query = "SELECT v FROM VentasDetalle v WHERE v.precio = :precio"),
    @NamedQuery(name = "VentasDetalle.findByCreated", query = "SELECT v FROM VentasDetalle v WHERE v.created = :created"),
    @NamedQuery(name = "VentasDetalle.findByChanged", query = "SELECT v FROM VentasDetalle v WHERE v.changed = :changed")})
public class VentasDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private double cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private double precio;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "changed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changed;
    @JoinColumn(name = "id_venta", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ventas idVenta;
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Productos idProducto;

    public VentasDetalle() {
    }

    public VentasDetalle(Integer id) {
        this.id = id;
    }

    public VentasDetalle(Integer id, double cantidad, double precio) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getChanged() {
        return changed;
    }

    public void setChanged(Date changed) {
        this.changed = changed;
    }

    public Ventas getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Ventas idVenta) {
        this.idVenta = idVenta;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentasDetalle)) {
            return false;
        }
        VentasDetalle other = (VentasDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.smtr.ejb.entities.VentasDetalle[ id=" + id + " ]";
    }
    
}
