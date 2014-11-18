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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Norita
 */
@Entity
@Table(name = "cajas_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CajasUsuarios.findAll", query = "SELECT c FROM CajasUsuarios c"),
    @NamedQuery(name = "CajasUsuarios.findById", query = "SELECT c FROM CajasUsuarios c WHERE c.id = :id"),
    @NamedQuery(name = "CajasUsuarios.findByActivo", query = "SELECT c FROM CajasUsuarios c WHERE c.activo = :activo"),
    @NamedQuery(name = "CajasUsuarios.findByCreated", query = "SELECT c FROM CajasUsuarios c WHERE c.created = :created"),
    @NamedQuery(name = "CajasUsuarios.findByIdUsuario", query = "SELECT c FROM CajasUsuarios c WHERE c.idUsuario = :idUsuario"),
    
    @NamedQuery(name = "CajasUsuarios.findByChanged", query = "SELECT c FROM CajasUsuarios c WHERE c.changed = :changed")})
public class CajasUsuarios implements Serializable {
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
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_caja", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cajas idCaja;

    public CajasUsuarios() {
    }

    public CajasUsuarios(Integer id) {
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

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
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
        if (!(object instanceof CajasUsuarios)) {
            return false;
        }
        CajasUsuarios other = (CajasUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.smtr.ejb.entities.CajasUsuarios[ id=" + id + " ]";
    }
    
}
