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
@Table(name = "compras_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComprasDetalle.findAll", query = "SELECT c FROM ComprasDetalle c"),
    @NamedQuery(name = "ComprasDetalle.findById", query = "SELECT c FROM ComprasDetalle c WHERE c.id = :id"),
    @NamedQuery(name = "ComprasDetalle.findByCantidad", query = "SELECT c FROM ComprasDetalle c WHERE c.cantidad = :cantidad"),
    @NamedQuery(name = "ComprasDetalle.findByCosto", query = "SELECT c FROM ComprasDetalle c WHERE c.costo = :costo"),
    @NamedQuery(name = "ComprasDetalle.findByCreated", query = "SELECT c FROM ComprasDetalle c WHERE c.created = :created"),
    @NamedQuery(name = "ComprasDetalle.findByChanged", query = "SELECT c FROM ComprasDetalle c WHERE c.changed = :changed")})
public class ComprasDetalle implements Serializable {
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
    @Column(name = "costo")
    private double costo;
    @Column(name = "created")
    @Temporal(TemporalType.TIME)
    private Date created;
    @Column(name = "changed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changed;
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Productos idProducto;
    @JoinColumn(name = "id_compra", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Compras idCompra;

    public ComprasDetalle() {
    }

    public ComprasDetalle(Integer id) {
        this.id = id;
    }

    public ComprasDetalle(Integer id, double cantidad, double costo) {
        this.id = id;
        this.cantidad = cantidad;
        this.costo = costo;
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

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
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

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    public Compras getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Compras idCompra) {
        this.idCompra = idCompra;
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
        if (!(object instanceof ComprasDetalle)) {
            return false;
        }
        ComprasDetalle other = (ComprasDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.smtr.ejb.entities.ComprasDetalle[ id=" + id + " ]";
    }
    
}
