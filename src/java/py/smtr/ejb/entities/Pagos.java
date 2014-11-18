/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.smtr.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Norita
 */
@Entity
@Table(name = "pagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagos.findAll", query = "SELECT p FROM Pagos p"),
    @NamedQuery(name = "Pagos.findById", query = "SELECT p FROM Pagos p WHERE p.id = :id"),
    @NamedQuery(name = "Pagos.findByMonto", query = "SELECT p FROM Pagos p WHERE p.monto = :monto"),
    @NamedQuery(name = "Pagos.findByCreated", query = "SELECT p FROM Pagos p WHERE p.created = :created"),
    @NamedQuery(name = "Pagos.findByChanged", query = "SELECT p FROM Pagos p WHERE p.changed = :changed"),
    
    @NamedQuery(name = "Pagos.findByCerrado", query = "SELECT p FROM Pagos p WHERE p.cerrado = :cerrado")})
public class Pagos implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPagos")
    private List<HistorialPagos> historialPagosList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private Double monto;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "changed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changed;
    @Column(name = "cerrado")
    private Boolean cerrado;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_factura", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Facturas idFactura;
    @JoinColumn(name = "id_caja", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cajas idCaja;

    public Pagos() {
    }

    public Pagos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
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

    public Boolean getCerrado() {
        return cerrado;
    }

    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Facturas getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Facturas idFactura) {
        this.idFactura = idFactura;
    }

    public Cajas getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Cajas idCaja) {
        this.idCaja = idCaja;
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
        if (!(object instanceof Pagos)) {
            return false;
        }
        Pagos other = (Pagos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.smtr.ejb.entities.Pagos[ id=" + id + " ]";
    }

    @XmlTransient
    public List<HistorialPagos> getHistorialPagosList() {
        return historialPagosList;
    }

    public void setHistorialPagosList(List<HistorialPagos> historialPagosList) {
        this.historialPagosList = historialPagosList;
    }
    
}
