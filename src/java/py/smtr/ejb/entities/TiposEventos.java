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
@Table(name = "tipos_eventos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposEventos.findAll", query = "SELECT t FROM TiposEventos t"),
    @NamedQuery(name = "TiposEventos.findById", query = "SELECT t FROM TiposEventos t WHERE t.id = :id"),
    @NamedQuery(name = "TiposEventos.findByNombre", query = "SELECT t FROM TiposEventos t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TiposEventos.findByActivo", query = "SELECT t FROM TiposEventos t WHERE t.activo = :activo"),
    @NamedQuery(name = "TiposEventos.findByCreated", query = "SELECT t FROM TiposEventos t WHERE t.created = :created"),
    @NamedQuery(name = "TiposEventos.findByChanged", query = "SELECT t FROM TiposEventos t WHERE t.changed = :changed")})
public class TiposEventos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoEvento", fetch = FetchType.LAZY)
    private List<Eventos> eventosList;

    public TiposEventos() {
    }

    public TiposEventos(Integer id) {
        this.id = id;
    }

    public TiposEventos(Integer id, String nombre) {
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
    public List<Eventos> getEventosList() {
        return eventosList;
    }

    public void setEventosList(List<Eventos> eventosList) {
        this.eventosList = eventosList;
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
        if (!(object instanceof TiposEventos)) {
            return false;
        }
        TiposEventos other = (TiposEventos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.smtr.ejb.entities.TiposEventos[ id=" + id + " ]";
    }
    
}
