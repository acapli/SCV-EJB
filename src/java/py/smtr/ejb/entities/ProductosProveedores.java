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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Norita
 */
@Entity
@Table(name = "productos_proveedores")
@XmlRootElement
@NamedQueries({
    
    
    @NamedQuery(name = "ProductosProveedores.findAll", query = "SELECT p FROM ProductosProveedores p"),
    @NamedQuery(name = "ProductosProveedores.findByIdProveedor", query = "SELECT p FROM ProductosProveedores p WHERE p.idProveedor =:idProveedor"),
    @NamedQuery(name = "ProductosProveedores.findById", query = "SELECT p FROM ProductosProveedores p WHERE p.id = :id"),
    @NamedQuery(name = "ProductosProveedores.findByIdProducto", query = "SELECT p FROM ProductosProveedores p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "ProductosProveedores.findByActivo", query = "SELECT p FROM ProductosProveedores p WHERE p.activo = :activo"),
    @NamedQuery(name = "ProductosProveedores.findByCreated", query = "SELECT p FROM ProductosProveedores p WHERE p.created = :created"),
    @NamedQuery(name = "ProductosProveedores.findByChanged", query = "SELECT p FROM ProductosProveedores p WHERE p.changed = :changed")})


public class ProductosProveedores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "activo")
    private Boolean activo;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "changed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changed;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Proveedores idProveedor;
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Productos idProducto;

    public ProductosProveedores() {
    }

    public ProductosProveedores(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
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

    public Proveedores getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedores idProveedor) {
        this.idProveedor = idProveedor;
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
        if (!(object instanceof ProductosProveedores)) {
            return false;
        }
        ProductosProveedores other = (ProductosProveedores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.smtr.ejb.entities.ProductosProveedores[ id=" + id + " ]";
    }
    
}
