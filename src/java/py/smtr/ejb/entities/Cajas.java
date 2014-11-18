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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Norita
 */
@Entity
@Table(name = "cajas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cajas.findAll", query = "SELECT c FROM Cajas c"),
    @NamedQuery(name = "Cajas.findById", query = "SELECT c FROM Cajas c WHERE c.id = :id"),
    @NamedQuery(name = "Cajas.findByNombre", query = "SELECT c FROM Cajas c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cajas.findByActivo", query = "SELECT c FROM Cajas c WHERE c.activo = :activo"),
    @NamedQuery(name = "Cajas.findByCreated", query = "SELECT c FROM Cajas c WHERE c.created = :created"),
    @NamedQuery(name = "Cajas.findByChanged", query = "SELECT c FROM Cajas c WHERE c.changed = :changed")})
public class Cajas implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<HistorialPagos> historialPagosList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "activo")
    private Boolean activo;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "changed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCaja", fetch = FetchType.LAZY)
    private List<CajasUsuarios> cajasUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCaja", fetch = FetchType.LAZY)
    private List<Pagos> pagosList;

    public Cajas() {
    }

    public Cajas(Integer id) {
        this.id = id;
    }

    public Cajas(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @XmlTransient
    public List<CajasUsuarios> getCajasUsuariosList() {
        return cajasUsuariosList;
    }

    public void setCajasUsuariosList(List<CajasUsuarios> cajasUsuariosList) {
        this.cajasUsuariosList = cajasUsuariosList;
    }

    @XmlTransient
    public List<Pagos> getPagosList() {
        return pagosList;
    }

    public void setPagosList(List<Pagos> pagosList) {
        this.pagosList = pagosList;
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
        if (!(object instanceof Cajas)) {
            return false;
        }
        Cajas other = (Cajas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.smtr.ejb.entities.Cajas[ id=" + id + " ]";
    }

    @XmlTransient
    public List<HistorialPagos> getHistorialPagosList() {
        return historialPagosList;
    }

    public void setHistorialPagosList(List<HistorialPagos> historialPagosList) {
        this.historialPagosList = historialPagosList;
    }
    
}
